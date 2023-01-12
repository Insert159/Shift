package CFT;

public class Test {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.fillStarter();
        starter.createFiles(starter.getNameIn());
    }
}
