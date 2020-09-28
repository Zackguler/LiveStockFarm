package data;

public class SystemAdapter {
    private static SystemAdapter systemAdapter;

    private SystemAdapter() {

    }

    public static SystemAdapter instance() {
        if (systemAdapter == null) {
            systemAdapter = new SystemAdapter();
        }
        return systemAdapter;
    }

    public void errPrint(String message){
        System.err.println(message);
    }

    public void outPrint(String message){
        System.out.println(message);
    }
}
