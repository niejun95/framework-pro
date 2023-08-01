package org.example.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author ryan
 * @version 1.0.0
 * @description 通过实现拦截器来实现部分字段的自动填充功能
 * @date 2023/07/29 11:24:17
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Slf4j
public class MybatisMetaInterceptor implements Interceptor {

    /**
     * 执行拦截逻辑的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.info("---sqlId---" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.info("---sqlCommandType---" + sqlCommandType);
        log.info("拦截查询请求 Executor#update 方法 " + invocation.getMethod());
        if (parameter == null) {
            return invocation.proceed();
        }
        if (SqlCommandType.INSERT == sqlCommandType) {
            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                log.info("---field.name---" + field.getName());
                if ("createTime".equals(field.getName())) {
                    field.setAccessible(true);
                    Object local_createDate = field.get(parameter);
                    if (local_createDate == null || "".equals(local_createDate)) {
                        field.set(parameter, LocalDateTime.now());
                    }
                    field.setAccessible(false);
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                log.info("---field.name---" + field.getName());
                if ("updateTime".equals(field.getName())) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                    field.setAccessible(false);
                }
            }
        }

        return invocation.proceed();
    }

    /**
     * 方法的参数 target 就是拦截器要拦截的对象，该方法会在创建被拦截的接口实现类时被调用
     * 这个方法的实现很简单，只需要调用 Mybatis 提供的 Plug 类的 wrap 静态方法就可以通过 JAVA 动态代理拦截目标对象
     */
    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    /**
     * 方法用来传递插件的参数，可以通过参数来改变插件的行为
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("===begin");
        System.out.println(properties.getProperty("param1"));
        System.out.println(properties.getProperty("param2"));
        Interceptor.super.setProperties(properties);
        System.out.println("===end");
    }

    /**
     * 获取类的所有属性，包括父类
     *
     * @param object 对象
     * @return Field[] 属性数组
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
