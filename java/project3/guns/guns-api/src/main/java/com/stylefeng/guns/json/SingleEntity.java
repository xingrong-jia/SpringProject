package com.stylefeng.guns.json;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 11:11
 */
@Data
public class SingleEntity  implements Serializable {

    /**
     * column : 1
     * seatId : 1
     * row : 1
     */
    private int column;
    private int seatId;
    private int row;

    public void setColumn(String column) {
        column.replace(" ","");
        this.column = Integer.parseInt(column);
    }

    public void setSeatId(String seatId) {
        seatId.replace(" ","");
        this.seatId = Integer.parseInt(seatId);
    }

    public void setRow(String row) {
        row.replace(" ","");
        this.row = Integer.parseInt(row);
    }
}
