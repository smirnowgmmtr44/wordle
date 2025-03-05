package out.storage;

import java.io.*;
import java.util.*;

public class WordFileIO implements WordIOInterface{
	
	String fileName;
	
	public WordFileIO(){
		this.fileName = "resources/wordle.txt";
	}
	
	public WordFileIO(String fileName){
		this.fileName = fileName;
	}

	public boolean search(String word){
		 try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String s;
			while((s=bw.readLine())!=null){
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter("\n\t,.;");
				while(scanner.hasNext()){
					if(scanner.next().equals(word)){
						return true;
					}
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean addWord(String word){
		if(!search(word)){
			
			try(FileWriter writer = new FileWriter(this.fileName, true)){
				writer.write(word.toLowerCase()+"\n");
				return true;
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
	
	public List<String> getAllWords(){
		List<String> words = new LinkedList<String>();
		try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String s;
			while((s=bw.readLine())!=null){
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter("\n\t,.;");
				while(scanner.hasNext()){
					words.add(scanner.next());
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return words;
	}
	
	public int getWordsCount(){
		int count = 0;
		try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String s;
			while((s=bw.readLine())!=null){
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter("\n\t,.;");
				while(scanner.hasNext()){
					count++;
					scanner.next();
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return count;
	}
	
	public String getWordById(int id){
		int position = 0;
		try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String s;
			while((s=bw.readLine())!=null){
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter("\n\t,.;");
				while(scanner.hasNext()){
					String next = scanner.next();
					if(id == position){
						return next;
					}
					position++;
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return null;
	}
	
}