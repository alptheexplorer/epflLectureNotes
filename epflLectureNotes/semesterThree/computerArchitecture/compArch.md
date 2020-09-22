# Computer Architecture 

## Week 1: Simple general processor

### Datapath 
The datapath consists of components capable of performing *arithmetic/logic* operations as well as components that *hold data*. 

<img src="src/w1.1.png" width="500" >

Let's take a simple example, the ALU(*Arithmetic Logic Unit*) will take an input operand from the register file(quite similar to how a MUX receives an input). The result is then written to the register file. 


## Lab 1: VHDL testbenching 
**link for school hosted vm: https://support.epfl.ch/epfl?id=epfl_kb_article_view&sys_kb_id=eff9d6aedb4b978cef64731b8c9619b3** 

The goal of this adder is to test a combinational and a sequential adder. Our template folder structure is as follows:

```
--main
  --modelsim
  --testbench
    ---tb_adder_combinatorial.vhd
    ---tb_adder_sequential.vhd
  --vhdl 
    ---adder_combinatorial.vhd
    ---adder_sequential.vhd
```

Our testbench files will not compile since they are empty. So we fill them up in the rawest form possible 

```vhdl 
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity tb_adder_combinatorial is
end tb_adder_combinatorial;
architecture test of tb_adder_combinatorial is
begin
end architecture test;
```

We must now instantiate the DUT(Design under test) as follows:

```vhdl 
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity tb_adder_combinatorial is
end tb_adder_combinatorial;
architecture test of tb_adder_combinatorial is
-- adder_combinatorial GENERICS
constant N_BITS : positive range 2 to positive'right := 4;
-- adder_combinatorial PORTS
signal OP1 : std_logic_vector(N_BITS - 1 downto 0);
signal OP2 : std_logic_vector(N_BITS - 1 downto 0);
signal SUM : std_logic_vector(N_BITS downto 0);
begin
dut : entity work.adder_combinatorial
generic map(N_BITS => N_BITS)
port map(OP1 => OP1,
OP2 => OP2,
SUM => SUM);
end architecture test;
```
We must now feed inputs to the DUT as:
Supply OP1 and OP2 then verify SUM

The best way to realize this is a process inside our testbench architecture. Since we want to delay our inputs, we also declare a TIME_DELTA constant that will change the value of OP1 an OP2. We achieve the delay through the **wait for** statement. 

```vhdl 
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity tb_adder_combinatorial is
end tb_adder_combinatorial;
architecture test of tb_adder_combinatorial is
-- "Time" that will elapse between test vectors we submit to the component.
constant TIME_DELTA : time := 100 ns;
-- adder_combinatorial GENERICS
constant N_BITS : positive range 2 to positive'right := 4;
-- adder_combinatorial PORTS

signal OP1 : std_logic_vector(N_BITS - 1 downto 0);
signal OP2 : std_logic_vector(N_BITS - 1 downto 0);
signal SUM : std_logic_vector(N_BITS downto 0);
begin
-- Instantiate DUT
dut : entity work.adder_combinatorial
generic map(N_BITS => N_BITS)
port map(OP1 => OP1,
OP2 => OP2,
SUM => SUM);
-- Test adder_combinatorial
simulation : process
begin
-- Assign values to circuit inputs.
OP1 <= "0001"; -- 1
OP2 <= "0101"; -- 5
-- OP1 and OP2 are NOT yet assigned. We have to wait for some time
-- for the simulator to "propagate" their values. Any infinitesimal
-- period would work here since we are testing a combinatorial
-- circuit.
wait for TIME_DELTA;
-- Assign values to circuit inputs.
OP1 <= "0011"; -- 3
OP2 <= "0010"; -- 2
-- OP1 and OP2 are NOT yet assigned. We have to wait for some time
-- for the simulator to "propagate" their values. Any infinitesimal
-- period would work here since we are testing a combinatorial
-- circuit.
wait for TIME_DELTA;
end process simulation;
end architecture test;
```

We may want the simulation to run on modelSim for as long as needed. To do this, we insert the `run -all` command and add an indefinite `wait` statement to the end of our process. 

Also notice how the a change in value of the above signals leads to code duplication. To avoid this, we use a **procedure**. The refactored code now looks like:


```vhdl 
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity tb_adder_combinatorial is
end tb_adder_combinatorial;
architecture test of tb_adder_combinatorial is
-- "Time" that will elapse between test vectors we submit to the component.
constant TIME_DELTA : time := 100 ns;
-- adder_combinatorial GENERICS
constant N_BITS : positive range 2 to positive'right := 4;
-- adder_combinatorial PORTS
signal OP1 : std_logic_vector(N_BITS - 1 downto 0);
signal OP2 : std_logic_vector(N_BITS - 1 downto 0);
signal SUM : std_logic_vector(N_BITS downto 0);
begin
-- Instantiate DUT
dut : entity work.adder_combinatorial
generic map(N_BITS => N_BITS)
port map(OP1 => OP1,
OP2 => OP2,
SUM => SUM);
-- Test adder_combinatorial
simulation : process
procedure check_add(constant in1 : in natural;
constant in2 : in natural) is
begin
-- Assign values to circuit inputs.
OP1 <= std_logic_vector(to_unsigned(in1, OP1'length));
OP2 <= std_logic_vector(to_unsigned(in2, OP2'length));
-- OP1 and OP2 are NOT yet assigned. We have to wait for some time
-- for the simulator to "propagate" their values. Any infinitesimal
-- period would work here since we are testing a combinatorial
-- circuit.
wait for TIME_DELTA;
end procedure check_add;
begin
-- Check test vectors
check_add(12, 8);
check_add(10, 6);
check_add(4, 1);
check_add(11, 7);
check_add(10, 13);
check_add(8, 7);
check_add(1, 9);
check_add(7, 3);
check_add(1, 4);
check_add(8, 0);
-- Make this process wait indefinitely (it will never re-execute from
-- its beginning again).
wait;
end process simulation;
end architecture test;
```



# Week 2: Memory and ISA(Instruction Set Architecture)

## Architecture of the memory

The best way to visualize memory is to see it as a 2D array of stored bits. Each row of bits corresponds to an *adress* and each *adress* stores a word. The 3 essential units we use to create a memory are:

- binary decoder
- MUX
- memory cells



As we can see below, N bits are sent into to the binary decoder to select the address. 

<img src="src/w2.1.png" width="500" >

However things aren't that simple. What really is the best way to organize our array of cells. Do we maximize the amount of adresses or word capacity? Well given that we will be transmitting signals to each row and column, and given that both of these signals have some sort of delay due to physics, we need the most optimal design. Let's model this delay(linear) as:

x = k*#Columns

y = k*#Rows

Thus the total delay which is x+y = k(#Rows + #Columns). The way to minimize this delay is to find the least sum given that the product of Columns and Rows is constant. It turns out that this is minimal when Rows=Columns, hence why our 2D array is as close to a square as possible. 



Now given this design, it is smarter to have one adjacent adresses. In fact this is the only way we can achieve that square design.



<img src="src/w2.2.png" width="500" >

A smart observation shows that using the least significant bit of the binary decoder output, we can already decide if we are looking at the left or right column for even adresses are all have 0 in their LSB. We then use the remaining 3 bits which ranges from 0 to 7 to decide on the row. Hence the complete architecture will look as:



<img src="src/w2.3.png" width="500" >

 