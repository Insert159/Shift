package CFT;

import java.util.ArrayList;

public class Merger {
    public static void mergeSorting() {
        while (Starter.getFilesWithData().size() > 1) {
            int sizeDataFromFiles = Starter.getFilesWithData().size();
            ArrayList<String> tempData = new ArrayList<>();

            while (Starter.getFilesWithData().get(sizeDataFromFiles - 1).size() > 0 && Starter.getFilesWithData().get(sizeDataFromFiles - 2).size() > 0) {
                String value1 = Starter.getFilesWithData().get(sizeDataFromFiles - 1).get(0);
                String value2 = Starter.getFilesWithData().get(sizeDataFromFiles - 2).get(0);

                if (Starter.getType().equalsIgnoreCase("s")) {
                    int numSign = value1.compareTo(value2);

                    if (Starter.isSort()) {
                        if (numSign <= 0) {
                            tempData.add(value1);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                    else {
                        if (numSign >= 0) {
                            tempData.add(value1);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                }
                else {
                    int number1 = Integer.parseInt(value1);
                    int number2 = Integer.parseInt(value2);

                    if (Starter.isSort()) {
                        if (number1 <= number2) {
                            tempData.add(value1);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                    else {
                        if (number1 >= number2) {
                            tempData.add(value1);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 1).remove(0);
                        }
                        else {
                            tempData.add(value2);
                            Starter.getFilesWithData().get(sizeDataFromFiles - 2).remove(0);
                        }
                    }
                }
            }

            while (Starter.getFilesWithData().get(sizeDataFromFiles - 1).size() > 0) {
                tempData.add(Starter.getFilesWithData().get(sizeDataFromFiles - 1).get(0));
                Starter.getFilesWithData().get(sizeDataFromFiles - 1).remove(0);
            }

            while (Starter.getFilesWithData().get(sizeDataFromFiles - 2).size() > 0) {
                tempData.add(Starter.getFilesWithData().get(sizeDataFromFiles - 2).get(0));
                Starter.getFilesWithData().get(sizeDataFromFiles - 2).remove(0);
            }

            Starter.getFilesWithData().set(sizeDataFromFiles - 2, tempData);
            Starter.getFilesWithData().remove(sizeDataFromFiles - 1);
        }

        NewWriter.writeFile(Starter.getFilesWithData().get(0), Starter.getFileNameOut());

        System.out.println("Сортировка слиянием произведена успешно.");
    }
}
