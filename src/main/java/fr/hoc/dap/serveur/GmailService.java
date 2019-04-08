package fr.hoc.dap.serveur;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 *
 * Regroupe les méthodes utiles à Gmail.
 *
 */

public final class GmailService extends GoogleService {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Début du singleton.
     */
    private static final GmailService INSTANCE = new GmailService();

    /**
     * Méthode du singleton.
     */
    private GmailService() {

    }

    /**
     * @return constante de GmailService.
     */
    public static GmailService getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @throws IOException si le credential n'est pas trouvé.
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     * @return retourne le service Gmail.
     */
    private Gmail getService() throws IOException, GeneralSecurityException {
        LOG.info("Construction service Gmail");

        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail serviceGmail = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(getConfiguration().getapplicationName()).build();

        return serviceGmail;

    }

    /**
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     * @throws IOException si le credential n'est pas trouvé.
     */
    protected void getNbEmail() throws GeneralSecurityException, IOException {
        LOG.info("Chargement des paramètres user et requête pour la méthode getNbMails");
        String user = "me";
        String query = new String("is:unread in:inbox");
        listMessageMatchingQuery(user, query);
    }

    /**
     * @param user indique l'iD de l'utilisateur.
     * @param query requête sur les messages non lus dans les boites de réception.
     * @return le nombre de messages non lus.
     * @throws IOException si le credential n'est pas trouvé.
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     */
    public List<Message> listMessageMatchingQuery(final String user, final String query)
            throws IOException, GeneralSecurityException {
        LOG.info("Constitution liste des emails non lus");

        Gmail service = getService();

        ListMessagesResponse response = service.users().messages().list(user).setQ(query).execute();
        List<Message> messages = new ArrayList<Message>();

        while (response.getMessages() != null) {
            LOG.info("Affichage nbr emails non lus de l'utilisateur");
            System.out.println("Nombre de message non lus :" + response.getMessages().size());
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(user).setQ(query).setPageToken(pageToken).execute();
            } else {
                break;
            }

        }
        return messages;

    }

}
