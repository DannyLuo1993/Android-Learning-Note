package Exception;

public class MemberQuantityLimitationException extends IllegalArgumentException{

    public MemberQuantityLimitationException() {
    }

    public MemberQuantityLimitationException(String message) {
        super(message);
    }

}
