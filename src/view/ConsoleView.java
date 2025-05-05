package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

import logic.WordleLogic;
import logic.Letter;
import logic.Word;
import logic.LetterStatus;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class ConsoleView {

    private List<Character> used;           //коллекция букв которые используюся в загаданом слове
    private List<Character> notUsed;        //коллекция букв которые не используются в загаданом слове
    private List<Character> inPosition;     //коллекция которая указывает позицию верно расположенных букв в загаданом слове
    private static final int ROUNDS = 5;

    public ConsoleView() {
        used = new LinkedList<Character>();
        notUsed = new LinkedList<Character>();
        inPosition = new LinkedList<Character>();
    }

    private void listsClear() {
        used.clear();
        notUsed.clear();
        inPosition.clear();
    }

    public void menu() {
        int choice = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            AnsiConsole.systemInstall();
            do {
                menuText();
                if (scanner.hasNext()) {
                    try {
                        choice = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        choice = -1;
                    }
                }
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        System.out.println();
                        break;
                    case 1:
                        System.out.println("Start game!");
                        listsClear();
                        start(scanner, ROUNDS);
                        break;
                    default:
                        System.out.println("Type number 0-1");
                        break;
                }
            } while (choice != 0);
        } finally {
            AnsiConsole.systemUninstall();
        }

    }

    private void menuText() {
        System.out.println(ansi().eraseScreen());
        System.out.println("Main menu:");
        System.out.println("0.	Exit");
        System.out.println("1.	Play Wordle");

    }

    void printList(List<Character> list) {
        for (Character c : list) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    void printAttempts(List<Word> list) {
        //System.out.println(ansi().eraseScreen());
        System.out.print("Used letters: \u001B[33m");
        printList(used);
        System.out.print("\u001B[0m");
        System.out.print("Not used letters: ");
        printList(notUsed);
        System.out.println("-----");
        for (Word word : list) {
            for (Letter l : word.getLetters()) {
                switch (l.getStatus()) {
                    case LetterStatus.IN_POSITION:
                        System.out.print("\u001B[32m[" + l.getLetter() + "]\u001B[0m");
                        break;
                    case LetterStatus.USED:
                        System.out.print("\u001B[33m[" + l.getLetter() + "]\u001B[0m");
                        break;
                    case LetterStatus.NOT_USED:
                        System.out.print("[" + l.getLetter() + "]");
                        break;
                    default:
                        System.out.print("[ ]");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    void start(Scanner scanner, int rounds) {
        WordleLogic logic = new WordleLogic(rounds);
        String choice;
        System.out.println(ansi().eraseScreen());
        System.out.println("Target word is: " + logic.getTargetWord());
        do {
            inPosition.clear();
            System.out.println("Attempts: " + logic.getAttemptsLeft() + "/" + logic.getCountOfTry());
            System.out.println("Try to guess the word:");

            if (scanner.hasNext()) {
                choice = scanner.next().toLowerCase();
                System.out.println(ansi().eraseScreen());
                if (logic.isWordNotExist(choice)) {
                    System.out.print("\u001B[31m");
                    System.out.println("This word dont exist");
                    System.out.println("The word consists of 5 Latin letters!!!");
                    System.out.print("\u001B[0m");
                } else {

                    for (Letter l : logic.wordAnalysis(choice)) {
                        if (LetterStatus.IN_POSITION.equals(l.getStatus())) {
                            inPosition.add(l.getLetter());
                        } else {
                            inPosition.add('_');
                        }
                        if ((LetterStatus.IN_POSITION.equals(l.getStatus()) || LetterStatus.USED.equals(l.getStatus())) && used.indexOf(l.getLetter()) == -1) {
                            used.add(l.getLetter());
                        }
                        if (LetterStatus.NOT_USED.equals(l.getStatus()) && notUsed.indexOf(l.getLetter()) == -1) {
                            notUsed.add(l.getLetter());
                        }
                    }

                }
                printAttempts(logic.getAttempts());
            }

        } while (logic.isGameActive());

        System.out.println("!!! GAME OVER !!!");
        System.out.println("Target word is: " + logic.getTargetWord());
        System.out.println("Press Enter to go to the menu");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}