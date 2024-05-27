package Code;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFiles {
    public static void eliminarArchivos(String ruta) {
        Path folderPath = Paths.get(ruta);
        try {
            if (Files.exists(folderPath) && Files.isDirectory(folderPath)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                    for (Path entry : stream) {
                        if (Files.isRegularFile(entry)) {
                            Files.delete(entry);
                        }
                    }
                }
            } else {
                System.out.println("La carpeta no existe o no es una carpeta.");
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar archivos: " + e.getMessage());
        }
    }
}
