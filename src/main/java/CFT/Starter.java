package CFT;

import java.io.*;
import java.util.ArrayList;


/**
 * Класс Starter принимает через консоль данные приватного
 * поля этого класса для создания входящих/исходящих
 * папок и файлов, а также методы их сортировки и тип сохраняемых данных
 */
public class Starter {
    private static boolean sort;
    private static String type;
    private static String fileNameOut;
    private static final ArrayList<String> fileNameIn = new ArrayList<>();
    private static final ArrayList<String> pathsIn = new ArrayList<>();
    private static String pathOut;
    private static final ArrayList<String> filesIO = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> filesWithData = new ArrayList<>();

    public Starter() {
    }


    public static boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        Starter.sort = sort;
    }

    public static String getType() {
        return type;
    }

    public static String getFileNameOut() {
        return fileNameOut;
    }


    public static ArrayList<String> getFileNameIn() {
        return fileNameIn;
    }


    public static ArrayList<String> getPathsIn() {
        return pathsIn;
    }


    public static String getPathOut() {
        return pathOut;
    }

    public static void setPathOut(String pathOut) {
        Starter.pathOut = pathOut;
    }

    public static ArrayList<String> getFilesIO() {
        return filesIO;
    }


    public static ArrayList<ArrayList<String>> getFilesWithData() {
        return filesWithData;
    }

    // Метод вызывает консоль и заполняет приватное поле класса
    public void fillStarter() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean cycle = true;
            System.out.println("Введите режим сортировки (\"a\"(ascending)- возрастающая, " +
                    "по умолчанию(не обязательна к заполнению); \"d\" (descending)- убывающая)");
            while (cycle) {
                String marker = bufferedReader.readLine();
                if (marker.equalsIgnoreCase("a")) {
                    setSort(true);
                    cycle = false;
                } else if (marker.equalsIgnoreCase("d")) {
                    System.out.println("Выбран режим сортировки \"по убыванию\"");
                    cycle = false;
                } else
                    System.out.println("Неверный ввод данных, пожалуйста введите повторно.");
            }
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }
        try {
            boolean cycle = true;
            System.out.println("Введите тип данных для сортировки, " +
                    "i - для целых чисел или s - для строк");
            while (cycle) {
                type = bufferedReader.readLine();
                if (type.equalsIgnoreCase("i")) {
                    System.out.println("Выбран формат целых чисел");
                    cycle = false;
                } else if (type.equalsIgnoreCase("s")) {
                    System.out.println("Выбран формат строк");
                    cycle = false;
                } else System.out.println("Неверный ввод данных, пожалуйста введите повторно.");
            }
        } catch (IOException e) {
            System.out.println("Неверный формат ввода");
        }

        System.out.println("Введите имя выходного файла (с учетом расширения файла)");
        try {
            fileNameOut = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Неверный формат ввода");
        }

        System.out.println("Введите имена входных файлов (с учетом расширения файла), " +
                "после ввода всех файлов введите \"quit\"");
        try {
            do {
                String s = bufferedReader.readLine();
                fileNameIn.add(s);
            }
            while (!fileNameIn.contains("quit"));
            fileNameIn.remove("quit");
        } catch (IOException e) {
            System.out.println("Неверный формат ввода");
        }
    }

}





