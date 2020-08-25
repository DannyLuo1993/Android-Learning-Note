package Exception;

public class NotDeveloperMemberException extends IllegalArgumentException{

    public NotDeveloperMemberException() {
    }

    public NotDeveloperMemberException(String s) {
        super(s);
    }
}
