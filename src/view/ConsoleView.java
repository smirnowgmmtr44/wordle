package view;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import logic.WordleLogic;
import logic.Letter;
import logic.LetterStatus;

public class ConsoleView{
	
	private List<Character> used;			//коллекция букв которые используюся в загаданом слове
	private List<Character> notUsed;		//коллекция букв которые не используются в загаданом слове 
	private List<Character> inPosition;		//коллекция которая указывает позицию верно расположенных букв в загаданом слове		
	private static final int ROUNDS = 5;
	
	public ConsoleView(){
		used = new LinkedList<Character>();
		notUsed = new LinkedList<Character>();
		inPosition = new LinkedList<Character>();
	}
	private void listsClear(){
		used.clear();
		notUsed.clear();
		inPosition.clear();
	}
	
	public void menu(){
		int choice = 0;
		try(Scanner scanner = new Scanner(System.in)){
			do{
				menuText();
				if(scanner.hasNext()){
					try{
						choice = Integer.parseInt(scanner.next());
					} catch(NumberFormatException e) {
						choice = -1;
					}
				}
				switch(choice){
					case 0:
						System.out.println("Exiting...");
						System.out.println();
						break;
					case 1:	
						System.out.println("Start game!");
						listsClear();
						start(scanner,ROUNDS);
						break;
					default:
						System.out.println("Type number 0-1");
						break;
				}
			}while(choice != 0);
		}
		
	}
	
	private void menuText(){
		System.out.println("Main menu:");
		System.out.println("0.	Exit");
		System.out.println("1.	Play Wordle");
	}
	
	static void printList(List<Character> list){
		for(Character c : list){
			System.out.print(c+" ");
		}
		System.out.println();
	}

	void start(Scanner scanner,int rounds){ 
		WordleLogic logic = new WordleLogic(rounds);
		String choice;
		do{
			inPosition.clear();
			System.out.println("---------");
			System.out.println("Аttempts left: "+logic.getCountOfTry());
			System.out.println("---------");
			System.out.println("Try to guess the word:");
			if(scanner.hasNext()){
				choice = scanner.next().toLowerCase();
				if(logic.isWordNotExist(choice)){ 
					System.out.println("This word dont exist");
					System.out.println("The word consists of 5 Latin letters!!!");
				} else {
					
					for(Letter l : logic.wordAnalysis(choice)){
						if(LetterStatus.IN_POSITION.equals(l.getStatus())){
							inPosition.add(l.getLetter());
						} else {
							inPosition.add('_');
						}
						if(LetterStatus.USED.equals(l.getStatus()) && used.indexOf(l.getLetter())==-1){
							used.add(l.getLetter());
						}
						if(LetterStatus.NOT_USED.equals(l.getStatus()) && notUsed.indexOf(l.getLetter())==-1){
							notUsed.add(l.getLetter());
						}
					}
					System.out.print("Character already in position in word: ");
					printList(inPosition);
					System.out.print("Character used in word: ");
					printList(used);
					System.out.print("Character not used in word: ");
					printList(notUsed);
				}
				System.out.println("---------");
			}
			
		}while(logic.isGameActive());
		
		System.out.println("!!! GAME OVER !!!");
		System.out.println("Target word is: "+logic.getTargetWord());
		System.out.println("---------");
	}
}