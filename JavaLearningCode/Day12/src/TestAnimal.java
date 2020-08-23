public class TestAnimal {

    public static void main(String[] args){

        Animal[] animal = new Animal[5];
        for(int i = 0; i < animal.length; i++){
            if(i%2==0){
                animal[i] = new Oviparity();
                animal[i].name = "OVIPARITY";
                animal[i].giveBirthTo();
            }else{
                animal[i] = new Viviparity();
                animal[i].name = "ViVIPARITY";
                animal[i].giveBirthTo();
            }
        }
    }
}
