package resources.configs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    private static final String CONFIG_PATH = "src\\resources\\configFile.txt";

    public static Map<String, String> loadConfig() throws Exception {
        Map<String, String> configFileMap = new HashMap<>();
        FileReader reader = new FileReader(CONFIG_PATH);
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (bufferedReader.ready()) {
            String param = bufferedReader.readLine(); //считали конфиг 1 парам
            String[] keyValue = param.split("="); // в конфиге
            configFileMap.put(keyValue[0], keyValue[1]);
        }
        bufferedReader.close();
        return configFileMap;
    };
}


