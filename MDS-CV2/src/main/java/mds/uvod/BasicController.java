package mds.uvod;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("basic")
public class BasicController {

    @GetMapping
    public String hallo() {
        return "<h1>hallo</h1>";
    }

    @GetMapping("list")
    public List<String> testList() {
        return List.of("Hello", "world", "in", "JSON");
    }

    @GetMapping("test1")
    public String test1(@RequestParam String name) {
        return String.format("Hallo %s", name);
    }

    @GetMapping("test2")
    public String testParam2(@RequestParam(defaultValue = "user", name = "jmeno") String name2) {
        return String.format("Hello %s", name2);
    }

    @GetMapping("test3")
    public String testParm3(@RequestParam(name = "n", defaultValue = "user") String name) {
        return String.format("Hallo %s", name);
    }

    @GetMapping("test4")
    public String testParm4(@RequestParam(defaultValue = "user") String name,
                            @RequestParam(defaultValue = "-1") String id) {
        return String.format("Hallo %s a id %s", name, id);
    }

    @GetMapping("test5")
    public String testParm5(@RequestParam List<String> id) {
        //      return "all ids are " + id;
        String a = "";
        for (String s : id) {
            a += s + "<br>";

        }
        return a;

    }

    @GetMapping("form")
    public String helloForm() {
        String html =
                "<html>" +
                        "<body>" +
                        "<form>" +
                        "<import type='text' name ='name'/>" +
                        "<import type='text' name ='name'/>" +
                        "</form>" +
                        "</body>" +
                        "</html>";
        return html;


    }

   /* @GetMapping("test2"){
        public String testParm2(@RequestParam(defaultValur = "user") String name)
    }*/


}
