/**
 */
package fr.hoc.dap.server;

/**
 * @author house
 *
 */
public class Config {
    /** Tokens folder path.*/
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";
    /** Credentials folder path.*/
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/dap/credentials_Web.json";
    /** Application name.*/
    private static final String APPLICATION_NAME = "HoC DaP";
    /**idem.*/
    private String applicationName;
    /** idem.*/
    private String tokensDirectoryPath;
    /** idem.*/
    private String credentialsFilePath;

    /**
     *constructor.
     */
    public Config() {
        applicationName = APPLICATION_NAME;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        credentialsFilePath = CREDENTIALS_FILE_PATH;
    }

    /**
     * @return the applicationName.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param newApplicationName the applicationName to set.
     */
    public void setApplicationName(final String newApplicationName) {
        this.applicationName = newApplicationName;
    }

    /**
     * @return the tokensDirectoryPath.
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /**
     * @param newTokensDirectoryPath the tokensDirectoryPath to set.
     */
    public void setTokensDirectoryPath(final String newTokensDirectoryPath) {
        this.tokensDirectoryPath = newTokensDirectoryPath;
    }

    /**
     * @return the credentialsFilePath.
     */
    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    /**
     * @param newCredentialsFilePath the credentialsFilePath to set.
     */
    public void setCredentialsFilePath(final String newCredentialsFilePath) {
        this.credentialsFilePath = newCredentialsFilePath;
    }

    /**
     * URl called by Google to provide user tokens.
     * @return the URL
     */
    public String getoAuth2CallbackUrl() {
        return "/oAuth2Callback";
    }

}
