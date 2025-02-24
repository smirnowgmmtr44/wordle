package out.view;

import java.util.*;
import out.storage.WordIO;
import out.storage.WordIOInterface;

public class ConsoleView{
	static WordIOInterface wordIO;
	static String fileName;
	static{
		fileName = "dictionary.txt";
		wordIO = new WordIO(fileName);
	}
	public void menu(){
		System.out.println("Главное меню:");
	/*	WordIOInterface wordIO = new WordIO();
		System.out.println(wordIO.addWord("ABC", "dictionary.txt"));
		System.out.println(wordIO.searchInFiles("ABC", "dictionary.txt"));*/
		int choice =0;
		String word="";
		
		//logic
		
		//
		do{
			menuText();
			Scanner scanner = new Scanner(System.in);
			if(scanner.hasNext()){
				choice = scanner.nextInt();
			}
		//	return choice;
			switch(choice){
				case 0:
					System.out.println("Exiting...");
					System.out.println();
					break;
				case 1:	
					System.out.println("Type word:");
					scanner = new Scanner(System.in);
					word = scanner.next();
					if(!wordIO.addWord(word)){
						System.out.println("! Word already exist");
					}
					System.out.println();
					break;
				case 2:	
					System.out.println("Type word:");
					scanner = new Scanner(System.in);
					word = scanner.next();
					if(wordIO.search(word)){
						System.out.println("Word exist");
					} else{
						System.out.println("! Word not found");
					}
					System.out.println();
					break;
				case 3:	
					System.out.println("all words:");
					printWords(wordIO.getWords());
					System.out.println();
					break;
				case 4:	
					System.out.println("Start game!");
					start();
					break;
			}
		}while(choice != 0);
	}
	
	private void menuText(){
		System.out.println("0.	Exit");
		System.out.println("1.	Add new word");
		System.out.println("2.	Search word");
		System.out.println("3.	Print all words");
		System.out.println("4.	Play Wordle");
	}
	
	static void printWords(List<String> words){
		for(String w : words){
			System.out.println("\t"+w);
		}
	}
	static boolean check(String word,String targetWord){
		String used="";
		String notUsed="";
		String onPosition="";
		
		if(targetWord.equals(word)){
			return true;
		} else{
			
			for(int i = 0;i < word.length();i++ ){
				if(targetWord.indexOf(word.charAt(i))!=-1){
					
					if(used.indexOf(word.charAt(i))==-1){ //добавление слова в список букв которые вхоядт в слово
						used += word.charAt(i);	
					}
					
					if(word.charAt(i)==targetWord.charAt(i)){ // добавление буквы на свою позицию или * если не совпадает
						onPosition += word.charAt(i);
					} else{
						onPosition += "*";
					}
					
				} else{
					notUsed += word.charAt(i);// добавление отсутсвующей буквы  в список
					onPosition += "*";
				}
			}
		}
		
		System.out.println("Used letters: "+used);
		System.out.println("Not used letters: "+notUsed);
		System.out.println("Letters in right position: "+onPosition);
		return false;
	}
	static void start(){
		String targetWord = wordIO.randomWord();
		System.out.println("Target word: "+targetWord);
		String choice = "";
		int count = 5;
		do{
			System.out.println("Try to guess the word:");
			Scanner scanner = new Scanner(System.in);
			if(scanner.hasNext()){
				choice = scanner.next();
				if(!wordIO.search(choice)){
					System.out.println("This word dont exist");
				} else {
					if(check(choice,targetWord)){
						System.out.println("Yay!");
						break;
					}
					count--;
				}
			}
		
		}while(count != 0);
	}
}