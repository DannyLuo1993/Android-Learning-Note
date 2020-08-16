class Day08_Exer2{

    public static void main(String[] args){
        
        /*
        Student Student1 = new Student();
        Student1.setName("Danny");
        Student1.setAge(27);
        Student1.setScore(100);
        */
        Student student1 = new Student("Danny", 27, 100);
        student1.getInfo();
    }
}

class Student {

    private String name;
    private int age;
    private int score;

    public Student(){

    }

    public Student(String name, int age, int score){
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void getInfo(){
        System.out.println("Student's Name: " + name + ", Student's age is: " + age + ", and Student's score is + " + score);
    }
}