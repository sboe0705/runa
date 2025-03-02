package io.github.sboe0705.runa.client.session.impl;

import io.github.sboe0705.runa.client.session.SessionHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionHolderImpl implements SessionHolder {

    private String user;

    @Override
    public boolean isLoggedIn() {
        return user != null;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void logInAs(String user) {
        this.user = user;
    }

    @Override
    public void logOut() {
        user = null;
    }

}
