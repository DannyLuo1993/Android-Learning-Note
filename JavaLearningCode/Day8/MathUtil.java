class MathUtil{



    public static void main(String[] args){
    
        System.out.println("The result of stringBuilder is" + stringBuilder(" HELLO ", "JAVA"));
        System.out.println("The result of num times num is " + numTimesSum(11,12,13,14));
    }

    public static String stringBuilder(String... string){

        String buildresult = " "; // buildresult = ""

        //case string[] != null
        for(int i = 0; i < string.length; i++){
            buildresult += string[i]; // bulidresult vaule depends on string[i]
        }

        return buildresult;

    }

    public static int numTimesSum(int numa, int... numb){

        int result = numa;

        for(int i =0; i < numb.length; i++){
            result *= numb[i];
        }
        
        return result;
    }

}