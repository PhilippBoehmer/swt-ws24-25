## Exercise 2b: Enabling the Switch Between Unit Testing and Integration Testing

To enable switching between using mocks for unit testing and real objects for integration testing, we can approach this with a flexible configuration that determines whether mocks are used or real objects are passed to the `StudentManager` class. This is typically achieved through dependency injection, which allows us to swap out the mock dependencies with real objects when needed.

### Steps to Refactor the Code:

1. **Use Dependency Injection to Allow Flexibility**:
    - You can use constructor-based or setter-based dependency injection to inject either real or mocked dependencies into the `StudentManager`. When running unit tests, we can inject mocks, while during integration testing, we can inject real objects.

2. **Changes in the `StudentManagerTest` Setup**:
    - **Unit Testing with Mocks**: Continue using the mocks as shown in the previous section.
    - **Integration Testing with Real Objects**: Instead of mocks, real objects (like actual `Course` and `Student` objects) will be injected into the `StudentManager`.

### Approach to Switching Between Mocks and Real Objects

In the refactor, we modify the `StudentManager` class to accept dependencies via constructor or setter methods. Then, we provide two configurations: one for unit testing with mocks and another for integration testing with real objects.

### Refactor the `StudentManager` Class:

First, ensure that the `StudentManager` can accept both mocked and real dependencies. In this case, we use the constructor to inject the dependencies.

```java
public class StudentManager {
     private final List<Student> students;
     private final List<Course> courses;

     // Constructor for real dependencies (integration testing)
     public StudentManager(List<Student> students, List<Course> courses) {
          this.students = students;
          this.courses = courses;
     }

     // Constructor for mock dependencies (unit testing)
     public StudentManager(List<Course> courses) {
          this.students = null;  // Not used in our tests for unit testing
          this.courses = courses;
     }

     // Method to generate student distribution
     public Map<String, List<String>> generateStudentDistribution(String courseId) {
          Course course = findCourseById(courseId);
          if (course == null) {
                throw new IllegalArgumentException("Course with ID " + courseId + " not found.");
          }

          Map<String, List<Student>> groupToStudents = course.getExerciseGroups().stream()
                     .collect(Collectors.toMap(
                                group -> group.getName(),
                                group -> group.getStudents()
                     ));

          Map<String, List<String>> result = new HashMap<>();
          for (Map.Entry<String, List<Student>> entry : groupToStudents.entrySet()) {
                String groupName = entry.getKey();
                List<String> studentDetails = entry.getValue().stream()
                          .map(student -> String.format("%s (%s)", student.getName(), student.getMatriculationNumber()))
                          .collect(Collectors.toList());
                result.put(groupName, studentDetails);
          }

          return result;
     }

     private Course findCourseById(String courseId) {
          return courses.stream()
                     .filter(course -> course.getId().equals(courseId))
                     .findFirst()
                     .orElse(null);
     }
}
```

### Unit Test for Mocked Objects (JUnit with Mocks):

In unit testing, we continue to use mocks to isolate the behavior of `StudentManager`. We use Mockito to mock the dependencies, as shown in the previous solution.

### Integration Test with Real Objects:

For integration testing, we use real `Course`, `ExerciseGroup`, and `Student` objects to validate the behavior of `StudentManager` with actual data.

### Unit Test Setup (with Mockito) for Mocks:

We continue using the mock configuration for unit testing, which you've already seen in the previous solution.

### Integration Test Setup (Real Objects):

In the integration test, we'll provide real objects instead of mocks. This will involve setting up real `Student`, `Course`, and `ExerciseGroup` instances and passing them to the `StudentManager`.

```java
package homework_mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerIntegrationTest {

     private StudentManager studentManager;

     @BeforeEach
     public void setup() {
          // Set up real objects for integration testing
          Student student1 = new Student("Alice", "A123");
          Student student2 = new Student("Bob", "B456");

          ExerciseGroup group1 = new ExerciseGroup("Group 1", List.of(student1));
          ExerciseGroup group2 = new ExerciseGroup("Group 2", List.of(student2));

          Course course = new Course("Software Engineering", List.of(group1, group2));

          // Inject real courses into StudentManager
          studentManager = new StudentManager(List.of(course)); // No mock dependency, real Course list
     }

     @Test
     public void testGenerateStudentDistribution_Integration() {
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
     public void testGenerateStudentDistribution_CourseNotFound_Integration() {
          // Act & Assert
          assertThrows(IllegalArgumentException.class, () -> studentManager.generateStudentDistribution("Nonexistent Course"));
     }
}
```

### Explanation of Integration Testing:

**Real Objects**:
- We manually create `Student`, `ExerciseGroup`, and `Course` objects, passing them into the `StudentManager`.
- This simulates a full-stack behavior where we use actual data and test the method `generateStudentDistribution` in a real scenario.

**Test Assertions**:
- We check that the distribution correctly maps the students into their respective groups.
- The `testGenerateStudentDistribution_CourseNotFound_Integration` test ensures that an exception is thrown if the course is not found.

### How to Switch Between Unit and Integration Testing:

**For Unit Testing (with Mocks)**:
- We use the `StudentManager(List<Course> courses)` constructor to inject mocked dependencies (as shown in the previous solution).
- This isolates the `StudentManager` behavior and only tests that specific unit in isolation.

**For Integration Testing (with Real Objects)**:
- We use the `StudentManager(List<Student> students, List<Course> courses)` constructor to inject real objects.
- This tests the integration of the `StudentManager` with actual `Course`, `Student`, and `ExerciseGroup` objects, ensuring the full behavior of the system.

### Summary:

- **Unit Testing**: Mock all external dependencies (like `Course` and `ExerciseGroup`) to isolate the unit under test.
- **Integration Testing**: Use real objects for the dependencies, allowing you to test the `StudentManager` with actual data.
- **Switching**: The constructor allows easy switching by providing real or mocked dependencies depending on the type of testing.