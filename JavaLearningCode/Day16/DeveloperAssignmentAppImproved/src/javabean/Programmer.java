package javabean;


public class Programmer extends Employee{


    private int team_member_id;


    public Programmer() {
    }

    public Programmer(int id, String name, int age, int salary, Status status) {
        super(id, name, age, salary, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setTeam_member_id(int team_member_id) {
        this.team_member_id = team_member_id;
    }

}
