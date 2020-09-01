import service.RaceThread;

public class MainActivity {

    public static void main(String[] args){

        RaceThread rabbit = new RaceThread("Rabbit", 30,100,10000);
        RaceThread tortoise = new RaceThread("Tortoise", 30, 1000, 1000);
        rabbit.start();
        tortoise.start();
        //等rabbit和tortoise线程结束后，才能让主线程抢到运行的资源
        try {
            //rabbit & tortoise 线程加塞当前线程；
            rabbit.join();
            tortoise.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //主线程和非主线程一起执行，所以时间不可以直接在主线程比较。
        System.out.println(rabbit.getTotal_time());
        System.out.println(tortoise.getTotal_time());
        if(rabbit.getTotal_time() < tortoise.getTotal_time()){
            System.out.println("Winner is rabbit and the total time is" + rabbit.getTotal_time() );
        }else {
            System.out.println("Winner is tortoise and the total time is" + tortoise.getTotal_time() );
        }

    }
}
