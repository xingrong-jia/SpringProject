package com.jiaxingrong.handler;

import com.jiaxingrong.utils.JsonUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * int数组转换成/String类型
 */
@MappedTypes(Integer[].class)
public class IntArray2String implements TypeHandler<Integer[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Integer[] integers, JdbcType jdbcType) throws SQLException {
        String s = JsonUtils.convertToJson(integers);
        preparedStatement.setString(index,s);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, String column) throws SQLException {
        String string = resultSet.getString(column);
        Integer[] integers = JsonUtils.convertToObject(string, Integer[].class);
        return integers;
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int index) throws SQLException {
        String string = resultSet.getString(index);
        Integer[] integers = JsonUtils.convertToObject(string, Integer[].class);
        return integers;
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int index) throws SQLException {
        String string = callableStatement.getString(index);
        Integer[] integers = JsonUtils.convertToObject(string, Integer[].class);
        return integers;
    }
}
