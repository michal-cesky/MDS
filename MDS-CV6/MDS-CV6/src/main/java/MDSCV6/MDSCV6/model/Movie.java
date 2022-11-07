package MDSCV6.MDSCV6.model;

import java.io.File;

public class Movie {

    File file = null;
    String image_name = null;
    String file_name = null;

    public Movie(File file, String image_name, String file_name){
        this.file = file;
        this.image_name = image_name;
        this. file_name = file_name;
    }

    public File getFile() {
        return file;
    }

    public String getImage_name() {
        return image_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}