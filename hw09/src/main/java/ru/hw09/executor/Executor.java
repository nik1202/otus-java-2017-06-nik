package ru.hw09.executor;

import ru.hw09.model.DataSet;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    /**
     * Save entity to DB.
     * @param user - user to save.
     * @param <T> - type of entity.
     * @throws SQLException -
     */
    <T extends DataSet> void save(T user) throws SQLException;

    /**
     * Load entity from DB.
     * @param id - user id in db.
     * @param clazz - class of entity.
     * @param <T> - type of entity.
     * @return - loaded entity.
     * @throws SQLException -
     */
    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

    /**
     * Load of users from db.
     * @param clazz - class of entity.
     * @param <T> - type of entity.
     * @return - loaded entites.
     * @throws SQLException -
     */
    <T extends DataSet> List<T> loadAll(Class<T> clazz) throws SQLException;

    /**
     * execute query.
     * @param query - query
     * @throws SQLException -
     */
    void exec(String query) throws SQLException;
}
