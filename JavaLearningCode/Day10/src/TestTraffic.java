public class TestTraffic {

    public static void main(String[] args){

        Traffic[] traffic = new Traffic[2];
        traffic[0] = new Car();
        traffic[1] = new Bicycle();
        for (int i = 0; i < traffic.length; i++){
            traffic[i].driver();
        }

    }
}
