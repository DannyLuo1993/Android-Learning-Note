import java.util.Scanner;

public class MainActivity {




    public static void main (String[] args){

        boolean loop_flag = true;
        int member_id;
        EmployeeManagement employeeManagement = new EmployeeManagement();
        TeamMemberManagement teamMemberManagement = new TeamMemberManagement();


        while (loop_flag){

            System.out.println("----------Main Menu----------");
            System.out.println("1. Team Member List" + "\t" + "2. Add Team Member" + "\t" + "3. Delete Team Member" + "\t" + "4. Exit");
            System.out.println("Please input number to continue(1-4)");
            Scanner input = new Scanner(System.in);

            try {
                switch (input.nextInt()){
                    case 1:
                        System.out.println(teamMemberManagement.getTeamMemberInfo());
                        break;
                    case 2:
                        System.out.print("Please input the member id you would like to add to the developer team: ");
                        member_id = input.nextInt();
                        teamMemberManagement.addTeamMember(member_id);
                        break;
                    case 3:
                        System.out.println("Please input the member id you would like to delete from the developer team: ");
                        member_id = input.nextInt();
                        teamMemberManagement.deleteTeamMember(member_id);
                        break;
                    case 4:
                        System.out.println("Are you sure to exit? (Y/N)");
                        switch (input.next()){
                            case "Y":
                            case "y":
                                loop_flag = false;
                                break;
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Illegal Argument!");
                                break;
                        }
                }
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }


        }
    }


}
