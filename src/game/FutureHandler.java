package game;

import entity.Cat;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureHandler {

    public static int counter = 0;
    public Cat cat;
    public Future future;

    public FutureHandler(Future<String> future, Cat cat) {
        this.future = future;
        counter++;
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Kot numer " + counter;
    }

    public String getState() {
        if (future.isCancelled()) {
            return "zcancelowali mnie";
        } else if (future.isDone()) {
            return "slay";
        } else {
            return "spadam";
        }
    }

    public String getResult() {
        if (future.isDone()) {
            try {
                return (String) future.get();
            } catch (InterruptedException | ExecutionException | CancellationException exception) {
                return "nie ma mnie";
            }
        } else {
            return "dalej lece";
        }
    }

}
