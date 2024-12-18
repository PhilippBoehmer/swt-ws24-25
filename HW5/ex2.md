| **Test Cases**   | **TC1** | **TC2** | **TC3** | **TC4** | **TC5** | **TC6** | **TC7** |
|------------------|---------|---------|---------|---------|---------|---------|---------|
| \( a < 0 \)      | X       |         | X       | X       |         |         |         |
| \( a = 0 \)      |         | X       |         |         |         | X       |         |
| \( a > 0 \)      |         |         |         |         | X       |         | X       |
| \( b < 0 \)      |         |         | X       |         |         |         |         |
| \( b = 0 \)      |         |         |         |         |         | X       |         |
| \( b > 0 \)      | X       | X       |         | X       | X       |         | X       |
| \( c < 0 \)      |         |         |         | X       |         |         |         |
| \( c = 0 \)      |         | X       | X       | X       |         |         |         |
| \( c > 0 \)      | X       |         |         |         | X       |         | X       |
| **Exception**    |         |         | X       |         |         | X       |         |
| **Input \( a \)**| -5      | 0       | minInt  | -1      | 50      | 0       | maxInt  |
| **Input \( b \)**| 10      | 10      | -1      | maxInt  | 10      | 0       | 1       |
| **Input \( c \)**| 5       | 5       | 0       | minInt  | 5       | 5       | 1       |
| **Expected Output**  | 0       | 0       | Exception | 0       | 0       | Exception | 1       |
| **Result**           | 0       | 0       | Exception | 0       | 0       | Exception | 1       |

---------------------------------------------------------------------

| **Parameters**   | **Description** |
|------------------|---------|
|  a      | Refers to the totalStudents parameter (number of students to assign)       |
|  b      |  Refers to the groupSize parameter (number of students per group)       |
|  c      |  Refers to the availableGroups parameter (number of groups available)       |
