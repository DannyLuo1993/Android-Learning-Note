import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String pwd;
    private int age;
    private char gender;
    private static final long serialVersionUID = 1L;

    public User(String username, String pwd, int age, char gender) {
        this.username = username;
        this.pwd = pwd;
        this.age = age;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
