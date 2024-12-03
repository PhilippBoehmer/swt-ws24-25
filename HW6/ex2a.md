In case the cloning didn't work, here is the code for the `StudentManagerTest` class:

```java
package homework_mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentManagerTest {

    @Mock
    private List<Course> courses; // Mocked dependency
    private StudentManager studentManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito annotations
        studentManager = new StudentManager(null, courses); // Inject mock courses
    }

    @Test
    public void testGenerateStudentDistribution_Success() {
        // Arrange
        Course mockCourse = mock(Course.class);
        ExerciseGroup mockGroup1 = mock(ExerciseGroup.class);
        ExerciseGroup mockGroup2 = mock(ExerciseGroup.class);
        Student mockStudent1 = mock(Student.class);
        Student mockStudent2 = mock(Student.class);

        // Mock student details
        when(mockStudent1.getName()).thenReturn("Alice");
        when(mockStudent1.getMatriculationNumber()).thenReturn("A123");
        when(mockStudent2.getName()).thenReturn("Bob");
        when(mockStudent2.getMatriculationNumber()).thenReturn("B456");

        // Mock exercise group details
        when(mockGroup1.getName()).thenReturn("Group 1");
        when(mockGroup1.getStudents()).thenReturn(List.of(mockStudent1));
        when(mockGroup2.getName()).thenReturn("Group 2");
        when(mockGroup2.getStudents()).thenReturn(List.of(mockStudent2));

        // Mock course details using getId() for identification
        when(mockCourse.getId()).thenReturn("Software Engineering");
        when(mockCourse.getExerciseGroups()).thenReturn(List.of(mockGroup1, mockGroup2));

        // Mock courses list to return the mock course as a stream
        when(courses.stream()).thenReturn(Stream.of(mockCourse));

        // Act
        Map<String, List<String>> distribution = studentManager.generateStudentDistribution("Software Engineering");

        // Assert
        assertEquals(2, distribution.size());
        assertTrue(distribution.containsKey("Group 1"));
        assertTrue(distribution.containsKey("Group 2"));
        assertEquals(List.of("Alice (A123)"), distribution.get("Group 1"));
        assertEquals(List.of("Bob (B456)"), distribution.get("Group 2"));
    }

    @Test
    public void testGenerateStudentDistribution_CourseNotFound() {
        // Arrange
        when(courses.stream()).thenReturn(Stream.empty()); // Mock no courses available

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> studentManager.generateStudentDistribution("Software Engineering"));
    }
}
```
