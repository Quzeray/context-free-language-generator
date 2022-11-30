package Application.Generator.Exception;

public class GeneratorException extends Throwable{

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(Throwable e) {
        super(e);
    }
}
