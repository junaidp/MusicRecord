package com.musicrecord.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.musicrecord.shared.Category;
import com.musicrecord.shared.Records;
import com.musicrecord.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    void signIn(String userid, String password, AsyncCallback<User> callback);

    void fetchRecords(HashMap<String, String> requestInfo, AsyncCallback<ArrayList<Records>> callback);

    void saveRecord(Records record, AsyncCallback<String> callback);

    void deleteRecord(Records record, AsyncCallback<String> callback);

    void fetchCategories(AsyncCallback<ArrayList<Category>> callback);

}
