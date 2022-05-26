package com.maf.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maf.DTO.DataMessageDTO;
import com.maf.DTO.MessageDTO;
import com.maf.enums.Action;
import com.maf.ia.Finder;
import com.maf.ia.Reader;
import javax.print.DocFlavor;

public class Util {

    static Finder finder = new Finder();
    static Reader reader = new Reader();

    // Toma un Objeto para convertirlo en un String con formato JSON
    public static String objectToJSON(MessageDTO obj) {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        return GSON.toJson(obj, MessageDTO.class);
    }

    // Toma un String con formato JSON para convertirlo en un Objeto
    public static MessageDTO JSONToObject(String json) {
        Gson GSON = new GsonBuilder().serializeNulls().create();
        return GSON.fromJson(json, MessageDTO.class);
    }

    // Arma un JSON para solicitar los usuarios conectados, para mas informacion ver la documentacion del WebSocket
    // se puede borrar?
//    public static String getConnectedUsers() {
//        MessageDTO msj = new MessageDTO();
//        DataMessageDTO dataMsj = new DataMessageDTO();
//        msj.setAction(Action.GET_CONNECTED_USERS.getString());
//        msj.setData(dataMsj);
//        return objectToJSON(msj);
//    }

    // Arma un JSON para desafiar a un usuario, este usuario es provisto como argumento de la funcion, para mas informacion ver la documentacion del WebSocket
    // se puede borrar?
//    public static String challenge(String oponent) {
//        MessageDTO msj = new MessageDTO();
//        DataMessageDTO dataMsj = new DataMessageDTO();
//        dataMsj.setOpponent(oponent);
//        msj.setAction(Action.CHALLENGE.getString());
//        msj.setData(dataMsj);
//        return objectToJSON(msj);
//    }

    // Arma un JSON para aceptar cualquier solicitus entrante, para responder necesita como argumento el id del tablero, para mas informacion ver la documentacion del WebSocket
    public static String acceptChallenge(String challenge_id) {
        MessageDTO msj = new MessageDTO();
        DataMessageDTO dataMsj = new DataMessageDTO();
        dataMsj.setChallenge_id(challenge_id);
        msj.setAction(Action.ACCEPT_CHALLENGE.getString());
        msj.setData(dataMsj);
        return objectToJSON(msj);
    }

    public static String makeBestMove(String game_id, String turn_token, MessageDTO receivedMsj) {
        String turn = receivedMsj.getData().getRemaining_moves();
        System.out.println("rem moves: "+turn);
//        if (turn.equals(200.0) || turn.equals(199.0)) {
//            return tacticWall(game_id, turn_token, turn);
//        }
        
        MessageDTO msj = new MessageDTO();
        DataMessageDTO dataMsj = new DataMessageDTO();
        dataMsj.setGame_id(game_id);
        dataMsj.setTurn_token(turn_token);
        String bestMove = finder.findBestMove(reader.makeMatrix(receivedMsj));
        if (receivedMsj.getData().getSide().equals("N")) {
            addMoveIntoDataMsj(
                    dataMsj,
                    (8 - Integer.valueOf(bestMove.substring(0, 1))),
                    (8 - Integer.valueOf(bestMove.substring(1, 2))),
                    (8 - Integer.valueOf(bestMove.substring(2, 3))),
                    (8 - Integer.valueOf(bestMove.substring(3, 4))));
        } else {
            addMoveIntoDataMsj(
                    dataMsj,
                    Integer.valueOf(bestMove.substring(0, 1)),
                    Integer.valueOf(bestMove.substring(1, 2)),
                    Integer.valueOf(bestMove.substring(2, 3)),
                    Integer.valueOf(bestMove.substring(3, 4)));
        }

        msj.setAction(Action.MOVE.getString());
        msj.setData(dataMsj);
        return objectToJSON(msj);
    }

    public static String dummyMove(String turn, String game_id, String turn_token) {
        MessageDTO msj = new MessageDTO();
        DataMessageDTO dataMsj = new DataMessageDTO();
        dataMsj.setGame_id(game_id);
        dataMsj.setTurn_token(turn_token);
        switch ((int) Math.rint(Double.valueOf(turn))) {
            case 200:
                addMoveIntoDataMsj(dataMsj, 0, 1, 1, 1);
                break;
            case 198:
                addMoveIntoDataMsj(dataMsj, 1, 1, 2, 1);
                break;
            case 196:
                addMoveIntoDataMsj(dataMsj, 2, 1, 3, 1);
                break;
//            case 194:
//                addMoveIntoDataMsj(dataMsj, 3, 1, 4, 1);
//                break;
//            case 192:
//                addMoveIntoDataMsj(dataMsj, 4, 1, 5, 1);
//                break;
//            case 190:
//                addMoveIntoDataMsj(dataMsj, 5, 1, 6, 1);
//                break;
//            case 188:
//                addMoveIntoDataMsj(dataMsj, 6, 1, 7, 1);
//                break;
//            case 186:
//                addMoveIntoDataMsj(dataMsj, 7, 1, 7, 0);
//                break;
//            case 184:
//                addMoveIntoDataMsj(dataMsj, 7, 0, 8, 0);
//                break;
//            case 182:
//                addMoveIntoDataMsj(dataMsj, 0, 1, 0, 2);
//                break;
            case 199:
                addMoveIntoDataMsj(dataMsj, 8, 7, 7, 7);
                break;
            case 197:
                addMoveIntoDataMsj(dataMsj, 7, 7, 6, 7);
                break;
            case 195:
                addMoveIntoDataMsj(dataMsj, 6, 7, 5, 7);
                break;
//            case 193:
//                addMoveIntoDataMsj(dataMsj, 5, 7, 4, 7);
//                break;
//            case 191:
//                addMoveIntoDataMsj(dataMsj, 4, 7, 3, 7);
//                break;
//            case 189:
//                addMoveIntoDataMsj(dataMsj, 3, 7, 2, 7);
//                break;
//            case 187:
//                addMoveIntoDataMsj(dataMsj, 2, 7, 1, 7);
//                break;
//            case 185:
//                addMoveIntoDataMsj(dataMsj, 1, 7, 1, 6);
//                break;
//            case 183:
//                addMoveIntoDataMsj(dataMsj, 1, 6, 0, 6);
//                break;
//            case 181:
//                addMoveIntoDataMsj(dataMsj, 8, 7, 8, 6);
//                break;
            default:
                addMoveIntoDataMsj(dataMsj, 0, 0, 0, 0);
        }
        msj.setAction(Action.MOVE.getString());
        msj.setData(dataMsj);
        return objectToJSON(msj);
    }

    public static String dummyWall(String game_id, String turn_token) {
        MessageDTO msj = new MessageDTO();
        DataMessageDTO dataMsj = new DataMessageDTO();
        dataMsj.setRow((int) (Math.round(Math.random() * 7)));
        dataMsj.setCol((int) (Math.round(Math.random() * 7)));
        dataMsj.setOrientation("h");
        dataMsj.setGame_id(game_id);
        dataMsj.setTurn_token(turn_token);
        msj.setAction(Action.WALL.getString());
        msj.setData(dataMsj);
        return objectToJSON(msj);
    }
    
//    public static String tacticWall(String game_id, String turn_token, String turn) {
//        MessageDTO msj = new MessageDTO();
//        DataMessageDTO dataMsj = new DataMessageDTO();
//        
//        if (turn.equals(200.0)) {
//        dataMsj.setRow((int) (7));
//        dataMsj.setCol((int) (3));
//        } else {
//        dataMsj.setRow((int) (1));
//        dataMsj.setCol((int) (3));
//        }
//        dataMsj.setOrientation("v");
//        dataMsj.setGame_id(game_id);
//        dataMsj.setTurn_token(turn_token);
//        msj.setAction(Action.WALL.getString());
//        msj.setData(dataMsj);
//        return objectToJSON(msj);
//    }

    public static DataMessageDTO addMoveIntoDataMsj(DataMessageDTO dataMsj, int fromrow, int fromcol, int torow, int tocol) {
        dataMsj.setFrom_row(fromrow);
        dataMsj.setFrom_col(fromcol);
        dataMsj.setTo_row(torow);
        dataMsj.setTo_col(tocol);
        return dataMsj;
    }
}
