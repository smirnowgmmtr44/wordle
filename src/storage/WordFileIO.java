package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

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
			String line;
			while((line=bw.readLine())!=null){
				if(line.equals(word)){
					return true;
				}
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return false;
	}
	
	public int getWordsCount(){
		int count = 0;
		try(BufferedReader bw = new BufferedReader(new FileReader(this.fileName))){
			String line;
			while((line=bw.readLine())!=null){
				count++;
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
			String line;
			while((line=bw.readLine())!=null){
				if(id == position){
					return line;
				}
				position++;
			}
		}
		catch(IOException e){
				System.out.println(e.getMessage());
		}
		return "";
	}
	
	public String getRandomWord(){
		Random random =  new Random();
		int index = random.nextInt(getWordsCount());
		return getWordById(index);
	}
}