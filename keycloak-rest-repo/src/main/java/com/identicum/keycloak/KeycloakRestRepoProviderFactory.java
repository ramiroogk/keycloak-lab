package com.identicum.keycloak;

import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;
import static org.jboss.logging.Logger.getLogger;

public class KeycloakRestRepoProviderFactory implements UserStorageProviderFactory<KeycloakRestRepoProvider> {

	private static final Logger logger = getLogger(KeycloakRestRepoProviderFactory.class);
	private List<ProviderConfigProperty> configMetadata;
	private RestHandler restHandler;

	@Override
	public void init(Scope config) {
		logger.infov("Initializing Keycloak Rest Repo factory version: " + getClass().getPackage().getImplementationVersion());

		ProviderConfigurationBuilder builder = ProviderConfigurationBuilder.create();
		configMetadata = builder.build();
	}

	@Override
	public KeycloakRestRepoProvider create(KeycloakSession session, ComponentModel model) {
		logger.infov("Creating a new instance of restHandler");
		RestConfiguration restConfiguration = new RestConfiguration(model.getConfig());
		restHandler = new RestHandler(restConfiguration);
		return new KeycloakRestRepoProvider(session, model, restHandler);
	}

	@Override
	public String getId() {
		return "rest-repo-provider";
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		return configMetadata;
	}
}
