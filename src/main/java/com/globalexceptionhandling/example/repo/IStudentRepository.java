package com.globalexceptionhandling.example.repo;

import com.globalexceptionhandling.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update student set active = false where id = :id")
    int deleteStudent(@Param("id") long id);
}
