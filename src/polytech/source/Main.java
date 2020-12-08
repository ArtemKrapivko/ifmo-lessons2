package polytech.source;

import java.io.FileNotFoundException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello, world");
        System.out.println(new Date(System.currentTimeMillis()));

        PropertyFile pf = new PropertyFile();

//        pf.get("prop1");
//        pf.set("prop2", "value2");
//        pf.set("prop3", "value3");
//        pf.save("my.props");
    }
}