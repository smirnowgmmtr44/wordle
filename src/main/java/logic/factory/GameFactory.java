package main.java.logic.factory;

import main.java.logic.model.Game;
import main.java.logic.storage.WordStorage;

public class GameFactory {

    private WordStorage storage;

    public GameFactory(WordStorage storage) {
        this.storage = storage;
    }

    public Game createGame(int rounds) {
      return new Game(storage, rounds);
    }
}
