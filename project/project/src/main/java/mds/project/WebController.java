package mds.project;

import mds.project.ProjectResourceComponent.ResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import static mds.project.FilePaths.DASH_DIRECTORY;
import static mds.project.FilePaths.VIDEO_FROM_URL;

@Controller
public class WebController {

    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value="/video", method={RequestMethod.POST})
    public String player(@RequestParam String url,
                         @RequestParam(defaultValue = "400") String height,
                         Model model){
        if(!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
            model.addAttribute("height", height);
        }else{
            model.addAttribute("url", VIDEO_FROM_URL);
            model.addAttribute("height", 400);
        }
        return "video";
    }

    private ResourceHttpRequestHandler handler;

    @Autowired
    public WebController(ResourceHttpRequestHandler handler) {
        this.handler = handler;
    }


    @RequestMapping(value = {"/dash/{stream}/{file}"}, method = RequestMethod.GET)
    public void streaming(
            @PathVariable(value = "stream") String stream,
            @PathVariable("file") String file,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        File STREAM_FILE = new File(DASH_DIRECTORY + stream + "\\" + file);


        request.setAttribute(ResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @RequestMapping(value = "/player/{stream}")
    public String player(@PathVariable("stream") String stream, Model model){
        model.addAttribute("stream", stream);
        return "player";
    }

    @GetMapping(value = "/videocollection")
    public String videoCollection(Model model) throws IOException {
        VideoLibrary library = new VideoLibrary();
        model.addAttribute("library", library);
        return "videocollection";
    }




}
