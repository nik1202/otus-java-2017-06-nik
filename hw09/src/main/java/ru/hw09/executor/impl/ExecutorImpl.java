package ru.hw09.executor.impl;

import ru.hw09.executor.Executor;
import ru.hw09.model.DataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecutorImpl implements Executor {
    private final Connection connection;

    public ExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T extends DataSet> void save(T dataSet) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            StringBuilder sql = createInsertQuery(dataSet);
            stmt.execute(sql.toString());
        }
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select * from UserDataSet where id='" + id + "';");
            ResultSet result = stmt.getResultSet();
            T user = getObjectFromResult(result, clazz);
            result.close();
            return user;
        }
    }

    @Override
    public <T extends DataSet> List<T> loadAll(Class<T> clazz) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select * from UserDataSet;");
            ResultSet result = stmt.getResultSet();
            List<T> users = new ArrayList<>();
            while (!result.isLast()) {
                users.add(getObjectFromResult(result, clazz));
            }
            result.close();
            return users;
        }
    }

    @Override
    public void exec(String query) throws SQLException {
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.execute();
        }
    }

    /**
     * Create query string for user insert query.
     * @param dataSet - user entity.
     * @param <T> - entity type.
     * @return - sql query string.
     */
    private <T extends DataSet> StringBuilder createInsertQuery(T dataSet) {
        StringBuilder sql = new StringBuilder("insert into ").append(dataSet.getClass().getSimpleName()).append(" (");
        List<Field> columns = getColumns(dataSet.getClass());
        for (Field field : columns) {
            if (!field.getName().equals("id")) {
                sql.append(field.getName()).append(",");
            }
        }
        sql.deleteCharAt(sql.length() - 1);

        sql.append(") values (");
        for (Field field : columns) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("id")) {
                    sql.append("'").append(field.get(dataSet).toString()).append("'").append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(");");

        return sql;
    }

    /**
     * get all class columns.
     * @param clazz - class.
     * @param <T> - type.
     * @return - list of fields.
     */
    private <T extends DataSet> List<Field> getColumns(Class<T> clazz) {
        List<Field> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        columns.addAll(Arrays.asList(fields));
        fields = clazz.getSuperclass().getDeclaredFields();
        columns.addAll(Arrays.asList(fields));
        return columns;
    }

    /**
     * Get object from resultset.
     * @param result - resultset.
     * @param clazz - class.
     * @param <T> - type.
     * @return - entity.
     * @throws SQLException -
     */
    private <T extends DataSet> T getObjectFromResult(ResultSet result, Class<T> clazz) throws SQLException {
        if (result.next()) {
            try {
                Object user = clazz.newInstance();
                List<Field> columns = getColumns(clazz);
                for (Field field : columns) {
                    field.setAccessible(true);
                    field.set(user, result.getObject(field.getName()));
                }
                return (T) user;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
