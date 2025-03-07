package logic;

import java.util.*;
import java.lang.*;
import java.util.regex.*;
import storage.WordIOInterface;
import storage.WordFileIO;

public class WordleLogic{
	
	WordIOInterface storage;
	List<Character> used;			//коллекция букв которые используюся в загаданом слове
	List<Character> notUsed;		//коллекция букв которые не используются в загаданом слове 
	List<Character> onPosition;		//коллекция которая указывает позицию верно расположенных букв в загаданом слове
	String targetWord;				
	int countOfTry;				
	Pattern pattern;				
	
	public WordleLogic(){
		countOfTry = 0;
		storage = new WordFileIO();
		used = new LinkedList<Character>();
		notUsed = new LinkedList<Character>();
		onPosition = new LinkedList<Character>();
		targetWord = "";
		pattern = Pattern.compile("[a-zA-Z]{5}", Pattern.CASE_INSENSITIVE);
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
		return targetWord;
	}
	
	//проверка на слово из 5 латинских букв
	public boolean isWord(String word){
		Matcher matcher = pattern.matcher(word);
		return word.length() == 5 && matcher.find();
	}
	
	//проверка существует ли в хранилище слово
	public boolean isWordExist(String word){
		return isWord(word) &&  storage.search(word);
	}
	
	//	Метод возвращает true если слово совпадает с загаданным, иначе возвращает false
	public boolean check(String word){
		countOfTry--;
		onPosition.clear();
		if(targetWord.equals(word)){
			return true;
		} else{
			
			for(int i = 0;i < word.length();i++ ){
				if(targetWord.indexOf(word.charAt(i))!=-1){
					
					if(used.indexOf(word.charAt(i))==-1){
						used.add(word.charAt(i));	
					}
					
					if(word.charAt(i)==targetWord.charAt(i)){
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
		}
		return false;
	}
	
	//загадать новое слово
	public void start(int countOfTry){
		this.countOfTry = countOfTry;
		targetWord = randomWord();
		used.clear();
		notUsed.clear();
		onPosition.clear();
	}
	
	//выбор случайного слова из хранилища
	public String randomWord(){
		Random random =  new Random();
		int index = random.nextInt(storage.getWordsCount());
		return storage.getWordById(index);
	}
	
}