package CFT;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Starter {
    private boolean sort;
    private String type;
    private String fileNameOut;
    private ArrayList<String> fileNameIn = new ArrayList<>();
    private ArrayList<String> pathsIn = new ArrayList<>();
    private String pathOut;
    private static ArrayList<String> filesIO = new ArrayList<>();
    static ArrayList<ArrayList<String>> data = new ArrayList<>();

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

    public String getFileNameOut() {
        return fileNameOut;
    }

    public void setFileNameOut(String fileNameOut) {
        this.fileNameOut = fileNameOut;
    }

    public ArrayList<String> getFileNameIn() {
        return fileNameIn;
    }

    public void setFileNameIn(ArrayList<String> fileNameIn) {
        this.fileNameIn = fileNameIn;
    }

    public ArrayList<String> getPathsIn() {
        return pathsIn;
    }

    public void setPathsIn(ArrayList<String> pathsIn) {
        this.pathsIn = pathsIn;
    }

    public String getPathOut() {
        return pathOut;
    }

    public void setPathOut(String pathOut) {
        this.pathOut = pathOut;
    }

    public static ArrayList<String> getFilesIO() {
        return filesIO;
    }

    public static void setFilesIO(ArrayList<String> filesIO) {
        Starter.filesIO = filesIO;
    }

    public static ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public static void setData(ArrayList<ArrayList<String>> data) {
        Starter.data = data;
    }

    //TODO убери лишние геттеры\сеттеры


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
            fileNameIn.remove("Enter");
        } catch (IOException e) {
            System.out.println("Не корретный формат ввода");
        }

    }


    public void createPaths() {

        for (int i = 0; i < getFileNameIn().size(); i++) {
            String strIn = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + getFileNameIn().get(i);
            pathsIn.add(strIn);
        }

        pathOut = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + getFileNameOut();

    }


    public void createFiles() {

        // Поиск/создание выходного файла
        File fileOutput = new File(pathOut);
        try {
            if (fileOutput.createNewFile()) {
                System.out.println("Файл " + fileNameOut + " успешно создан");
            } else {
                System.out.println("Файл " + fileNameOut + " уже существует");

                if (fileOutput.canWrite()) {
                    System.out.println("Доступ на запись в файл есть. Информация будет перезаписана");
                } else {
                    System.out.println("Доступ на запись в файл отсутствует");
                    pathsIn.clear();
                    System.exit(200);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Поиск входных файлов
        for (int i = 0; i < pathsIn.size(); ) {
            String filePathIn = pathsIn.get(i);
            File fileInput = new File(filePathIn);

            try {
                if (fileInput.createNewFile()) {
                    System.out.println("Файл " + fileNameIn.get(i) + " успешно создан");
                } else {

                    if (fileInput.canRead()) {
                        System.out.println("Доступ на чтение из файла есть");
                        i++;
                    } else {
                        System.out.println("Доступ на чтение из файла отсутствует. Файл будет удалён из списка");
                        pathsIn.remove(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (pathsIn.size() < 2) {
            System.out.println("Не обнаружен минимальный набор доступных входных и выходных данных (2 и более файла)");
            pathsIn.clear();
            System.exit(201);
        }
    }


    public void fillTheFiles() {
        System.out.println("Введите входные данные:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (String path : pathsIn) {
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

    public void readTheFiles() {
        System.out.println("Считываем данные с входных файлов...");
        for (int i = 0; i < pathsIn.size(); i++) {
            {
                try (BufferedReader reader = new BufferedReader(new FileReader(pathsIn.get(i)))) {
                    while (reader.ready()) {
                        if (getType().equalsIgnoreCase("i")) {
                            String str = reader.readLine();
                            try {
                                int result = Integer.parseInt(str);
                                getFilesIO().add(str);
                            } catch (NumberFormatException e) {
                                System.out.println("Найдены недопустимые данные: " + str);
                                System.out.println("Данные файла " + getFileNameIn().get(i) +
                                        " отсортированы не будут");
                                break; //TODO убери break
                            }
                        } else if (getType().equalsIgnoreCase("s")) {
                            String str = reader.readLine();
                            if (str.indexOf(" ") > 0) {
                                System.out.println("Найдены недопустимые данные: " + str);
                                System.out.println("Данные файла " + getFileNameIn().get(i) +
                                        " отсортированы не будут");
                                break; //TODO убери break
                            }
                            getFilesIO().add(str);
                        } else {
                            System.out.println("Введен неверный формат типов данных");
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void print(){
        for (String s: getFilesIO()) {
            System.out.println(s);

        }
    }
}





