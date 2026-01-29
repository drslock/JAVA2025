## Further Language Constructs
### Task 1: Introduction


The aim of this workbook is to introduce further important concepts and constructs from the Java programming language.
In order to support this, we will continue working with the "Shapes" projects from the last two weeks,
which you will extend and enhance with additional features and functionality.
As before, each section in this workbook links to some fragments of teaching materials that introduce a concept,
as well as an activity to undertake that will allow you to apply your knowledge. 
  


# 
### Task 2: Abstract Classes
 <a href='02%20Abstract%20Classes/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Abstract%20Classes/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

An inspection of the code inside the `TwoDimensionalShape` class (found somewhere in the `src/main/java/` folder of the Maven project)
will reveal that the class has two **abstract** methods.
These are methods _without_ implementations - they have signatures ("prototypes"), but no internal code.
View the slides and video lecture above to find out more about the notion of **abstract** and the reasons why we might want to use this feature of Java.

Now, inside your `Triangle` class, write complete `calculateArea` and `calculatePerimeterLength` methods so that each performs a suitable
calculation and returns an appropriate result.
These two methods will **override** the abstract (blank) methods defined in the `TwoDimensionalShape` class.

The reason why these two methods were originally defined as **abstract** in the `TwoDimensionalShape` class is as follows...
All shapes _will_ have an area and perimeter length, however these will be _calculated_ differently depending on the particular shape being represented.
For circles these are calculated using their radius and Pi, with Rectangles this involves using their width and height etc.
As such, we can't write anything _generally useful_ inside the `TwoDimensionalShape` class - we have to leave
everything down to the subclasses themselves. That said, it is still worth _defining_ that all shapes have area and perimeter calculations
(even if they have to be left blank in the `TwoDimensionalShape` class).

Once you have implemented area and perimeter calculation methods for your `Triangle` class, create a few
`Triangles` with different length sides and call your freshly created methods to make sure they work correctly.
You _could_ do this by just adding a few lines of code to your main method, however it is good practice to create a
JUnit test file in the `src/test/java` folder.
  


**Hints & Tips:**  
Calculating the perimeter length of a triangle is easy.  
In order to calculate the area you might like to use
<a href="https://www.mathsisfun.com/geometry/herons-formula.html" target="_blank">Herons Formula</a>.  


# 
### Task 3: Interfaces
 <a href='03%20Interfaces/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Interfaces/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

As we have discussed previously, some shapes have a number of variants,
whilst others (such as Circles) have only one. 
It would be useful to have some kind of "marker" that indicates
whether or not a particular shape has multiple variants.

One way to do this is using a Java **Interface** - this is a mechanism, independent of the class hierarchy,
that allows us to "mark" classes as having particular characteristics. You can think of an Interface as a "contract"
that the class "conforms to" - guaranteeing to provide certain properties and features.
Take a look at the slides and video above for more detailed information about interfaces.

As an illustration of interfaces in action, we have provided a `MultiVariantShape` interface in the Maven
project that can be used to indicate that a shape has more than one variant.
We can mark our `Triangle` class as a shape which has multiple variants by updating the class definition
so that it `implements` the `MultiVariantShape` interface:
``` java
class Triangle extends TwoDimensionalShape implements MultiVariantShape
```
This indicates that the `Triangle` class conforms to the properties and features of the `MultiVariantShape` interface.

Elsewhere in our code, we can check to see if a particular object conforms to an interface by using the `instanceof` keyword
Note that this is not a particularly suitable name - it is part of Java and is used for other purposes (where the name does make sense !).
We can check any shape object to see if it implements the `MultiVariantShape` interface using the following code:
``` java
if(shape instanceof MultiVariantShape) System.out.println("This shape has multiple variants")
else System.out.println("This shape has only one variant")
```

Try creating a number of different types of shape (Circles, Rectangles, Triangles etc) and check each to see if it
implements the `MultiVariantShape` using the `instanceof` keyword. Again, you might like to write a JUnit test method to
formally test this functionality.

You may have noticed that the `MultiVariantShape` interface defines a method called `getVariant`
that should return the particular variant of a shape. This allows code outside of the class to interrogate an instance
in order to find out which variant it is. You will also notice that in the interface definition, their is no code that
provides an implementation of this method - it your job as the programmer's to write this !
Luckily you wrote this method during the previous workbook, so it is therefore a quick and an easy job to implement
the `MultiVariantShape` interface in your Triangle class at this stage !

  


# 
### Task 4: Arrays
 <a href='04%20Arrays/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='04%20Arrays/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Common to most programming languages, Java provides an **array** data structure (similar in many ways to that found in C). In Java, arrays can hold primitives
(e.g. `int`, `float`, `char`, `boolean` etc) or alternatively complex Objects (such as `Strings`, `Robots` or even `Triangles`) !

Take a look at the slides and video above for more information on using arrays in Java.
Then (in your main method) create an array of size 100 that can hold `TwoDimensionalShapes`.
Fill this array with randomly chosen shapes (`Circles`, `Rectangles`, `Triangles`).
In order to randomly select a shape from those available, you may like to make use of the
Java `Math.random()` method. This will return a randomly selected double precision floating point
number (somewhere between 0.0 and 1.0). You will need to write an IF statement and use some cunning maths
to look at this number and decide which shape to create.

Once you have filled the array with 100 random shapes, loop through it and print out the details of each shape in turn.
At the end of your main method, print out a summary of how many `Triangles` were in the array.
In order to do this, you will need to make use of an integer counter variable to keep a tally of the number of triangles encountered.
You will also need to make use of the `instanceof` operator to check the type of each instance in the array.
Notice how this use of `instanceof` is much more logical than the previous use of the keyword - we are using it to identify the
class which each object is an instance of !

  


# 
### Task 5: Class Variables and Methods
 <a href='05%20Class%20Variables%20and%20Methods/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='05%20Class%20Variables%20and%20Methods/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

In the previous task, we counted the number of `Triangles` in the array by looping through it.
But there is an alternative approach to achieving the same objective (without putting code inside the main loop).
We could maintain a counter _inside_ the Triangle class to keep track of of how many `Triangles` have been created.
Each time the Triangle class constructor is called, we can increment the counter in order to keep track of the number of Triangles.
This is an elegant approach because it encapsulates the "population" count inside the Triangle class itself - which is nice.

There is however one problem this with approach - we only want _one_ counter for the whole program
(not a counter inside _each and every_ `Triangle` instance). For this reason, we can't just use a "normal" variable.
View the slides and audio narration above to gain an understanding of the difference
between **instance** and **class** variables. Then add an integer **class** variable to your `Triangle` class to
keep track of how many `Triangles` have been created. Once you have declared this variable, update your
`Triangle` constructor method so that the counter is incremented each time a new `Triangle` is created.
You should also add a `getPopulation` method to your `Triangle` class that returns the current value of the
counter variable, so that it can be accessed from outside the class.

Update your main method, so that it prints out the number of `Triangles` that have been created using
the class variable population counter _as well as_ the previous loop counter from task 4
(just to make sure that both counters agree !)

Just as we can create **class variables** that are associated with the class (rather than each instance),
we can also write **class methods** which are similarly associated with the class (rather than the instances).
You have already encountered just such a class method (namely the `random` method of the `Math` class).
Have a think about the reasons why you might wish to write a class method, rather than an instance method.
Considering the `Triangle` class - might some of its methods be better suited to being class methods,
rather than instance methods ?
  


# 
### Task 6: Casting


Let's imagine that we wanted to find out the variant of all `Triangles` that are stored in our array.
We would need to be very careful about how we went about interrogating the elements of that array.
We can't call `getVariant` on just any element, since not all objects will actually _be_ a `Triangle`
(it might be a `Circle` for example, which doesn't support the `getVariant` method !)
Java will in fact block us from trying to call `getVariant` on a `TwoDimensionalShape`
(try it and see !).

To solve this problem, Java allows us **cast** objects from one class into a _more specific_ class.
For example, we can "down-cast" (or "narrow-cast") any `TwoDimensionalShape` into one of its sub-classes.
We can then quite happily call methods of that subclass on the instance.
For example, in the code fragment below we extract the first element from the array,
convert it into a `Triangle` and then call the `getVariant` method on it:

``` java
TwoDimensionalShape[] shapes = new TwoDimensionalShape[100];
...
TwoDimensionalShape firstShape = shapes[0];
// Down-cast the shape into a triangle
Triangle firstTriangle = (Triangle)firstShape;
TriangleVariant variant = firstTriangle.getVariant();
```

The down-casting code above asks Java to "trust" that the `firstShape` is a `Triangle`.
As a consequence of this, we can then treat this object as though it were a `Triangle` and thus call
any method of the `Triangle` class on it.

This is fine, _provided_ that the shape at element 0 of the array _is actually_ a `Triangle`.  
But what happens if it _isn't_ ?  
What if it is a `Circle` instead ?  
Will Java let us convert a `Circle` into a `Triangle` in this way ?

Why not try it and see what happens !
  


# 
### Task 7: Multiple Inheritance
 <a href='07%20Multiple%20Inheritance/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='07%20Multiple%20Inheritance/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

As a final topic for this workbook, let us think about the challenging concept of _multiple_ inheritance.
In some situations it might be useful for a class to have _multiple_ parents/superclasses.
This would allow it to derive methods and attributes from numerous different sources.
This is however dangerous - take a look at the slides and video above to find out why !

Java's solution to this is to only allow single inheritance :o(  
But to augment this with **interfaces** :o)  

Although a bit of a compromise, this combination of features allows us to gain _some_ of the benefits of multiple inheritance, whilst at the same time avoiding many of the main pitfalls.  


# 
