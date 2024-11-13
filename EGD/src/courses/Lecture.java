package courses;

public class Lecture extends Course {
    private Integer lectureId;

    public Lecture(Integer courseId, String name) {
        super(courseId, name);
    }

    public Integer getLectureId() {
        return lectureId;
    }
}
