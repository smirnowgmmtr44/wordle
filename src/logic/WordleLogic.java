package out.logic;

import java.util.*;
import java.lang.*;
import java.util.regex.*;

public class WordleLogic{
	
	List<Character> used;
	List<Character> notUsed;
	List<Character> onPosition;
	String targetWord;
	Pattern pattern;
	int currentRound;
	
	public WordleLogic(){
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
	
	//проверка на слово из 5 латинских букв
	public boolean isWord(String word){
		Matcher matcher = pattern.matcher(word);
		return matcher.find();
	}
	
	//проверка существует ли в списке слово
	public boolean isWordExist(String word){
		return isWord(word);
	}
	
	// результаты раунда 
	public boolean check(String word){
		currentRound++;
		onPosition.clear();
		if(targetWord.equals(word)){
			return true;
		} else{
			
			for(int i = 0;i < word.length();i++ ){
				if(targetWord.indexOf(word.charAt(i))!=-1){
					
					if(used.indexOf(word.charAt(i))==-1){ //добавление слова в список букв которые вхоядт в слово
						used.add(word.charAt(i));	
					}
					
					if(word.charAt(i)==targetWord.charAt(i)){ // добавление буквы на свою позицию или * если не совпадает
						onPosition.add(word.charAt(i));
					} else{
						onPosition.add('*');
					}
					
				} else{
					
					if(notUsed.indexOf(word.charAt(i))==-1){ // добавление отсутсвующей буквы  в список
						notUsed.add(word.charAt(i));
					}	
					onPosition.add('*');
				}
			}
		}
		return false;
	}
	
	//запуск игры
	public void start(){
		currentRound = 1;
		targetWord = randomWord();
		//System.out.println("// "+view.menu());
	}
	
	//выбор случайного слова для раунда
	public String randomWord(){
		return " ";
	}
	
}