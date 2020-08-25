package Exception;

public class MemberRepeatAdditionException extends IllegalArgumentException{

    public MemberRepeatAdditionException() {
    }

    public MemberRepeatAdditionException(String s) {
        super(s);
    }
}
