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
