package exception;

public class MemberNotFoundException extends IllegalArgumentException{

    public MemberNotFoundException() {
    }

    public MemberNotFoundException(String s) {
        super(s);
    }
}