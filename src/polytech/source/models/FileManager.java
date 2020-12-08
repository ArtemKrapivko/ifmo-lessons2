package polytech.source.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<String> getRowsFromFiles(File srcFile) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(srcFile))) {
            List<String> productsList = new ArrayList<>(); //создаем список пустой

            while (br.ready()) {
                String row = br.readLine();
                productsList.add(row);

            }
            return productsList;
        }
    }
}
