package com.unifonic.assignment.validator;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.unifonic.assignment.domain.model.UserVerificationCodes;
import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.exception.BadRequestException;
import com.unifonic.assignment.formbean.UserForm;
import com.unifonic.assignment.repository.UserRepository;
import com.unifonic.assignment.repository.VerificationCodesRepository;
import com.unifonic.assignment.util.Constants;
import com.unifonic.assignment.util.ErrorMessages;

@Component
public class UserFormValidator implements Validator{

	@Autowired
	private  UserRepository userDao;

	@Autowired
	private VerificationCodesRepository codeDao;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserForm.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Pattern mailPattern = Pattern.compile(Constants.MAIL_REGEX);

		UserForm userForm = (UserForm) target;
		// Check the fields of UserForm.

		//validate first name//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.FIRST_NAME, ErrorMessages.NOT_EMPTY_FIRST_NAME.getErrorMessage());
		if (userForm.getFirstName()!=null){
			if (userForm.getFirstName().length() < 2 || userForm.getFirstName().length() > 20) {
				errors.rejectValue( Constants.FIRST_NAME,ErrorMessages.FIRST_NAME_LENGTH.getErrorMessage());
			}
			if(!userForm.getFirstName().equals("") && !userForm.getFirstName().matches(Constants.ALPHABETICAL_REGEX)){
				errors.rejectValue( Constants.FIRST_NAME, ErrorMessages.ALPHABETICAL_FIRST_NAME.getErrorMessage());
			}
		}
		//**************************************************************************************************//
		//validate last name//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.LAST_NAME, ErrorMessages.NOT_EMPTY_LAST_NAME.getErrorMessage());
		if (userForm.getLastName()!=null){
			if ((userForm.getLastName().length() < 2 || userForm.getLastName().length() > 20) ){
				errors.rejectValue(Constants.LAST_NAME, ErrorMessages.LAST_NAME_LENGTH.getErrorMessage());
			}
			if(!userForm.getLastName().equals("") && !userForm.getLastName().matches(Constants.ALPHABETICAL_REGEX)){
				errors.rejectValue(Constants.LAST_NAME, ErrorMessages.ALPHABETICAL_LAST_NAME.getErrorMessage());
			}
		}
		//**********************************************************************************************************//
		//validate mail//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.EMAIL,ErrorMessages.NOT_EMPTY_EMAIL.getErrorMessage());
		if(userForm.getEmail()!=null){
			if (!mailPattern.matcher(userForm.getEmail()).matches() ) {
				// Invalid email.
				errors.rejectValue( Constants.EMAIL, ErrorMessages.IN_VAILD_EMAIL_PATERN.getErrorMessage());
			} 
			Users user = userDao.findByEmail(userForm.getEmail());
			if (user != null) {
				// Email has been used by another account.
				errors.rejectValue(Constants.EMAIL, ErrorMessages.DUPLICATE_EMAIL.getErrorMessage());
			}
			if (userForm.getEmail().length() < 7 || userForm.getEmail().length() > 20) {
				errors.rejectValue(Constants.EMAIL, ErrorMessages.EMAIL_LENGTH.getErrorMessage());
			}
		}
		//***************************************************************************************************//
		//validate verificationCode
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.VERIFICATION_CODE,ErrorMessages.NOT_EMPTY_VERIFICATION_CODE.getErrorMessage());
		if(userForm.getPhoneNumber()!=null){
			UserVerificationCodes code =codeDao.findByPhoneNumber(userForm.getPhoneNumber());
			if(code!=null && !code.getCode().equals(userForm.getCode())){
				errors.rejectValue(Constants.VERIFICATION_CODE, ErrorMessages.INCORRECT_VERIFICATION_CODE.getErrorMessage());
			}
		}
		//***************************************************************************************************//

		//validate phone//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.PHONE_NUMBER, ErrorMessages.NOT_EMPTY_PHONE_NUMBER.getErrorMessage());
		if(userForm.getPhoneNumber()!=null){
			if (!userForm.getPhoneNumber().equals("") && !userForm.getPhoneNumber().matches(Constants.NUMBERS_REGEX)) {
				errors.rejectValue(Constants.PHONE_NUMBER,ErrorMessages.PHONE_NUMBER_NUMBERS_ONLY.getErrorMessage());
			}
			if (userForm.getPhoneNumber().length() !=12) {
				errors.rejectValue(Constants.PHONE_NUMBER, ErrorMessages.PHONE_NUMBER_LENGTH.getErrorMessage());
			}
			if (!userForm.getPhoneNumber().equals("")&& !userForm.getPhoneNumber().matches(Constants.JORDON_NUMBERS_REGEX)) {
				errors.rejectValue(Constants.PHONE_NUMBER, ErrorMessages.JORDANIAN_FORMAT_NUMBER.getErrorMessage());
			}
			Users user =userDao.findByPhoneNumber(userForm.getPhoneNumber());
			if(user !=null){
				errors.rejectValue(Constants.PHONE_NUMBER, ErrorMessages.DUPLICATE_PHONE_NUMBER.getErrorMessage());
			}

		}
	}



}


