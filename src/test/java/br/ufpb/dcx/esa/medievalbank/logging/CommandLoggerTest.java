package br.ufpb.dcx.esa.medievalbank.logging;


import br.ufpb.dcx.esa.medievalbank.command.Command;
import br.ufpb.dcx.esa.medievalbank.command.InsertAttendee;
import br.ufpb.dcx.esa.medievalbank.command.RemoveAttendee;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLoggerTest {


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

    public Atendee builAttendee(String name, String email) {
        Atendee att = new Atendee();
        att.setName(name);
        att.setEmail(email);
        return att;
    }

    public Atendee insertSingleAttendee(Atendee attendee) {
        Command insertAttendee = new InsertAttendee(attendee);
        try {
            this.agencyService.execute(insertAttendee);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return attendee;
    }

    public void insertMultipleAttendess(List<Atendee> attendees) {
        int index = 1;
        for(Atendee i: attendees) {
            insertSingleAttendee(builAttendee("A" + index++, "a@mail.com"));
        }
    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t052_successfulAttendeeCreationLogs() {
        Command insertAttendee = new InsertAttendee(builAttendee("A1", "a@mail.com"));
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

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t053_SuccesfullyRemoveAttendeeWithLogs() {
        /*
        Atendee att = insertSingleAttendee(builAttendee("A1", "a@a.com"));

        Command removeAttendee = new RemoveAttendee(att);
        try {
            this.agencyService.execute(removeAttendee);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String beforeMethod = "Executing remove attendee";
        String afterMethod = "Executed remove attendee";

        List<String> logs = this.agencyService.getLogger().getLogs();

        assertTraceLog(beforeMethod, logs.get(0));
        assertTraceLog(afterMethod, logs.get(1));
         */
    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t054_AddAttendeeAndLogExceptions(){

    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t055_RemoveAttendeeAndLogExceptions() {

    }

}
