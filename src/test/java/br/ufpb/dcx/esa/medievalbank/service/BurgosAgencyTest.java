package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BurgosAgencyTest {

	@Test
	public void initialConfiguration() {

        BurgosAgency.setName("Burgosland");
        assertEquals("Burgosland", BurgosAgency.getName());

        BurgosAgency.setManager("Joseph");
        assertEquals("Joseph", BurgosAgency.getManager());

	}

}
