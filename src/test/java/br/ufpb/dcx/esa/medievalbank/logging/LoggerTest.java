package br.ufpb.dcx.esa.medievalbank.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AgencyService;
import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;
import br.ufpb.dcx.esa.medievalbank.utils.logging.LoggingMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    @Autowired
    private AgencyService agencyService;

    private Logger logger;

    @Before
    public void resetTick() {
        this.agencyService.resetTick();
        this.logger = new LoggingMock();
        this.agencyService.setLogger(this.logger);
    }

    public void assertSuccessLog(String value, String log) {
        assertTrue(log.equals(String.format("SUCCESS: %s", value)));
    }
    public void assertErrorLog(String value, String log) {
        assertTrue(log.equals(String.format("ERROR: %s", value)));
    }
    public void assertWarnLog(String value, String log) {
        assertTrue(log.equals(String.format("WARN: %s", value)));
    }
    public void assertTraceLog(String value, String log) {
        assertTrue(log.equals(String.format("TRACE: %s", value)));
    }
    public void assertInfoLog(String value, String log) {
        assertTrue(log.equals(String.format("INFO: %s", value)));
    }

    @Test
    public void InitialTest() {
        String successLog = "Isso é pra funcionar";
        logger.success(successLog);

        assertTrue(logger.getLogs().size() >= 1);

        assertEquals(logger.getLogs().get(0), "SUCCESS: " + successLog);
    }

    @Test
    @Transactional
    public void t052_successfulAttendeeCreationLogs() {
        String beforeMethod = "Trying to create attendee";
        String afterMethod = "Attendee created";

        Atendee atendee = new Atendee();
        atendee.setName("A1");

        this.agencyService.addAttendee(atendee);
        List<String> logs = this.agencyService.getLogger().getLogs();

        assertEquals(2, logs.size());
        assertInfoLog(beforeMethod, logs.get(0));
        assertSuccessLog(afterMethod, logs.get(1));

    }

}
