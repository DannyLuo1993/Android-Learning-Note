package service;
import exception.MemberNotFoundException;
import javabean.*;

public class TeamMemberManagement {

    private Programmer[] team_member;
    private int total = 0;
    public static final int MAX_MEMBER = 6;
    private int pCount = 0;
    private int dCount = 0;
    private int aCount = 0;

    //构造器可以协助在类初始化时初始化变量；
    public TeamMemberManagement() {
        team_member = new Programmer[6];
    }

    public void setTeam_member_id(int team_member_id) {
        this.team_member_id = team_member_id;
    }

    private int team_member_id = 0;

    //我传的是id，参考答案传的是员工对象
    public void addMember(Programmer emp){

        //判断总人数
        if(total >= MAX_MEMBER){
            throw new MemberNotFoundException("Team Member quantity reach the limitation already.");
        }

        //判断状态,如果需要获取状态信息，需要把emp对象向下转型？
        switch (emp.getStatus()){
            case BUSY:
                throw new MemberNotFoundException("It is a member of developer team already");

            case HOLIDAY:
                throw new MemberNotFoundException("This member is on holiday");
        }

        //判断每种的人数
        if(emp instanceof Arichitect){
            if(aCount == 1){
                throw new MemberNotFoundException("Arichitect member reach limitation");
            }else{
                aCount++;
            }
        }else if(emp instanceof Designer){
            if(dCount == 2){
                throw new MemberNotFoundException("Designer member reach limitation");
            }else{
                dCount++;
            }
        }else {
            if(pCount == 3){
                throw new MemberNotFoundException("Programmer member reach limitation");
            }else{
                pCount++;
            }
        }

        //可以正常添加
        emp.setStatus(Status.BUSY);
        emp.setTeam_member_id(team_member_id);
        team_member[total++] = emp;

    }
}
