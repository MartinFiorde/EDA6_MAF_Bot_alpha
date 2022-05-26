package com.maf.DTO;

import java.util.List;
import com.google.common.base.MoreObjects;
import lombok.Data;

@Data
public class DataMessageDTO {

    // USERS LIST
    private List<String> users;
    // CHALLENGE
    private String opponent;
    private String challenge_id;
    // YOUR TURN
    private String game_id;
    private String turn_token;
    private String remaining_moves;
    private String side;        // "N" or "S"
    private String walls;       // Integer 0-10
    private String player_1;    // "N" username
    private String score_1;     // "N" score
    private String player_2;    // "S" username
    private String score_2;     // "S" score
    private String board;
    // PUT WALL
    private Integer row;
    private Integer col;
    private String orientation;
    // MOVE PAWN
    private Integer from_row;
    private Integer from_col;
    private Integer to_row;
    private Integer to_col;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("users", users)
                .add("opponent", opponent)
                .add("challenge_id", challenge_id)
                /*.add("game_id", game_id)*/
                /*.add("turn_token", turn_token)*/
                .add("remaining_moves", remaining_moves)
                .add("side", side)
                .add("walls", walls)
                .add("player_1", player_1)
                .add("score_1", score_1)
                .add("player_2", player_2)
                .add("score_2", score_2)
                /*.add("board", board)*/
                .add("row", row)
                .add("col", col)
                .add("orientation", orientation)
                .add("from_row", from_row)
                .add("from_col", from_col)
                .add("to_row", to_row)
                .add("to_col", to_col)
                .toString();
    }

}
