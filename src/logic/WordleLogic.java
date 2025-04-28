package logic;

import java.util.*;
import java.util.regex.Pattern;
import storage.WordIOInterface;
import storage.WordFileIO;

public class WordleLogic{
	
	private WordIOInterface storage;	
	private int countOfTry = 6;		

	private final String targetWord;		
	private final int worldLength = 5;
	private final Pattern pattern = Pattern.compile("[a-zA-Z]{"+worldLength+"}", Pattern.CASE_INSENSITIVE);	
	
	public WordleLogic(){
		storage = new WordFileIO();
		targetWord = storage.getRandomWord();
	}
	public WordleLogic(int rounds){
		this();
		countOfTry = rounds;
	}
	
	public int getCountOfTry(){
		return countOfTry;
	}
	public String getTargetWord(){
		return targetWord;
	}
	
	/**
	* Метод для проверки на слово из 5 латинских букв
	*/
	public boolean isWord(String word){
		return word.length() == worldLength && pattern.matcher(word).find();
	}
	
	/**
	* Метод для проверки существует ли в хранилище слово
	*/
	public boolean isWordExist(String word){
		return isWord(word) &&  storage.search(word);
	}
	
	public boolean isWordNotExist(String word){
		return !isWordExist(word);
	}
	
	/**	
	* Метод для анализа букв в слове
	*
	* @param word Введенное пользователем слово 
	* @return Метод возвращает true если слово совпадает с загаданным, иначе возвращает false
	*/
	public List<Letter> wordAnalysis(String word){
		countOfTry--;
		Word result = new Word();
		if(targetWord.equals(word)){
			return  Word.GetWordWithAllLettersInStatus(word,LetterStatus.IN_POSITION);
		} 
		for(int i = 0;i < word.length();i++ ){
			if(targetWord.indexOf(word.charAt(i))!=-1){
				
				if(word.charAt(i)==targetWord.charAt(i)){
					result.add(new Letter(word.charAt(i),LetterStatus.IN_POSITION));
				} else {
					result.add(new Letter(word.charAt(i),LetterStatus.USED));
				}
				
			} else{
				
				result.add(new Letter(word.charAt(i),LetterStatus.NOT_USED));
				
			}
		}
		return result.getLetters();
	}
	
	public boolean isGameActive(){
		return countOfTry != 0;
	}
	
}