public class Test {

    public static void main (String[] args){
        new Father(){
            @Override
            public void method() {
                System.out.println("Hellp, my kid");
            }
        }.method();
    }
}
