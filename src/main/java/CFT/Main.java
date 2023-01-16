package CFT;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.fillStarter();
        DataMaker.createPaths();
        DataMaker.createFiles();
        DataMaker.fillTheFiles();
        NewReader.readTheFiles();
        NewReader.readFiles();
        Merger.mergeSorting();
        // TODO переопредели методы
        // TODO сделай описание каждому классу
        // TODO Readme, офрмление и тд

    }
}
