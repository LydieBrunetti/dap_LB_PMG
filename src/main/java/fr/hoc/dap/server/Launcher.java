package fr.hoc.dap.server;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point.
 * @author house
 *
 */
final class Launcher {

    /**
     * Utility class.
     */
    private Launcher() {
        super();
    }

    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @param args pour générer des arguments.
     * @throws IOException le parametre qui gère les exceptions.
     * @throws GeneralSecurityException le parametre qui gère la sécurité générale.
     */
    public static void main(final String... args) throws IOException, GeneralSecurityException {
        // System.out.println("Mon agenda 2019");
        // LOG.info("Salut le monde!");
        // LOG.error("Ceci est une erreur!");
        // LOG.debug("Ceci est un debug");
        //
        // Config configuration = new Config();
        // configuration.setApplicationName("Bob");
        //
        // CalendarService date = CalendarService.getInstance();
        // date.setConfiguration(configuration);
        // date.getNextEvents();
        //
        // GmailService mail = GmailService.getInstance();
        // mail.setConfiguration(configuration);
        // mail.getNbEmail();

    }

}
