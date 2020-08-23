
@Test.Table("t_user")
public class User {

    @Test.Column(name = "No.", type = "int")
    private int id;

    @Test.Column(name = "username", type = "varchar(20)")
    private String username;

    @Test.Column(name = "pwd", type = "char(6)")
    private String password;

    @Test.Column(name = "email", type = "varchar(20)")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
