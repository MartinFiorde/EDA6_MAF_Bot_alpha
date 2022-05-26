package com.maf.DTO;

import com.google.common.base.MoreObjects;
import lombok.Data;

@Data
public class MessageDTO {

    private String action;
    private String event;
    private DataMessageDTO data;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("action", action)
                .add("event", event)
                .add("data", data)
                .toString();
    }

}
