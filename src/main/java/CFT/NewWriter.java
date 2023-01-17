package CFT;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class NewWriter {


    public static void writeFile(List<String> sortedData, String fileName) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            for (String data : sortedData) {
                fileOutputStream.write(data.getBytes(Charset.forName("cp1251")), 0, data.length());
                fileOutputStream.write("\n".getBytes());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл.");
        }
        catch (IOException e) {
            System.out.println("Не удалось произвести запись в файл.");
        }
    }
}
