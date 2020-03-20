package br.ufpb.dcx.esa.medievalbank.logging;


import br.ufpb.dcx.esa.medievalbank.command.Command;
import br.ufpb.dcx.esa.medievalbank.command.InsertAttendee;
import br.ufpb.dcx.esa.medievalbank.command.RemoveAttendee;
import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.service.AgencyService;
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

    public Atendee builAttendee(String name, String email) {
        Atendee att = new Atendee();
        att.setName(name);
        att.setEmail(email);
        return att;
    }

    public Atendee insertSingleAttendee(Atendee attendee, boolean shouldFail) {
        Command insertAttendee = new InsertAttendee(attendee);
        try {
            this.agencyService.execute(insertAttendee);
        } catch (Exception e) {
            if(shouldFail) fail(e.getMessage()); else throw e;
        }
        return attendee;
    }

    private void removeSingleAttendee(Atendee att, boolean shouldFail) {
        Command removeAttendee = new RemoveAttendee(att);
        try {
            this.agencyService.execute(removeAttendee);
        } catch (Exception e) {
            if(shouldFail) fail(e.getMessage()); else throw e;
        }
    }

    public void insertMultipleAttendess(List<Atendee> attendees, boolean shouldFail) {
        int index = 1;
        for(Atendee i: attendees) {
            insertSingleAttendee(builAttendee("A" + index++, "a@mail.com"), shouldFail);
        }
    }

    private boolean isLogInLogs(String log) {
        for(String i: this.logger.getLogs()) {
            if(i.equals(log)) return true;
        }
        return false;
    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t052_successfulAttendeeCreationLogs() {
        insertSingleAttendee(builAttendee("A1", "a@mail.com"), true);

        String beforeMethod = "TRACE: Executing insert attendee";
        String afterMethod = "TRACE: Executed insert attendee";

        assertTrue(isLogInLogs(beforeMethod));
        assertTrue(isLogInLogs(afterMethod));

    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t053_SuccesfullyRemoveAttendeeWithLogs() {
        Atendee att = insertSingleAttendee(builAttendee("A1", "a@a.com"), true);

        removeSingleAttendee(att, true);

        String beforeMethod = "TRACE: Executing remove attendee";
        String afterMethod = "TRACE: Executed remove attendee";

        assertTrue(isLogInLogs(beforeMethod));
        assertTrue(isLogInLogs(afterMethod));
    }


    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t054_AddAttendeeAndLogExceptions(){
        try {
            insertSingleAttendee(builAttendee(null, null), false);
            fail("Should throw exception");
        } catch (Exception e) {

        }

        String errorLog = "ERROR: Error executing insert attendee, exception message was Name is mandatory";
        assertTrue(isLogInLogs(errorLog));


    }

    @Test
    @Transactional
    @WithMockUser(username = "john", roles = { "MANAGER" })
    public void t055_RemoveAttendeeAndLogExceptions() {

    }

}
