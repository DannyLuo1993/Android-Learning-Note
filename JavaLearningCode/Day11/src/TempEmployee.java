public class TempEmployee extends Employee{

    double working_hours;
    double hourly_pay;


    public TempEmployee(double working_hours, double hourly_pay){
        this.working_hours = working_hours;
        this.hourly_pay = hourly_pay;

    }

    @Override
    public double earning() {
        return hourly_pay * working_hours;
    }
}
