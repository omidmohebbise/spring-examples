package security.securityinmemory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping("/secured")
    public String getSecuredHelloWorld() {
        return "<a href=\"logout\"> log from seured result</a>";
    }

    @RequestMapping("/home")
    public String getHomeHelloWorld() {
        return "Hello Home World";
    }

}
