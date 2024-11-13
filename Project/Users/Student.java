import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<ExerciseGroup> registeredGroups;

    public Student(String id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.registeredGroups = new ArrayList<>();
    }

    public void registerForExerciseGroup(ExerciseGroup group) {
        if (group.getRegisteredStudents().size() < group.getMaxCapacity()) {
            registeredGroups.add(group);
            group.addStudent(this);
        } else {
            System.out.println("Exercise group is full.");
        }
    }

    public List<ExerciseGroup> getRegisteredGroups() {
        return registeredGroups;
    }
}
