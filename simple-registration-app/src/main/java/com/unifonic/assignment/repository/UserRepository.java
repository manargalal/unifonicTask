package com.unifonic.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unifonic.assignment.domain.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	Users findByEmail(String email);
	Users findByPhoneNumber(String phoneNumber);
}
