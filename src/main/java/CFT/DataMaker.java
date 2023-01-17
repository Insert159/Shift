package CFT;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Класс DataMaker создает входящие/исходящие файлы, пути их сохранения,
 * а также заполняет исходящие файлы данными через консоль
 */
public class DataMaker {

    // Метод createPaths() создает пути, по которым создадутся файлы и сами исходные файлы
    public static void createPaths() {
        try {
            Path dir = Files.createDirectories(Paths.get("path", "to", "files"));

            for (int i = 0; i < Starter.getFileNameIn().size(); i++) {
                String strIn = dir + "\\" + Starter.getFileNameIn().get(i);
                Starter.getPathsIn().add(strIn);
            }
            String strOut = dir + "\\" + Starter.getFileNameOut();
            Starter.setPathOut(strOut);
        } catch (IOException e) {
            System.out.println("Нет доступа к пути создания файлов");
        }
    }



    // Метод fillTheFiles() наполняет исходящие файлы данными, принимая @param PathsIn
// коллекцию путей исходящих файлоав

    public static void fillTheFiles() {
        System.out.println("Введите входные данные. После окончания ввода данных, введите \"quit\"");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (String path : Starter.getPathsIn()) {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path),
                    StandardCharsets.UTF_8)) {
                System.out.println("Начинаем запись в файл: " + path);
                String line;
                while (!"quit".equalsIgnoreCase(line = bufferedReader.readLine())) {
                    bufferedWriter.append(line);
                    bufferedWriter.append("\n");
                }
            } catch (IOException e) {
                System.err.println("Нет доступа к пути " + path);
                e.printStackTrace();
            }
        }
    }
}

