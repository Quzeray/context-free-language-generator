package Language.Exception;

public class LanguageException extends Throwable{

    public LanguageException(String message) {
        super(message);
    }

    public LanguageException(Throwable e) {
        super(e);
    }
}
