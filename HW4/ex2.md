2a)

**OCL for Class Invariants:**

```ocl
context CourseManager
inv noNullValues: courses->forAll(course | course <> null)
inv noDuplicates: courses->isUnique(course)
```

**Preconditions and Postconditions for addCourse:**

**Preconditions:**

- The input `courseName` must not be null.
- The `courseName` must not already exist in the courses list.

**Postconditions:**

- If the method succeeds, `courseName` is added to the list.
- The size of the courses list increases by one.

**OCL for addCourse:**

```ocl
context CourseManager::addCourse(courseName: String)
pre courseNotNull: courseName <> null
pre courseNotAlreadyInList: not courses->includes(courseName)
post courseAdded: courses->includes(courseName)
post sizeIncreased: courses->size() = courses@pre->size() + 1
```

2b)

1. **Single Responsibility Principle (SRP): Violation**
    - **Problem:** The `CourseManager` class handles both course management logic and the storage mechanism for courses (`ArrayList<String>`).
    - **Fix:** Introduce a separation of concerns. Use a dedicated repository class or service to handle storage and retrieval of courses. For example:
      ```java
      class CourseRepository {
            private List<String> courses;
            // Methods for adding, removing, and retrieving courses.
      }
      ```

2. **Open/Closed Principle (OCP): Adherence**
    - The class is open for extension (you can add new methods) but closed for modification (existing functionality need not change). This is due to proper encapsulation.

3. **Liskov Substitution Principle (LSP): Adherence**
    - The `CourseManager` class does not inherit from another class, so LSP is trivially satisfied.

4. **Interface Segregation Principle (ISP): Uncertain**
    - **Further Information Needed:** If `CourseManager` is part of a broader system and clients only need parts of its functionality (e.g., some only read courses, others modify them), this principle could be violated. A possible solution is to extract smaller interfaces like `CourseReader` and `CourseWriter`.

5. **Dependency Inversion Principle (DIP): Violation**
    - **Problem:** The class depends on a concrete implementation (`ArrayList<String>`) instead of an abstraction.
    - **Fix:** Use the `List<String>` interface instead of `ArrayList<String>` for courses. This makes the code more flexible and adheres to DIP.
      ```java
      private List<String> courses;
      ```

 **Improved Code**
```java
import java.util.ArrayList;
import java.util.List;

public class CourseManager {

    private final List<String> courses; // Use abstraction

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    /**
     * Adds a course to the list. It should only do so
     * if courseName is not null and the course is not
     * already in the list.
     *
     * @param courseName The name of the course to add.
     * @throws IllegalArgumentException if the courseName is null or already exists.
     */
    public final void addCourse(String courseName) {
        if (courseName == null || courses.contains(courseName)) {
            throw new IllegalArgumentException("Invalid course name");
        }
        courses.add(courseName);
    }

    /**
     * Removes a course from the list. It should only do so
     * if courseName is not null and the course is in the list.
     *
     * @param courseName The name of the course to remove.
     * @return true if the course was removed, false otherwise.
     */
    public final boolean removeCourse(String courseName) {
        if (courseName == null) {
            throw new IllegalArgumentException("Course name cannot be null");
        }
        return courses.remove(courseName);
    }
}
```
