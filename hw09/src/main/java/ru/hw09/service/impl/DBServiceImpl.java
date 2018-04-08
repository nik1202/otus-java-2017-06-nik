package ru.hw09.service.impl;

import ru.hw09.connection.ConnectionHelper;
import ru.hw09.executor.Executor;
import ru.hw09.executor.impl.ExecutorImpl;
import ru.hw09.model.UserDataSet;
import ru.hw09.service.DBService;

import java.sql.Connection;
import java.util.List;

public class DBServiceImpl implements DBService {
    private static final String CREATE_USERDATASET_TABLE = "CREATE TABLE IF NOT EXISTS UserDataSet (id bigint auto_increment, name VARCHAR(255), age INT(3), CONSTRAINT pk PRIMARY KEY(id));";
    private static final String DROP_USERDATASET_TABLE = "DROP TABLE UserDataSet";

    private final Connection connection;
    private final Executor executor;

    public DBServiceImpl() {
        connection = ConnectionHelper.getConnection();
        executor = new ExecutorImpl(connection);
    }

    @Override
    public void prepareTables() throws Exception {
        executor.exec(CREATE_USERDATASET_TABLE);
    }

    @Override
    public void addUser(UserDataSet userDataSet) throws Exception {
        executor.save(userDataSet);
    }

    @Override
    public UserDataSet getUser(int id) throws Exception {
        return executor.load(id, UserDataSet.class);
    }

    @Override
    public List<UserDataSet> getAllUsers() throws Exception {
        return executor.loadAll(UserDataSet.class);
    }

    @Override
    public void deleteTables() throws Exception {
        executor.exec(DROP_USERDATASET_TABLE);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
