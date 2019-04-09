package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.CredentialService;

/** JEE Service for Web view. */
@Controller
public class ServiceController {

    /** users list. */
    @Autowired
    private CredentialService credSrv;

    /** link with administrator view (Browser).
     * @param model HTML view.
     * @throws GeneralSecurityException Security error handling.
     * @throws IOException Google IO error handling.
     * @return view.
     */
    @RequestMapping("/admin")
    public String admin(final ModelMap model) throws GeneralSecurityException, IOException {
        model.addAttribute("maVar", "Admin");

        DataStore<StoredCredential> userMap;
        userMap = credSrv.getUser();

        Map<String, StoredCredential> mapUsers = new HashMap<>();
        Set<String> keys = userMap.keySet();

        for (String key : keys) {
            StoredCredential value = userMap.get(key);
            mapUsers.put(key, value);

        }
        model.addAttribute("users", mapUsers);

        return "admin";

    }
}

// model.addAttribute("essai", userMap);

// List<String> bestioles = new ArrayList<>();

// bestioles.add("Chien");
// bestioles.add("Zèbre");
// bestioles.add("Suricate");
// bestioles.add("Chat");

// model.addAttribute("bebettes", bestioles);
