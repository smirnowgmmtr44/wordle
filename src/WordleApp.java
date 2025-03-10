import view.*;

public class WordleApp{
	public static void main(String args[]){
		start();
	}
	static void start(){
		ConsoleView view = new ConsoleView();
		view.menu();
	}
}