import Exception.*;
import org.junit.jupiter.api.Test;

public class EmployeeManagement {

    Employee[] all_employees = new Employee[10];

    String member_position;
    String member_name;
    int member_id;
    int member_age;
    int member_salary;
    int member_bonus;
    int member_stock;


    /**
     * 将二维String数组中的员工信息按Employee，Programmer， Designer及Architect对象归类后，放到Employeep[] 对象数组里。
     * @return Employee[]
     */
    @Test
    public Employee[] getAllEmployee(){

        //初始化Employee数组；

        //遍历二维数组，取出其中的一维数组，验证一维数组的职员位置，将职员安排到对应的对象中；
        for(int i = 0; i < EmployeesInfo.EMPLOYEES.length; i++){
            member_position = EmployeesInfo.EMPLOYEES[i][0];
            switch (member_position){
                case "10":
                    member_id = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][1]);
                    member_name = EmployeesInfo.EMPLOYEES[i][2];
                    member_age = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][3]);
                    member_salary = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][4]);
                    all_employees[i] = new Employee(member_name,member_position,member_age,member_salary,member_id);
                    break;

                case "11":
                    member_id = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][1]);
                    member_name = EmployeesInfo.EMPLOYEES[i][2];
                    member_age = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][3]);
                    member_salary = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][4]);
                    all_employees[i] = new Programmer(member_name,member_position,member_age,member_salary,member_id);
                    break;

                case "12":
                    member_id = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][1]);
                    member_name = EmployeesInfo.EMPLOYEES[i][2];
                    member_age = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][3]);
                    member_salary = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][4]);
                    member_bonus = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][5]);
                    all_employees[i] = new Designer(member_name,member_position,member_age,member_salary,member_id,member_bonus);
                    break;

                case "13":
                    member_id = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][1]);
                    member_name = EmployeesInfo.EMPLOYEES[i][2];
                    member_age = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][3]);
                    member_salary = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][4]);
                    member_bonus = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][5]);
                    member_stock = Integer.parseInt(EmployeesInfo.EMPLOYEES[i][6]);
                    all_employees[i] = new Architect(member_name,member_position,member_age,member_salary,member_id,member_bonus,member_stock);
                    break;
            }
        }

        return all_employees;
    }

    /**
     *
     * @param member_id 输入需要查找的职员id
     * @return Emloyee 返回对应职员对象
     * @throws MemberNotFoundException 如果查询不到对应的职员id，即抛出异常
     */
    public Employee selectEmployeeById(int member_id) throws MemberNotFoundException {

        Employee[] single_employee = new Employee[1];
        //遍历对象数组，查询其中对应id的对象,如果能查到，就赋值给single_employee[]，如果查不到，就抛出异常
        try {
            for(int i = 0; i < all_employees.length; i++){
                if(all_employees[i].id == member_id){
                    single_employee[0] = all_employees[i];
                }
            }
        }catch (MemberNotFoundException e){
            e.printStackTrace();
        }

        return single_employee[0];
    }
}
