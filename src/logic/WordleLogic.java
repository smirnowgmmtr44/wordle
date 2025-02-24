package out.logic;

import java.util.*;
import java.lang.*;
import java.util.regex.*;
import out.storage.WordIOInterface;
import out.storage.WordFileIO;

public class WordleLogic{
	
	WordIOInterface storage;		//хранение
	List<Character> used;			//коллекция букв которые используюся в загаданом слове
	List<Character> notUsed;		//коллекция букв которые не используются в загаданом слове 
	List<Character> onPosition;		//коллекция которая указывает позицию верно расположенных букв в загаданом слове
	String targetWord;				//загаданое слово
	int countOfTry;				//количество попыток
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
<<<<<<< HEAD
=======
>>>>>>> 12851a7 (Логика вынеселна из класса ConsoleView в класс WorldeLogic)
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
	
	//загадать новое слово
	public void start(int countOfTry){
		this.countOfTry = countOfTry;
		targetWord = randomWord();
		used.clear();
		notUsed.clear();
		onPosition.clear();
<<<<<<< HEAD
=======
>>>>>>> 12851a7 (Логика вынеселна из класса ConsoleView в класс WorldeLogic)
	}
	
	//выбор случайного слова из хранилища
	public String randomWord(){
		Random random =  new Random();
		int index = random.nextInt(storage.getWordsCount());
		return storage.getWordById(index);
	}
	
}