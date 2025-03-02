package io.github.sboe0705.runa.server.service;

import io.github.sboe0705.runa.server.rest.Message;

import java.util.List;

public interface MessageStorage {

    void addMessage(Message message);

    List<Message> retrieveMessagesFor(String recipient);

}
