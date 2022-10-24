package mds.streamingserver;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

@Component
public class MyResorceshttpRequestHandler extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = MyResorceshttpRequestHandler.class.getName() + ".file";


    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        final File file = (File)  request.getAttribute(ATTR_FILE);
        return new FileSystemResource(file);
    }
}
