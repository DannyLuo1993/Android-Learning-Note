class Day4_Exer2{
    public static void main(String[] args){

        for(int i = 1; i<=150; i++){
            if( i % 3 == 0){
                System.out.println("foo");
            }else if(i % 5 == 0){
                System.out.println("biz");
            }else if(i % 7 ==0){
                System.out.println("baz");
            }else{
                System.out.println(i);
            }
        }

    }
}