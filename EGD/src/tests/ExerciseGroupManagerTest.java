package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.ExerciseGroupManager;

public class ExerciseGroupManagerTest {

    private final ExerciseGroupManager manager = new ExerciseGroupManager();

    @Test
    public void testAllStudentsCanBeAccommodated() {
        int result = manager.checkGroupCapacities(30, 10, 3);
        assertEquals(0, result, "Expected no students left unassigned when there is enough capacity.");
    }

    @Test
    public void testMoreStudentsThanCapacity() {
        int result = manager.checkGroupCapacities(35, 10, 3);
        assertEquals(5, result, "Expected 5 students left unassigned as capacity is exceeded.");
    }

    @Test
    public void testZeroTotalStudents() {
        int result = manager.checkGroupCapacities(0, 10, 3);
        assertEquals(0, result, "Expected 0 as there are no students to assign.");
    }

    @Test
    public void testNegativeOrZeroGroupSize() {
        assertThrows(IllegalArgumentException.class, () -> manager.checkGroupCapacities(20, 0, 3),
                "Expected IllegalArgumentException for group size of zero.");
        assertThrows(IllegalArgumentException.class, () -> manager.checkGroupCapacities(20, -1, 3),
                "Expected IllegalArgumentException for negative group size.");
    }

    @Test
    public void testNegativeOrZeroAvailableGroups() {
        assertThrows(IllegalArgumentException.class, () -> manager.checkGroupCapacities(20, 10, 0),
                "Expected IllegalArgumentException for zero available groups.");
        assertThrows(IllegalArgumentException.class, () -> manager.checkGroupCapacities(20, 10, -1),
                "Expected IllegalArgumentException for negative available groups.");
    }
}
