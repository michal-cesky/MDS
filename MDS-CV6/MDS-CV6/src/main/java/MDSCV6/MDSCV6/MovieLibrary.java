package MDSCV6.MDSCV6;

import MDSCV6.MDSCV6.model.Movie;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.file.SimplePathVisitor;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieLibrary extends ArrayList<Movie> {

    private String imagesDirectory, searchDirectory, keepSuffix;
    private int frameTime;

    public MovieLibrary(String imagesDirectory, String searchDirectory, String keepSuffix, int frameTime) throws JCodecException, IOException {
        super();

        this.imagesDirectory = imagesDirectory;
        this.searchDirectory = searchDirectory;
        this.keepSuffix = keepSuffix;
        this.frameTime = frameTime;

        //Nalezení souborů a vytvoření obrázků
        fillMoviesWithImages();
    }

    //Naplnění jednotlivých objektů, tvorba obrázků
    public void fillMoviesWithImages() throws IOException, JCodecException {
        for (File file : discoverFiles(Path.of(searchDirectory), keepSuffix)) {
            String imageName = FilenameUtils.getBaseName(file.getName()) + "frame_" + frameTime + ".png";
            File imageFile = new File(imagesDirectory, imageName);

            if(!imageFile.exists()) { // Vytvoření snímků, pouze pokud neexistují
                Picture frame = FrameGrab.getFrameFromFile(file, frameTime);
                BufferedImage bufferedImage = AWTUtil.toBufferedImage(frame);
                ImageIO.write(bufferedImage, "png", imageFile);
            }
            // Přidání nového objektu Movie do ArrayListu (tento celý objekt je vlastně array list)
            this.add(new Movie(file, imageName, file.getName()));
        }
    }


    // Metoda pro automatické zjisťování souborů ze zadané cesty
    // Využívá walkFileTree, která naplní list souborů. Přidává pouze soubory, které končí zadanou koncovkou
    private static List<File> discoverFiles(Path directory, String keepSuffix) throws IOException {
        List<File> files = new ArrayList<>();

        // FileWalker, dokáže projít i více složek. Jednodušší varianta je na objekt File, který je kořenová složka použít
        // file.listFiles((ff) -> {return ff.getName().endsWith(keepSuffix);}); To automaticky získá všechny soubory z dané složky, které splňují filter
        Files.walkFileTree(directory, new SimplePathVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String filePath = String.valueOf(file);
                if (filePath.endsWith(keepSuffix)) files.add(new File(filePath)); //Přidat pouze soubory, které mají určitou koncovku
                return super.visitFile(file, attrs);
            }
        });
        return files;
    }
}
