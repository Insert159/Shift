package CFT;

import java.io.*;



/**
Класс NewReader считывает данные с входных вайлов и
 записывает их в коллекцию filesIO
 */
public class NewReader {

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
                                System.out.println("Недопустимые значения данных: " + str);
                                System.out.println("Данные " + Starter.getFileNameIn().get(i) +
                                        " не будут отсортированы");
                                break;
                            }
                        } else if (Starter.getType().equalsIgnoreCase("s")) {
                            String str = reader.readLine();
                            if (str.indexOf(" ") > 0) {
                                System.out.println("Недопустимые значения данных: " + str);
                                System.out.println("Данные " + Starter.getFileNameIn().get(i) +
                                        " не будут отсортированы");
                                break;
                            }
                            Starter.getFilesIO().add(str);

                        } else {
                            System.out.println("Неверный формат типов данных");
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
}
