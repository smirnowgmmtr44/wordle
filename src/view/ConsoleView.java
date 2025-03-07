package view;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import logic.WordleLogic;

public class ConsoleView{
	
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
						start(scanner,5);
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
	


	static void start(Scanner scanner,int rounds){ 
		WordleLogic logic = new WordleLogic();
		logic.start(rounds);
		String choice;
		do{
			System.out.println("---------");
			System.out.println("Аttempts left: "+logic.getCountOfTry());
			System.out.println("---------");
			System.out.println("Try to guess the word:");
			if(scanner.hasNext()){
				choice = scanner.next().toLowerCase();
				if(!logic.isWordExist(choice)){
					System.out.println("This word dont exist");
					System.out.println("The word consists of 5 Latin letters!!!");
				} else {
					if(logic.check(choice)){
						System.out.println("---------");
						System.out.println("!!! Сongratulations you won !!!");
						break;
					} else {
						System.out.print("Character already in position in word: ");
						printList(logic.getOnPosition());
						System.out.print("Character used in word: ");
						printList(logic.getUsed());
						System.out.print("Character not used in word: ");
						printList(logic.getNotUsed());
					}
				}
				System.out.println("---------");
			}
			
		}while(logic.getCountOfTry() != 0);
		
		System.out.println("!!! GAME OVER !!!");
		System.out.println("Target word is: "+logic.getTargetWord());
		System.out.println("---------");
	}
}