package io.github.sboe0705.runa.client.commands;

import io.github.sboe0705.runa.client.rest.RunaRestClient;
import io.github.sboe0705.runa.client.session.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

import java.util.function.Supplier;

@Command(group = "User Commands")
public class MessagingCommands {

    @Autowired
    private SessionHolder sessionHolder;

    @Autowired
    private RunaRestClient runaRestClient;

    @Command(command = "send to", description = "Send a message to another user.")
    public String send(String recipient, String message) {
        return executeIfLoggedIn(() -> {
            runaRestClient.sendMessage(sessionHolder.getUser(), recipient, message);
            return "Message to " + recipient + " sent!";
        });
    }

    private String executeIfLoggedIn(Supplier<String> supplier) {
        if (sessionHolder.isLoggedIn()) {
            return supplier.get();
        } else {
            return "You have to log in first!";
        }
    }

}
