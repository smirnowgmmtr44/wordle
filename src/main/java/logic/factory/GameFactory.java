package logic.factory;

import logic.model.Game;
import logic.storage.WordStorage;

public class GameFactory {

    private WordStorage storage;

    public GameFactory(WordStorage storage) {
        this.storage = storage;
    }

    public Game createGame(int rounds) {
      return new Game(storage, rounds);
    }
}
