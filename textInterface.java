import java.util.Scanner;
import java.util.ArrayList;
import Logic.*;
public class textInterface {
	private boolean nemesisAppears = false;
	private Room currentRoom;
	private Player player;
	private int difficulty;
	private int counter;
	private int numberOfDoors;
	boolean keepPlaying;
	
	/**
	 * constructor, does all the one time operations at the start, picking difficulty
	 * and character and starting up the first room.
	 */
	public textInterface() {
		player = new Player();
		difficulty = pickDifficulty();
		int chosenCharacter = pickCharacter();
		int initialHealth = player.startHealth(chosenCharacter);
		ArrayList<Item> items = new ArrayList<Item>(player.startItems(chosenCharacter));
		player = new Player(initialHealth, items);
		currentRoom = new Room(difficulty, nemesisAppears);
	}
	
	/**
	 * main gameplay loop.
	 */
	public void play() {
		keepPlaying = true;
		counter = 1;
		numberOfDoors = entryMessage(counter);
		while (counter <= 6) {
			while (keepPlaying) {
				if (counter == 6) {
					keepPlaying = false;
				}
				System.out.println("Choices: ");
				System.out.println("1: use an item");
				System.out.println("2: " + "try the puzzle");
				System.out.print("Enter your choice: ");
				int pickOption = chooseIntOption();
				while(pickOption != 1 && pickOption != 2){
					System.out.println("please enter a valid choice.");
					pickOption = chooseIntOption();
				}
				if (pickOption == 1) {
					tryTheDoor();
				}
				else if (pickOption == 2) {
					tryThePuzzle();
				}
			}
		}	
		System.out.println("Congratulations, you completed our game!.");
	}

	/**
	 * pickDifficulty lets the user choose what difficulty they would like to play on.
	 * this is fine.
	 * @return
	 */
	public int pickDifficulty(){
		System.out.println("There are 3 difficulty levels: ");
		System.out.println("1: easy");
		System.out.println("2: medium");
		System.out.println("3: hard");
		System.out.println("Choice: ");
		int difficulty2 = chooseIntOption();
		while(difficulty2 != 1 && difficulty2 != 2 && difficulty2 !=3){
				System.out.println("please enter a valid choice.");
				difficulty2 = chooseIntOption();
		}
		return difficulty2;
	}
	
	/**
	 * pickCharacter lets the user choose which character they would like to play the game as.
	 * this is fine.
	 * @return
	 */
	public int pickCharacter() {
		System.out.println ("1: Jeff, starting health: 5,  Starting Item: Skeleton Key");
		System.out.println ("2: Dan, starting health: 10,  Starting Item: Key1,Key2,Key3,Key4,Key5");
		System.out.println ("3: Suzy, starting health: 7,  Starting Item: Key2");
		System.out.println ("4: Tej, starting health: 8,  Starting Item: Key3");
		System.out.println ("5: Suki, starting health: 4,  Starting Item: Key4, Key5");
		System.out.println ("6: Deprived, starting health: 1,  Starting Item: None");
		System.out.print ("Pick your character: ");
		Scanner keyboard = new Scanner(System.in);
		
		boolean keepTrying = true;
		int characterChoose = 0;
		
		while (keepTrying) {
			
			/* Will re-prompt if user enters non-integer inputs */
			while (!keyboard.hasNextInt()){
				keyboard.next();
				System.out.println("Please enter a number: ");
			}
			characterChoose = keyboard.nextInt();

			if (characterChoose == 1 || characterChoose == 2 || characterChoose == 3 || characterChoose == 4 || characterChoose == 5 || characterChoose == 6) {
				keepTrying = false;
			}
			else {
				System.out.println("please enter a valid choice.");
			}
		
		}
		return characterChoose;
		
	}
	
	/**
	 * tryTheDoor is used when the player tries to use the door in a room
	 * this is probably fine
	 */
	public void tryTheDoor() {
		if (player.hasItem(currentRoom.getDoorVariable())) {
			System.out.println("you may go through the door");
			pickARoom();
		} else {
			System.out.println("you don't have the correct item for that");
		}
	}
	
	/**
	 * tryThePuzzle is used when the players tries to do the puzzle in the room.
	 *some of the logic here, like the updating health, may need to be moved out to a logic class.
	 */
	public void tryThePuzzle() {
		if (currentRoom.isNemesisRoom()) {
			System.out.println("A NEMESIS APPEARS");
			System.out.println("The nemesis asks you a question");
		}
		System.out.println(currentRoom.getChallenge().getQuestion());
		System.out.print("Enter your answer: ");
		String answer = chooseStringOption();
		if (!(currentRoom.getChallenge().checkValidInput(answer))){
			System.out.println("please enter a valid one word answer, with no spaces.");
			answer = chooseStringOption();
		}
		boolean correctAnswer = currentRoom.getChallenge().verifyAnswer(answer);
		if (correctAnswer) {
			System.out.println("Correct answer! you may go through the door.");
			pickARoom();
		}
		//maybe a little too much logic here.
		else if (!correctAnswer) {
			System.out.println("Incorrect answer! you lost some health.");
			if (currentRoom.isNemesisRoom()) {
				player.updateHealth(2);
				System.out.println("your current health:" + player.getHealth());
				if (!player.isAlive()) {
					System.out.print("YOU DIED");
					System.exit(0);
				}
			}
			else {
				player.updateHealth(1);
				System.out.println("your current health:" + player.getHealth());
				if (!player.isAlive()) {
					System.out.print("YOU DIED");
					System.exit(0);
				}
			}
		}
	}
	
	/**
	 * pickARoom is used when the player completes a room and must choose which door to go through.
	 * logic here might need to be moved.
	 */
	public void pickARoom() {
		exitMessage(counter);
		int roomChoice;
			roomChoice = chooseIntOption();
			while(roomChoice <= 0 || roomChoice > numberOfDoors){
				System.out.println("please enter a valid choice.");
				roomChoice = chooseIntOption();
			}
			//logic like this is used for text and gui, put in logic class of some sort.
			if((counter == 4 || counter == 5) && roomChoice == 2) {
			nemesisAppears = true;
			} else {
			nemesisAppears = false;
			}
			currentRoom = new Room(difficulty, nemesisAppears);
			counter ++;
			numberOfDoors = entryMessage(counter);
		
	}
	
	/**
	 * chooseIntOption is used when we need the player to enter an integer as input.
	 * @return
	 */
	public int chooseIntOption() {
		Scanner keyboard1 = new Scanner(System.in);
		
		while (!keyboard1.hasNextInt()){
				keyboard1.next();
				System.out.println("Please enter a number: ");
			}
		
		int pickedOption = keyboard1.nextInt();
		return pickedOption;
		
	}
	
	/**
	 * chooseStringOption is used when we need the player to enter a string as input.
	 * @return
	 */
	public String chooseStringOption() {
		Scanner keyboard1 = new Scanner(System.in);
		
		while (!keyboard1.hasNextLine()){
				keyboard1.next();
				System.out.println("Please enter a string: ");
			}
		
		String pickedOption = keyboard1.nextLine();		
		return pickedOption;
		
	}
	
	/**
	 * entryMessage is used to display an entry message when the player enters a new room.
	 * the purpose of the counter is to keep track of which room they are in.
	 * I think this is fine since it just does print statements.
	 * @param counter
	 * @return
	 */
	public int entryMessage(int counter) {
		if(counter == 1){
			numberOfDoors = 2;
			System.out.println("You step into the first room. You see two doors, which appear to have to same lock, in front of you, as well as a button that says 'puzzle' on it");
		} else if(counter == 2) {
			numberOfDoors = 3;
			System.out.println("You step into the second room. You see three doors, which appear to have to same lock, in front of you, as well as a button that says 'puzzle' on it ");
		} else if(counter == 3) {
			numberOfDoors = 1;
			System.out.println("You step into the third room. You see a door, as well as a button that says 'puzzle' on it ");
		} else if(counter == 4) {
			numberOfDoors = 3;
			System.out.println("You step into the fourth room. You see three doors, which appear to have to same lock, in front of you, as well as a button that says 'puzzle' on it  ");
		} else if(counter == 5) {
			numberOfDoors = 2;
			System.out.println("You step into the fifth room. You see two doors, which appear to have to same lock, in front of you, as well as a button that says 'puzzle' on it ");
		} else if(counter == 6) {
			numberOfDoors = 1;
			System.out.println("You step into the sixth room. You see one very nice looking door, as well as a button that says 'puzzle' on it   ");
		}
		return numberOfDoors;
	}
	
	/**
	 * exitMessage is used to display a message whenever the user completes the challenge in a
	 * room and we need them to pick which door they would like to go through. the purpose
	 * of the counter is to keep track of which of the 6 rooms they are in.
	 * I think this is probably fine, since its just print statements and no logic.
	 * @param counter
	 * @return
	 */
	public void exitMessage(int counter) {
		if(counter == 1){
			System.out.println("Do you want to go through the first door or the second door?");
			System.out.println("Choice: ");
		} else if(counter == 2) {
			System.out.println("Do you want to go through the first, second, or third door?");
			System.out.println("Choice: ");
		} else if(counter == 3) {
			System.out.println("Do you want to go through the first door?");
			System.out.println("Choice: ");
		} else if(counter == 4) {
			System.out.println ("You see a red glow coming from the second door.");
			System.out.println("Do you want to go through the first, second, or third door?");
			System.out.println("Choice: ");
		} else if(counter == 5) {
			System.out.println ("You see a red glow coming from the second door.");
			System.out.println("Do you want to go through the first door or the second door?");
			System.out.println("Choice: ");
		} else if(counter == 6) {
			System.out.println("Do you want to go through the very nice looking door?");
			System.out.println("Choice: ");
		}
	}
	
	/**
	 * main method.
	 * @param args
	 */
	public static void main (String[] args) {
		textInterface ti1 = new textInterface();
		ti1.play();
	}
}
