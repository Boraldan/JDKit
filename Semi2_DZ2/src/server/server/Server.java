package server.server;

import server.client.Client;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerView serverView;
    private MessageRepo messageRepo;
    private List<Client> clientList;
    private boolean work;

    public Server(ServerView serverView, MessageRepo messageRepo) {
        this.serverView = serverView;
        this.messageRepo = messageRepo;
        clientList = new ArrayList<>();
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        serverView.clientAction("Подключен: " + client.getName());
        return true;
    }

    public String getHistory() {
        return readLog();
    }

    public void disconnectUser(Client client) {     //  изменил сам метод
        if (client != null & clientList.remove(client)) {
            client.disconnect();
            serverView.clientAction("Отключен: " + client.getName());
        }
    }

    public void sendMessage(String text) {
        if (!work) {
            return;
        }
//        text += "";
        serverView.appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text) {
        for (Client client : clientList) {
            client.serverAnswer(text);
        }
    }

    private void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(messageRepo.getLOG_PATH(), true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(messageRepo.getLOG_PATH())) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startServer() {
        if (work) {
            serverView.appendLog("Сервер уже был запущен");
        } else {
            work = true;
            serverView.appendLog("Сервер запущен!");
        }
    }

    public void stopServer() {
        if (!work) {
            serverView.appendLog("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clientList.isEmpty()) {
                disconnectUser(clientList.get(clientList.size() - 1));
            }
            serverView.appendLog("Сервер остановлен!");
        }
    }

}
