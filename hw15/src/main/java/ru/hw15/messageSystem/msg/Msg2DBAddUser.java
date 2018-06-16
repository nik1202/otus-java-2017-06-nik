package ru.hw15.messageSystem.msg;

import ru.hw09.model.UserDataSet;
import ru.hw09.service.DBService;
import ru.hw15.messageSystem.Address;
import ru.hw15.messageSystem.Addressee;
import ru.hw15.messageSystem.Message;

public class Msg2DBAddUser extends Message {
    private final UserDataSet user;
    public Msg2DBAddUser(Address from, Address to, UserDataSet user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            try {
                ((DBService) addressee).addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
