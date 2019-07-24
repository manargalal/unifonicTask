package com.unifonic.assignment.service;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unifonic.assignment.domain.model.UserVerificationCodes;
import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.exception.UserRegistrationException;
import com.unifonic.assignment.formbean.UserForm;
import com.unifonic.assignment.models.HttpResponseDataModel;
import com.unifonic.assignment.models.SendingDataRequestModel;
import com.unifonic.assignment.repository.UserRepository;
import com.unifonic.assignment.repository.VerificationCodesRepository;
import com.unifonic.assignment.util.Constants;
import com.unifonic.assignment.util.ErrorMessages;
import com.unifonic.assignment.util.Utilties;

@Service
public class UserServiceImpl  implements UserService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private VerificationCodesRepository codeDao;

	@Autowired
	private SenderService senderService;

	@Override

	public Users creatUser(UserForm userForm) throws UserRegistrationException {
		Users user =null;
		try{
			user = userDao.save(modelMapper.map(userForm, Users.class));
		}catch(Exception e){
			throw new UserRegistrationException(HttpStatus.BAD_REQUEST,
					ErrorMessages.COULD_NOT_CREATE_USER.getErrorMessage() + e.getMessage());
		}
		return user;
	}


	public String generatOTPCode(String phoneNumber) throws UserRegistrationException{
		UserVerificationCodes vCode = null;
		SendingDataRequestModel sendingDataRequestModel = new SendingDataRequestModel();
		String verificationCode = "";
		try{
			String randomeCode = Utilties.generatRandomCode();
			vCode =codeDao.findByPhoneNumber(phoneNumber);
			if(vCode==null){
				vCode = new UserVerificationCodes();
				vCode.setCode(randomeCode);
				vCode.setPhoneNumber(phoneNumber);
			}else{
				vCode.setCode(randomeCode);
			}
			UserVerificationCodes code =codeDao.save(vCode);
			verificationCode= code.getCode();
			sendingDataRequestModel.setRecipient(phoneNumber);
			sendingDataRequestModel.setMessage(Constants.VERIFICATION_CODE_MESSAGE + verificationCode);
			HttpResponseDataModel model =(HttpResponseDataModel)senderService.send(sendingDataRequestModel);
			log.trace("the response after sending sms is"+model);
		}catch(IOException ex){
			throw new UserRegistrationException(HttpStatus.GATEWAY_TIMEOUT,
					ErrorMessages.COULD_NOT_GENERATE_VERIFICATION_CODE.getErrorMessage() + ex.getMessage());
		}catch(Exception e){
			throw new UserRegistrationException(HttpStatus.BAD_REQUEST,
					ErrorMessages.COULD_NOT_GENERATE_VERIFICATION_CODE.getErrorMessage() + e.getMessage());
		}
		return verificationCode;
	}
}




