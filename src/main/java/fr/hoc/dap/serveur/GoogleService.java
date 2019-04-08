package fr.hoc.dap.serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

/**
 * Regroupe la gestion des autorisations d'accès à Google.
 *
 */
public class GoogleService {

    private static final Logger LOG = LogManager.getLogger();
    /**
     * APPLICATION_NAME Le nom affiché de l'application dans la pop-in google.
     */
    // protected static final String APPLICATION_NAME = "Data Access Project";

    /**
     * Port local pour la requête du consentement de l'utilisateur.
     */
    private static final Integer PORT = 8888;
    /**
     * Récupération et stockage des infos de l'utilisateur.
     */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Dossier de sauvegarde des autorisations utilisateur.
     */
    // private static final String TOKENS_DIRECTORY_PATH = "tokens";
    /**
     * périmètres des autorisations de l'utilisateur.
     */
    private static List<String> scopes;

    /**
     * Emplacement du fichier d'authentification.
     */
    // private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Config configuration;

    /**
     *
     * @param httpTransport flux.
     * @return credential.
     * @throws IOException Si le credential n'est pas trouvé
     */

    protected Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        LOG.info("Récupération des crédentials de l'utilisateur");

        LOG.info("Liste des scopes de l'utilisateur dans gmail et calendar");
        scopes = new ArrayList<String>();
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        InputStream in = GmailService.class.getResourceAsStream(configuration.getcredentialFolder());
        LOG.info("Chargement du Client Secret");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        LOG.info("Récupération de l'autorisation de l'utilisateur");
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(
                                new FileDataStoreFactory(new java.io.File(configuration.getclientSecretFill())))
                        .setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }

    protected Config getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Config conf) {
        this.configuration = conf;
    }

}
