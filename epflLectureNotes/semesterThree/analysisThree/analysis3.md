# Analysis 3 

## Week 1: Differential operators 

**Gradient**: For $\Omega$ open, $f: \Omega \to \mathbb{R}$ is defined as: 

$$\nabla f(x) = (\frac{\partial f}{\partial x_{1}}, ...)$$

**Divergence**: For $\Omega$ open, $f: \Omega \to \mathbb{R}^{n}$ is defined as: 

$$div F(x) = (\nabla \cdot F)(x) = \frac{ \partial F_{1}}{\partial x_{1}} + ... \ + \frac{ \partial F_{n}}{\partial x_{n}} $$ 

**Rotational**: Let $F: \Omega \to \mathbb{R}^{n}$ 

if $n=2$ then: 

$$rot F(x,y) = \frac{\partial F_{2}}{\partial x} - \frac{\partial F_{1}}{\partial y}$$

if $n=3$ then: 

$$rot F(x,y) = (\frac{\partial F_{3}}{\partial y} - \frac{\partial F_{2}}{\partial z}, \frac{\partial F_{1}}{\partial z} - \frac{\partial F_{3}}{\partial x}, \frac{\partial F_{2}}{\partial x} - \frac{\partial F_{1}}{\partial y} )$$

The best way to remember the formula for the case $n=3$ is by using the determinant formula for the following matrix:

$$\begin{bmatrix} e_{1} \ e_{2} \ e_{3} \\ \frac{\partial}{\partial x} \ \frac{\partial}{\partial y} \ \frac{\partial}{\partial z} \\ F_{1} \ F_{2} \ F_{3} \end{bmatrix}$$


**Laplacian**: Let $F: \Omega \to \mathbb{R}$, then:

$$ lap(f) = \Delta f = div(grad(f)) = \frac{\partial F_{1}^{2}}{\partial{x_{1}}^{2}} + ... \ +  \frac{\partial F_{n}^{2}}{\partial{x_{n}}^{2}}$$

If $\Delta f = 0$ then $f$ is harmonic. 

**Important result**: Let $\Omega \subset \mathbb{R}^{n}$ and $f$ a scalar map with $f \in C^2$ and $F: \Omega \to \mathbb{R}^{n}$ with $F \in C^2$ then: 

1. $div \ grad f = \Delta f$
2. for $n=2$, $rot \ grad f = 0$
3. for $n=3$, $rot \ grad f = \vec0$


## Week 2: Line integrals, Greens theorem 

$R \subset \mathbb{R}^{n}$ is a simple regular curve if there exists an interval $[a,b] \subset \mathbb{R}$ and a function $f: [a,b] \to \mathbb{R}^{n}$ such that:

1. $R = f([a,b])$
2. $r$ is injective on $[a,b[$
3. $r \in C^{1}$
4. $||r^{\prime}(t)|| \not = 0$

If the first two properties hold, the curve is said to be *simple*. If the two start and endpoints meet, the curve is said to be *closed*. 

Some visuals below: 

<img src="src/w2.1.png" width="300" >


