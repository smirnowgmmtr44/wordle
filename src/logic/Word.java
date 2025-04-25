package logic;

import java.util.List;
import java.util.ArrayList;

public class Word{
	
	List<Letter> letters;
	
	public Word(String word,LetterStatus status){
		this();
		for(char c : word.toCharArray()){
			letters.add(new Letter(c,status));
		}
	}
	
	public Word(){
		letters = new ArrayList<>();
	}
	
	public void add(Letter letter){
		letters.add(letter);
	}
	
	public List<Letter> getLetters(){
		return letters;
	}
}