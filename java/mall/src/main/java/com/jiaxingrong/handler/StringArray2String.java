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
 * string数组转换成String类型
 */
@MappedTypes(String[].class)
public class StringArray2String implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, String[] strings, JdbcType jdbcType) throws SQLException {
        String s = JsonUtils.convertToJson(strings);
        preparedStatement.setString(index,s);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String column) throws SQLException {
        String string = resultSet.getString(column);
        String[] strings = JsonUtils.convertToObject(string, String[].class);
        return strings;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int index) throws SQLException {
        String string = resultSet.getString(index);
        String[] strings = JsonUtils.convertToObject(string, String[].class);
        return strings;
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int index) throws SQLException {
        String string = callableStatement.getString(index);
        String[] strings = JsonUtils.convertToObject(string, String[].class);
        return strings;
    }
}
