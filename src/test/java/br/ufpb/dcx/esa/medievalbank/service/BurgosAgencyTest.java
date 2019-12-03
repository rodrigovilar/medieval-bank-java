package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BurgosAgencyTest {
	
	@Autowired
	private AgencyService service;
	

	@Test
	public void initialConfiguration() {

		service.setName("Burgosland");
        assertEquals("Burgosland", service.getName());

        service.setManager("Joseph");
        assertEquals("Joseph", service.getManager());

	}
	
	@Test
	public void agencyStatusWhithoutAtendee() {
		String result = service.getStatus();
		assertEquals("Atendees: []\n" + 
				"Queue: []", result);

	}
	

}
