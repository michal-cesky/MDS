package mds.streamingserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

@Controller
public class WebController {

    private MyResorceshttpRequestHandler handler;

    @Autowired
    public WebController(MyResorceshttpRequestHandler handler) {
        this.handler = handler;
    }

    private final static File MP4_File = new File("D:\\MDS\\files\\videos\\bbb_1080p.mp4");

    //http://localhost:8080/video
    @GetMapping("video")
    public String video(){
        return "videoMP4";
    }

    @GetMapping(path = "file",produces = "video/mp4")
    @ResponseBody
    public FileSystemResource file() {
        return new FileSystemResource(MP4_File);
    }

    //http://localhost:8080/byterange
    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResorceshttpRequestHandler.ATTR_FILE, MP4_File) ;
        handler.handleRequest(request, response);

    }

    //http://localhost:8080/index
    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value="player", method={RequestMethod.POST})
    public String player(@RequestParam String url, @RequestParam String width, @RequestParam String autoplay, @RequestParam String muted, @RequestParam String poster, Model model){
        if(!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
            model.addAttribute("width", width);
            model.addAttribute("autoplay", autoplay);
            model.addAttribute("muted", muted);
            model.addAttribute("poster", poster);
        }else{
            model.addAttribute("error", "URL not found");
        }
        return "player";
    }

}
