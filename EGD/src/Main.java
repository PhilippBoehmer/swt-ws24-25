import logic.EntityManager;
import users.Student;

public class Main {
    public static void main(String[] args) {

        EntityManager<Student> studentManager = new EntityManager<>();
        Student student = new Student(1, "John", "Doe");
        studentManager.addEntity(student);

        Student foundStudent = studentManager.getEntityById(1, s -> s.getId().equals(1));
        System.out.println("Found Student: " + foundStudent.getFirstName());

    }
}
