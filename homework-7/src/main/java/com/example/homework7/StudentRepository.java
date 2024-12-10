package com.example.homework7;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMatNr(String matNr);
    List<Student> findByName(String name);
}