package CFT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Starter {
    private boolean sort;
    private String type;
    private String nameOut;
    private ArrayList<String> nameIn = new ArrayList<>();


    public Starter() {
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameOut() {
        return nameOut;
    }

    public void setNameOut(String nameOut) {
        this.nameOut = nameOut;
    }

    public ArrayList<String> getNameIn() {
        return nameIn;
    }

    public void setNameIn(ArrayList<String> nameIn) {
        this.nameIn = nameIn;
    }

    public void fillStarter() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите режим сортировки (\"a\" - возрастающая, по умолчанию; \"d\" - убывающая)");
        try {
            String marker = bufferedReader.readLine();
            if (marker.equals("d")) {
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
            nameOut = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

        System.out.println("Введите имена входных файлов, после ввода всех файлов введите \"Enter\"");
        try {
            do {
                String s = bufferedReader.readLine();
                nameIn.add(s);
            }
            while (!nameIn.contains("Enter"));// TODO Создается файл "Enter" и бросает ошибку
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

    }

    public void createFiles(ArrayList<String> names) {
        try {
            for (String s : names) {
                String str = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + s;
                Path path = Path.of(str).toAbsolutePath();
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Что то не так с входными именами"); // TODO что за искоючение?
        }

        String str = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + getNameOut();
        Path path = Path.of(str).toAbsolutePath();
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Что то не так с выходными именами"); // TODO что за искоючение?
        }

    }
}




