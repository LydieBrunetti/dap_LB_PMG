package fr.hoc.dap.serveur;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "userKey", defaultValue = "utilisateur") final String userKey) {
        return "Hello world !";
    }
}