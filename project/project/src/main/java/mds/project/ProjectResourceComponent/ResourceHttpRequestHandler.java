package mds.project.ProjectResourceComponent;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class ResourceHttpRequestHandler extends org.springframework.web.servlet.resource.ResourceHttpRequestHandler {


    public final static String ATTR_FILE = ResourceHttpRequestHandler.class.getName() + ".file";


    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        final File file = (File)  request.getAttribute(ATTR_FILE);
        return new FileSystemResource(file);
    }

}
