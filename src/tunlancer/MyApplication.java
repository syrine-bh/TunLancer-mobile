package tunlancer;

import com.codename1.components.ToastBar;
import com.codename1.io.websocket.WebSocket;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import org.json.JSONObject;
import tunlancer.Views.WalkthruForm;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;
    private int connectedId = 0;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        Toolbar.setGlobalToolbar(false);

    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        connectedId = 1;
        final WebSocket socket = new WebSocket("ws://192.168.1.5:3000") {

            @Override
            protected void onOpen() {
                System.out.println("connection established");
            }

            @Override
            protected void onClose(int statusCode, String reason) {
            }

            @Override
            protected void onMessage(String message) {
                showNotifications(message);
            }

            @Override
            protected void onError(Exception ex) {
            }

            @Override
            protected void onMessage(byte[] message) {
            }

        }.autoReconnect(5000);
        socket.connect();
        new WalkthruForm(theme).show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }

    public void destroy() {
    }

    public void showNotifications(String message) {
        JSONObject json = new JSONObject(message);
        
        String idSender = json.getString("idSender");
        String idReceiver = json.getString("idReceiver");
        String link = json.getString("link");
        String content = json.getString("content");
        
        String coonectedId = (String) String.valueOf(connectedId);
        System.out.println("idReceiver from socket :" + idReceiver);
        System.out.println("connectedId :" + connectedId);
        if (idReceiver.equals(coonectedId)) {
            ToastBar.showMessage("Nouvelle notification : \n" + content, FontImage.MATERIAL_INFO);
        } else {
            System.out.println("not for you");
        }

    }

}
