package mds.project;

import java.util.ArrayList;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static mds.project.FilePaths.DASH_DIRECTORY;

public class VideoLibrary extends ArrayList<String> {

    private static String suffix = ".mpd";

    public VideoLibrary() throws IOException {

        for(File file : discoverFiles(Path.of(DASH_DIRECTORY),suffix)){
            this.add(file.getParentFile().getName());
        }
    }

    private static List<File> discoverFiles(Path directory, String suffix) throws IOException {
        List<File> files = new ArrayList<>();

        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String filePath = String.valueOf(file);
                if(filePath.endsWith(suffix)) files.add(new File(filePath));
                return super.visitFile(file, attrs);
            }
        });
        return files;
    }
}

