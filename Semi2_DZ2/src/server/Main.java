package server;
import server.client.ClientGUI;
import server.client.Person;
import server.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        new ClientGUI(serverGUI.getServer(), new Person("192.168.0.1", "8808", "Alex", "123"));
        new ClientGUI(serverGUI.getServer(), new Person("192.168.0.2", "8808", "Alis", "321"));

    }
}
