package fr.hoc.dap.serveur;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * Regroupe les méthodes utiles à Calendar.
 */
public final class CalendarService extends GoogleService {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Début du singleton.
     */
    private static final CalendarService INSTANCE = new CalendarService();

    /**
     * Méthode du singleton.
     */
    private CalendarService() {

    }

    /**
     * @return constante de GmailService.
     */
    public static CalendarService getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @throws IOException si le credential n'est pas trouvé
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     * @return retourne le service Calendar.
     */
    private Calendar getService() throws IOException, GeneralSecurityException {

        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar serviceCalendar = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(getConfiguration().getapplicationName()).build();
        return serviceCalendar;

    }

    /*
     * méthode récupérant le prochain évènement.
     *
     * @throws IOException si le credential n'est pas trouvé.
     *
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     */
    protected void getevenement() throws GeneralSecurityException, IOException {
        Calendar service = getService();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary").setMaxResults(1).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("Aucun évènement à venir.");
        } else {
            System.out.println("Prochain évènement");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }
}
