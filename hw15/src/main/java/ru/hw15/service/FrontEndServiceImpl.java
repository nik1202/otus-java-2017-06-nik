package ru.hw15.service;

import ru.hw09.model.UserDataSet;
import ru.hw15.app.FrontEndService;
import ru.hw15.app.MessageSystemContext;
import ru.hw15.messageSystem.Address;
import ru.hw15.messageSystem.Message;
import ru.hw15.messageSystem.MessageSystem;
import ru.hw15.messageSystem.msg.Msg2DBAddUser;

public class FrontEndServiceImpl implements FrontEndService {
    private final Address address;
    private final MessageSystemContext context;

    public FrontEndServiceImpl(Address address, MessageSystemContext context) {
        this.address = address;
        this.context = context;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMS() {
        return this.context.getMessageSystem();
    }

    @Override
    public void addUser(UserDataSet user) {
        Message message = new Msg2DBAddUser(getAddress(), context.getDbAddress(), user);
        context.getMessageSystem().sendMessage(message);
    }
}
