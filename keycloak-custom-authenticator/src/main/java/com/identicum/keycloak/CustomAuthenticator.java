package com.identicum.keycloak;

import java.util.Random;
import org.jboss.logging.Logger;
import static org.jboss.logging.Logger.getLogger;

import javax.ws.rs.core.MultivaluedMap;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.PasswordForm;
import org.keycloak.forms.login.LoginFormsProvider;
		
public class CustomAuthenticator extends PasswordForm {

	private static final Logger logger = getLogger(CustomAuthenticator.class);
	String token;
	@Override
	public void authenticate(AuthenticationFlowContext context) {
		// Generar codigo random y mostrarlo en el theme
		LoginFormsProvider form = context.form();
	
		Random random = new Random();
		token = random.ints(48, 122 + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(10)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
		

		logger.infov("TOKEN GENERADO! {0}", token);
		form.setAttribute("token", token);


		super.authenticate(context);
	}

	@Override
	public void action(AuthenticationFlowContext context) {
		// Obtener informacion de la request y validar si es igual al codigo generado
		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
		
		String pass = formData.getFirst("password");
		logger.infov("password in action {0}", pass);

		if(!pass.equals(token)){
			logger.infov("not equals {0}", pass);
			return;
		}
		
		context.success();

		// super.action(context);
	}

}
