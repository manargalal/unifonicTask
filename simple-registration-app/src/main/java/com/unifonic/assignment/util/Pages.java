package com.unifonic.assignment.util;

public enum Pages {
	REGISTRATION_FORM_PAGE("registrationFormPage"),
	REGISTRATION_SUCCESS_PAGE("registrationSuccessPage"),
	REGISTRATION_FAILURE_PAGE("registrationFailurePage");
	
	private String page;
	Pages(String page)
	{
		this.page = page;    
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}


}
