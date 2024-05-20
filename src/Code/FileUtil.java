package Code;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static byte[] readFile(String filePath) {
        File file = new File(filePath);
        byte[] fileData = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
}