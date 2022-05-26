package com.maf.enums;

public enum Event {

    LIST_USERS("list_users"),
    CHALLENGE("challenge"),
    YOUR_TURN("your_turn"),
    GAMEOVER("game_over");

    private final String string;

    Event(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
