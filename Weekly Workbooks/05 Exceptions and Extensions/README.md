## Exceptions and Extensions
### Task 1: Introduction


In this workbook, we will add some extensions to the OXO game that you started in the previous workbook.
The tasks in this workbook focus on handling errors and implementing some advanced extensions to the basic OXO game.  


# 
### Task 2: Error Handling in Java
 <a href='02%20Error%20Handling%20in%20Java/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Error%20Handling%20in%20Java/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='02%20Error%20Handling%20in%20Java/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='02%20Error%20Handling%20in%20Java/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

It is more than possible that a user will at some point make a mistake and input a "bad" cell identifier
(for example, a cell that doesn't exist or one that has already been claimed by one of the players).
In Java, handling these kinds of unintended run-time errors is achieved using a language feature called **Exceptions**.
This includes a set of special classes and an error handling mechanism that is part of the Java programming language itself.
View the slides and video above to gain an understanding of the concept of exceptions and to
see a demonstration of exceptions in action.

When making use of exceptions in our OXO game, we first need to appreciate the range of the different types of user
input error that are possible. These include the following:

- Invalid Identifier Length: The entire identifier string is longer (or shorter) than the required two characters
- Invalid Identifier Character: The row character is not alphabetical and/or the column character is not numerical
- Outside Range: The identifiers are valid, but they are outside the range of the board size (i.e. too big or too small)
- Already Taken: The specified cell exists, but it has already been claimed by a player

Note that these input errors are listed in order of precedence (i.e. when processing an incoming command, you should
check for the errors in the order listed above). The reason for this is that there are some situations where an inputted
command falls under more than one class of error - in these situations, you should use the first error type detected.

To help you to handle user input errors in your OXO game, we have provided you with a set of exception classes that
can be used to represent each of the above types of error. Note that all of these exceptions can be found inside the
`OXOMoveException` file in the template project's `src` folder. At the beginning of this unit we said that there is
_normally_ a one-to-one mapping between a file and a class. In this situation however we break this convention in the
interests of a tidy file system ! Each exception class is very short and as such don't warrant individual files of their own.

The exception classes that you have been provided with are illustrated in the class hierarchy shown below
(note that the notations used in the diagram are from UML - something that you will be studying in the Software Engineering unit !)  


![](02%20Error%20Handling%20in%20Java/images/inheritance.jpg)

**Hints & Tips:**  
Make sure you have a solid grasp of these concepts before moving on to the next section. In the following tasks you will get the opportunity to use the exceptions illustrated above to implement error handling in your OXO game.
  


# 
### Task 3: Error Handling in Practice
 <a href='03%20Error%20Handling%20in%20Practice/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Error%20Handling%20in%20Practice/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Now that you have a high-level understanding of Java's exception handling mechanism and an appreciation of the range of input errors a user might make, let us use this information to actually implement error handling in the OXO game.
Take a look at the slides and video linked to above to find out how to work with exceptions in Java.

With the knowledge you have gained from the above, implement error handling in your `OXOController` class.
Add appropriate input checking features to your `handleIncomingCommand` method to determine whether or not the
specified cell identifier can be accepted.
If the identifier is acceptable, mark the specified cell as being owned by the player as normal.
If on the other hand the identifier is unacceptable, prevent the move from taking place and instead instantiate and
'throw' the relevant exception (depending on the type of input error that has been made).

The `OXOGame` will 'catch' any exceptions that the `OXOController` has 'thrown' and will print out a relevant
error message to the terminal/console as feedback to the user.

Test your program manually by playing a game and entering a range of erroneous inputs,
making sure that all errors are trapped and reported correctly.  


**Hints & Tips:**  
Note that in order to be able to access all of the OXO move exceptions introduced in the previous section, you will need to
`import edu.uob.OXOMoveException.*;` at the top of your controller class.

You may assume a maximum grid size of 9x9 (rows `a`-`i` and columns `1`-`9`)
so that a cell identifier can ONLY ever be two characters long.
Note that your code should accept upper as well as lower case row characters
(e.g. `A1` and `a1` are equivalent and both are acceptable).  


# 
### Task 4: Testing Exceptions


As we have discuss previously, automated testing is a key practice in Software Engineering.
It is time for you to start applying this practice more extensively in your own work.
To this end, you should add a set of additional test cases to your automated testing script
that will trigger (and then check) all of the different types of OXO move exception discussed in the previous section.

There are a number of different ways to do this - perhaps the simplest is to use the `assertThrows` assertion.
We pass this assertion a particular method to call and an exception that we expect to be thrown.
If the method does NOT cause the exception, then JUnit will report that the test has failed.
For example, we could check to make sure that the user input command `aa1` results in an `InvalidIdentifierLengthException`
using the following code:
```java
assertThrows(InvalidIdentifierLengthException.class, ()-> controller.handleIncomingCommand("aa1"));
```

There are a couple of things to note about this code:  

Firstly, it is important that we pass in the `InvalidIdentifierLengthException` _class_ rather than an _instance_ (object) of that class.
This is because we want JUnit to check that an instance of the `InvalidIdentifierLengthException` class has been thrown, but we don't care _which_ instance
(in fact we have no way of predicting which instance has been created).

Secondly, the expression `()->` might look a little bit strange, but it is in fact correct Java syntax !  
This element is called a **lambda** and it allows us to "bundle up" a method to pass into `assertThrows`:
- _WITHOUT_ `()->` the code above would run the `handleIncomingCommand` method first, and then pass the _result returned_ into `assertThrows`.
If an error is encountered, an exception will be thrown _before_ the `handleIncomingCommand` method returns, and as a result there will be _nothing_ to pass into `assertThrows`.
- _WITH_ `()->` the code above passes the entire `handleIncomingCommand` method into `assertThrows`, so that `assertThrows` can then call it.
If an error is encountered, an exception will be thrown _inside_ `assertThrows` so that it is able to detect and check that it is of the correct type.

A subtle, yet important distinction !  


# 
### Task 5: Win Threshold


The 'Win Threshold' is the number of cells in a row required to win a match (3 in a "standard" game).
It would make for a more interesting game if it were possible to change this threshold during a game.
To support this feature, the `OXOGame` class includes features that allow the user to interactively alter the win threshold:
- Pressing the `+` key on the keyboard should increase the win threshold 
- Pressing the `-` key on the keyboard should decrease the win threshold 

Although the `OXOGame` class is able to detect the pressing of the `+` and `-` keys, the internal win threshold is currently not updated.
The `increaseWinThreshold` and `decreaseWinThreshold` methods inside `OXOController` are called when the keys are pressed,
however these two methods are currently empty, so pressing the keys has no effect.
Add code to the `increaseWinThreshold` and `decreaseWinThreshold` methods in your `OXOController`
so that they alter the win threshold value held inside the `OXOModel` (by calling the relevant "getter" and "setter" methods).

You should make your code as robust as possible, preventing the data held in the model from getting into any undesirable states.
Your controller must adhere to the following rules:
- The minimum win threshold should be 3 (a game with a lower threshold is going to be pretty pointless !).
- The maximum win threshold should be the current size of the board (whichever dimension is smaller: width or height).
- Users should NOT be able to _reduce_ the win threshold _during_ a game (only at beginning).
- It should be possible to increase the win threshold at any point during the game, except that...
- It should NOT be possible to change the win threshold _after_ a win - gameplay should cease when someone has won !

Note that you should NOT alter the win threshold when the game is reset (i.e. when the `ESC` key is pressed).
It is up to the players to choose the win threshold for the next game.
You should also ensure that all game state is stored inside the `OXOModel` and NOT in your `OXOController` (or anywhere else for that matter).

Be sure to add suitable test cases to your test scripts in order to fully test the variable win threshold features
(including checking that the controller maintains the threshold according to the rules specified above).  


# 
### Task 6: More Players


Although two players is the traditional number in an OXO game, allowing additional players would make for a more interesting game.
As an extension to the standard game, add features to your code so that it can support any number of players.
To achieve this you will need to make changes to both the `OXOModel` as well as your `OXOController`.
You will need to use alternative player characters for the additional players
(having more than one player who's playing letter is `X` will lead to a lot of confusion !)

You should NOT attempt to alter the `OXOView` or `OXOGame` classes in order to allow the _interactive_ setting of the number of players.
Instead, you should focus on testing multi-player games by creating additional test cases in your testing script.

Remember to try to ensure that your code is flexible versatile (avoid hard-wiring any particular fixed values) !
  


# 
