package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.GmailService;

/**
 * @author house.
 *
 */
@RestController

public class EmailController {
    /** a Gmail configured service. */
    @Autowired
    private GmailService service;

    /**
     * display number of unread email.
     * @param userKey Clé d'utilisateur.
     * @return the number of unread emails.
     * @throws GeneralSecurityException Security problems.
     * @throws IOException Google error.
     */
    @RequestMapping("/email/nbunread")
    public Integer displayNbEmail(@RequestParam final String userKey) throws GeneralSecurityException, IOException {

        Integer nbEmails = service.getNbEmail(userKey);
        return nbEmails;
    }

}
