package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.ExerciseGroupManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    // Test for boundary and equivalence class cases
    @ParameterizedTest
    @CsvSource({
            "0, 5, 10, 0",              // totalStudents = 0, no students to assign
            "20, 5, 4, 0",             // totalStudents <= capacity, no overflow
            "25, 5, 4, 5",             // totalStudents > capacity, some students overflow
            "50, 5, 10, 0",            // totalStudents == capacity, no overflow
            "-10, 5, 10, 0",           // Invalid: negative totalStudents, returns 0
            "30, 0, 5, exception",     // Invalid: groupSize = 0, expect exception
            "30, 5, 0, exception",     // Invalid: availableGroups = 0, expect exception
            "30, -5, 4, exception",    // Invalid: negative groupSize, expect exception
            "30, 5, -4, exception"     // Invalid: negative availableGroups, expect exception
    })
    public void testCheckGroupCapacities(int totalStudents, int groupSize, int availableGroups, String expected) {
        if (expected.equals("exception")) {
            assertThrows(IllegalArgumentException.class, () -> {
                manager.checkGroupCapacities(totalStudents, groupSize, availableGroups);
            });
        } else {
            int expectedOutput = Integer.parseInt(expected);
            assertEquals(expectedOutput, manager.checkGroupCapacities(totalStudents, groupSize, availableGroups));
        }
    }

    // Additional edge cases
    @ParameterizedTest
    @CsvSource({
            "0, 1, 1, 0",                       // Smallest valid inputs
            "1, 1, 1, 0",                       // Only one student, fits exactly
            "2147483647, 1, 1, 2147483646",     // Overflow case with Integer.MAX_VALUE
            "1, 2147483647, 2147483647, 0"      // Extremely large capacity
    })
    public void testCheckGroupCapacitiesEdgeCases(int totalStudents, int groupSize, int availableGroups, int expected) {
        assertEquals(expected, manager.checkGroupCapacities(totalStudents, groupSize, availableGroups));
    }

}
