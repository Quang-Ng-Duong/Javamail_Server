import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmtpServerTest {

    private SmtpServer smtpServer;

    @BeforeEach
    void setUp() {
        smtpServer = new SmtpServer();
    }

    @Test
    void testConnect() {
        assertTrue(smtpServer.connect("smtp.example.com", 587));
    }

    @Test
    void testAuthenticate() {
        assertTrue(smtpServer.authenticate("user@example.com", "password"));
    }

    @Test
    void testSendEmail() {
        boolean result = smtpServer.sendEmail("from@example.com", "to@example.com", "Subject", "Body");
        assertTrue(result);
    }

    @Test
    void testSecureConnection() {
        assertTrue(smtpServer.establishSecureConnection());
    }
}