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
