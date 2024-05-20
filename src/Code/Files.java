package Code;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Files {
    public static void writeFile(byte[] data, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
