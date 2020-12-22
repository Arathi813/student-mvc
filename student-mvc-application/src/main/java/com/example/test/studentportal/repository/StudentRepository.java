package com.example.test.studentportal.repository;

import com.example.test.studentportal.domain.DomainStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<DomainStudent, Long> {

}
