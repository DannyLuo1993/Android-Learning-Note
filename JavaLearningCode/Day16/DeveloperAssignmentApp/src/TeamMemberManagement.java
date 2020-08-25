import Exception.*;

public class TeamMemberManagement {

    Employee[] team_member;
    int programmer_count;
    int architeture_count;
    int desigher_count;
    int select_count = 0;
    String member_position;

    public void addTeamMember(int member_id){

        //创建Employee对象
        EmployeeManagement employeeManagement = new EmployeeManagement();
        //初始化Employee[]数组
        team_member = new Employee[6];
        member_position = employeeManagement.selectEmployeeById(member_id).member_position;


        if(member_position.equals("10")){
            throw new NotDeveloperMemberException("This employee can't be developer team member");
        }

        if(member_position.equals("11") && programmer_count < 4){
            programmer_count ++;
            team_member[select_count] = employeeManagement.selectEmployeeById(member_id);
            select_count ++;
        }





        select_count ++;


    }

    public void deleteTeamMember(int member_id){

    }

    public Employee[] getTeamMemberInfo(){
        return team_member;
    }
}
