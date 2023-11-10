package server.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    JButton btnStart, btnStop;
    JTextArea log;
    private Server server;

    public ServerGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        server = new Server(this, new MesRepo());
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
@Override
    public void appendLog(String text) {
        log.append(text + "\n");
    }
    @Override
    public void clientAction(String text){
        log.append(text + "\n");
    }

    public Server getServer() {
        return server;
    }
}
