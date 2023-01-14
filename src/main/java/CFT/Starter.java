package CFT;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Starter {
    private boolean sort;
    private String type;
    private String fileNameOut;
    private ArrayList<String> fileNameIn = new ArrayList<>();
    private ArrayList<String> pathsIn = new ArrayList<>();
    private String pathOut;
    private ArrayList<String> filesIO = new ArrayList<>();
    private ArrayList<ArrayList<String>> filesWithData = new ArrayList<>();
    Scanner scanner;

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

    public ArrayList<String> getFilesIO() {
        return filesIO;
    }

    public void setFilesIO(ArrayList<String> filesIO) {
        this.filesIO = filesIO;
    }

    public ArrayList<ArrayList<String>> getFilesWithData() {
        return filesWithData;
    }

    public void setFilesWithData(ArrayList<ArrayList<String>> filesWithData) {
        this.filesWithData = filesWithData;
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





    public void readFiles() {
        for (String file : getPathsIn()) {
            ArrayList<String> sortableDataFromFile = new ArrayList<>();


            try {
                FileInputStream inputStream = new FileInputStream(file);
                scanner = new Scanner(inputStream, "cp1251");
            }
            catch (FileNotFoundException e) {
                System.out.println("Не удалось найти файл.");
            }

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                if (data == null) continue;

                sortableDataFromFile.add(data);
            }

            updatingSortedDataInFile(sortableDataFromFile);

            getFilesWithData().add(sortableDataFromFile);
        }

    }

    private void updatingSortedDataInFile(List<String> file) {
        if (file.size() < 2) return;

        for (int index = 0; index < file.size() - 1; index++) {
            if (checkSortedDataInFile(file, index)) {
                System.out.println("Данные в файле отсортированы неверно. " +
                        "Файл не будет участвовать в сортировке.");
                while (index < file.size()) {
                    file.remove(index);
                }
                return;
            }
        }
    }

    private boolean checkSortedDataInFile(List<String> file, int currentIndex) {
        try {
            if (getType().equalsIgnoreCase("s")) {
                int numSign = file.get(currentIndex).compareTo(file.get(currentIndex + 1));
                if (isSort()) {
                    if (numSign >= 0) return true;
                } else {
                    if (numSign < 0) return true;
                }
            } else {
                int currentValue = Integer.parseInt(file.get(currentIndex));
                int nextValue = Integer.parseInt(file.get(currentIndex + 1));

                if (isSort()) {
                    if (currentValue > nextValue) return true;
                } else {
                    if (currentValue < nextValue) return true;
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива. " +
                    "Указан неверный индекс для списка с данными.");
            System.exit(203);
        }
        return false;
    }


    public void mergeSorting() {
        while (filesWithData.size() > 1) {
            int sizeDataFromFiles = filesWithData.size();
            ArrayList<String> tempData = new ArrayList<>();

            while (filesWithData.get(sizeDataFromFiles - 1).size() > 0 && filesWithData.get(sizeDataFromFiles - 2).size() > 0) {
                String value1 = filesWithData.get(sizeDataFromFiles - 1).get(0);
                String value2 = filesWithData.get(sizeDataFromFiles - 2).get(0);

                if (getType().equalsIgnoreCase("s")) {
                    int numSign = value1.compareTo(value2);

                    if (isSort()) {
                        if (numSign <= 0) {
                            tempData.add(value1);
                            filesWithData.get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            filesWithData.get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                    else {
                        if (numSign >= 0) {
                            tempData.add(value1);
                            filesWithData.get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            filesWithData.get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                }
                else {
                    int number1 = Integer.parseInt(value1);
                    int number2 = Integer.parseInt(value2);

                    if (isSort()) {
                        if (number1 <= number2) {
                            tempData.add(value1);
                            filesWithData.get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            filesWithData.get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                    else {
                        if (number1 >= number2) {
                            tempData.add(value1);
                            filesWithData.get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            filesWithData.get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                }
            }

            while (filesWithData.get(sizeDataFromFiles - 1).size() > 0) {
                tempData.add(filesWithData.get(sizeDataFromFiles - 1).get(0));
                filesWithData.get(sizeDataFromFiles - 1).remove(0);
            }

            while (filesWithData.get(sizeDataFromFiles - 2).size() > 0) {
                tempData.add(filesWithData.get(sizeDataFromFiles - 2).get(0));
                filesWithData.get(sizeDataFromFiles - 2).remove(0);
            }

            filesWithData.set(sizeDataFromFiles - 2, tempData);
            filesWithData.remove(sizeDataFromFiles - 1);
        }

        writeFile(filesWithData.get(0), fileNameOut);

        System.out.println("Сортировка слиянием произведена успешно.");
    }


    private void writeFile(List<String> sortedData, String fileName) {
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





