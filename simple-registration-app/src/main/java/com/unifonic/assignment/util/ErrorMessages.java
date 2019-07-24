package com.unifonic.assignment.util;

public enum ErrorMessages {
	COULD_NOT_CREATE_USER("Could not create user "),
	COULD_NOT_GENERATE_VERIFICATION_CODE("Could not generate verification code  "),
	COULD_NOT_SEND_VERIFICATION_CODE("the Verification code SMS couldn't be sent"),
	
	NOT_EMPTY_FIRST_NAME("NotEmpty.userForm.firstName"),
	FIRST_NAME_LENGTH("Size.userForm.firstName"),
	ALPHABETICAL_FIRST_NAME("Alphabetical.userForm.firstName"),

	NOT_EMPTY_LAST_NAME("NotEmpty.userForm.lastName"),
	LAST_NAME_LENGTH ("Size.userForm.lastName"),
	ALPHABETICAL_LAST_NAME("Alphabetical.userForm.lastName"),
	
	NOT_EMPTY_PHONE_NUMBER("NotEmpty.userForm.phoneNumber"),
	PHONE_NUMBER_NUMBERS_ONLY("Numbers.userForm.phoneNumber"),
	PHONE_NUMBER_LENGTH("Length.userForm.phoneNumber"),
	JORDANIAN_FORMAT_NUMBER("Jordanian.userForm.phoneNumber"),
	DUPLICATE_PHONE_NUMBER("Duplicate.appUserForm.phoneNumber"),
	
	NOT_EMPTY_EMAIL("NotEmpty.userForm.email"),
	IN_VAILD_EMAIL_PATERN("Pattern.userForm.email"),
	DUPLICATE_EMAIL("Duplicate.appUserForm.email"),
	EMAIL_LENGTH("Size.userForm.email"),
	
	NOT_EMPTY_VERIFICATION_CODE("NotEmpty.userForm.code"),
	INCORRECT_VERIFICATION_CODE("Incorrect.verification.code");
	
	

	private String errorMessage;

	ErrorMessages(String errorMessage)
	{
		this.errorMessage = errorMessage;    
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
