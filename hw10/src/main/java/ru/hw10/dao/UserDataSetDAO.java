package ru.hw10.dao;

import org.hibernate.Session;
import ru.hw09.model.UserDataSet;

import java.util.List;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    /**
     * Save user to DB.
     * @param dataSet - user.
     * @return - saved user.
     */
    public UserDataSet save(UserDataSet dataSet) {
        session.save(dataSet);
        return dataSet;
    }

    /**
     * Get user from DB.
     * @param id - user id.
     * @return - user.
     */
    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }

    /**
     * Get all users.
     * @return - all users.
     */
    public List<UserDataSet> readAll() {
        return session
                .createQuery("FROM UserDataSet", UserDataSet.class)
                .getResultList();
    }
}