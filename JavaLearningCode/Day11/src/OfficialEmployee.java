public class OfficialEmployee extends Employee{

    double working_day;
    double off_day;
    int birthday;

    public OfficialEmployee(int birthday, double working_day, double off_day){
        this.off_day = off_day;
        this.working_day = working_day;
        this.birthday = birthday;
    }

    @Override
    public double earning() {
        double base_salary = super.salary;
        double net_salary = base_salary - (base_salary/working_day * off_day);
        return net_salary;
    }

}
