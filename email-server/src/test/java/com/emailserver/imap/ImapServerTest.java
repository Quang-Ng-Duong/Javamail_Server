package com.emailserver.imap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImapServerTest {

    private ImapServer imapServer;

    @BeforeEach
    void setUp() {
        imapServer = new ImapServer();
    }

    @Test
    void testConnect() {
        assertTrue(imapServer.connect("imap.example.com", 993));
    }

    @Test
    void testAuthenticate() {
        assertTrue(imapServer.authenticate("user@example.com", "password"));
    }

    @Test
    void testRetrieveEmails() {
        imapServer.authenticate("user@example.com", "password");
        assertNotNull(imapServer.retrieveEmails());
    }

    @Test
    void testManageMailboxes() {
        imapServer.authenticate("user@example.com", "password");
        assertTrue(imapServer.createMailbox("TestMailbox"));
        assertTrue(imapServer.deleteMailbox("TestMailbox"));
    }

    @Test
    void testSynchronizeEmailStates() {
        imapServer.authenticate("user@example.com", "password");
        assertTrue(imapServer.synchronizeEmailStates());
    }
}