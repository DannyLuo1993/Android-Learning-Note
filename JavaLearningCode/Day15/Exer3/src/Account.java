

public class Account {

    private int id;
    private double balance;
    private double annualinterestrate;

    public Account(int id, double balance, double annualinterestrate) {
        this.id = id;
        this.balance = balance;
        this.annualinterestrate = annualinterestrate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualinterestrate() {
        return annualinterestrate;
    }

    public void setAnnualinterestrate(double annualinterestrate) {
        this.annualinterestrate = annualinterestrate;
    }

    public double getMonthlyRate(){
        return annualinterestrate/12;
    }

    public double getMonthInterest(){
        return balance * getMonthlyRate();
    }

    public void balanceWithdraw(double withdrawamount) throws AccountException{
        if(withdrawamount < 0){
            throw new AccountException("Can't not withdraw a negative amount");
        }else if(withdrawamount > balance){
            throw new AccountException("Insufficient Balance");
        }else {
        balance -= withdrawamount;}

    }

    public void balanceDeposite(double depositeamount) throws  AccountException{
        if(depositeamount < 0){
            throw new AccountException("Can't not deposite a negative amount");
        }
        balance += depositeamount;
    }
}
