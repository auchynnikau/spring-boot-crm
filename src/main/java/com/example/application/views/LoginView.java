package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Sign In")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm login = new LoginForm();

	public LoginView(){
		LoginI18n i18n = LoginI18n.createDefault();
		LoginI18n.Form i18nForm = i18n.getForm();
		i18nForm.setTitle("Sign In");
		i18nForm.setUsername("Username");
		i18nForm.setPassword("Password");
		i18nForm.setSubmit("Sign in");
		i18n.setForm(i18nForm);
		LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
		i18nErrorMessage.setTitle("Error!");
		i18nErrorMessage.setMessage("Incorrect username or password.");
		i18n.setErrorMessage(i18nErrorMessage);

		login.setI18n(i18n);

		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER); 
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");  

		VerticalLayout header = new VerticalLayout();
		header.add(new H1("Spring Boot CRM"));
		header.setAlignItems(Alignment.CENTER);

		add(header, login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if(beforeEnterEvent.getLocation()
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
	}
}
