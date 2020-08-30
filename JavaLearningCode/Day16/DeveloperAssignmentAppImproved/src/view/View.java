package view;

import exception.MemberNotFoundException;
import javabean.Employee;
import javabean.Programmer;
import service.EmployeesList;
import service.TeamMemberManagement;

import java.util.Scanner;

public class View {

    TeamMemberManagement teamMemberManagement = new TeamMemberManagement();
    EmployeesList employeesList = new EmployeesList();
    Scanner input = new Scanner(System.in);
    private Boolean exit_flag = true;

    public void printMenu(){
        System.out.println("---------- Main Menu ----------");
        System.out.println("ID\tName\tAge\tSalary\tStatus\tBouns\tStock\t");
        getAllemp();
        System.out.println("---------- End ----------");
        System.out.println("1.TeamList\t2.Add Team Member\t3. Delete Team Member\t4. Exit");
        while (exit_flag){
           switch (input.nextInt()){
               case 1:
                   pirntTeamMemberList();
                   break;

               case 2:
                   addTeamMember();
                   break;

               case 3:
                   deleteTeamMember();
                   break;

               case 4:
                   exit_flag = false;
           }
        }
    }


    private void deleteTeamMember() {

    }


    private void pirntTeamMemberList() {

    }

    private void addTeamMember() {
        System.out.println("---------- Add Team Member ----------");
        System.out.println("Please input the member Id need to be added.");
        int memberid = input.nextInt();
        Employee emp = employeesList.getEmplyeeById(memberid);
        //判断成员是否符合要求
        if(!(emp instanceof Programmer)){
            throw new MemberNotFoundException("This employee is not a developer.");
        }else{
            Programmer pemp = (Programmer) emp;
            teamMemberManagement.addMember(pemp);
            System.out.println(" ----- Succeed! ----- ");
        }

    }

    private void getAllemp(){
        EmployeesList employeesList = new EmployeesList();
        Employee[] allemp = employeesList.getAllemp();
        for(int i = 0; i < allemp.length; i++){
            System.out.println(allemp[i]);
        }
    }
}
