package com.maf.DTO;

import com.google.common.base.MoreObjects;
import lombok.Data;

@Data
public class ResponseDTO {

    private boolean exist;
    private int fromRow;
    private int fromCol;
    private String piece;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("exist", exist)
                .add("fromRow", fromRow)
                .add("fromCol", fromCol)
                .add("piece", piece)
                .toString();
    }
    
}
