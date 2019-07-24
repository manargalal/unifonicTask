package com.unifonic.assignment.util;

public class Constants {
	//**********************************REGEX************************************************//
	public static final String ALPHABETICAL_REGEX		= "^[a-zA-Z]*$";
	public static final String JORDON_NUMBERS_REGEX 	= "^((?:[+?0?0?9627]+)(?:\\s?\\d{2})(?:\\s?\\d{7}))$";
													
	public static final String MAIL_REGEX				="[A-Z1-9a-z._-]+@[a-z]+\\.com";
	public static final String NUMBERS_REGEX			="^(0|[1-9][0-9]*)$";
	
	//*******************************FORM_CONSTANTS******************************************//
	public static final String FIRST_NAME				="firstName";
	public static final String LAST_NAME				="lastName";
	public static final String PHONE_NUMBER				="phoneNumber";
	public static final String EMAIL					="email";
	public static final String VERIFICATION_CODE		="code";
	//********************************************INTEGRATION**********************************************//
	
	public static final String UNIFONIC_APP_SID_KEY 	="AppSid";
	public static final String UNIFONIC_APP_SID_VALUE 	="SpRpFe5w7P_5XNxBF8BSnuk6PxXTc0";
	public static final String UNIFONIC_BODY_KEY 		="Body";
	public static final String UNIFONIC_SENDER_ID_KEY 	="SenderID";
	public static final String UNIFONIC_SENDER_ID_VALUE ="test sender";
	public static final String UNIFONIC_RECEIPIECT_KEY 	="Recipient";
	public static final String HTTP_POST_METHOD 		="post";
	public static final String HTTP_GET_METHOD 			="get";
	//Service urls
	public static final String SEND_SMS_MESSAGES_API_URL ="http://api.unifonic.com/rest/Messages/Send";
	
	//******************************************************************************************************//
	public static final String VERIFICATION_CODE_MESSAGE ="Your verification is : ";
	public static final String VERIFICATION_CODE_SUCCESS_MESSAGE ="Please Check Your Mobile Verification Code has been sent on it";
	//****************************************************************************************************************//
	public static final String VERIFY_PHONE_NUMBER ="Send SMS To Verify";
	public static final String SUBMIT ="submit";
	
}
