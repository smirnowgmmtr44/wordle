package logic;

import java.util.*;
import java.util.regex.Pattern;
import storage.WordIOInterface;
import storage.WordFileIO;

public class WordleLogic{
	
	private WordIOInterface storage;
	private List<Character> used;			//коллекция букв которые используюся в загаданом слове
	private List<Character> notUsed;		//коллекция букв которые не используются в загаданом слове 
	private List<Character> onPosition;		//коллекция которая указывает позицию верно расположенных букв в загаданом слове			
	private int countOfTry = 6;		

	private final String TARGET_WORD;		
	private final int WORD_LENGTH = 5;
	private final Pattern PATTERN = Pattern.compile("[a-zA-Z]{"+WORD_LENGTH+"}", Pattern.CASE_INSENSITIVE);	
	
	public WordleLogic(){
		storage = new WordFileIO();
		used = new LinkedList<Character>();
		notUsed = new LinkedList<Character>();
		onPosition = new LinkedList<Character>();
		TARGET_WORD = storage.getRandomWord();
	}
	public WordleLogic(int rounds){
		this();
		countOfTry = rounds;
	}
	
	public List<Character> getUsed(){
		return used;
	}
	public List<Character> getNotUsed(){
		return notUsed;
	}
	public List<Character> getOnPosition(){
		return onPosition;
	}
	public int getCountOfTry(){
		return countOfTry;
	}
	public String getTargetWord(){
		return TARGET_WORD;
	}
	
	//проверка на слово из 5 латинских букв
	public boolean isWord(String word){
		return word.length() == WORD_LENGTH && PATTERN.matcher(word).find();
	}
	
	//проверка существует ли в хранилище слово
	public boolean isWordExist(String word){
		return isWord(word) &&  storage.search(word);
	}
	
	//	Метод возвращает true если слово совпадает с загаданным, иначе возвращает false
	public boolean check(String word){
		countOfTry--;
		onPosition.clear();
		if(TARGET_WORD.equals(word)){
			return true;
		} 
		for(int i = 0;i < word.length();i++ ){
			if(TARGET_WORD.indexOf(word.charAt(i))!=-1){
				
				if(used.indexOf(word.charAt(i))==-1){
					used.add(word.charAt(i));	
				}
				
				if(word.charAt(i)==TARGET_WORD.charAt(i)){
					onPosition.add(word.charAt(i));
				} else{
					onPosition.add('*');
				}
				
			} else{
				
				if(notUsed.indexOf(word.charAt(i))==-1){
					notUsed.add(word.charAt(i));
				}	
				onPosition.add('*');
			}
		}
		return false;
	}
	
}