

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







## Application layer

### Network interface

Consists of an interface between an end-system and the network:

- piece of hardware or software that receives packets 
- network card on pc is a network interface

### DNS name

- identifies a network interface
- also called *hostname*, since an end system is also called a host 

### URL

- identifies a web object: for instance www.epfl.ch/index.html 
- identifies a format: DNS name + file name --> www.epfl.ch is the network interface, index.html is the file 

 ### Process

- piece of code that runs in application layer
- reachable at IP address + port number: 128.178.50.12 - 80(port number)
- port 80 is universally reserved for web server processes 

### Summary of a web request 

1. enter URL into web-client: (http://....)
2. web client extracts DNS name 
3. DNS is translated to IP adress
4. web-server process name is formed 



### Translating a DNS to an IP address

<img src="src/w4.1.png" width="500" >

A DNS server is essentially a database that maps a bunch of DNS names to IP addresses. Now we naturally ask whether we could have one single DNS  server that serves the whole internet? This approach is not possible because it is not *scalable*. Instead we have a hierarchy of DNS servers:

- root server
- TLD(top-level domain) server (ie. .com, .org, .ch)
- authoritative server

In this hierarchy, each level server is able to reach its children servers. 

Now we said that DNS servers are distributed but how exactly does that work?

- every DNS client knows at least one local DNS server(physically close)
- one way that the root DNS server can get the IP address is through recursion: it asks the root server which asks the TLD server and so forth:

<img src="src/w4.2.png" width="500" >

- the other approach is the iterative request method, the DNS server asks the root which returns the IP address of the TLD server and so forth: 

  <img src="src/w4.3.png" width="500" >

 ### DNS caching 

To be quicker and more efficient, DNS servers cache the responses they receive. To avoid stale data, each cached address comes with an expiration date. 

### DNS attacks

- (Impersonation attack) If Persa knows that the client has asked for some IP address and is able to send a response quicker than the DNS server, this will lead to a malicious connection established and the client will simply ignore the response of the DNS server: 

<img src="src/w.4.4.png" width="500" >

- (Denial of service) Denis could launch a denial of service attack to all servers in the DNS hierarchy: 

<img src="src/w4.5.png" width="500" >

- (Trashing) Trish could launch an attack to affect the performance of the DNS server by sending request for a lot of unpopular websites such as `X.com` eventually leading to the cache becoming full and losing data of more popular websites. 

<img src="src/w4.6.png" width="500" >

### File distribution 

We saw two architectures for file distribution:

- Client-server: time increases linearly with the number of clients
- Peer-to-peer: time increases sub-linearly with number of peers 

Hence peer-to-peer scales better than client-server 

### Example: Peer-to-peer vs client-server

Let's take an example. Suppose Alice wants to share a file with 3 other friends:

<img src="src/w4.7.png" width="500" >

Now let's first take the **client-server** case:

The file distribution time(D_cs) is as below: 

<img src="src/w4.8.png" width="500" >

### Retrieving content from a P2P file distribution system

Content is a set of data files, stored in a peer. Each content has a **metadata** file which stores:

- file identity
- location information

To retrieve content:

- learn metadata file ID
- find metadata file location
- get metadata file
- find data file location
- get data files 

### Finding metadatafile location 

- Tracker: end-system that knows locations of the files: IP addresses of peers that store each file 
- Distributed hash table: distributed system that knows locations of the files 
- both can be seen as blackboxes that as input take file ID and output IPs of peers that have the file 

### Getting metadata file

- is either on a web server
- or stored on a peer which means we learn its ID from a web server and location from a tracker or DHT 

### P2P DHT

- peers partition filespace(ie. each peer owns certain file IDs)
- to say a peer owns a file ID does not mean it stores it, it simply means that it knows where it is 

## Transport layer 

The transport layer is sandwiched between the application layer and the network layer. 

Here is what the transport-layer header looks like:

<img src="src/w5.1.png" width="500" >

And some new terminology, that is **datagram** and **segment**. Every segment is included inside a datagram. 

<img src="src/w5.2.png" width="500" >

### UDP 

**Sending side**
- Transport layer asks application layer to create a process(called a socket). 
- application layer binds socket to a IP adress and a port number 
- process is ready to send a message
- transport layer puts together a packet which is essentially the **segment** mentioned earlier on 
- when the process is done, transport layer will delete the socket 

### TCP 
- the first couple steps are the same as UDP, that is a socket is create and bound to an IP adress and a port 

A TCP socket has:
- listening & connection sockets 
- each connection socket has a unique(local IP,local port, remote IP, remote port) tuple 
- a process must use a different TCP connection socket per remote process 

Thus at the heart of TCP is the notion of **process-to-process** communication whereby we have:

- Multiplexing: 
  -- upon receiving a new message from a process, create new packets 
  -- identify the correct IP address and port 


- Demultiplexing: 
  -- many processes running in app layer
  -- upon receiving a new packet from the network, identify the correct dest. process  

### Reliable data delivery 

Data delivery becomes unreliable because the network layer may potentially drop data. Thus the transport layer must compensate for this.

<img src="src/w5.3.png" width="500" >

To determine if data was corrupted, we use a checksum which is:

- redundant information, ie. binary sum of all data bytes 

- sender adds checksum C to each segment 

- the checksum is used to communicate acknowledgments between the two sides:

<img src="src/w5.4.png" width="500" >

- the acknowledgment is a piece of **feedback** from the receiver to the sender which is added to every segment 


Now consider what happens when the *ack* itself is corrupted. 

- every segment sent by alice gets assigned a sequence number

- each time alice sends something, a timer is started which will timeout if it doesn't receive an ack in due time, it will then send the data again 

<img src="src/w5.5.png" width="500" >

So let's summarize the basic elements of reliable data transmission:
- checksum: detects data corruption
- ACK, retransmission, SEQ: overcome data corruption
- Timeout, ACK, retransmission, SEQ: overcome data loss 

### Pipelining 
Now here is a little inefficieny in our system. When Alice's transport layer sends a message, it remains in idle state for a long time until it receives the ack. The time of it being busy is expressed by the sender/channel utilization ratio. 

<img src="src/w5.6.png" width="500">

A way to make this more efficient is by sending multiple segments without waiting for an ack. Each time she receives an ack, she advances the window and sends the next patch of segments. The **window size** is the maximum number of unacknowledged segments Alice has sent. 

### Data loss in pipelining 

Go-back-N model: 
<img src="src/w5.7.png" width="500">

Selective repeat model: 

<img src="src/w5.8.png" width="500">

Note that TCP is a mix of Go-back-N and SR since it uses cumulative ACKs but retransmits 1 segment. 

## More details on transport layer protocols 

**UDP** provides checksums to detect for corruption 

Process running on Alice's side called `rdt_send()` is called.  This puts data into a segment and will call `udt_send()`. This will travel to Bob where `rdt_rcv()` gets called. Then Bob's transport extracts the data using `deliver_data()`. The only extra layer of security that UDP adds is a check for corruption. 

Meanwhile, TCP will add `checksum`, `ACKs`, `SEQs`, `timeouts`, `retransmissions` at the cost of being slower. 

**TCP** (seqs and acks)

Here is a sample TCP process: 

<img src="src/w6.1.png" width="500">

We define **SEQ** as number of the first data byte, and **ACK** is the number of the next data byte. 

<img src="src/w6.2.png" width="500">

 In the above we see that Bob replies with an ACK 5 because  he has received 4 bits from Alice and is waiting now for the 5th bit. 

Some simple facts about TCP:

- TCP connection may carry bidirectional data
- A segment may or may not carry data
- There exists a maximum segment size 

**TCP timeouts and retransmits**

 TCP timeouts are purely emprical, in that they rely on times that simply are known to work.  

Now here's a little problem: 

<img src="src/w6.3.png" width="500">

What ACK number must Bob respond with after receiving SEQ2 but having failed to receive SEQ1? Since ACKs are cumulative, Bob responds with ACK1.  Receiving a bunch of duplicate ACKs, Alice will perfrom a *fast retransmit*.

Thus in TCP, we have 2 types of retransmission triggers:

- Timeout: retransmission of oldest unacknowledged segment
- 3 duplicate ACKs: fast retransmit of oldest unacknowledged segment 

**Connection setup and teardown**

 When Alice wants to communicate with Bob, her side opens a connection socket, and Bob's side opens a listening socket. A connection setup request is a *segment* form `SYN|SEQ x|ACK -` . Once the listening socket accepts the request, it will create a connection socket on Bob's side. Then both sides create a send and receive buffer. 

A connection setup follows a **3 way handshake** wherein:

- TCP client: end-system initiating handshake
- TCP server: other end-system 
- First 2 segments always carry SYN flag which is a 1-bit field in TCP header 

**TCP connection attack**

When Jack knows that Alice is requesting a file from Bob, he may send a segment identical to what Bob would send in which case Alice would ignore what she receives from Bob. So how do we prevent this? When Alice and Bob set up the connection, they must choose random sequence numbers that are hard to guess. 

**SYN flooding**

When Alice tries to establish a connection with Bob, Bob will keep all previous requests in a buffer. Thus, Denis may send a lot of incomplete connection requests to flood Bob's connection buffer. Here's how we prevent this: 

<img src="src/w6.4.png" width="500">

When Bob sends a hadh of some secrete to Alice and Alice returns this hash+1 as an ACK, Bob will know it had agreed to connect with Alice before. 

**Flow control**

Bob communicates to Alice how much he can receive. 

<img src="src/w6.5.png" width="500">

**Congestion control**

Here the goal is to not overwhelm the network. The **congestion window** is the number of unacknowledged bytes that the sender can transmit without creating congestion. Alice will send data at a rate *R* with round trip time *RTT* . We define the **bandwith delay product** as *R x RTT* which is the maximum amount of data Alice can transmit before receiving an ACK which is **equal** to the maximum congestion window size. 

**Self-clocking**

Alice will send more data if she observes she can and slow down if she times out:

<img src="src/w6.6.png" width="500">

**Window size algorithm**

- exp: Alice will double the number of unacknowledged bytes sent(denoted N aka. **MSS**), which will by definition double the congestion window 
- linear: Imagine Alice tries with N=400 and has a timeout. Observing that SEQ 300 onwards have failed, she will then set *ssthresh* to 200. She now sets N=100 and then to N=200. Now previously, we had a timeout at N=200 so it makes more sense to increase N by 50 to 250. 

**Tahoe algorithm**

Tahoe uses a combination of exponential and linear increase:

- Set window to 1 MSS, increase exponentially
- On timeout, reste window to 1 MSS, set sstresh to last window/2
- On reaching sstresh, transition to linear increase 

## Preparation notes for midterm 

### Notes from HW2

**Important points on application layer**

- The DNS servers that together implement the DNS distributed database store **resource records (RRs)**, including RRs that provide hostname-to-IP address mappings. An **RR** is a 4 tuple of shape `(Name, Value, Type, TTL)`:

  -  If *Type=A*, then *Name* is a hostname and *Value* is the IP address for the hostname. Thus, a Type A record provides the standard hostname-to-IP address mapping. As an example, (***relay1.bar.foo.com***, 145.37.93.126, A)* is a Type A record.

  -  If *Type=NS*, then *Name* is a domain (such as **foo.com**) and *Value* is the hostname of an authoritative DNS server that knows how to obtain the IP addresses for hosts in the domain. This record is used to route DNS queries further along in the query chain. As an example, *(***foo.com***,* **dns.foo.com***, NS)* is a Type NS record.

  -  If *Type=CNAME*, then *Value* is a canonical hostname for the alias hostname *Name*. This record can provide querying hosts the canonical name for a hostname. As an example, *(***foo.com***,* **relay1.bar.foo.com***, CNAME)* is a CNAME record.If *Type=MX*, then *Value* is the canonical name of a mail server that has an alias hostname *Name*.

    As an example, *(***foo.com***,* **mail.bar.foo.com***, MX)* is an MX record. MX records allow the hostnames of mail servers to have simple aliases. Note that by using the MX record, a company can have the same aliased name for its mail server and for one of its other servers (such as its Web server). To obtain the canonical name for the mail server, a DNS client would query for an MX

Here is a summary of a chain of DNS and HTTP protocol requests:

<img src="src/w6.9.png" width="500">

<img src="src/w6.10.png" width="500">

<img src="src/w6.11.png" width="500">

You are an ordinary user (not a network/system administrator), and your computer is inside the EPFL network. All computers in this network use the same local DNS server.

Can you find out whether a given external URL, e.g., www.mit.edu, was recently accessed by another EPFL user?

**Answer**:  Yes, we can use dig to query that website in the local DNS server. For example, dig example.com will return the query time for finding example.com. If example.com was just accessed a couple of seconds ago, an entry for example.com is cached in the DNS cache of the university’s DNS server, so the query time is negligible (almost 0 msec). Otherwise, the query time is large.

More on retrival times:

<img src="src/w6.13.png" width="500">

<img src="src/w6.12.png" width="500">



<img src="src/w6.14.png" width="500">

Hence the case with parallel connections leads generally to a lower download time. 



## Notes from HW3

<img src="src/6.19.png" width="500">

<img src="src/6.16.png" width="500">



<img src="src/6.18.png" width="500">

<img src="src/6.17.png" width="500">

