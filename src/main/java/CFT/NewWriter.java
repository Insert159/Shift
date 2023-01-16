package CFT;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class NewWriter extends Writer {
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    //TODO переопредели методы

    public static void writeFile(List<String> sortedData, String fileName) {
        File file = new File(fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
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
