package MDSCV6.MDSCV6;

import java.io.File;

public class FilePaths {

    //cesty k souborum ve skole
    /*private final static String BASE_PATH = "D:\\MDS\\files\\";


    public final static String MP4_FILE = BASE_PATH + "videos\\bbb_1080p.mp4";


    public final static String HLS_PATH = BASE_PATH + "streams\\HLS\\";
    public final static String DASH_PATH = BASE_PATH + "streams\\MPEG-DASH\\";


    public final static String MP4_DIRECTORY = BASE_PATH + "videos\\";
    public final static String IMAGES_DIRECTORY = BASE_PATH + "videos\\images\\";
    public final static String SUFFIX = "mp4";*/

    private final static String BASE_PATH = "C:\\Users\\Oem\\OneDrive - Vysoké učení technické v Brně\\škola\\VŠ\\3. ročník\\MDS\\files\\";

    public final static File MP4_FILE = new File(BASE_PATH + "Videosoubory\\video.mp4");


    public final static String HLS_PATH = BASE_PATH + "HLS\\";
    public final static String DASH_PATH = BASE_PATH + "MPEG-DASH\\";


    public final static String MP4_DIRECTORY = BASE_PATH + "Videosoubory\\";
    public final static String IMAGES_DIRECTORY = BASE_PATH + "IMAGES";
    public final static String SUFFIX = "mp4";


}
