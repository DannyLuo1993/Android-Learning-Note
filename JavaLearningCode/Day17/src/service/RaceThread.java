package service;


public class RaceThread extends Thread{

    private int total_time;
    private int runway_length;
    private int speed;
    private int sleep_time;
    private int total_length;

    public RaceThread(String name, int runway_length, int speed, int sleep_time) {
        super(name);
        this.runway_length = runway_length;
        this.speed = speed;
        this.sleep_time = sleep_time;
    }


    @Override
    public void run() {

        for(total_length = 1; total_length < 31; total_length++){
            System.out.println(getName() + "has run for" + total_length + "M");
            try {
                Thread.sleep(speed);
                total_time += speed;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(total_length == 10 || total_length == 20){
                System.out.println(getName() + "are now resting.");
                try {
                    Thread.sleep(sleep_time);
                    total_time += sleep_time;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public int getTotal_time() {
        return total_time;
    }

}