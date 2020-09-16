# Computer Architecture 

## Week 1: Simple general processor

### Datapath 
The datapath consists of components capable of performing *arithmetic/logic* operations as well as components that *hold data*. 

<img src="src/w1.1.png" width="500" >

Let's take a simple example, the ALU(*Arithmetic Logic Unit*) will take an input operand from the register file(quite similar to how a MUX receives an input). The result is then written to the register file. 


## Lab 1: VHDL testbenching 

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
