package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/** l'Emplacement pour obtenir la date. */
@Service
public final class CalendarService extends GoogleService {
    /**
     *
     */
    private CalendarService() {

    }

    /**
     *
     */
    private static final CalendarService INSTANCE = new CalendarService();

    /**
     *
     * @return ça retourne l'instance.
     */
    public static CalendarService getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @return retourne le service calendar.
     * @throws IOException dsfjsfsdfb.
     * @throws GeneralSecurityException hdfgsfgdhfj.
     */
    private Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar serviceCalendar = new Calendar.Builder(httpTRANSPORT, JSON_FACTORY,
                getCredentials(httpTRANSPORT, userKey)).setApplicationName(getConf().getApplicationName()).build();
        return serviceCalendar;
    }

    /**  L'emplacement pour afficher les prochains événement.
     * @param userKey 
     * @param config
     * @throws IOException les exceptions à faire.
     * @throws GeneralSecurityException pour la sécurité générale.
     * @return THE next Event
     */
    public List<Event> getNextEvents(final String userKey) throws IOException, GeneralSecurityException {
        System.out.println(getConf().getApplicationName());
        final Integer top = 1;
        // Calendar service = getService();
        // List the next event from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey).events().list("primary").setMaxResults(top).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();

        return items;
    }

    /**
     * next event basic content.
     * @param userKey 
     * @return a string represnetation of next events
     * @throws GeneralSecurityException Security problems
     * @throws IOException Google error
     */
    public String getNextEventText(final String userKey) throws IOException, GeneralSecurityException {
        List<Event> events = getNextEvents(userKey);

        String theNextEvent = "";
        if (events.isEmpty()) {
            theNextEvent = "No upcoming events found.";
            System.out.println("No upcoming events found.");
        } else {
            theNextEvent = "Upcoming events";
            System.out.println("Upcoming events ; ");
            for (Event event : events) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                theNextEvent = theNextEvent + event.getSummary() + "(" + start + "), ";
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

        return theNextEvent;
    }

}
