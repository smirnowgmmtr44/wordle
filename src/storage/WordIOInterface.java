package storage;

import java.util.*;

public interface WordIOInterface{
	
	/**
	* Этот метод ищет слово в хранилище
	* возвращает true если слово найдено, и false если не найдено
	*/
	public boolean search(String word);
	
	/**
	* Этот метод добавляет слово в хранилище
	*/
	public boolean addWord(String word);
	
	/**
	* Этот метод возвращает все слова из хранилища
	*/
	public List<String> getAllWords();
	
	/**
	* Этот метод возвращает количество слов в хранилище
	*/
	public int getWordsCount();
	
	/**
	* Этот метод возвращает слово из хранилища по его id
	*/
	public String getWordById(int id);
	
}