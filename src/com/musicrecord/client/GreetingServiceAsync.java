package com.musicrecord.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.musicrecord.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
    void signIn(String userid, String password, AsyncCallback<User> callback);

}
