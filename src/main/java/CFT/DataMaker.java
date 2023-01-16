package CFT;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Класс DataMaker создает входящие/исходящие файлы, пути их сохранения,
 * а также заполняет исходящие файлы данными через консоль
 */
public class DataMaker {

    // Метод createPaths() создает пути, по которым создадутся файлы. В конце вызывет
    // метод createFiles для создания файлов
    public static void createPaths() {

        for (int i = 0; i < Starter.getFileNameIn().size(); i++) {
            String strIn = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + Starter.getFileNameIn().get(i);
            Starter.getPathsIn().add(strIn);
        }

        Starter.setPathOut("C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + Starter.getFileNameOut());
        // TODO сделай универсальные пути

        createFiles();
    }

    // Метод createFiles() создает файлы для чтения и записи. Вконце вызывает
// метод fillTheFiles(), для заполнения исходящих файлов данными
    public static void createFiles() {

        // Создание/поиск выходного файла и проверка на запись в файл
        File fileOutput = new File(Starter.getPathOut());
        try {
            if (fileOutput.createNewFile()) {
                System.out.println("Файл " + Starter.getFileNameOut() + " успешно создан");
            } else {
                System.out.println("Файл " + Starter.getFileNameOut() + " уже существует");
                if (fileOutput.canWrite()) {
                    System.out.println("Доступ на запись в файл есть. Информация будет перезаписана");
                } else {
                    System.out.println("Доступ на запись в файл отсутствует");
                    Starter.getPathsIn().clear();
                    System.exit(200);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }

        // Создание/поиск входного файла и проверка на чтение из файла
        for (int i = 0; i < Starter.getPathsIn().size(); ) {
            String filePathIn = Starter.getPathsIn().get(i);
            File fileInput = new File(filePathIn);

            try {
                if (fileInput.createNewFile()) {
                    System.out.println("Файл " + Starter.getFileNameIn().get(i) + " успешно создан");
                } else {

                    if (fileInput.canRead()) {
                        System.out.println("Доступ на чтение из файла есть");
                        i++;
                    } else {
                        System.out.println("Доступ на чтение из файла отсутствует. Файл будет удалён из списка");
                        Starter.getPathsIn().remove(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Starter.getPathsIn().size() < 2) {
            System.out.println("Минимальный набор доступных входных " +
                    "данных - 2 и более файла");
            Starter.getPathsIn().clear();
            System.exit(201);
        }
    }

    // Метод fillTheFiles() наполняет исходящие файлы данными, принимая @param PathsIn
// коллекцию путей исходящих файлоав
    public static void fillTheFiles() {
        System.out.println("Введите входные данные:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (String path : Starter.getPathsIn()) {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8)) {
                String line;
                while (!"quit".equalsIgnoreCase(line = bufferedReader.readLine())) {
                    bufferedWriter.append(line);
                    bufferedWriter.append("\n");
                }
            } catch (IOException e) {
                System.err.println("Нет доступа к пути " + path); 
            }
        }
    }
}

