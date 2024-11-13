import java.util.ArrayList;
import java.util.List;

public class Lecturer extends User {
    private List<Course> courses;

    public Lecturer(String id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.courses = new ArrayList<>();
    }

    public void manageCourse(Course course) {
        courses.add(course);
    }

}