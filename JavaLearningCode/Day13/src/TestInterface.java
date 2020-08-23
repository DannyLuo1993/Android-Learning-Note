public class TestInterface {

    public static void main(String[] args){

        MyRunable[] myRunables = new MyRunable[3];
        myRunables[0] = new Car();
        myRunables[1] = new Dog();
        myRunables[2] = new Person();

        for(int i = 0; i < myRunables.length; i++){
            myRunables[i].run();
        }
    }


}
