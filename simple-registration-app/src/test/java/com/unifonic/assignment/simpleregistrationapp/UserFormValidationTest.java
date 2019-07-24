package com.unifonic.assignment.simpleregistrationapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.apache.tomcat.jni.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.unifonic.assignment.domain.model.UserVerificationCodes;
import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.formbean.UserForm;
import com.unifonic.assignment.repository.UserRepository;
import com.unifonic.assignment.repository.VerificationCodesRepository;
import com.unifonic.assignment.validator.UserFormValidator;

public class UserFormValidationTest {
	@InjectMocks
	UserFormValidator userValidator ;
	@Mock
	private  UserRepository userDao;
	@Mock
	private  VerificationCodesRepository codeDao;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validate_FirstName_FieldTest_when_Length_greaterThan_20() {
		UserForm form = new UserForm();
		form.setFirstName(
				"aaaaaaaaaaaaaaeeeeeeee");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("firstName"));
	}

	@Test
	public void validate_FirstName_FieldTest_when_Length_LessThan_2() {
		UserForm form = new UserForm();
		form.setFirstName("a");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("firstName"));
	}
	@Test
	public void validate_FirstName_FieldTest_when_Contains_Numbers() {
		// given
		//UserFormValidator userValidator = new UserFormValidator();
		UserForm form = new UserForm();
		form.setFirstName("a3545");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("firstName"));
	}

	@Test
	public void validate_LastName_FieldTest_when_Length_greaterThan_20() {
		UserForm form = new UserForm();
		form.setLastName(
				"aaaaaaaaaaaaaaeeeeeeee");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lastName"));
	}

	@Test
	public void validate_LastName_FieldTest_when_Length_LessThan_2() {
		UserForm form = new UserForm();
		form.setLastName(
				"a");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lastName"));
	}
	@Test
	public void validate_LastName_FieldTest_when_Contains_Numbers() {
		UserForm form = new UserForm();
		form.setLastName("a3545");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lastName"));
	}

	@Test
	public void validate_Email_FieldTest_when_Length_LessThan_7() {
		UserForm form = new UserForm();
		form.setEmail("f@e.cm");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("email"));
	}

	@Test
	public void validate_Email_FieldTest_when_Length_GreaterThan_20() {
		UserForm form = new UserForm();
		form.setEmail("eng.manar_anan@example.com");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("email"));
	}

	@Test
	public void validate_Email_FieldTest_when_Already_Registered() {
		//given
		Users user = new Users();
		user.setFirstName("manar");
		user.setLastName("anan");
		user.setPhoneNumber("962799999999");
		user.setEmail("manar@example.com");
		when(userDao.findByEmail("manar@example.com")).thenReturn(user);
		UserForm form = new UserForm();
		form.setEmail("manar@example.com");
		Errors errors = new BeanPropertyBindingResult(form, "");

		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("email"));
	}

	@Test
	public void validate_Email_FieldTest_when_In_Vaild_Patern() {
		UserForm form = new UserForm();
		form.setEmail("Foo@Example.com");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("email"));
	}

	@Test
	public void validate_PhoneNumber_FieldTest_when_Length_NotEqual_12() {
		UserForm form = new UserForm();
		form.setPhoneNumber("9627999999996");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("phoneNumber"));
	}

	@Test
	public void validate_PhoneNumber_FieldTest_when_Contains_Alphabet() {
		UserForm form = new UserForm();
		form.setPhoneNumber("96279999999aa");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("phoneNumber"));
	}

	@Test
	public void validate_PhoneNumber_FieldTest_when_Not_Jordanian_Number_formate() {
		//given
		UserForm form = new UserForm();
		form.setPhoneNumber("968560246635");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("phoneNumber"));
	}
	
	@Test
	public void validate_VerificationCode_FieldTest_when_Not_Match_the_Entered_Value() {
		//given
		UserVerificationCodes code = new UserVerificationCodes();
	
		code.setPhoneNumber("962799999999");
		code.setCode("1234");
		when(codeDao.findByPhoneNumber("962799999999")).thenReturn(code);
		UserForm form = new UserForm();
		form.setCode("1345");
		form.setPhoneNumber("962799999999");
		Errors errors = new BeanPropertyBindingResult(form, "");
		// when
		userValidator.validate(form, errors);
		// then
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("code"));
	}
}


