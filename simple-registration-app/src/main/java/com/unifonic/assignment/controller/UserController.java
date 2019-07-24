package com.unifonic.assignment.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unifonic.assignment.domain.model.Users;
import com.unifonic.assignment.exception.UserRegistrationException;
import com.unifonic.assignment.formbean.UserForm;
import com.unifonic.assignment.service.SenderService;
import com.unifonic.assignment.service.UserService;
import com.unifonic.assignment.util.Constants;
import com.unifonic.assignment.util.Pages;
import com.unifonic.assignment.validator.UserFormValidator;
@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserFormValidator userValidator;

	@Autowired
	private UserService userService;
	@Autowired
	private SenderService smsSenderServiceImpl;


	@InitBinder
	protected void initBinderUserForm(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == UserForm.class) {
			dataBinder.setValidator(userValidator);
		}
	}



	// Show Register page.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewRegistrationPage(Model model) {
		UserForm userform = new UserForm();
		model.addAttribute("userForm", userform);
		return "registrationFormPage";
	}


	@RequestMapping(value = "/register", method = RequestMethod.POST )
	public String registerUser(Model model,@ModelAttribute("userForm") @Valid  UserForm userForm, BindingResult result,@RequestParam String action,final RedirectAttributes redirectAttributes) {
		Users user = null;
		String code="";
		try{
			if(action.equalsIgnoreCase(Constants.VERIFY_PHONE_NUMBER) && !userForm.getPhoneNumber().equals("") ){
				code = userService.generatOTPCode(userForm.getPhoneNumber());
				model.addAttribute("message", Constants.VERIFICATION_CODE_SUCCESS_MESSAGE);
				log.info("<<<<----VERIFICATION_CODE ------: >>>>"+code);
			}
			if (result.hasErrors()) {
				return Pages.REGISTRATION_FORM_PAGE.getPage();
			}
			if(action.equalsIgnoreCase(Constants.SUBMIT)){
				user = userService.creatUser(userForm);
			}
		} catch (UserRegistrationException e) {
			return 	Pages.REGISTRATION_FAILURE_PAGE.getPage();
		}
		redirectAttributes.addFlashAttribute("savedUser", user);
		return Pages.REGISTRATION_SUCCESS_PAGE.getPage();
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String handleRegisterGetRequest (Model model) {
		UserForm form = new UserForm();
		model.addAttribute("userForm", form);
		return Pages.REGISTRATION_FORM_PAGE.getPage();
	}

}
