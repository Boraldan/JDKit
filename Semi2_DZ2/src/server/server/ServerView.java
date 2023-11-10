package server.server;

public interface ServerView {
   void appendLog(String text);
   void clientAction(String text);
}
