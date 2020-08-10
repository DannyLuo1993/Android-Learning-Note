class Day4_Exer1{
    public static void main(String[] args){

        for (int i = 100; i>1000; i++){
            int hundreds_place = i/100;
            int tens_place = i%100/10;
            int ones_place = i%100%10;
            int sum = (hundreds_place * hundreds_place * hundreds_place) + (tens_place * tens_place * tens_place) + (ones_place * ones_place * ones_place);
            if( i == sum){
                System.out.println(i);
            }
        }
    }
}