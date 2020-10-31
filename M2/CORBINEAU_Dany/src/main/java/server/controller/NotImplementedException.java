package server.controller;

public class NotImplementedException extends Exception {
    private final String message;
    public NotImplementedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
