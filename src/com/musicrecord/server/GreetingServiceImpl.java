package com.musicrecord.server;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.musicrecord.client.GreetingService;
import com.musicrecord.shared.User;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    HttpSession session;

    @Override
    public User signIn(String userid, String password) throws Exception {
	MusicHelper musicHelper = new MusicHelper();
	return musicHelper.signIn(userid, password);
    }

}
