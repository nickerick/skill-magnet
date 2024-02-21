package com.skillmagnet.Enrolls;

import com.skillmagnet.Course.Course;
import com.skillmagnet.User.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollsRepository extends JpaRepository<Enrolls, Long>{
    List<Enrolls> findByEnrolledUser(User u);
    // List<Enrolls> findByCourse()
}
