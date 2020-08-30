package service;

public class EmployeeInfo {

    // Employee ： 10,id，name，age，salary
    // Programmer ： 11,id，name，age，salary
    // Designer ： 12,id，name，age，salary,bonus
    // Architect ： 13,id，name，age，salary,bonus,stock
    public static final int EMPLOYEE = 10;
    public static final int PROGRAMMER = 11;
    public static final int DESIGNER = 12;
    public static final int ARCHITECT = 13;

    //
    public static final int PC = 21;
    public static final int NOTEBOOK = 22;
    public static final int PRINTER = 23;

    //  Number, id, name, age, salary, bonus, stock
    public static final String[][] EMPLOYEES = {

            {"10","1","Jane","23","6000"},
            {"13","2","Fan","31","20000", "40000", "2000"},
            {"11","3","Danny","27","8000"},
            {"11","4","Walter","29","6000"},
            {"12","5","Nelson","31","8000","8000"},
            {"11","6","Leo","38","20000"},
            {"12","7","Jay","44","6000","6000"},
            {"13","8","Lee","31","25000","50000", "4000"},
            {"12","9","Tao","31","6000","6000"},
            {"11","10","Charlie","29","9000"}

    };

    // PC:          21, model,  display
    // Notebook:    22, model,  price
    // Printer:     23, type,   name
    public static final String[][] EQUIPMENTS = {

            {"22", "Lenovo-Y5", "6000"},
            {"21", "Acer", "AT7-N52"},
            {"21", "Dell", "3800-R33"},
            {"23", "Laser","Canon 2900"},
            {"21", "ASUS", "K30BD-21"},
            {"21", "Haier", "18-511X 19"},
            {"23", "Dot-Matrix", "Epson 20K"},
            {"21", "Lenovo", "ThinkCentre"},
            {"21", "ASUS", "KBD-A54M5"},
            {"22", "HP-m6", "5800"},

    };
}
