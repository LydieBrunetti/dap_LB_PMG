/**
 *
 */
package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/** Méthode pour le service de messagerie. */
@Service
public final class GmailService extends GoogleService {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     *
     * @param userKey .
     * @return retourne le service.
     * @throws IOException nrhtkjnh.
     *@throws GeneralSecurityException frhvbfejhgeh.
     */
    private Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail serviceGmail = new Gmail.Builder(httpTRANSPORT, JSON_FACTORY, getCredentials(httpTRANSPORT, userKey))
                .setApplicationName(getConf().getApplicationName()).build();

        return serviceGmail;
    }

    /** L'emplacement pour obtenir le nombre de messages. */
    /**
     * @param userKey clé d'utilisateur.
     * @throws GeneralSecurityException pour la sécurité.
     * @throws IOException pour les exceptions.
     * @return skndskvn.
     */
    public Integer getNbEmail(final String userKey) throws GeneralSecurityException, IOException {

        String userId = "me";
        String query = "is:unread in:inbox";
        List<Message> messages = listMessagesMatchingQuery(userId, query, userKey);

        Integer nbResult = messages.size();

        return nbResult;

    }

    /**
     *
     * @param userId c'est le parametre qui gere l'identifiant de l'utilisateur.
     * @param userkey clé d'utilisateur.
     * @param query c'est le parametre qui gere les recherches.
     * @return message
     * @throws IOException c'est le parametre qui gere les exception.
     * @throws GeneralSecurityException efvjfdjh.
     */
    public List<Message> listMessagesMatchingQuery(final String userId, final String query, final String userkey)
            throws IOException, GeneralSecurityException {

        Gmail service = getService(userkey);

        ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();

        List<Message> messages = new ArrayList<Message>();

        while (response.getMessages() != null) {
            LOG.info(
                    "Récupération d'une nouvelle page de messages pour : " + userId + ", avec comme filtre : " + query);

            messages.addAll(response.getMessages());

            if (response.getNextPageToken() != null) {

                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(userId).setQ(query).setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        System.out.println(messages.size());

        return messages;
    }
}
