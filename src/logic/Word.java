package logic;

import java.util.List;
import java.util.ArrayList;

public class Word{
	
	private List<Letter> letters;
	
	public Word(){
		letters = new ArrayList<>();
	}
	/**
	* Возващает слово у которого все буквы в определенном статусе
	*
	* @param word Слово
	* @param status Статус который будет указан у всех букв в слове
	* @return Возвращает список букв в определенном статусе
	*/
	public static List<Letter> GetWordWithAllLettersInStatus(String word, LetterStatus status){
		List<Letter> letters = new ArrayList<>();
		for(char c : word.toCharArray()){
			letters.add(new Letter(c,status));
		}
		return letters;
	}
	
	public void add(Letter letter){
		letters.add(letter);
	}
	
	public List<Letter> getLetters(){
		return letters;
	}
}