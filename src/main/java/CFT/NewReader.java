package CFT;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NewReader {

// TODO тут что, 2 одинаковых метода?



    public static void readTheFiles() {
        System.out.println("Считываем данные с входных файлов...");
        for (int i = 0; i < Starter.getPathsIn().size(); i++) {
            {
                try (BufferedReader reader = new BufferedReader(new FileReader(Starter.getPathsIn().get(i)))) {
                    while (reader.ready()) {
                        if (Starter.getType().equalsIgnoreCase("i")) {
                            String str = reader.readLine();
                            try {
                                Starter.getFilesIO().add(str);
                            } catch (NumberFormatException e) {
                                System.out.println("Найдены недопустимые данные: " + str);
                                System.out.println("Данные файла " + Starter.getFileNameIn().get(i) +
                                        " отсортированы не будут");
                                break; //TODO убери break
                            }
                        } else if (Starter.getType().equalsIgnoreCase("s")) {
                            String str = reader.readLine();
                            if (str.indexOf(" ") > 0) {
                                System.out.println("Найдены недопустимые данные: " + str);
                                System.out.println("Данные файла " + Starter.getFileNameIn().get(i) +
                                        " отсортированы не будут");
                                break; //TODO убери break
                            }
                            Starter.getFilesIO().add(str);

                        } else {
                            System.out.println("Введен неверный формат типов данных");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Checker.updatingSortedDataInFile(Starter.getFilesIO());
            Starter.getFilesWithData().add(Starter.getFilesIO());
        }
    }


/*
    public static void readFiles() {
        for (String file : Starter.getPathsIn()) {
            ArrayList<String> sortableDataFromFile = new ArrayList<>();


            try {
                FileInputStream inputStream = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(inputStream, "cp1251"));
            }
            catch (FileNotFoundException e) {
                System.out.println("Не удалось найти файл.");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    if (!br.ready()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String data = null;
                try {
                    data = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (data == null) continue;

                sortableDataFromFile.add(data);
            }

            Checker.updatingSortedDataInFile(sortableDataFromFile);

            Starter.getFilesWithData().add(sortableDataFromFile);
        }

    }
*/
}
