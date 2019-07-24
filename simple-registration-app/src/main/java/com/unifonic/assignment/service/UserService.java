package com.unifonic.assignment.service;

import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.exception.UserRegistrationException;
import com.unifonic.assignment.formbean.UserForm;

public interface UserService {
	public Users creatUser(UserForm userForm) throws UserRegistrationException;
	public String generatOTPCode(String phoneNumber) throws UserRegistrationException;
	
}
