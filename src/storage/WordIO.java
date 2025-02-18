package out.storage;

import java.io.*;
import java.util.*;

public class WordIO implements WordIOInterface{
	
	String fileName;
	
	public WordIO(String fileName){
		this.fileName = fileName;
	}
	
	public boolean searchInFile(String word){
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
		if(!searchInFile(word)){
			
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
	public List<String> getWords(){
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
	public String randomWord(){
		Random random =  new Random();
		int index = random.nextInt(getWordsCount());
		System.out.println(index);
		int position = 0;
		try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String s;
			while((s=bw.readLine())!=null){
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter("\n\t,.;");
				while(scanner.hasNext()){
					String next = scanner.next();
					if(index == position){
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