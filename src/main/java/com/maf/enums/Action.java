package com.maf.enums;

public enum Action {

    LOGIN("login"),
    GET_CONNECTED_USERS("get_connected_users"),
    CHALLENGE("challenge"),
    ACCEPT_CHALLENGE("accept_challenge"),
    MOVE("move"),
    WALL("wall");

    private final String string;

    Action(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
