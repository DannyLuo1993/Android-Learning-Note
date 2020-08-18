import java.util.Scanner;

public class Account {

    private int id;
    private double balance;
    private double rate;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;

    }

    public double getRate(){
        return rate;
    }

    public void setRate( double rate){
        this.rate = rate;
    }

    public void getMonthlyRate(){
        System.out.println(rate/12);
    }

    public void withdraw(){
        System.out.print("Please input the money you would like to withdraw: ");
        Scanner input = new Scanner(System.in);
        double amount = input.nextDouble();
        if(balance >= amount){
            balance -= amount;
            System.out.println("Current Balance: " + balance);
        }else{
            System.out.println("Balance is not enough");
            System.out.println("Current Balance: " + balance);
        }
    }

    public void deposit(){
        System.out.print("Please input the money you would like to deposit: ");
        Scanner input = new Scanner(System.in);
        balance += input.nextDouble();
        System.out.println("Current Balance: " + balance);
    }

}
