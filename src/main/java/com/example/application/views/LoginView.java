package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm login = new LoginForm();

	public LoginView(){
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER); 
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");  

		VerticalLayout header = new VerticalLayout();
		header.add(new H1("Vaadin CRM"), new Span("Username: user"), new Span("Password: userpass"));
		header.setAlignItems(Alignment.CENTER);

		add(header, login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation() 
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
	}
}
