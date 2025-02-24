package out.storage;

import java.util.*;

public interface WordIOInterface{
	
	public boolean search(String word);
	public boolean addWord(String word);
	public List<String> getAllWords();
	public int getWordsCount();
	public String getWordById(int id);
	
}