package MDSCV6.MDSCV6;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
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

import static MDSCV6.MDSCV6.FilePaths.*;


@Controller
public class WebController {

    //pro dash-stream
    private MyResorceshttpRequestHandler handler;

    @Autowired
    public WebController(MyResorceshttpRequestHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/video")
    public String video() {
        return "videoMP4stream";
    }

    //http://localhost:8080/index
    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    //video URL s player nastavenim bez dash-stream
    @GetMapping("VideoURL")
    public String VideoURL() {
        return "VideoURL";
    }

    //pro URL s dash-stream
    @RequestMapping(value="dashPlayer", method={RequestMethod.GET, RequestMethod.POST})
    public String Dashstrema(@RequestParam String url, Model model){
        if(!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
        }else{
            model.addAttribute("error", "URL not found");
        }
        return "dashPlayer";
    }

    //local video s dash-stream
    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResorceshttpRequestHandler.ATTR_FILE, MP4_FILE) ;
        handler.handleRequest(request, response);
    }

    //nastaveni parametru
    @RequestMapping(value="player", method={RequestMethod.POST})
    public String player(@RequestParam String url,
                         @RequestParam String muted,
                         @RequestParam(defaultValue = "false") boolean autoplay,
                         @RequestParam(defaultValue = "1000") String width,
                         @RequestParam String poster,
                         Model model){
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

    //vyber typu souboru
    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method={RequestMethod.GET, RequestMethod.POST})
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

    //pouze pro soubor dash
    /*@RequestMapping(value = "dashPlayer", method = {RequestMethod.POST})
    public String dashPlayer(Model model, @RequestParam String url) {
        model.addAttribute("url", url);

        //Type pro vyřešení problémů při přehrávání non dash streamů s controls
        String type = "stream";
        if (url.contains(".mpd")) type = "stream";
        else if (url.contains("hls")) type = "hls";
        else type = "video";

        model.addAttribute("type", type);
        return "dashPlayer";
    }*/

    //show video
    @RequestMapping(value = {"/GalleryVideo/{file}"}, method = RequestMethod.GET)
    public String showVideo(@PathVariable("file") String file, Model model) throws IOException {
        model.addAttribute("file", file);
        return "GalleryVideo";
    }

    //ziskani videa
    @GetMapping(value = "/getvideo/{file}")
    public void getVideo(@PathVariable("file") String name, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(MyResorceshttpRequestHandler.ATTR_FILE, new File(MP4_DIRECTORY + name));
        handler.handleRequest(request, response);
    }

    //zobrazeni videi v galerii
    private MovieLibrary library = null;

    @GetMapping(value = "/Gallery")
    public String Gallery(Model model) throws IOException, JCodecException {

        if (library == null){
            library = new MovieLibrary(IMAGES_DIRECTORY, MP4_DIRECTORY, SUFFIX, 150);
        }
        model.addAttribute("library", library);
        return "Gallery";
    }
}



