package CFT;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Starter {
    private boolean sort;
    private String type;
    private String nameOut;
    private ArrayList<String> nameIn = new ArrayList<>();
    private ArrayList<String> pathsIn = new ArrayList<>();
    private String pathOut;


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


    public void createPaths() {

        for (int i = 0; i< getNameIn().size(); i++) {
            String strIn = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + getNameIn().get(i);
            pathsIn.add(strIn);
        }

        pathOut = "C:\\Users\\Anna\\OneDrive\\Рабочий стол\\Java\\Shift\\Shift\\" + getNameOut();

    }



    public void createFiles() {

            // Поиск/создание выходного файла
            File fileOutput = new File(pathOut);
            try {
                if(fileOutput.createNewFile()){
                    System.out.println("Файл "+nameOut+" успешно создан");
                } else {
                    System.out.println("Файл " + nameOut + " уже существует");

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
            for (int i = 0; i < pathsIn.size();) {
                String filePathIn = pathsIn.get(i);
                File fileInput = new File(filePathIn);

                try {
                    if(fileInput.createNewFile()){
                        System.out.println("Файл "+nameIn.get(i)+" успешно создан");
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

    }





