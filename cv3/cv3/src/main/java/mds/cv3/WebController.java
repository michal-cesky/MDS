package mds.cv3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    //http://localhost:8080/alice
    @GetMapping("alice")
    public String alice(Model model) {
        model.addAttribute("name", "Alice");
        return "template";
    }

    //http://localhost:8080/bob
    @GetMapping("bob")
    public String bob(Model model) {
        model.addAttribute("name", "Bob");
        return "template";
    }

    //http://localhost:8080/myself
    @GetMapping("myself")
    public String myself() {
        return "templatestatic";
    }

}
