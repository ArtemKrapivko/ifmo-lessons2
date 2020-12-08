package polytech.source;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PropertyFile {
    private Map<String, String> props = new HashMap<>();

    private File originFile;

    public PropertyFile() {
    }

    public PropertyFile(String srcFile) throws IOException {
        this(new File(srcFile));
    }

    public PropertyFile(File srcFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(srcFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                if (!s.isEmpty() && !s.startsWith("#")) {
                    String[] a = s.split(";", 2);
                    if (a.length == 1)
                        throw new IOException("Wrong file format.");
                    props.put(a[0], a[1]);
                }
            }
        }
        originFile = srcFile;
    }

    public String get(String propertyName) {
        return props.get(propertyName);
    }

    public void set(String propertyName, String value) {
        props.put(propertyName, value);
    }


    public void remove(String propertyName) {
        props.remove(propertyName);
    }

    public boolean contains(String propertyName) {
        return props.containsKey(propertyName);
    }

    public void save() throws FileNotFoundException {
        if (originFile != null)
            save(originFile);
        else
            throw new IllegalStateException("Origin file is unknown.");
    }

    public void save(String targetFile) throws FileNotFoundException {
        save(new File(targetFile));
    }

    public void save(File targetFile) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(targetFile)) {
            for (Map.Entry<String, String> kv : props.entrySet())
                pw.println(kv.getKey() + "=" + kv.getValue());
        }
        originFile = targetFile;
    }
}


