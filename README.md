<h1 align="center">
  Sudoku
</h1>
<p align="center">
<img src="https://github.com/user-attachments/assets/46de086c-1fd0-40f4-abe4-9e3424272967"/>
</p>

# Introduction
The Sudoku Game is made in Java. For UI I used JavaFX, because it was easy to understand and fast to produce UI.   
For static content I used Scene Builder and for dynamic like cells in grid I wrote the code that populates the grid.  
  
## The game
The game allows player to chose the difficulty of the board ranging from easy to hard. After the player clicks the button to start the game, new scene is showed with the board. 
<p align="center">
<img src="https://github.com/user-attachments/assets/2aa74175-bbb0-4019-9255-859ddb01cd21"/>
</p>

To play, player have to click on the tile and press the number in keyboard from 1 to 9. Player is notified if the answer he chosed for the tile is wrong or correct indicating with red for wrong answer and green for correct answer.  

If the player managed to 3 times wrong answer, the game stops and tells the player that he lost. After that player can come back with the button in the top left corner and try again. When player wins the game, there is the big green text telling the player that he won and then he can proceed to the main menu similarly when player loses.

<p align="center">
<img src="https://github.com/user-attachments/assets/b9f16b62-0802-4ac5-9a70-6d470496fed5"/>
</p>


For the difficulty there is 3 levels of difficulty:  
* Easy - hides 8 tiles in the board  
* Medium - hides 16 tiles in the board  
* Hard - hides 24 tiles in the board  


## How to play

### Requirements
1. IDE for Java
2. Java or OpenJDK (best if it's the newest version)
   
There are 3 ways to play the game. All of the methods requires to download the repository and IDE if you don't want to open trought the .jar.

1. In IDE (I use eclipse), You have to import the repository as a maven project. Then You can run the MainLauncher.java as a Java Applicaton.
2. This method is the same as the first one, but You can use maven to start the game through the command: maven javafx:run
3. Last method is to go to the target folder and in command-line, if you have java installed type ```java -jar sudoku-0.0.1-SNAPSHOT.jar```.


# Future plans

* Rewrite the game in Flutter.  
* Make point system for winning/losing the game.  
* Store the points in database with the user attached to it.  
* Auth system.
  
