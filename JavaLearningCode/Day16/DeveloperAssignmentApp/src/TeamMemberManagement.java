import Exception.*;

public class TeamMemberManagement {

    private int programmer_count;
    private int architeture_count;
    private int desigher_count;
    private int select_count;
    private String member_position;
    private EmployeeManagement employeeManagement = new EmployeeManagement();
    private Employee[] team_member = new Employee[6];

    public void addTeamMember(int member_id){

        //初始化member position
        employeeManagement.getAllEmployee();
        member_position = employeeManagement.selectEmployeeById(member_id).member_position;

        if(select_count == 6){
            throw new MemberQuantityLimitationException("Team Member quantity already reach the limitation");
        }else{
            //a. 检查id对应的职位； b. 判断开发团队的人是否已经满；如果满了就抛出异常，如果没满就继续。
            switch(member_position){
                case "10":
                    throw new NotDeveloperMemberException("This employee can't be the member of developer team");

                case "11":
                    if(programmer_count < 3){
                        //将id对应的对象取出，赋值给Employee【】team_member数组
                        team_member[select_count] = employeeManagement.selectEmployeeById(member_id);
                        //计数：已经添加一位程序员
                        programmer_count++;
                    }else{
                        throw new MemberQuantityLimitationException("Programmer quantity already reach the limitation");
                    }
                    break;
                case "12":
                    if(desigher_count < 2) {
                        team_member[select_count] = employeeManagement.selectEmployeeById(member_id);
                        desigher_count++;
                    }else{
                        throw new MemberQuantityLimitationException("Designer quantity already reach the limitation");
                    }
                    break;
                case "13":
                    if(architeture_count < 1){
                        team_member[select_count] = employeeManagement.selectEmployeeById(member_id);
                        architeture_count++;
                    }else{
                        throw new MemberQuantityLimitationException("Architect quantity already reach the limitation");
                    }
                    break;
            }
        }
        //选完人后，序数+1
        select_count ++;
        System.out.println("\n" + "succeed!");

    }

    public void deleteTeamMember(int member_id){

        if(select_count == 0){
            throw new MemberNotFoundException("No this member in developer team yet, please check.");
        }


        for (int i = 0; i < team_member.length; i++) {

            String test;
            if(team_member[i] == null){
                System.out.println(i);
                System.out.println("team_member[i] is null");
            }

            if (member_id == team_member[i].id) {

                //将数组中最后一位对象替换掉选中的对象
                member_position = employeeManagement.selectEmployeeById(member_id).member_position;
                team_member[i] = team_member[select_count];
                //然后数组中有内容的最后一位对象替换为空
                team_member[select_count] = null;

                //根据职位，减去对应职业人数的累计
                switch (member_position){
                    case "11":
                        programmer_count--;
                        break;
                    case "12":
                        desigher_count--;
                        break;
                    case "13":
                        architeture_count--;
                        break;
                }
            }


            //完成操作后，数组中以添加内容减一；
            select_count --;
            System.out.println("\n" + "succeed!");
        }



    }

    public Employee[] getTeamMemberInfo(){
        return team_member;
    }
}
