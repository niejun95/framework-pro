package org.example.handler;

import jodd.util.StringUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.example.utils.AESUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: 加解密处理 randomKey 应该通过读取配置文件获取较为合适
 * @author: ryan
 * @date 2023/6/9 16:44
 * @version: 1.0
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({String.class})
public class EncryptTypeHandler<T> extends BaseTypeHandler<T> {

    private static final String randomKey = "fraf4vbx1gd8a76h";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, AESUtil.encrypt((String) parameter, randomKey));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return StringUtil.isBlank(columnValue) ? null : (T) AESUtil.decrypt(columnValue, randomKey);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return StringUtil.isBlank(columnValue) ? null : (T) AESUtil.decrypt(columnValue, randomKey);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return StringUtil.isBlank(columnValue) ? null : (T) AESUtil.decrypt(columnValue, randomKey);
    }
}
