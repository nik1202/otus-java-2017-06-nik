package ru.hw09.service;

import ru.hw09.model.UserDataSet;

import java.util.List;

public interface DBService extends AutoCloseable {
    /**
     * Create table on start.
     * @throws Exception
     */
    void prepareTables() throws Exception;

    /**
     * add user to db.
     * @param userDataSet - user.
     * @throws Exception
     */
    void addUser(UserDataSet userDataSet) throws Exception;

    /**
     * load user from db.
     * @param id - loaded user id.
     * @return - user entity.
     * @throws Exception
     */
    UserDataSet getUser(int id) throws Exception;

    /**
     * Load all users from db.
     * @return - list of users.
     * @throws Exception
     */
    List<UserDataSet> getAllUsers() throws Exception;

    /**
     * Delete table before end.
     * @throws Exception
     */
    void deleteTables() throws Exception;
}
