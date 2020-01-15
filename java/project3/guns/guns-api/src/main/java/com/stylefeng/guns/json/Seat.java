package com.stylefeng.guns.json;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 11:09
 */
@Data
public class Seat implements Serializable {


    /**
     * single : [[{"column":1,"seatId":1,"row":1},{"column":2,"seatId":2,"row":1},{"column":3,"seatId":3,"row":1},{"column":4,"seatId":4,"row":1},{"column":5,"seatId":5,"row":1}],[{"column":1,"seatId":6,"row":2},{"column":2,"seatId":7,"row":2},{"column":3,"seatId":8,"row":2},{"column":4,"seatId":9,"row":2},{"column":5,"seatId":10,"row":2}]]
     * couple : [[{"column":1,"seatId":11,"row":3},{"column":2,"seatId":12,"row":3},{"column":3,"seatId":13,"row":3},{"column":4,"seatId":14,"row":3},{"column":5,"seatId":15,"row":3},{"column":6,"seatId":16,"row":3}],[{"column":1,"seatId":17,"row":4},{"column":2,"seatId":18,"row":4},{"column":3,"seatId":19,"row":4},{"column":4,"seatId":20,"row":4},{"column":5,"seatId":21,"row":4},{"column":6,"seatId":22,"row":4}]]
     * limit : 5
     * ids : 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22
     */
    private List<List<SingleEntity>> single;
    private List<List<SingleEntity>> couple;
    private int limit;
    private String ids;

    public void setSingle(List<List<SingleEntity>> single) {
        this.single = single;
    }

    public void setCouple(List<List<SingleEntity>> couple) {
        this.couple = couple;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<List<SingleEntity>> getSingle() {
        return single;
    }

    public List<List<SingleEntity>> getCouple() {
        return couple;
    }

    public int getLimit() {
        return limit;
    }

    public String getIds() {
        return ids;
    }

    public class SingleEntity {
        /**
         * column : 1
         * seatId : 1
         * row : 1
         */
        private int column;
        private int seatId;
        private int row;

        public void setColumn(int column) {
            this.column = column;
        }

        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public int getSeatId() {
            return seatId;
        }

        public int getRow() {
            return row;
        }
    }

    public class CoupleEntity {
        /**
         * column : 1
         * seatId : 11
         * row : 3
         */
        private int column;
        private int seatId;
        private int row;

        public void setColumn(int column) {
            this.column = column;
        }

        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public int getSeatId() {
            return seatId;
        }

        public int getRow() {
            return row;
        }
    }
}
