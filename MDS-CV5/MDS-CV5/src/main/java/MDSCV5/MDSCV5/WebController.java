package MDSCV5.MDSCV5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Controller
public class WebController {

    private MyResorceshttpRequestHandler handler;

    @Autowired
    public WebController(MyResorceshttpRequestHandler handler) {
        this.handler = handler;
    }

    private final static File MP4_File = new File("D:\\MDS\\files\\videos\\bbb_1080p.MP4");

    private final static String HLS_PATH = "D:\\MDS\\files\\streams\\HLS-BBB\\HLS\\";
    private final static String DASH_PATH = "D:\\MDS\\files\\streams\\MPEG-DASH-BBB\\MPEG-DASH\\";

    //http://localhost:8080/index
    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("video")
    public String video(){
        return "videoMP4";
    }

    @GetMapping("VideoURL")
    public String VideoURL() {
        return "VideoURL";
    }

    @RequestMapping(value="dashPlayer", method={RequestMethod.GET, RequestMethod.POST})
    public String dashPlayer(@RequestParam String url, Model model){
        if(!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
        }else{
            model.addAttribute("error", "path not found");
        }
        return "dashPlayer";
    }

    @GetMapping("Gallery")
    public String Gallery(Model model) {
        return "Gallery";
    }

    //http://localhost:8080/byterange
    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResorceshttpRequestHandler.ATTR_FILE, MP4_File) ;
        handler.handleRequest(request, response);
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

    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void adaptive_streaming(
            @PathVariable("file") String file,
            @PathVariable(value = "quality", required = false) String quality,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        File STREAM_FILE = null;
        String handle = (String ) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        switch (handle){
            case "/dash/{file}":
                STREAM_FILE = new File(DASH_PATH + file);
                break;
            case "/hls/{file}":
                STREAM_FILE = new File(HLS_PATH + file);
                break;
            case "/hls/{quality}/{file}":
                if(!StringUtils.isEmpty(quality)){
                    STREAM_FILE = new File(HLS_PATH + quality + "\\" + file);
                }
                break;
            default:

                break;
        }
        request.setAttribute(MyResorceshttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @GetMapping("DashVideoURL")
    public String DashVideoURL() {
        return "DashVideoURL";
    }

    @GetMapping("HLSstream")
    public String HLSstream() {
        return "HLSstream";
    }

    @GetMapping(path = "file",produces = "video/mp4")
    @ResponseBody
    public FileSystemResource file() {
        return new FileSystemResource(MP4_File);
    }

}