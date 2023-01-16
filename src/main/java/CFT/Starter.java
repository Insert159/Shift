package CFT;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Starter {
    private static boolean sort;
    private static String type;
    private static String fileNameOut;
    private static ArrayList<String> fileNameIn = new ArrayList<>();
    private static ArrayList<String> pathsIn = new ArrayList<>();
    private static String pathOut;
    private static ArrayList<String> filesIO = new ArrayList<>(); //TODO нам нужна эта переменная?
    private static ArrayList<ArrayList<String>> filesWithData = new ArrayList<>();
    Scanner scanner; // TODO убери сканер, всё через БР

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

    public void setType(String type) {
        Starter.type = type;
    }

    public static String getFileNameOut() {
        return fileNameOut;
    }

    public void setFileNameOut(String fileNameOut) {
        Starter.fileNameOut = fileNameOut;
    }

    public static ArrayList<String> getFileNameIn() {
        return fileNameIn;
    }

    public void setFileNameIn(ArrayList<String> fileNameIn) {
        Starter.fileNameIn = fileNameIn;
    }

    public static ArrayList<String> getPathsIn() {
        return pathsIn;
    }

    public void setPathsIn(ArrayList<String> pathsIn) {
        Starter.pathsIn = pathsIn;
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

    public void setFilesIO(ArrayList<String> filesIO) {
        Starter.filesIO = filesIO;
    }

    public static ArrayList<ArrayList<String>> getFilesWithData() {
        return filesWithData;
    }

    public void setFilesWithData(ArrayList<ArrayList<String>> filesWithData) {
        Starter.filesWithData = filesWithData;
    }

    //TODO убери лишние геттеры\сеттеры


    public void fillStarter() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите режим сортировки (\"a\" - возрастающая, по умолчанию; \"d\" - убывающая)");
        try {
            String marker = bufferedReader.readLine();
            if (marker.equals("a")) {
                setSort(true);
            }

        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

        System.out.println("Введите тип данных");
        try {
            type = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

        System.out.println("Введите имя выходного файла");
        try {
            fileNameOut = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

        System.out.println("Введите имена входных файлов, после ввода всех файлов введите \"Enter\"");
        try {
            do {
                String s = bufferedReader.readLine();
                fileNameIn.add(s);
            }
            while (!fileNameIn.contains("Enter"));
            fileNameIn.remove("Enter"); // TODO это костыль
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

    }

}





