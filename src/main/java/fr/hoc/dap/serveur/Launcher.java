package fr.hoc.dap.serveur;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe d'entrée.
 *
 */
abstract class Launcher {

    /**
     * ouverture journal de logs.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @param args les paramètres d'entrée du programme.
     * @throws GeneralSecurityException classe générique de gestion des exceptions.
     * @throws IOException Si le credential n'est pas trouvé
     */
    public static void main(final String[] args) throws IOException, GeneralSecurityException {

        LOG.info("Salut tout le monde");
        LOG.error("Au secours !");
        LOG.debug("Erreur?");

        Config configuration = new Config();
        configuration.setapplicationName("Ma super appli");

        CalendarService moncalendrier = CalendarService.getInstance();
        moncalendrier.setConfiguration(configuration);
        moncalendrier.getevenement();

        GmailService mesmails = GmailService.getInstance();
        mesmails.setConfiguration(configuration);
        mesmails.getNbEmail();

    }

}
