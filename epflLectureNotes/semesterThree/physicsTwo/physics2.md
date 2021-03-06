<!-- vscode-markdown-toc -->
* 1. [Week 3](#Week3)
	* 1.1. [Electric field distribution on surfaces](#Electricfielddistributiononsurfaces)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc --># GENERAL PHYSICS 2

##  1. <a name='Week3'></a>Week 3 


###  1.1. <a name='Electricfielddistributiononsurfaces'></a>Electric field distribution on surfaces 

Granted that an electric field is simply 

$$E = \frac{F}{Q_{t}}$$

A single point charge will induce an electric field of size 

$$\vec E = \frac{Q}{4\pi\epsilon r^{2}}$$



Similarly, for a collection of point charges, the electric field becomes the vector sum of the single $E_{i}$

**The rod**
An recurent problem is the electric field from continous charge distribution on a rod of length $L$. This is given by:

$$ F = \frac{Q \lambda L}{4 \pi \epsilon_{0}x \sqrt{x^{2}+ (\frac{L}{2}^{2})}}$$

**Plane circular sheet**

$$E = \frac{\sigma}{2\epsilon_{0}}$$

Here is the derivation of the above:

<img src="src/w3.1.png" width="500" >

Note that the given formula holds only when $b >> a$, that is we are significantly close to the plate which means that the most RHS term in the derivation will go to 0.

**Homogenous field around two plates**

Imagine we put two plates, of the same charge but opposite signs. Ie. something like this:

<img src="src/w3.2.png" width="500" >

Now in between the fields, the total field is simply the sum of the two plates which is 

$$E_{\text{between}} = \frac{\sigma}{\epsilon_{0}}$$

And outside of the plates, if we observe the vector lines, the fields cancel each other out, resulting in $$E_{\text{outside}} = 0$$

**Electric potential**
Let's recall some definitions:

*E-field*: Force per test charge, vector field
*E-potential*: Potential energy per test charge, scalar field 

More formally, potential from point $A$ to $B$ is

$$V_{AB} = \frac{W_{AB}}{Q_{t}}$$

Where the $W_{AB}$ in the above is defined as:

$$W_{AB} = \int_{A}^{B} -\vec F \cdot d \vec L = \int_{A}^{B} -\vec Q_{t}\vec E \cdot d \vec L  \rightarrow V_{B} = \int_{A}^{B} -\vec E \cdot d \vec L  $$

An **important** point is that the potential is **independent** of path taken. 

Using the integral definition of $V_{p}$ we have that for a point charge

$$V_{P} = \int_{\infty}^{P} - \frac{Q\vec r \cdot d\vec L}{4 \pi \epsilon_{0}r^{2}}$$

Similarly, the potential of a collection of point charges is

$$V = \frac{1}{4 \pi \epsilon_{0}} \sum_{i} \frac{Q_{i}}{r_{i}}$$

**Electric potential around uniform charged disk**

Returning to the disk from earlier on, the expression for potential becomes 

$$ V = \frac{\sigma}{2 \pi \epsilon_{0}}(\sqrt{a^{2} + b^{2} -a})$$

Now we let $b$ go to infinity, well shouldnt the potential then go to infinity? The problem with this approach is that when $b$ goes to infinity, it will almost start touching our object which is a problem. 

And here is how potential varies for a positive and negative charge:

<img src="src/w3.3.png" width="500" >

**Obtaining E-field from V**

$$\vec E (\vec r) = - \nabla V(x,y,z)$$

Finding force from E-field:
$$\vec F = Q \vec E = Q(V_{B}-V_{A})$$

## Week 4

<img src="src/w3.4.png" width="500" >

We introduce the **Electronvolt** which is energy of $e^{-1}$ passing $1V$ of potential difference. 

Now let's analyse the kinematics of a charged particle in an electric field:

<img src="src/w3.5.png" width="500" >

A more interesting case is a charged particle entering an electric field at an angle for which we have the following result 

<img src="src/w3.6.png" width="500" >

**Electric dipole**

An electric dipole is a two-point charge with opposite charges such that $\sum Q = 0$ We have that $+q$ and $-q$ are at a distance $a$. An $ideal$ dipole is one where $a$ is small compared to other distances. 

The *dipole moment* is $\vec p = q \vec a$. Now a natural question to ask is, what is $v_{p}$ due to $+q$ and how does it compare to $v_{p}$ due to $-q$ given there is a path difference. 
<img src="src/w3.7.png" width="500">

We have:

$$v_{q} = \frac{q}{4 \pi \epsilon_{0}r}$$ and also

$$v_{-q} = -v_{q} - d v_{q}$$

Thus the net $v_{p}$ at the point $p$ becomes $v_{q} + v_{-q} = -d v_{q} $ where $-d v_{q}$ is the distance over which potential has changed multiplied by the rate of change which is

$$ -d v_{q} = -a \frac{\partial \frac{q}{4 \pi \epsilon_{0}r}}{\partial x}$$

Taking everything not depending on $x$ outside we obtain 

$$v_{p} = - \frac{q}{4 \pi \epsilon_{0}} \frac{\partial \frac{1}{r}}{\partial x} = \frac{q}{4 \pi \epsilon_{0}} \frac{\partial r}{\partial x} $$ which simplifies to 

$$ \frac{qa \cos{\theta}}{4 \pi \epsilon_{0} r^{2}}$$

Which in terms of the dipole moment becomes 

$$ \frac{\vec p \cdot\vec r}{4 \pi \epsilon_{0} r^{2}}$$

The last formula gives the *potential of a dipole* 

The electric field of a dipole then looks like

<img src="src/w3.8.png" width="500">

where

$$|E_{\theta}| = \frac{p\sin{\theta}}{4 \pi \epsilon_{0}r^{3}}$$

$$|E_{r}| = \frac{2p\cos{\theta}}{4 \pi \epsilon_{0}r^{3}}$$

Ofcourse the total electric field is the vector sum of the above two expressions. 

<img src="src/w4.1.png" width="500">


**Multipoles**

<img src="src/w4.2.png" width="500">


**Gauss' law**
*Flux* is defined as $\sigma = \vec E \cdot d \vec A$, positive when field lines go inward and negative when they go outward. The total flux is thus $\int_{A} \vec E \cdot d \vec A$

*Flux around a spherical surface*

<img src="src/w4.3.png" width="500">

*Gauss' law*: The flux of E-field over any closed surface is equal to the total enclosed charge over $\epsilon_{0}$ that is:

$$\sum _{i} \frac{Q_{i}}{\epsilon_{0}}$$

A consqeuence of Gauss' law is that no E-field can exist inside a hollow conductor due to electrostatic shielding. 

*Gauss' divergence theorem*

$\nabla \cdot \vec E = \frac{p}{\epsilon_{0}}$ note that this presents the same idea as Gauss' law. 

## Week 5 

Let's begin with a summary from last week:
<img src="src/w5.1.png" width="500">


**Using Gauss' law to find E-fields**
Here is an example on how we would find the E-field around a uniformly charged spehere using Gauss: 
<img src="src/w5.2.png" width="500">


