import service.RaceThread;

public class MainActivity {

    public static void main(String[] args){

        RaceThread rabbit = new RaceThread("Rabbit", 30,100,10000);
        RaceThread tortoise = new RaceThread("Tortoise", 30, 1000, 1000);
        rabbit.start();
        tortoise.start();
        if(rabbit.getTotal_time() < tortoise.getTotal_time()){
            System.out.println("Winner is rabbit and the total time is" + rabbit.getTotal_time() );
        }else {
            System.out.println("Winner is tortoise and the total time is" + tortoise.getTotal_time() );
        }

    }
}
