package com.identicum.keycloak;

import javax.ws.rs.core.MultivaluedMap;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.forms.login.LoginFormsProvider;

public class CustomAuthenticator extends UsernamePasswordForm {

	@Override
	public void authenticate(AuthenticationFlowContext context) {
		// Generar codigo random y mostrarlo en el theme
		LoginFormsProvider form = context.form();

		super.authenticate(context);
	}

	@Override
	public void action(AuthenticationFlowContext context) {
		// Obtener informacion de la request y validar si es igual al codigo generado
		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
		
		super.action(context);
	}

}
