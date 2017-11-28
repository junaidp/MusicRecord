package com.musicrecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicrecord.server.MusicHelper;
import com.musicrecord.shared.User;

import junit.framework.TestCase;

public class MusicRecordTests extends TestCase {

    MusicHelper musicHelper;

    @Before
    public void setUp() throws Exception {
	musicHelper = new MusicHelper();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSignIn() throws Exception {

	User user = musicHelper.signIn("test", "test");
	if (user != null) {
	    assertTrue(true);
	} else {
	    assertFalse(true);
	}
    }

}
