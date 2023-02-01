package com.identicum.keycloak;

import lombok.Getter;
import org.jboss.logging.Logger;
import org.keycloak.common.util.MultivaluedHashMap;
@Getter
public class RestConfiguration {
	public static final String PROPERTY_BASE_URL = "baseURL";
	private static final Logger logger = Logger.getLogger(RestConfiguration.class);
	private String baseUrl;

	public RestConfiguration(MultivaluedHashMap<String, String> keycloakConfig) {
		this.baseUrl = keycloakConfig.getFirst(PROPERTY_BASE_URL);
		logger.infov("Loaded baseURL from module properties: {0}", baseUrl);
		if(baseUrl.endsWith("/")) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length()-1);
			logger.infov("Removing trailing slash from URL: {0}", baseUrl);
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("baseUrl: " + baseUrl + "; ");

		return buffer.toString();
	}
}
