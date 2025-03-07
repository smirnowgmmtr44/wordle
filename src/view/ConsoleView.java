package view;

import java.util.*;
import logic.WordleLogic;

public class ConsoleView{
	static WordleLogic logic;
	static{
		logic = new WordleLogic();
	}
	public void menu(){
		int choice = 0;
		String word="";
		
		do{
			menuText();
			Scanner scanner = new Scanner(System.in);
			if(scanner.hasNext()){
				try{
					choice = scanner.nextInt();
				} catch(InputMismatchException e) {
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
					start(5);
					break;
				default:
					System.out.println("Type number 0-1");
					break;
			}
		}while(choice != 0);
	}
	
	private void menuText(){
		System.out.println("Main menu:");
		System.out.println("0.	Exit");
		System.out.println("1.	Play Wordle");
	}
	
	static void printWords(List<String> words){
		for(String w : words){
			System.out.println("\t"+w);
		}
	}
	
	static void printList(List<Character> list){
		for(Character c : list){
			System.out.print(c+" ");
		}
		System.out.println();
	}
	


	static void start(int rounds){ 
		logic.start(rounds);
		String choice;
		do{
			System.out.println("---------");
			System.out.println("Аttempts left: "+logic.getCountOfTry());
			System.out.println("---------");
			System.out.println("Try to guess the word:");
			Scanner scanner = new Scanner(System.in);
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