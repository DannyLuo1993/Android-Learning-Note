package service;

import exception.MemberNotFoundException;
import javabean.*;

public class EmployeesList {

    Employee[] allemp;

    //类初始化时，就初始化Employee【】allemp数组
    public EmployeesList() {
        getAllemp();
    }

    /**
     * Get All employees of the company
     * @return Employee[] allemp
     */
    public Employee[] getAllemp(){

        allemp = new Employee[10];

        for(int i = 0; i < EmployeeInfo.EMPLOYEES.length; i++){
            int position = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][0]);
            int id = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][1]);
            String name = EmployeeInfo.EMPLOYEES[i][2];
            int age = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][3]);
            int salary = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][4]);

            Status status = Status.FREE;
            switch (position){
                case EmployeeInfo.EMPLOYEE:
                    allemp[i] = new Employee(id, name, age, salary, status);
                    break;

                case EmployeeInfo.PROGRAMMER:
                    allemp[i] = new Programmer(id, name, age, salary, status);
                    break;

                case EmployeeInfo.DESIGNER:
                    int bouns = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][5]);
                    allemp[i] = new Designer(id, name, age, salary, status, bouns);
                    break;

                case EmployeeInfo.ARCHITECT:
                    bouns = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][5]);
                    int stock = Integer.parseInt(EmployeeInfo.EMPLOYEES[i][6]);
                    allemp[i] = new Arichitect(id, name, age, salary, status, bouns, stock);
            }
        }
        return allemp;
    }


    /**
     *
     * @param member_id 输入需要查找的职员id编号
     * @return 对应id的职员对象
     */
    public Employee getEmplyeeById(int member_id){
        for(int i = 0; i < allemp.length; i++){
            if(allemp[i].getId() == member_id){
                return allemp[i];
            }
        }
        throw new MemberNotFoundException("Member id: " + member_id + "is not found. Please double check");
    }
}
