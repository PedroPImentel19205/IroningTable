package Db;

public class IntegrityException extends RuntimeException {
    public IntegrityException(String msg) {
        super(msg);
    }
}
