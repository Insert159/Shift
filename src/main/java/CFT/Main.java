package CFT;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.fillStarter();
        starter.createPaths();
        starter.createFiles();
        starter.fillTheFiles();
        starter.readTheFiles();
        starter.print();
        starter.readFiles();
        starter.mergeSorting();


    }
}
