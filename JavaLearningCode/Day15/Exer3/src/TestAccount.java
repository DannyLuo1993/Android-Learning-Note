public class TestAccount {

    public static void main(String[] args){
        Account account = new Account(1122,20000,0.45);
        try{
            account.balanceWithdraw(30000);
            account.balanceWithdraw(2500);
            account.balanceDeposite(3000);
        } catch (AccountException e){
            e.printStackTrace();
        }
    }
}
