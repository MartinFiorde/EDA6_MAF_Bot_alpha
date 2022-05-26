package com.maf.connection;

import java.net.URI;
import java.net.URISyntaxException;
import com.maf.DTO.MessageDTO;
import com.maf.ia.Reader;
import com.maf.utils.*;

public class Connection {

    private Reader reader = new Reader();
    private WebSocketClient clientEndPoint;
    private final String root = "wss://4yyity02md.execute-api.us-east-1.amazonaws.com/ws?token=";
    // token de usuario de prueba. Usar el personal en la prueba final.
    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiTUFGIn0.9xcmdu0KvKUzLVfGhm-nxfv9Pri4_yejbXQLPdXyOOQ";
    private String url;

    // Constructor que se encarga de desencadenar la app, seteando la URL y dandole inicio a la app
    public Connection() {
        setUrl();
        start();
    }

    // Genera la URL en base a los atributos de la clase
    private void setUrl() {
        url = root.concat(token);
    }

    public String getUrl() {
        return url;
    }

    // Gestiona los mensajes recibidos por parte del socket, para determinar el accionar especifico en base a cada mensaje
    public void start() {
        try {
            clientEndPoint = new WebSocketClient(new URI(url));
            clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
                
                public void handleMessage(String msj) {
                    MessageDTO message = Util.JSONToObject(msj);
                    String answer = null;

                    if (!msj.contains("list_users")) {
                        System.out.println(message);
                    }
                    // mensajes de challenge
//                    if (msj.contains("your_turn")) {
//                        reader.showMatrix(reader.makeMatrix(message));
//                    }

                    // Get Challenge > Accept
                    if (msj.contains("event") && msj.contains("challenge") && msj.contains("MAF")) {
                        answer = Util.acceptChallenge(message.getData().getChallenge_id());
                        System.out.println(Util.JSONToObject(answer).toString());
                        clientEndPoint.sendMessage(answer);
                    }

                    // Get Your_Turn > Do Preset Move
//                    if (msj.contains("event") && msj.contains("your_turn")) {
//                        answer = Util.dummyMove(message.getData().getRemaining_moves(), message.getData().getGame_id(), message.getData().getTurn_token());
//                        System.out.println(Util.JSONToObject(answer).toString());
//                        clientEndPoint.sendMessage(answer);
//                    }
                   
                    // Get Your_Turn > Do Random Wall
//                    if (msj.contains("event") && msj.contains("your_turn")) {
//                        String infoSent = Util.dummyWall(message.getData().getGame_id(), message.getData().getTurn_token());
//                        System.out.println(Util.JSONToObject(infoSent).toString());
//                        clientEndPoint.sendMessage(infoSent);
//                    }

                    // Get Your_Turn > Do Best Move
                    if (msj.contains("event") && msj.contains("your_turn")) {
                        String infoSent = Util.makeBestMove(message.getData().getGame_id(), message.getData().getTurn_token(), message);
                        System.out.println(Util.JSONToObject(infoSent).toString());
                        clientEndPoint.sendMessage(infoSent);
                    }

                }
            });
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
