public class MyDate {
    int year;
    int month;
    int day;

    public void setMonth(int month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public String getInfo(){
        return year + "year" + month + "month" + day + "day";
    }
}
