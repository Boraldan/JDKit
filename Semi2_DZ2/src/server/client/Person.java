package server.client;

import javax.swing.*;

public class Person {
    private String IPAddress;
    private String port;
    private String login;
    private String password;

    public Person(String IPAddress, String port, String login, String password) {
        this.IPAddress = IPAddress;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getPort() {
        return port;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
