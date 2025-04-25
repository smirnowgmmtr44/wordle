package logic;

public class Letter{
	
	LetterStatus status;
	char letter;
	
	public Letter(char letter ,LetterStatus status){
		this.letter = letter;
		this.status = status;
	}
	public Letter(char letter){
		this.letter = letter;
		this.status = LetterStatus.NOTUSED;
	}
	
	public LetterStatus getStatus(){
		return status;
	}
	
	public char getLetter(){
		return letter;
	}
}