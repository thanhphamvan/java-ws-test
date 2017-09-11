import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class WebClient {
    private Socket socket;

    private WebClient(Socket arg) {
        this.socket = arg;

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                socket.emit("ping");
            }
        }).on("login", new Emitter.Listener() {
            public void call(Object... objects) {
                JSONObject obj = (JSONObject) objects[0];
                System.out.println(obj);
            }
        }).on("new message", new Emitter.Listener() {
            public void call(Object... objects) {
                JSONObject obj = (JSONObject) objects[0];
                System.out.println(obj);
            }
        }).on("pong", new Emitter.Listener() {
            public void call(Object... objects) {
                System.err.println("PONG");
            }
        });

        socket.connect();
        //socket.emit("add user", "Khai");
    }

    public static WebClient createConn() throws URISyntaxException {
//        IO.Options opts = new IO.Options();
//
//        opts.port = 3000;

        Socket client = IO.socket("http://localhost:3000");

        return new WebClient(client);

    }
}
