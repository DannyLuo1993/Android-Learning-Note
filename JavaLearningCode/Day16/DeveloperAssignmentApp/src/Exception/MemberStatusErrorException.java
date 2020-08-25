package Exception;

public class MemberStatusErrorException extends RuntimeException{

    public MemberStatusErrorException() {
    }

    public MemberStatusErrorException(String message) {
        super(message);
    }
}
