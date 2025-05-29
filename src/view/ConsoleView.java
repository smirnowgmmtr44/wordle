package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import logic.enums.LetterStatus;
import logic.model.Letter;
import logic.model.Attempt;
import logic.model.Game;
import org.fusesource.jansi.AnsiConsole;
import storage.FileWordStorage;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleView {

    private static final int ROUNDS = 5;

    public ConsoleView() {
    }

    public void menu() throws IOException {
        String choice = "";
        try (Scanner scanner = new Scanner(System.in)) {
            AnsiConsole.systemInstall();
            do {
                menuText();
                if (scanner.hasNext()) {
                    choice = scanner.next().trim();
                }
                switch (choice) {
                    case "0":
                        System.out.println("Exiting...");
                        System.out.println();
                        break;
                    case "1":
                        System.out.println("Starting game...");
                        start(scanner, ROUNDS);
                        break;
                    default:
                        System.out.println("Type number 0-1");
                        break;
                }
            } while (!choice.equals("0"));
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

    private void printErrorMessage(String message) {
        System.out.print(Colors.RED.getCode());
        System.out.println(message);
        System.out.print(Colors.WHITE.getCode());
    }

    private void printGameStatus(List<Attempt> attempts) {
        List<Character> used = new ArrayList<>(); //коллекция букв которые используюся в загаданом слове
        List<Character> notUsed = new ArrayList<>(); //коллекция букв которые не используются в загаданом слове

        for (Attempt attempt : attempts) {
            for (Letter l : attempt.getLetters()) {
                if ((LetterStatus.IN_POSITION.equals(l.getStatus()) || LetterStatus.USED.equals(l.getStatus())) && !used.contains(l.getLetter())) {
                    used.add(l.getLetter());
                }
                if (LetterStatus.NOT_USED.equals(l.getStatus()) && !notUsed.contains(l.getLetter())) {
                    notUsed.add(l.getLetter());
                }
            }
        }
        System.out.print("Used letters: " + Colors.YELLOW.getCode());
        printList(used);
        System.out.print(Colors.WHITE.getCode());
        System.out.print("Not used letters: ");
        printList(notUsed);
        System.out.println("-----");
        for (Attempt word : attempts) {
            for (Letter l : word.getLetters()) {
                switch (l.getStatus()) {
                    case IN_POSITION:
                        System.out.print(Colors.GREEN.getCode() + "[" + l.getLetter() + "]" + Colors.WHITE.getCode());
                        break;
                    case USED:
                        System.out.print(Colors.YELLOW.getCode() + "[" + l.getLetter() + "]" + Colors.WHITE.getCode());
                        break;
                    case NOT_USED:
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

    void start(Scanner scanner, int rounds) throws IOException {

        Game game = new Game(new FileWordStorage(), rounds);
        if (game.isNotValid()) {
            printErrorMessage("Error! Can't start game.");
            System.out.println("Press Enter to go to the menu");
            System.in.read();
            return;
        }
        String choice;
        System.out.println(ansi().eraseScreen());
        do {
            System.out.println("Attempts: " + game.getAttemptsLeft() + "/" + game.getCountOfTry());
            System.out.println("Try to guess the word:");

            if (scanner.hasNext()) {
                choice = scanner.next().toLowerCase();
                System.out.println(ansi().eraseScreen());

                if (game.isWordExist(choice)) {
                    game.createAttempt(choice);
                } else {
                    printErrorMessage("! Word not found in storage. The word contains 5 latin letters.");
                }

                printGameStatus(game.getAttempts());
            }

        } while (game.isGameActive());

        String endgameText;
        if (game.isWin()) {
            endgameText = "!!! CONGRATULATIONS YOU WON !!!";
        } else {
            endgameText = "!!! GAME OVER !!!";
        }
        System.out.println(endgameText);
        System.out.println("Target word is: " + game.getTargetWord());
        System.out.println("Press Enter to go to the menu");
        System.in.read();


    }
}