import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Logic.*;
public class GameIBT implements MouseListener, ActionListener{
	private boolean nemesisAppears = false;
	private Room currentRoom;
	private Player player;
	private int difficulty;
	private int counter = 1;
	private int numberOfDoors;
	private String answer;
	private GameGUI gui = new GameGUI();
	private StatsGUI statsGui = new StatsGUI();
	private int roomChoice;
	private int uiCounter = 1;
	private int uiCounter2 = 1;
	
	/**
	 * Constructor, creates the player instance, adds the listeners, and sets the first message on the label.
	 */
	public GameIBT(){
		player = new Player();
		gui.getGuessBtn().addActionListener(this);
		pickDifficulty();
		gui.getContentPane().addMouseListener(this);
	}
	
	/**
	 * When the user clicks we take the coordinates and see if they clicked a door or the puzzle icon.
	 * @param x  x coordinate of click
	 * @param y  y coordinate of click
	 */
	public void startRoom(int x, int y) {
		if ((counter == 1 || counter == 5) && (x >= 270 && x <= 430 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		else if ((counter == 1 || counter == 5) && (x >= 730 && x <= 900 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		else if ((counter == 2 || counter == 4) && (x >= 110 && x <= 290 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		
		else if((counter == 2 || counter == 4) && (x >= 490 && x <= 660 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		else if ((counter == 2 || counter == 4) && (x >= 820 && x <= 990 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		
		else if ((counter == 3 || counter == 6) && (x >= 450 && x <= 620 && y >= 185 && y <= 570)) {
			tryTheDoor();
		}
		
		
		else if (x >= 1040 && x <= 1100 && y >= 45 && y <= 110) {
			tryThePuzzle();
		}
	}
	
	/**
	 * when we want the user to pick a door to go through we get their click and see which door they chose.
	 * @param x  x coordinate of click
	 * @param y  y coordinate of click
	 */
	public void chooseDoor(int x, int y) {
		if ((counter == 1 || counter == 5) && (x >= 270 && x <= 430 && y >= 185 && y <= 570)) {
			roomChoice = 1;
			nextRoom(roomChoice);
		}
		else if ((counter == 1 || counter == 5) && (x >= 730 && x <= 900 && y >= 185 && y <= 570)) {
			roomChoice = 2;
			nextRoom(roomChoice);
		}
		else if ((counter == 2 || counter == 4) && (x >= 110 && x <= 290 && y >= 185 && y <= 570)) {
			roomChoice = 1;
			nextRoom(roomChoice);
		}
		
		else if((counter == 2 || counter == 4) && (x >= 490 && x <= 660 && y >= 185 && y <= 570)) {
			roomChoice = 2;
			nextRoom(roomChoice);
		}
		else if ((counter == 2 || counter == 4) && (x >= 820 && x <= 990 && y >= 185 && y <= 570)) {
			roomChoice = 3;
			nextRoom(roomChoice);
		}
		
		else if (counter == 3 && (x >= 450 && x <= 620 && y >= 185 && y <= 570)) {
			roomChoice = 1;
			nextRoom(roomChoice);
		}
		
		
		else if (counter == 6 && (x >= 450 && x <= 620 && y >= 185 && y <= 570)) {
			gui.setMessage("Congratulations! You completed our game!");
			gui.setBackground("win.jpg");
		}
	}
	
	/**
	 * takes their door choice and sets if the next room is a nemesis room accordingly, sets the welcome message.
	 * @param aroomChoice  which door they chose to go through.
	 */
	public void nextRoom(int aroomChoice) {
		if((counter == 4 || counter == 5) && aroomChoice == 2) {
			nemesisAppears = true;
			} else {
			nemesisAppears = false;
			statsGui.setStatNem("");
			}
			currentRoom = new Room(difficulty, nemesisAppears);
			counter ++;
			numberOfDoors = entryMessage(counter);
			if (currentRoom.isNemesisRoom()) {
				statsGui.setStatNem("A NEMESIS APPEARS.");
			}
			uiCounter = 3;
	}
	
	/**
	 * button listener, does things with their input dependent on what stage of the game the user is at.
	 * uicounter is used to determine what "stage" the game is in, i.e. choosing the difficulty stage, choosing the character stage,etc.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Answer")) {
			
			if (uiCounter == 1) {
				
				if (Integer.parseInt(gui.getEntry().getText()) > 0 && Integer.parseInt(gui.getEntry().getText()) < 4) {
					difficulty = (Integer.parseInt(gui.getEntry().getText()));
					pickCharacter();
					gui.setBackground("Tutorial.jpg");
					gui.clearField();
					uiCounter++;
				}
				else {
					statsGui.setStatNem("please enter a valid input");
				}
			}
			
			else if (uiCounter == 2) {
				
				if (Integer.parseInt(gui.getEntry().getText()) > 0 && Integer.parseInt(gui.getEntry().getText()) < 7) {
					int chosenCharacter = (Integer.parseInt(gui.getEntry().getText()));
					int initialHealth = player.startHealth(chosenCharacter);
					statsGui.setStatHealth(Integer.toString(initialHealth));
					ArrayList<Item> items = new ArrayList<Item>(player.startItems(chosenCharacter));
					String itemNames = "";
					for (int index2 = 0; index2 < items.size();index2 ++) {
						itemNames = itemNames + items.get(index2).getName() + " ";
					}
					if (items.size() == 5) {
						statsGui.setStatItem("All Keys");
					}
					else {
						statsGui.setStatItem(itemNames);
					}
					statsGui.setStatNem("");
					player = new Player(initialHealth, items);
					numberOfDoors = entryMessage(counter);
					currentRoom = new Room(difficulty, nemesisAppears);
					gui.setBackground("Background1.jpg");
					gui.clearField();
					uiCounter++;
				}
				else {
					gui.setMessage("please enter a valid input");
				}
			}
			
			else if (uiCounter2 == 90) {
				answer = (gui.getEntry().getText());
				if ((currentRoom.getChallenge().checkValidInput(answer))){
				
					boolean correctAnswer = currentRoom.getChallenge().verifyAnswer(answer);
					if (correctAnswer) {
						gui.setMessage("Correct answer! you may go through the door.");
						gui.clearField();
						uiCounter = 4;
					}
					else if (!correctAnswer) {
						gui.setMessage("Incorrect answer! you lost some health.");
						if (currentRoom.isNemesisRoom()) {
							if (player.getHealth() >= 2) {
								player.updateHealth(2);
							}
							else if (player.getHealth() < 2) {
								player.updateHealth(1);
							}
							statsGui.setStatHealth(Integer.toString(player.getHealth()));
							if (!player.isAlive()) {
								gui.setMessage("Game Over");
								gui.setBackground("death.jpg");
								uiCounter = 30;
								uiCounter2 = 2;
							}
						}
						else {
							player.updateHealth(1);
							statsGui.setStatHealth(Integer.toString(player.getHealth()));
							if (!player.isAlive()) {
								gui.setMessage("Game Over");
								gui.setBackground("death.jpg");
								uiCounter = 30;
								uiCounter2 = 2;
							}
						}
					}
				}
				else {
					statsGui.setStatNem("please enter a valid one word answer, with no spaces.");
				}
				gui.clearField();
			}
			
			
		}
	}
	
	public void mousePressed(MouseEvent click){}
    public void mouseEntered(MouseEvent click){}
	public void mouseExited(MouseEvent click){}
	public void mouseReleased(MouseEvent click){}
	/**
	 * mouseListener, when a click event happens, if it happens during a stage of the game when we need a click, then it sends the click x and y location
	 * to the appropriate method.
	 */
	public void mouseClicked(MouseEvent click) {
		if (uiCounter == 3) {
			startRoom(click.getX(),click.getY());
		}
		else if (uiCounter == 4) {
			chooseDoor(click.getX(),click.getY());
		}
	}
	
	/**
	 * sets the label to print the difficulty levels.
	 */
	public void pickDifficulty(){
		gui.setMessage("There are 3 difficulty levels: 1: easy 2: medium 3: hard choose one.");
	}
	
	/**
	 * sets the label to print the character list.
	 */
	public void pickCharacter() {
		statsGui.setStatNem(
		"1: Jeff, starting health: 5,  Starting Item: Fire Key \n " +
		"2: Dan, starting health: 10,  Starting Item: All Keys \n " +
		"3: Suzy, starting health: 7,  Starting Item: Earth Key \n " +
		"4: Tej, starting health: 8,  Starting Item: Metal Key \n " +
		"5: Suki, starting health: 4,  Starting Item: Water Key, Wood Key \n " +
		"6: Deprived, starting health: 1,  Starting Item: None  ");
		gui.setMessage("Pick your character: ");
	}
	
	/**
	 * tryTheDoor is used when the player tries to use the door in a room
	 */
	public void tryTheDoor() {
		if (player.hasItem(currentRoom.getDoorVariable())) {
			gui.setMessage("you may go through the door");
			uiCounter = 4;
		} else {
			gui.setMessage("You need a " + currentRoom.getDoorVariable().getName() + " to unlock the door.");
		}
	}
	
	/**
	 * tryThePuzzle is used when the players tries to do the puzzle in the room.
	 */
	public void tryThePuzzle() {
		if (currentRoom.isNemesisRoom()) {
			statsGui.setStatNem("The nemesis asks you a question.");
		}
		gui.setMessage(currentRoom.getChallenge().getQuestion() + " Enter your answer.");
		//get string from text field
		uiCounter2 = 90;
	}
	
	/**
	 * entryMessage is used to display an entry message when the player enters a new room.
	 * the purpose of the counter is to keep track of which room they are in.
	 * @param counter  the number indicating which room the player is in.
	 * @return numberOfDoors  the number of doors in the next room.
	 */
	public int entryMessage(int counter) {
		if(counter == 1){
			numberOfDoors = 2;
			/*statsGui.setStatNem("TUTORIAL: click on one of the doors to try and use an item to unlock it."
					+ " if you have the correct item you will be able to go through to the next room."
					+ " you can also click the puzzle icon in the top right, this will give you a puzzle"
					+ " to solve, and if you are correct you can continue to the next room, if you are incorrect you will lose some health."
					+ " if a nemesis appears, then it will ask you a tougher question, and you will lose a higher amount of health.");*/
			gui.setMessage("You step into the first room.");
		} else if(counter == 2) {
			numberOfDoors = 3;
			gui.setBackground("Background2.jpg");
			gui.setMessage("You step into the second room.");
		} else if(counter == 3) {
			numberOfDoors = 1;
			gui.setBackground("Background3.jpg");
			gui.setMessage("You step into the third room.");
		} else if(counter == 4) {
			numberOfDoors = 3;
			gui.setBackground("Background4.jpg");
			gui.setMessage("You step into the fourth room.");
		} else if(counter == 5) {
			numberOfDoors = 2;
			gui.setBackground("Background5.jpg");
			gui.setMessage("You step into the fifth room.");
		} else if(counter == 6) {
			numberOfDoors = 1;
			gui.setBackground("Background3.jpg");
			gui.setMessage("You step into the sixth room.");
		}
		return numberOfDoors;
	}
	
	/**
	 * exitMessage is used to display a message whenever the user completes the challenge in a
	 * room and we need them to pick which door they would like to go through. the purpose
	 * of the counter is to keep track of which of the 6 rooms they are in.
	 * @param counter
	 * @return
	 */
	public void exitMessage(int counter) {
		gui.setMessage("click a door to go through");
	}
	
	/**
	 * main method.
	 * @param args
	 */
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				GameIBT game = new GameIBT();
				game.gui.pack();
				game.gui.setVisible(true);
				game.gui.setResizable( false );
				game.gui.setLocationRelativeTo( null );
				game.statsGui.pack();
				game.statsGui.setVisible(true);
				game.statsGui.setResizable( false );
				game.statsGui.setLocationRelativeTo(game.gui);
            }
        });
    }
}