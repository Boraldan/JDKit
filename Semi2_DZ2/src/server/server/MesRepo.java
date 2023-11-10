package server.server;

public class MesRepo implements MessageRepo {
    private  String LOG_PATH = "Semi2_DZ2/src/server/logMessage.txt";
    public String getLOG_PATH() {
        return LOG_PATH;
    }

}
