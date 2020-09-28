# Computer Networks 

## The internet
### What comprises the internet? 
The internet is comprised of: 
1. **end system**: this being everything that sends or receives data 
2. **access networks**: A common access network is *DSL(Digital subscriber line)*. The DSL uses a customers preexisting phoneline. 
Hence the telephone line will carry data and telephone signals simultaneously with the signals being encoded at different frequencies.
<img src="src/w1.1.png" width="500" >
The alternative to DSL is *cable internet access* which uses the existing television service cable. Fiber optics will connect the cable head end to end users through fiber optics but generally the cable head end to the internet connection is done through coaxial cables hence the system being called a *HFC(hybrid fiber coax)*
<img src="src/w1.2.png" width="500" >
Yet another alternative is *FTTH(fiber to the home*. Each home has an optical network terminator that is connected to the neighbourhood splitter from which the connection to the central office happens through one shared fiber optic. 
<img src="src/w1.3.png" width="500" >

### Who owns the infrastructure?
The internet infrastructre is owned by ISP(Internet service provider). However, there isn`t one global company that dominates the market. 

### Circuit vs packet switching 
In packet switching, the router waits for the whole packet of information to arrive and only then will it proceed to the transmission to the destination. Another difference is that the information may be sent immediately even if all the transimission capacity is taken, the information will simply get queued. In contrast, circuit switching which for isntance telecommunication networks use, first ensures the availability of a line and only then may the transmission occur. This is akin to how one is assured to find a place at a restaurant that requires reservations vs one that does not require nor accept any.  

### Network of networks 
The first step into connecting to the internet starts with having an access ISP. 
--to complete

### Toolset of unix commands
```bash
// to list computer`s network interfaces 
ifconfig
// to map DNS names to an IP adress
host >target<
// to check if target is reachable 
ping >target<
// to list all routers between computer and target 
traceroute >target<
// establish a secure communication channel between computer and a remote one 
ssh >DNS or IP< 


```

## Evaluating a network 

### Evaluation metrics

The core network evaluation metrics are:

- Packet loss: fracion of packets lost from src to dst
- Packet delay: time taken for packet to get from src to dst 
- Average throughput: average rate at which dst receives data 

Generally one considers *packet delay* for small messages, and *average throughput* for bulk transfers. 

**transmission delay = packet size / link transmission rate**

**propagation delay = link length / link propagation speed**

**packet delay = transmission delay + propagation delay + queuing delay**  

### Queuing delay 

Queuing delay approaches infinity when arrival rate starts to exceed departure rate.

### Throughput and bottlenecks

**average throughput = min{R,R'}** where the R represent transmission rate of links on the line. Generally, the link with the slowest rate is known as the **bottleneck link** 

<img src="src/w2.1.png" width="500" >

As we can see above, the word *bottleneck* is illustrative of what the slower link actually causes, it is essentially what determines how fast data is obtained at the dst. This is similar to pouring water out of a bottle that regardless of how wide it is, the pouring rate is determined by the neck of the bottle. 

Generally, a bottleneck is determined by a links transmisssion rate, or because of queuing delay. 

### Resource management

Inside a switch we find:

- queue: to store packets
- forwarding table: stores meta-data, indicates where to send each packet 

Now one can go two ways about getting a switch to transfer the data:

- **packet switching**: packets treated on demand, admission control & forwarding decision per packet
- **circuit switching**: resources reserved per active connection 

Both have their pros/cons;

**Circuit switching**:

+Predictable performance 

-Inefficient use of space as seen below. When reserved for one source, even if idle queuing space is present, it can not serve other users. 

<img src="src/w2.2.png" width="500" >

**Packet switching**:

+Efficient, good resource management 

-Unpredicatble performance 

Through packet switching, we are able to achieve **statistical multiplexing**, that is many users share the same resource simply because they are not all active at the same time. 

There exist multiple forms of achieving circuit switching: 

- Physical circuits: separate sequence of physical links per connection 
- Virtual circuits: manage resources as if there was a separate sequence of physical links per connection 
- Time division multiplexing: divide time into slots and reserve a time slot for each connection 
- Frequence division multiplexing: divide into frequency bans and reserve a band per connection, ie. radio stations 

### Security considerations

There are certain possible attack methods that might occur during a connection between Alice and Bob. 

- Eavesdropping: attacker will try to listen to the communication, obtain copies of data
- Impersonation: pretends to be Alice and extract information from Bob
- Denial of service: send loads of junk to Bob to disrupt communication between Alice and Bob 
- Malware: bad software on both sides 
