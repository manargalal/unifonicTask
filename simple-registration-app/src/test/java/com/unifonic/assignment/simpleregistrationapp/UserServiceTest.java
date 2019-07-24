package com.unifonic.assignment.simpleregistrationapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.unifonic.assignment.domain.model.UserVerificationCodes;
import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.formbean.UserForm;
import com.unifonic.assignment.repository.UserRepository;
import com.unifonic.assignment.repository.VerificationCodesRepository;
import com.unifonic.assignment.service.SenderService;
import com.unifonic.assignment.service.UserServiceImpl;
public class UserServiceTest {
	@InjectMocks
	private UserServiceImpl userService ;
	@Mock
	private  UserRepository userDao;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private SenderService senderService;
	@Mock
	private VerificationCodesRepository codeDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void creatUser_Successfully_In_Users_Table() throws Exception{
		Users newUser = new Users();
		newUser.setFirstName("manar");
		newUser.setLastName("anan");
		newUser.setEmail("manar@gmail.com");
		newUser.setPhoneNumber("962799999999");
		when(userDao.save(any())).thenReturn(newUser);
		//when(userDao.save(Mockito.any(Users.class))).thenReturn(newUser);
		UserForm  userForm = new UserForm();
		userForm.setFirstName("manar");
		userForm.setLastName("anan");
		userForm.setEmail("manar@gmail.com");
		userForm.setPhoneNumber("962799999999");
		Users returnedUser = userService.creatUser(userForm);

		assertEquals(returnedUser.getEmail(),newUser.getEmail());
	}
	
	@Test
	public void generateOtpCode_And_Save_To_UsersVerificationCodes_Table() throws Exception{
		UserVerificationCodes newCode =new UserVerificationCodes();
		newCode.setPhoneNumber("962799999999");
		newCode.setCode("1234");
		when(codeDao.save(any())).thenReturn(newCode);
		String returnedCode = userService.generatOTPCode("962799999999");
		assertEquals(returnedCode,newCode.getCode());
		
	}
}
