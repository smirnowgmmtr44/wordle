package logic;

public class Letter{
	
	private LetterStatus status;
	private char letter;
	
	public Letter(char letter ,LetterStatus status){
		this.letter = letter;
		this.status = status;
	}
	public Letter(char letter){
		this.letter = letter;
		this.status = LetterStatus.NOT_USED;
	}
	
	public LetterStatus getStatus(){
		return status;
	}
	
	public char getLetter(){
		return letter;
	}
}