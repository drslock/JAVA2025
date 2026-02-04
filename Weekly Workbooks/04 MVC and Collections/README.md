## MVC and Collections
### Task 1: Introduction


The next TWO workbooks will lead you through an extended programming exercise.
Your aim in this exercise is to build a digital version of the classic turn-taking game
"Noughts and Crosses" / "Tic-Tac-Toe" / "OXO". You will NOT be required to construct the _entire_ game...
the Graphical User Interface (see screenshot below) and the core Data Model classes will be provided for you.
You will however be required to write the 'Controller' that handles all of the game logic.
  


![](01%20Introduction/images/game.jpg)

# 
### Task 2: Model-View-Controller
 <a href='02%20Model-View-Controller/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Model-View-Controller/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

The Model-View-Controller (MVC) 'pattern' is a common structural convention that is used widely in the development of
interactive systems. To help get you started in this exercise, you have been provided with a <a href="cw-oxo/" target="_blank">Maven template</a>
that conforms to the Model-View-Controller pattern. Copy this project into your development folder and add code to achieve each of the tasks in this workbook.
It is NOT a good idea to work on the project "in-situ" (i.e. still inside the repository folder), this is because it might get overwritten by future GitHub pull commands !

Take a look at the slides and video above for an introduction to the concept of the Model-View-Controller pattern,
then use the knowledge gained to explore the OXO game template.

The `OXOModel` class contains all of the core data structures required by the game - you can use the public methods
provided by this class in order to access and manipulate the following internal state:

- The number of cells in a row required to win the game (3 in a standard game)
- The set of players currently playing the game (2 in a standard game !)
- The player whose turn it currently is
- The "owner" (player who has claimed) each cell in the game grid
- The winner of the game (when the game ends)
- Whether or not the game has been drawn (all cells filled, but with no winner !)

The 'Rendering Logic' has already been implemented for you: any changes to the state of the `OXOModel` will be automatically rendered by the `OXOView`. 
Your main task is therefore to implement the 'Event Handling Logic' in the `OXOCOntroller` class.

The code that you write must access and manipulate the state of the `OXOModel` by calling appropriate methods on it.
Note that you will NOT need to interface directly with the `OXOView` class.

You may alter and add new methods to the `OXOController` and `OXOModel` classes,
however you should NOT change the signatures of any of the _existing_ methods.
You should NOT alter the `OXOView` or `OXOGame` classes at all (this is not part of the assignment).
The remaining tasks in this week's (and next week's) workbooks will lead you through the features that you need to implement to complete the exercise.

Finally, it is very important to remember that the `OXOModel` class should be used to maintain ALL game state.
You should definitely NOT store any game state in your `OXOController` class - bad things might happen if you do !  


**Hints & Tips:**  
You may notice that some of the classes that we have provided in the template project implement the `Serializable` interface and many have a private attribute called `serialVersionUID`. GUI applications written in Java require classes to meet these requirements so that the application can be stored ('serialized') in certain situations. We won't be doing any serialization in this exercise, however we still need to include the above features in the project template (otherwise we will get warnings when we try to compile the project).  


# 
### Task 3: Responding to Input
 <a href='03%20Responding%20to%20Input/video/oxo.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

The first step in creating a fully operational game is to get your program to respond to input from the user.
Players will take it in turns to enter desired cell position into the `OXOGame` GUI window as demonstrated in the video above
(note there is no audio narration in this particular video !).
`OXOGame` deals with reading in keypresses from the user and will then pass the inputted command to the `OXOController` for processing
(via a call to the `handleIncomingCommand` method).
Your task is to interpret this incoming command and update the game state accordingly.

An inputted cell position consists of the row letter and the column number of the cell a player wishes to "claim".
For example, if a user wished to claim the centre cell of a 3x3 board, they would type: `b2`. Note that cell
identifiers are _case insensitive_ - so that `B2` is equivalent to (and as equally acceptable as) `b2`.

Once one player has entered a command and the board has been updated, play should switch to the next player.
Note that the order of play should be the same as the order that players were added to the model
(i.e. the first player added to the game will take the first go, followed by the second etc.)

When updating the game state, you should set the 'current player' to be the player _whose turn it is next_.
This is to ensure that the view shows graphically the player _who is about to take a turn_.

It is possible that the user may make a mistake when interacting with the system and enter a "bad" cell identifier.
Do not try to deal with these here - we will address error handling in the next workbook.
For the time being, you may assume that any cell identifiers entered by the user are always valid.

You should test your code by inputting a series of cell identifiers into the `OXOGame` window and check to make sure
that the board updates as expected. Note that at this stage in the exercise, 'win detection' has not yet been implemented,
so as a result the game will never end !

If at any point the players get bored of a game, or wish to start a new game, they can press the `ESC` key to reset the board.
In order to fully implement this reset, you will need to add some code to the `reset` method in your `OXOController`.
This should call the relevant methods of the `OXOModel` class in order to clear the board and reinitialise the game state
to the original settings.
  


# 
### Task 4: Dynamic Board Size
 <a href='04%20Dynamic%20Board%20Size/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='04%20Dynamic%20Board%20Size/slides/segment-2.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='04%20Dynamic%20Board%20Size/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='04%20Dynamic%20Board%20Size/video/segment-2.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='04%20Dynamic%20Board%20Size/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='04%20Dynamic%20Board%20Size/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

Currently the core data structure of the `OXOModel` class is a 2D array.
This allows us to maintain a data representation of the current state of the game board.
There is however a problem in that this constrains us to a particular board size (namely 3x3).
It would be nice to be able to alter the size of the board _during_ a game !
For example, if during a game it became clear that there was going to be a draw
(with all cells being occupied, but with no clear winner)
it would be nice if the user could optionally increase the board size in order to allow play to continue
(in the hope that a winner might eventually triumph !)

Luckily there are a number of dynamic data types (such as Queues, Stacks, Lists etc.)
that are provided by the core Java libraries that allow us to store _dynamically_ sized groups of objects.
Take a look at the slides and video above to gain an understanding of these data structures and the problems
and challenges involved in using them.

These slides and videos cover a feature of Java called **Generics**. This mechanism allows us to designate a particular compound data structure to hold a specific object type. This allows us to make use of untyped data structures, whilst at the same time enforcing type checking at compile time.

Using the knowledge you have gained from the above slides and videos, convert the board grid data structure contained in the `OXOModel` class from arrays into an
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html" target="_blank">ArrayList</a> data structure.

In order to fully implement support for dynamic board sizes, you will need to:
- Change your `cells` variable from a 2D array into some `ArrayList` elements
- Alter the OXOModel constructor to initialise your `ArrayList` data structure
- Update the cell owner "getter" and "setter" methods to operate on the `ArrayList` elements
- Add two methods to `OXOModel` called `addColumn()` and `addRow()` that allow the board size to grow
- Add two methods to `OXOModel` called `removeColumn()` and `removeRow()` that allow the board size to shrink

You should make your code as robust as possible, preventing the data held in the model from getting into any undesirable states. Remember that a key feature of encapsulation is enforcing the _safe_ access and manipulation of object attributes.  


**Hints & Tips:**  
In order to manually test your implementation of dynamic board size, the `OXOGame` class contains interaction features
that allow the user to change the size of the board during the game. If the user **left** clicks their mouse on either the grey column or row labels, that dimension will **expand**. If the user **right** clicks their mouse (or `control` click on a Mac) on either the grey column or row labels, that dimension will **shrink**. Note that clicking with the mouse will cause the add/remove row/column methods of your `OXOController` to be called. You will therefore need to update these 4 methods so that they in turn call the relevant methods of the `OXOModel`.

The minimum allowable board size is 3x3 and the maximum allowable board size 9x9 - your event handler should not allow the board size to change outside of this range. In addition to this, your game should not allow the board size to be reduced if the rows/columns being removed are not empty (i.e. don't allow reduction if it will remove previous moves).

You should NOT alter the board size when the game is reset (i.e. when the `ESC` key is pressed).
Keep the board size the same and leave it to the players to change the dimensions, if they wish.  


# 
### Task 5: Win detection


There is no point playing the game if nobody can actually win !
With this in mind, add code to a suitable location in your `OXOController` class so that it
will detect when a win has been achieved. You should check for wins in all directions
(horizontally, vertically and diagonally). Note that horizontal and vertical checking is _relatively_ easy,
but win checking of diagonals is a bit more difficult !

If the game reaches a situation where all cells are filled, but no player has reached the win threshold,
the game should be considered a "draw" and the model updated to reflect this (by calling the appropriate methods).
Players may choose to accept a draw, or they might alternatively deciding to increase the board size and continue playing.

You should attempt to make your `OXOController` as flexible and versatile as possible.
It should therefore be able to perform win detection on grids of any size (not just the standard 3x3 board).

When a game has been won, the game should NOT exit - this is to allow the winner to glory in their victory !
It is however important that your controller should accept no further play commands after a win
(i.e. no additional cells can be claimed) and should not allow the board size to be changed.
This is to stop the loser carrying on playing after a game has already been won !

Note that if the players wish to play another game, they can reset the board in the usual way (by pressing the `ESC` key).  


# 
### Task 6: Automated Testing


The problem with manual testing is that it can be a very time consuming activity to undertake.
Imagine trying to test the 'draw' state for a 9x9 board - this would involve taking 81 separate turns to reach the end of the game.
Now imagine having to test this draw state a number of times (maybe your code didn't work the first time you tried it -
and you have to change your code and test it again). This would soon become very tedious !

This is where automated testing can become invaluable - we can test the 'model' and 'controller' components of the system by replacing
the graphical 'view' with an automated test script. The game can then be extensively tested without having to click the mouse or press a key.

We have provided a skeleton test script called `ExampleControllerTests` that can be found in the `src/test/java/edu/uob` folder.
You will notice that the file has only a couple of test methods in it.
You should populate this test script with a comprehensive set of test cases that will fully test your game.
Remember that you can run these tests inside IntelliJ, by clicking on the green "run" icons to the left of each method in the editor.
Alternatively you can run them from the command line using Maven.

These kinds of test scripts are an essential element of Test-Driven Development (TDD) as discussed in the Software Engineering unit.
They help you to think about what the system needs to do _in advance_ of implementation, as well as allowing you to keep track of the
correct operation of your code, _as you develop it_. With this in mind, you should add a suitable range of tests to your project as you progress.
Remember that your aim is to cover all required features of the game and verify the correct operation of your code in all situations.

Such test cases are not easy to come up with - writing a good set of tests is a difficult and time consuming activity.
This is because writing test cases requires you to perform "problem analysis" in order to understand the situation
and think about all the possible states of the system (something that _we_ did for you in the Triangles exercise !).
Such analysis is an essential developer skill, as is the ability to document the outcomes of this analysis formally as a set of test cases.
  


**Hints & Tips:**  
When viewing the example test script, you may have noticed a new test feature not previously encountered - the `@BeforeEach` annotation
is used to mark a method which will be executed _before_ any `@Test` methods are run.
This allows us to create a "setup" method that will initialise any instances that are required to successfully run a `@Test` method.
You will also see the use of the `()->` 'lambda' expression. Don't worry about this for the moment, we will consider it in more detail in the next workbook.

  


# 
### Task 7: Brief Pause


When you get to this point in the workbook, you should have implemented all of the core features of the OXO game.
This is not the end of the exercise however - in the next workbook you will get to implement an error handling mechanism
as well as a number of extensions to the basic game. It is worth pausing for a moment to see how far you have come.
Take a look at your code to see if there is anything that could be refactored to improve structure and readability.
You should be making backups of your work regularly - if you haven't done so recently, now is a good time.
Maybe also time for a couple of games of OXO before moving on !  


# 
