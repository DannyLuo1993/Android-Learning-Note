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


        //a. 检查id对应的职位； b. 判断开发团队的人是否已经满；如果满了就抛出异常，如果没满就继续。
        //c. 将id对应的对象取出，赋值给Employee【】team_member数组
        switch(member_position){
            case "10":
                
        }





        select_count ++;


    }

    public void deleteTeamMember(int member_id){

    }

    public Employee[] getTeamMemberInfo(){
        return team_member;
    }
}
