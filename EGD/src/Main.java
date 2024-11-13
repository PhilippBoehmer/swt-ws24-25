public class Main {
    public static void main(String[] args) {
        // Create some students
        // Student student1 = new Student("S001", "John", "Doe");
        // Student student2 = new Student("S002", "Jane", "Smith");

        // Create a lecturer
        // Lecturer lecturer = new Lecturer("L001", "Dr.", "Brown");

        // Create a lecture course and an exercise group
        // Lecture lecture = new Lecture("C001", "Software Engineering");
        // ExerciseGroup group = new ExerciseGroup("G001", 2, "Monday 10:00 AM");

        // Lecturer manages the course
        // lecturer.manageCourse(lecture);

        // Students register for the exercise group
        // student1.registerForExerciseGroup(group);
        // student2.registerForExerciseGroup(group);

        // Test login functionality
        // student1.login();
        // lecturer.login();

        // Display registered students in the exercise group
        // System.out.println("Registered students in group " + group.getGroupId() + ":");
        // for (Student student : group.getRegisteredStudents()) {
        //     System.out.println(student.getFirstName() + " " + student.getLastName());
        // }

        EntityManager<Student> studentManager = new EntityManager<>();
        Student student = new Student("1", "John", "Doe");
        studentManager.addEntity(student);

        Student foundStudent = studentManager.getEntityById("1", s -> s.getId().equals("1"));
        System.out.println("Found Student: " + foundStudent.getFirstName());


    }
}
