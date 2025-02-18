package out;

import out.view.*;

public class WordleApp{
	public static void main(String args[]){
		start();
	}
	static void start(){
		ConsoleView view = new ConsoleView();
		//System.out.println(view.menu());
		view.menu();
	}
}