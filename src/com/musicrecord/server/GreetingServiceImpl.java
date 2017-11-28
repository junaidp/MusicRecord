package com.musicrecord.server;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.musicrecord.client.GreetingService;
import com.musicrecord.shared.Records;
import com.musicrecord.shared.User;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    HttpSession session;
    MusicHelper musicHelper = new MusicHelper();

    @Override
    public User signIn(String userid, String password) throws Exception {

	return musicHelper.signIn(userid, password);
    }

    @Override
    public ArrayList<Records> fetchRecords(HashMap<String, String> map) throws Exception {
	return musicHelper.fetchRecords(map);
    }

    @Override
    public String editRecord(Records record) throws Exception {
	return musicHelper.editRecord(record);
    }

    @Override
    public String deleteRecord(Records record) throws Exception {
	return musicHelper.deleteRecord(record);
    }

}
