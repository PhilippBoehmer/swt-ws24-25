public class User {
    private String id;
    private String firstName;
    private String lastName;

    public User(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void login() {
        System.out.println("User " + firstName + " " + lastName + " logged in.");
    }
}
