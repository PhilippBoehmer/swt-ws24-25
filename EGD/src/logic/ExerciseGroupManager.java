package logic;

public class ExerciseGroupManager {
/**
     * This method is used to regularly check whether the maximum capacity of students
     * for the available groups has been exceeded.
     *
     * @param totalStudents    The total number of students to assign.
     * @param groupSize        The maximum number of students in each group.
     * @param availableGroups  The total number of groups available for assignment.
     * @return The number of students that must be assigned with the current number
     *         of registered students and available groups. Returns 0 if totalStudents <= 0.
     * @throws IllegalArgumentException if groupSize or availableGroups is zero or negative.
     */
    public int checkGroupCapacities(int totalStudents, int groupSize, int availableGroups) {
        if (totalStudents <= 0) {
            return 0;
        }
        if (groupSize <= 0 || availableGroups <= 0) {
            throw new IllegalArgumentException("Group size and available groups must be greater than zero.");
        }

        int totalCapacity = groupSize * availableGroups;
        if (totalStudents <= totalCapacity) {
            return 0; // All students can be accommodated within available groups
        } else {
            return totalStudents - totalCapacity; // Number of students that exceed the group capacity
        }
    }
}

