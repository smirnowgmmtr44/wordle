package out.storage;

import java.util.*;

public interface WordIOInterface{
	
	public boolean searchInFile(String word);
	public boolean addWord(String word);
	public List<String> getWords();
	public String randomWord();
	
}