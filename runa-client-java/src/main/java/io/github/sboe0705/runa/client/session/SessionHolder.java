package io.github.sboe0705.runa.client.session;

public interface SessionHolder {

    boolean isLoggedIn();

    String getUser();

    void logInAs(String user);

    void logOut();

}
