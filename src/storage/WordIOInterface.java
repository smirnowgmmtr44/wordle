package storage;

public interface WordIOInterface{
	
	/**
	* Этот метод ищет слово в хранилище
	* возвращает true если слово найдено, и false если не найдено
	*/
	public boolean search(String word);

	/**
	* Этот метод возвращает количество слов в хранилище
	*/
	public int getWordsCount();
	
	/**
	* Этот метод возвращает слово из хранилища по его id
	*/
	public String getWordById(int id);
	
	/**
	* Этот метод возвращает случайное слово из хранилища
	*/
	public String getRandomWord();
	
}