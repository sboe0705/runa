package io.github.sboe0705.runa.server.service.impl;

import io.github.sboe0705.runa.server.rest.Message;
import io.github.sboe0705.runa.server.rest.RunaRestController;
import io.github.sboe0705.runa.server.service.MessageStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.synchronizedList;

@Service
public class InMemoryMessageStorage implements MessageStorage {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryMessageStorage.class);

    private final Map<String, List<Message>> messages = new HashMap<>();

    @Override
    public void addMessage(Message message) {
        List<Message> recipientsMessages = getRecipientsMessages(message.recipient());
        recipientsMessages.add(message);
        logger.atDebug().log("{} messages waiting for {}.", recipientsMessages.size(), message.recipient());
    }

    @Override
    public List<Message> retrieveMessagesFor(String recipient) {
        List<Message> retrievedMessages;
        List<Message> recipientsMessages = getRecipientsMessages(recipient);
        synchronized (recipientsMessages) {
            retrievedMessages = new ArrayList<>(recipientsMessages);
            recipientsMessages.clear();
        }
        logger.atDebug().log("{} messages for {} have been retrieved.", recipientsMessages.size(), recipient);
        return retrievedMessages;
    }

    private List<Message> getRecipientsMessages(String recipient) {
        return messages.computeIfAbsent(recipient, k -> synchronizedList(new ArrayList<>()));
    }

}
