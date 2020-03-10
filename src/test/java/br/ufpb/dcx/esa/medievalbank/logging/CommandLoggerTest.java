package br.ufpb.dcx.esa.medievalbank.logging;


import br.ufpb.dcx.esa.medievalbank.command.Command;
import br.ufpb.dcx.esa.medievalbank.command.InsertAttendee;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AgencyService;
import br.ufpb.dcx.esa.medievalbank.service.AtendeeService;
import br.ufpb.dcx.esa.medievalbank.service.DemandService;
import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;
import br.ufpb.dcx.esa.medievalbank.utils.logging.LoggingMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLoggerTest {


    @Autowired
    private AgencyService agencyService;
    @Autowired
    private AtendeeService atendeeService;
    @Autowired
    private DemandService demandService;

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
    public void t052_successfulAttendeeCreationLogs() {
        this.agencyService.setLogger(logger);
        Atendee attendee = new Atendee();
        attendee.setName("A1");
        attendee.setEmail("a@mail.com");
        Command insertAttendee = new InsertAttendee(attendee);
        insertAttendee.setAgencyService(agencyService);
        try {
            this.agencyService.execute(insertAttendee);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        String beforeMethod = "Executing insert attendee";
        String afterMethod = "Executed insert attendee";

        List<String> logs = this.agencyService.getLogger().getLogs();

        assertTraceLog(beforeMethod, logs.get(0));
        assertTraceLog(afterMethod, logs.get(1));

    }

}
