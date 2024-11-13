import java.util.ArrayList;
import java.util.List;


public class ExerciseGroup {
    private String groupId;
    private int maxCapacity;
    private String timeSlot;
    private List<Student> registeredStudents;

    public ExerciseGroup(String groupId, int maxCapacity, String timeSlot) {
        this.groupId = groupId;
        this.maxCapacity = maxCapacity;
        this.timeSlot = timeSlot;
        this.registeredStudents = new ArrayList<>();
    }
/*
    public String getGroupId() {
        return groupId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void addStudent(Student student) {
        if (registeredStudents.size() < maxCapacity) {
            registeredStudents.add(student);
        } else {
            System.out.println("Group " + groupId + " is at full capacity.");
        }
    }
    */
}


