package courses;

public class Course {
    private Integer courseId;
    private String name;

    public Course(Integer courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }
}

