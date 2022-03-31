	Title: The Descent


	Synopsis:

This is a game in which the user is trapped in a maze. The only way out is to answer a 
series of riddles. Each incorrect answer results in a loss of a life point, while a correct
answer allows the user to pass through the door. Throughout the game, the user may encounter 
a Nemesis. When the nemesis is activated, the riddles get harder and the consequences for 
failing become greater. Instead of loosing 1 life point, the user will loose 2 points. 

At the beginning of the game, the user is presented with a list of characters they can 
choose from. Each character has a unique number of health points, as well as a certain number 
of "keys." These keys will unlock a door without the user needing to solve the puzzle. However, 
they user does not know which door (if any) the key will open until they try it. 

There are 3 difficulty levels to the game: Easy, Medium, and Hard. There are 20 possible 
riddles/challengesfor each level, and the game selects a riddle randomly as the user enters 
each new room.  There are 5 rooms per game, which allows the user to play the game multiple 
times and have a different playing experience, whether or not they change the level. 

The game ends if the user is able to complete all 5 riddles they are presented with, or 
they run out of life points. 


	How to compile this code: go to the FinalProject directory, on the command line type javac *.java



	How to run the text based version: java TextInterface



	How to run the GUI version of the game: java GameIBT



	How to run the JUNIT tests for the logic classes: go to the FinalProject/Logic directory, use the junit commands to compile and run each test file.
	
Ensure that junit-4.12.jar and hamcrest-core-1.3.jar are in the same file as the classes you wish 
to test. Each logic class (Rooms.java, Challenge.java, Player.java...) has an associated 
JUnit test. 

Run and compile the test classes by using the commands below: 

javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore <Test Class>

	Contributors: 
	
Eric Gantz, Jung Kim and Marcia Kiewiet as part of a project for CPSC 233 during the fall 
semester of 2017. 
