package CFT;


/**
 * Класс Main - вызывющий класс
 */
public class Main {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.fillStarter();
        DataMaker.createPaths();
        DataMaker.fillTheFiles();
        NewReader.readTheFiles();
        Merger.mergeSorting();
        // TODO Через аргументы командной строки!!!!!


    }
}
