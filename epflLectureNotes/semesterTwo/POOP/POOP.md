<style TYPE="text/css">
code.has-jax {font: inherit; font-size: 100%; background: inherit; border: inherit;}
</style>
<script type="text/x-mathjax-config">
MathJax.Hub.Config({
    tex2jax: {
        inlineMath: [['$','$'], ['\\(','\\)']],
        skipTags: ['script', 'noscript', 'style', 'textarea', 'pre'] // removed 'code' entry
    }
});
MathJax.Hub.Queue(function() {
    var all = MathJax.Hub.getAllJax(), i;
    for(i = 0; i < all.length; i += 1) {
        all[i].SourceElement().parentNode.className += ' has-jax';
    }
});
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML-full"></script>



## Week 2

### Genericity

A generic class is one whose definition has multiple type parameters. 
As an example `List<String>` corresponds to the parametrized type `List<E>` 


Here's an example of a generic class:

``` java 
public final class Cell<E> {
  private final E e;

  public Cell(E e) { this.e = e; }
  public E get() { return e; }
}
``` 

And here's how we instantiate a generic class:

``` java
Cell<String> message = new Cell<>("Bonne année ");
Cell<Date> date = new Cell<>(Date.today());
System.out.println(message.get() + date.get().year());
``` 

And here's how we define a generic pair class:

``` java
public final class Pair<F, S> {
  private final F fst;
  private final S snd;

  public Pair(F fst, S snd) {
    this.fst = fst;
    this.snd = snd;
  }
  public F fst() { return fst; }
  public S snd() { return snd; }
}
``` 

Now a special generic method syntax is as follows:

``` java
public final class Cell<E> {
  private final E e;
  // … comme avant
  public <S> Pair<E, S> pairWith(S s) {
    return new Pair<>(e, s);
  }
}
``` 

This allows our Cell to be paired with any different type.
Here's an example:

``` java
Cell<String> message = new Cell<>("Bonne année ");
Pair<String, Date> pair =
  message.pairWith(Date.today());
System.out.println(pair.fst() + pair.snd().year());
``` 

### Primitive types and genericity 

If a generic class accepts a primitive class as argument, we must make sure to pass in the wrapper class instead done as follows:

``` java
Cell<Integer> c = new Cell<>(new Integer(1));
int succ = c.get().intValue() + 1;
``` 

### Bounds

If we want to limit the type passed into a parameter we set a bound as follows:

``` java
public final class Cell<E extends Number> {
  // … comme avant
}
``` 


## Week 2

### Collection
Generally, for each collection, Java contains an interface and multiple implementations of the interface. 

A *view* is a type exposing part of another type. For instance `subList` is a view of `List`. 

Now some implementations of collection offer methods to modify the object. However, the object may be easily made immutable by setting all these methods to throw `UnsupportedOperationException`

To achieve a nonmodifiable implementation of collection, we must do one of the following:

- Use a method returning an immutable structure such as `List.of`
- Obtain a non-modifiable view of collection by using methods such as `unmodifiableList`

### Lists
A list is ordered and dynamic in Java. 

``` java
public interface List<E> extends Collection<E> {
  // … méthodes
}
``` 
And here is the list view method:

``` java
List<E> subList(int b, int e)
// returns subList between given index lats index being exclusive
```

And as mentioned before, we use the `of` method to construct an immutable List. Similarly, to obtain an immutable copy, use `copyof`

Two popular implementation of lists are ArrayList and LinkedList. While LinkedList performs adding in constant time, it does search in linear time and vice versa for ArrayList. 

And some common List types:
- stack(insert and delete at same extremite)
- queue(insertion and deletion at two extrema)

And here's an easy way to loop through a list:
``` java 
List<String> l = …;
for (String s: l)
  System.out.println(s);
``` 

Alternatively, we can use the iterator object:

```java
List<String> l = …;
Iterator<String> i = l.iterator();
while (i.hasNext()) {
  String s = i.next();
  System.out.println(s);
}
```

## Week 3

### Set extends collection

The set interface extends Collection with `HashSet` and `TreeSet` as implementations. The `set` interface is similar to its mathematics counterpart offering the following methods: 

<table>


<colgroup>
<col class="org-left">

<col class="org-left">
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">Opération</th>
<th scope="col" class="org-left">Méthode</th>
</tr>
</thead>
<tbody>
<tr>
<td class="org-left">union (<mjx-container class="MathJax CtxtMenu_Attached_0" jax="CHTML" tabindex="0" ctxtmenu_counter="0" style="font-size: 117.4%;"><mjx-math class="MJX-TEX"><mjx-mo class="mjx-n"><mjx-c class="mjx-c222A"></mjx-c></mjx-mo></mjx-math></mjx-container>)</td>
<td class="org-left"><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#addAll(java.util.Collection)"><code>addAll</code></a></td>
</tr>

<tr>
<td class="org-left">test d'appartenance (<mjx-container class="MathJax CtxtMenu_Attached_0" jax="CHTML" tabindex="0" ctxtmenu_counter="1" style="font-size: 117.4%;"><mjx-math class="MJX-TEX"><mjx-mo class="mjx-n"><mjx-c class="mjx-c2208"></mjx-c></mjx-mo></mjx-math></mjx-container>?)</td>
<td class="org-left"><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#contains(java.lang.Object)"><code>contains</code></a></td>
</tr>

<tr>
<td class="org-left">test d'inclusion (<mjx-container class="MathJax CtxtMenu_Attached_0" jax="CHTML" tabindex="0" ctxtmenu_counter="2" style="font-size: 117.4%;"><mjx-math class="MJX-TEX"><mjx-mo class="mjx-n"><mjx-c class="mjx-c2286"></mjx-c></mjx-mo></mjx-math></mjx-container>?)</td>
<td class="org-left"><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#containsAll(java.util.Collection)"><code>containsAll</code></a></td>
</tr>

<tr>
<td class="org-left">différence (\)</td>
<td class="org-left"><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#removeAll(java.util.Collection)"><code>removeAll</code></a></td>
</tr>

<tr>
<td class="org-left">intersection (<mjx-container class="MathJax CtxtMenu_Attached_0" jax="CHTML" tabindex="0" ctxtmenu_counter="3" style="font-size: 117.4%;"><mjx-math class="MJX-TEX"><mjx-mo class="mjx-n"><mjx-c class="mjx-c2229"></mjx-c></mjx-mo></mjx-math></mjx-container>)</td>
<td class="org-left"><a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html#retainAll(java.util.Collection)"><code>retainAll</code></a></td>
</tr>
</tbody>
</table>

TreeSet owes its name to the fact that it stores the elements of the set in a binary search tree, while HashSet owes its name to the fact that it stores them in a hash table. These concepts will be discussed later, but it is important to know that thanks to them, TreeSet can implement the main operations on sets (adding, membership test, etc.) in O(log n), while HashSet does even better and implements them in O(1), which is remarkable!


HashSet / TreeSet rule: Use HashSet as an implementation of sets in Java, except when it is useful to browse items in ascending order, in which case you may prefer TreeSet.


### Equality of sets
Now how do we consider if two set objects are equal. We can resort to `equality by reference` or `equality by structure`. 


#### Comparable interface 
This interface offers `compareTo` which returns an int, -1,0,1. The below shows how equals is equivalent to compareTo. 

`o1.equals(o2) ⇔ o1.compareTo(o2) == 0`

#### Comparator interface
This interface although similar to comparable has the main difference that it is implemented by a foreign class. That is it is capable of comparing two objects of different type. 

#### TreeSet
Stores objects in sorted, ascending order. 

``` java
Set<Integer> s = new TreeSet<>();
s.addAll(Arrays.asList(1,3,5,7,2,4,6,8));
for (int i: s)
  System.out.println(i); // 1, 2, …, 8
``` 

It is possible to make TreeSet in descending order as below:

``` java
Set<Integer> s = new TreeSet<>(new IntInvComparator());
s.addAll(Arrays.asList(1,3,5,7,2,4,6,8));
for (int i: s)
  System.out.println(i);
``` 

### Hashing 

All hashing functions satisfy:


$$\forall x, y : x = y \Rightarrow h(x) = h(y)$$

A hashing function is *perfect* if:

$$
\forall x, y : x \ne y \Rightarrow h(x) \ne h(y)
$$

Taking the contrapositive of the above it is obvious that a hashing function is perfect if it is injective. 


#### Hashing in JAVA

The `equals` and `hashCode` methods are said to be *compatible* if the following holds: 

x.equals(y) $\Rightarrow$ x.hashCode() == y.hashCode()


#### Quick summary of Collectible objects 

<table>


<colgroup>
<col class="org-left">

<col class="org-left">
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">Collection</th>
<th scope="col" class="org-left">Methods required</th>
</tr>
</thead>
<tbody>
<tr>
<td class="org-left"><code>List&lt;E&gt;</code></td>
<td class="org-left"><i>none</i></td>
</tr>

<tr>
<td class="org-left"><code>Set&lt;E&gt;</code></td>
<td class="org-left"><code>equals</code></td>
</tr>

<tr>
<td class="org-left"><code>HashSet&lt;E&gt;</code></td>
<td class="org-left"><code>equals</code> and <code>hashCode</code></td>
</tr>

<tr>
<td class="org-left"><code>TreeSet&lt;E&gt;</code></td>
<td class="org-left"><code>equals</code> and <code>compareTo</code></td>
</tr>

<tr>
<td class="org-left"><code>TreeSet&lt;E&gt;</code></td>
<td class="org-left"><code>equals</code> and <code>compare</code></td>
</tr>
</tbody>
</table>

## Week 4

### Streams in Java
We focus on input/output of document rather than the same process through the console. 

#### What really is a file
A file is a collection of bytes that carry information. A file also has metadata representing things like date,name,etc. 

There are two types of files:
- text files
- binary files(image,sound)

#### InputStream as an abstract class in java.io

``` java
abstract public class InputStream {
  int read();

  int readNBytes(byte[] b, int o, int l);
  byte[] readNBytes(int l);
  byte[] readAllBytes();

  long transferTo(OutputStream o);

  long skip(long n);
  int available();

  boolean markSupported();
  void mark(int l);
  void reset();

  void close();
}
```

#### Creating a stream

``` java
InputStream s = new FileInputStream("file.bin");
```

We may instead do 
``` java
InputStream s = new BufferedInputStream(new FileInputStream("file.bin"));
```
for a quicker read. 

### Reading a file
`int read()`: reads and returns the next byte as a value between 0 and 255 inclusive, or -1 if the end of the stream has been reached

`int readNBytes(byte[] b, int o, int l)`: reads at most l bytes from the stream, places them in array b from position o and returns the number of bytes read; this number is less than l only if the end of the stream has been reached during reading

`byte[] readNBytes(int l)`: similar to the previous method, but the bytes read are placed in a new array of good size, which is returned

`byte[] readAllBytes()`: reads all the remaining bytes in the stream and places them in an array of good size, which is returned


#### OutputStream

``` java
abstract public class OutputStream {
  void write(int b);
  void write(byte[] b);
  void write(byte[] b, int o, int l);

  void flush();

  void close();
}
``` 

The hierarchy of subclasses in OutputStream is very similar to the hierarchy of subclasses in InputStream.

All the input stream classes we have described have an equivalent in the output stream world. For example, to the FileInputStream class corresponds the FileOutputStream class, and so on. These classes corresponding directly to others already examined will no longer be examined here.

The PrintStream class adds to the OutputStream class print, println and printf methods allowing to write textual representations of Java data in the stream. The streams representing "standard output" and "standard error", stored respectively in the static attributes out and err of the System class, are of the PrintStream type.

#### Pay attention when using close

Executing the below code:

``` java
InputStream s = new FileInputStream(…);
System.out.println(s.available());
s.close();
``` 
it becomes possible that the close method may never be called as available may lead to an exception. Instead we must use a try-catch block.

``` java
InputStream s = new FileInputStream(…);
try {
  System.out.println(s.available());
} finally {
  s.close();
}
```

Even better then this, we may use the **try-with resource** syntax offered by java as below:

``` java
try( InputStream s = new FileInputStream(...)){
  System.out.println(s.available());
}
``` 
which doesn't require a call to close. 

#### Some practical examples

##### Counting the frequency of each letter in a .txt file
``` java
private static int[] byteFrequencies(String fileName) throws IOException {
        try (InputStream stream = new FileInputStream(fileName)) {
            int[] freq = new int[256];
            int b;
            while ((b = stream.read()) != -1)
                freq[b] += 1;
            return freq;
        }
    }
``` 

## Week 6/7 (Design patterns)


### Adapter pattern
Generally, when one wishes to use an instance of a given class where an instance of another class is expected, it is possible to write a class serving as an adapter. Of course, the behavior of the two classes must be relatively similar, otherwise the adaptation does not make sense.

The Adapter design pattern describes this solution.

### Observer pattern 
When an object observes another object, the first is called the observer ( observe ) and the second the subject ( subject ). Note that a given object can be both subject and observer. Thus, in our example, the cell A3is both observer - of cells A1and A2- and subject to observation - by the cell C3.

An observer must be able to be informed of changes in the subject (s) he is observing. For this purpose, he has a method, named updatebelow, which is called when a subject he observes has been modified. The subject in question is passed as an argument to this method, to allow its identification. The interface Observerbelow represents such an observer:

``` java
public  interface Observer   {
   void update (Subject s)  ;
}
``` 

A subject must memorize all of his observers, and warn them when his state changes. To do this, the subject offers methods for adding and removing an observer to its set. In addition, a subject must take care to warn his observers when his condition changes. The interface Subjectbelow represents such a subject:

```java
public  interface Subject   {
   void addObserver (Observer o)  ;
  void removeObserver (Observer o)  ;
}
``` 

The code common to all cells is placed in an abstract class of named cell Cell. Any cell must be able to be subject to observation, therefore Cellimplements the interface Subjectand implements the methods addObserverand removeObserver. It also offers a protected method ( notifyObservers) to notify observers of a change.

```java
public  abstract  class Cell implements Subject     {
   private Set <Observer> observers = new HashSet <> ();
  abstract public int value ()    ;

  public void addObserver (Observer o)    {
    observers.add (o);
  }

  public void removeObserver (Observer o)    {
    observers.remove (o);
  }

  protected void notifyObservers ()    {
    for(Observer o: observers)
      o.update ( this );
  }
}
``` 

### MVC pattern 
The model ( model ) is all the code that handles the data specific to the application. The model does not contain any code linked to the user interface.

For example, in a web browser, the model contains the code responsible for managing network access, analyzing received HTML files, interpreting JavaScript programs, decompressing images, etc.

The view ( view ) is all the code responsible for displaying data on the screen.

For example, in a web browser, the view contains the code responsible for transforming the received HTML content into something that can be displayed, displaying the progress of downloads in progress, etc.

The controller ( controlling ) is the set of the code responsible for the management of the user input.

For example, in a web browser, the controller contains the code responsible for handling clicks on links, entering text in text boxes, pressing the interrupt loading button, etc. 

## Week 8

### Lambdas 

For the record, since Java 8, interfaces can contain concrete methods, which can be:
- static methods, or
- default methods ( default methods ), not static, which are inherited by all classes that implement the interface and does not redefine.
  
An interface is called functional interface ( functional interface ) if it has exactly one abstract method. It can nevertheless have any number of concrete, static or default methods.

One of the reasons why lambdas are powerful is that they have access to all the entities visible where they are defined: parameters and local variables of the method in which they are possibly defined, attributes and methods of the encompassing class, etc 2 .

To illustrate this possibility, let us admit that we want to generalize the sorting method by adding an additional argument specifying whether the sorting should be done in ascending or descending order. We can simply write:

```java
public  final  class Sorter   {
   enum Order {ASCENDING, DESCENDING};

  public static void sort (ArrayList <Integer> l, Order o)     {
    Collections.sort (l, (i1, i2) ->
		     o == Order.ASCENDING
		     ? Integer.compare (i1, i2)
		     : Integer.compare (i2, i1));
  }
}
``` 

Let's give some more examples of the lambda expression syntax:

``` java

// a function to multiply an int by 2:
doubleNumber = (int a) -> {return a*2;}

``` 

We also have what's called a functional interface. This interface has only one abstract method though an arbitrary number of default methods. You may have wondered how we go about calling a lambda function. Well suppose we declare a functional interface:

``` java
@FunctionalInterface 
public interface Test{
  void TestMethod();
}

public class TestImplementation implements Test{
  Test lambda = () -> {1+1;}

  lambda.TestMethod(); // will perform the lambda function.
}
``` 