package Application.Generator.Exception;

public class GrammarException extends Throwable {
    public GrammarException(String message) {
        super(message);
    }

    public GrammarException(Throwable e) {
        super(e);
    }
}
