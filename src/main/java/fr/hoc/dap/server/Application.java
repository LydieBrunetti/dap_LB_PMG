/**
 *
 */
package fr.hoc.dap.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author house
 *
 */
@SpringBootApplication
public class Application {

    private static final Logger LOG = LogManager.getLogger();

    /**
     *
     * @param args.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Create a customised config.
     * @return the customised config.
     */
    @Bean
    public Config createConf() {
        Config configuration = new Config();
        configuration.setApplicationName("Bob");

        return configuration;
    }

    /**
     *
     * @param ctx ?.
     * @return args.
      * @Bean
      */
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {
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

        };
    }

}
