package io.github.sboe0705.runa.client.commands;

import io.github.sboe0705.runa.client.session.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

@Command(group = "User Commands")
public class LoginCommands {

    @Autowired
    private SessionHolder sessionHolder;

    @Command(command = "login as", description = "Log into Runa Network with a specific name.")
    public String logInAs(String user) {
        sessionHolder.logInAs(user);
        return "Logged in as " + user + " ...";
    }

    @Command(command = "logout", description = "Log out of Runa Network.")
    public String logOut() {
        sessionHolder.logOut();
        return "Logged out!";
    }



}