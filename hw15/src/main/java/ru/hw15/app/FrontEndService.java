package ru.hw15.app;

import ru.hw09.model.UserDataSet;
import ru.hw15.messageSystem.Addressee;

public interface FrontEndService extends Addressee {
    void init();
    void addUser(UserDataSet user);
}