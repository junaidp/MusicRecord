package com.musicrecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicrecord.server.MusicHelper;
import com.musicrecord.shared.User;

import junit.framework.TestCase;

public class MusicRecordTests extends TestCase {

    MusicHelper musicHelper = new MusicHelper();

    @Before
    public void setUp() throws Exception {

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
