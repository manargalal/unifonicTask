package com.unifonic.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifonic.assignment.domain.model.UserVerificationCodes;

@Repository
public interface VerificationCodesRepository extends JpaRepository<UserVerificationCodes, Long>{
	UserVerificationCodes findByPhoneNumber(String phoneNumber);
}
