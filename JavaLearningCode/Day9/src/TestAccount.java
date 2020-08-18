public class TestAccount {

    public static void main(String[] args) {

        Account account = new Account();
        account.setId(11223344);
        account.setBalance(20000.00);
        account.setRate(0.45);
        account.withdraw();
        account.withdraw();
        account.deposit();
        account.getMonthlyRate();
    }
}


