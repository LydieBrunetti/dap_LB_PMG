/**
 *
 */
package fr.hoc.dap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author house.
 *
 */
@RestController
public class HelloController {
    /**
     *
     * @return dgjfdj.
     */
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     *
     * @param theName .
     * @return vjkh ..
     */
    @RequestMapping("/bonjour")
    public String direBonjour(@RequestParam("name") final String theName) {
        return "Salut!" + theName;
    }
}
