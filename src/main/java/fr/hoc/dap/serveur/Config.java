/*
 *
 */
package fr.hoc.dap.serveur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author house
 *
 */
public class Config {
    private static final Logger LOG = LogManager.getLogger();

    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String APPLICATION_NAME = "Data Access Project";

    private String applicationName;
    private String credentialFolder;
    private String clientSecretFill;

    public Config() {
        applicationName = APPLICATION_NAME;
        credentialFolder = CREDENTIALS_FILE_PATH;
        clientSecretFill = TOKENS_DIRECTORY_PATH;

    }

    public String getapplicationName() {
        return applicationName;
    }

    public String getcredentialFolder() {
        return credentialFolder;
    }

    public String getclientSecretFill() {
        return clientSecretFill;
    }

    public void setapplicationName(final String newapplicationName) {
        this.applicationName = newapplicationName;
    }

    public void setcredentialFolder(final String newcredentialFolder) {
        this.credentialFolder = newcredentialFolder;
    }

    public void setclientSecretFill(final String newclientSecretFill) {
        this.clientSecretFill = newclientSecretFill;
    }

}
