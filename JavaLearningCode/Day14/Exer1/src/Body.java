import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Random;

public class Body {

    public static String owner;
    public static double weight;
    public static boolean health;
    Organ heart;


    public Body(String owner, double weight, boolean health) {
        this.owner = owner;
        this.weight = weight;
        this.health = health;
    }

    public String getOwner() {
        return owner;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isHealth() {
        return health;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    private abstract class Heart extends Organ{
        int heratbeatrate;
        String color;
        double weight;


        @Override
        public void doWork() {

            this.weight = Body.weight * 0.005;
            if(Body.health){
                this.color = "fresh red";
            }else{
                this.color = "dark red";
            }
            if(this.color.endsWith("fresh red")){
                Random random = new Random();
                this.heratbeatrate = random.nextInt(41) + 60;
            }
            System.out.println("Heart Bear Rate: " + heratbeatrate + "weight: " + weight +"g"+ "Color: " + color);
        }
    }

}
