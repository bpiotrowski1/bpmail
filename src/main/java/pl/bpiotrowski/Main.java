package pl.bpiotrowski;

public class Main {
    public static void main(String[] args) {
        OracleConnection connection = new OracleConnection();

        System.out.println("DB SIZE = " + connection.executeQuery() + "MB");
    }
}
