package users;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void login() {
        System.out.println("User " + firstName + " " + lastName + " logged in.");
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Integer getId(){
        return id;
    }
}

