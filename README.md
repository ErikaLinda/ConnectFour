Implementation of the [Connect Four](https://en.wikipedia.org/wiki/Connect_Four) game using JavaFX to create the proper game experience. Moreover, the assignment had to leverage the Observer design pattern.


The original assignment brief:

# Homework 2: Graphical Connect Four



The objective of this assignment is to add a graphical user interface to your previous Connect Four codebase using JavaFX. 
In doing so, your code should follow some semblance of model view controller.



## Updates from Homework 1



### Classes


In addition to the previous classes that were provided, there are a few additions to the structure:


Package | Class | Extends | Implements | Summary
    
---|    ---|      ---|         ---|        
---
api  | Controller | | | Manages view events by acting as an interface to the model.

impl.view | Graphical | | | JavaFX view of the game
impl.controller | | | | Directory to add controller implementation(s).



As will later be discussed, implementation of the Controller API is optional. If you decide to do so, 
implementations should be added to `src/impl/controller`.



### Build



The file ConnectFourTest.java has been added to the `test.system` package. This file is 
intended to replace ConnectFourConsoleTest.java. However, we have added it to the repository 
so that it does not overwrite your old files. The code can still be run via Ant -- the build 
file has been updated to work with it (ConnectFourTest.java). Specifically, running
```bash
$> ant run
```
should launch your graphical view. 


## Adding a GUI



You must use JavaFX. How you do so is up to you:



### Building the view



JavaFX views can be built either statically, or programatically. In the former, you create FXML 
files that specify layout elements, and the Java classes that handle enhancing those elements and
handling events. There are tools that can assist in writing FXML -- Scene Builder from Oracle is 
most notable. Creating the views programatically involves uses the JavaFX libraries to set the 
scene and display the stage. Either approach is fine; although programmatically is probably easiest 
as that is what we have covered in class, and the approach taken by the course text.



### Model/controller communication



There are a number of ways in which your view can communicate with the model and controller. 
One method is to continue using the default Java Observer interface and thus redraw the stage
on update. Such a design decision would require `impl.view.Graphical` to implement Observer 
directly rather than `api.View`. 

Another method is to use bindings, a specialized type of variable 
connection defined by the JavaFX framework. Bindings are similar to observervables, but specific 
to attributes. In this case, the graphical view does not necessarily need to be an observer, 
as observation is implicit in the variable types. If this case is chosen, it is highly recommended 
that a controller be used to manage things.



## Expectations



The expectations of this assignment are largely centered on the view:



### Usability (70 points)



* Display the game and allow the user to interact 
with it using a mouse. Specifically, it should display the entire grid. When 
a user clicks a column, that user's should chip should appear in the appropriate row.


* Keep a running display of the current player.


* Display the winning player.


* Give some indication that the game is over. A user should be able to distinguish between a tie and a win.


* Once the game is determined to be finished, disallow more chips being played.



### Programmatic (30 points)



* Ability to operate with other models that implement the `Game` API. This includes, but is not limited 
to, other versions of Connect Four.


* The model should continue to respect the API. Any change to the model should be well documented in the commit logs.



## Working with Git



You should sync your current fork with this one, prior to beginning. Before doing so, it is 
a good idea to "tag" your current version:
```bash
$> git tag -a v1 -m "Homework 1 final"
$> git push origin v1
```

Tagging allows you to "bookmark" a milestone in your projects development. Once the tag is complete, you can go 
about syncing your fork; instructions for doing so can be found [here](https://help.github.com/articles/syncing-a-fork/).
If you happen to sync before you tag, all is not lost: you can [retroactively tag](http://stackoverflow.com/a/4404197) 
if you know the commit you would like to tag. Syncing should update your README and add the new class structure to your existing work.



## Workflow



It is expected that you will use GitHub religiously. You should fork this project, then `branch`/`commit`/`push` freely.


See the assignment listing in NYU Classes for the due date. We will grade the code that is on your master branch at the exact deadline.
