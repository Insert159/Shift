package CFT;

import java.util.List;

/**
 * Класс Checker проверяет предварительно отсортированны ли данные согласно условию
 */
public class Checker {

    public static void updatingSortedDataInFile(List<String> file) {
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

    private static boolean checkSortedDataInFile(List<String> file, int currentIndex) {
        try {
            if (Starter.getType().equalsIgnoreCase("s")) {
                int numSign = file.get(currentIndex).compareTo(file.get(currentIndex + 1));
                if (Starter.isSort()) {
                    if (numSign >= 0) return true;
                } else {
                    if (numSign < 0) return true;
                }
            } else {
                int currentValue = Integer.parseInt(file.get(currentIndex));
                int nextValue = Integer.parseInt(file.get(currentIndex + 1));

                if (!Starter.isSort()) {
                    if (currentValue < nextValue) return true;
                } else {
                    if (currentValue > nextValue) return true;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Выход за пределы массива. " +
                    "Указан неверный индекс для списка с данными.");
            System.exit(203);
        }
        return false;
    }
}
