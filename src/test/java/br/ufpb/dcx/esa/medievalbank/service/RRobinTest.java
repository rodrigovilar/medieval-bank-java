package br.ufpb.dcx.esa.medievalbank.service;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static br.ufpb.dcx.esa.medievalbank.service.AtendeeServiceTestHelper.*;
import static br.ufpb.dcx.esa.medievalbank.service.DemandServiceTestHelper.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;
import br.ufpb.dcx.esa.medievalbank.model.Demand;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class RRobinTest {

	@Autowired
    private AgencyService agencyService;

    @Autowired
    private AtendeeService atendeeService;

    @Autowired
    private DemandService demandService;
    
    @Test
    @Transactional
    public void agencyStatusSemAtendente() {
		String result = agencyService.getStatus();
		assertEquals("Quantum: 3\n" + "Atendees: []\n" + "Queue: []", result);
	}

    @Test
    @Transactional
    public void testDemandaSemConcorrenciaPor4Ticks() {

        createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");

        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1]", status);
        assertEquals(tick, 0);

        agencyService.increaseTick();
        tick = agencyService.getTick();
        status = agencyService.getStatus();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 1);

        for(int i = 0; i < 3; i++){
            agencyService.increaseTick();
        }
        tick = agencyService.getTick();
        assertEquals(tick, 4);
    }
    
    @Test
    @Transactional
    public void testDemandaFinalizaAoEstourarOQuantum() {

        createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");

        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1]", status);
        assertEquals(tick, 0);

        ticks(agencyService, 3);

        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 3);
        
        agencyService.finalizeDemandAtTheNextTick("D1");

        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 3);

        agencyService.increaseTick();
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: []", status);
        assertEquals(tick, 4);
   
    }
    
    @Test
    @Transactional
    public void testDuasDemandasConcorrendoAté4tick() {

        createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        createDemand(demandService, "D2");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1, D2]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 4);

        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: [D2]", status);
        assertEquals(tick, 4);
        
        agencyService.finalizeDemandAtTheNextTick("D1");
        
        ticks(agencyService, 1);
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D2]\n" + "Queue: []", status);
        assertEquals(tick, 5);
        
    }
    @Test
    @Transactional
    public void testDuasDemandasComDoisAtendentes() {
    	createAtendee(atendeeService, "A1");
    	createAtendee(atendeeService, "A2");
        createDemand(demandService, "D1");
        createDemand(demandService, "D2");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1, A2]\n" + "Queue: [D1, D2]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1, A2->D2]\n" + "Queue: []", status);
        assertEquals(tick, 1);
        
    }
    
    @Test
    @Transactional
    public void testTresDemandasComDoisAtendentes() {
    	createAtendee(atendeeService, "A1");
    	createAtendee(atendeeService, "A2");
        createDemand(demandService, "D1");
        createDemand(demandService, "D2");
        createDemand(demandService, "D3");
        
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1, A2]\n" + "Queue: [D1, D2, D3]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 3);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1, A2->D2]\n" + "Queue: [D3]", status);
        assertEquals(tick, 3);
        
        agencyService.finalizeDemandAtTheNextTick("D1");
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D3, A2->D2]\n" + "Queue: []", status);
        assertEquals(tick, 4);
        
    }
    @Test
    @Transactional
    public void testUmaDemandaFinaliza() {
    	createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 1);
        
        agencyService.finalizeDemandAtTheNextTick("D1");
        
        ticks(agencyService, 3);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: []", status);
        assertEquals(tick, 4);
        
    }
    
    @Test
    @Transactional
    public void testDuasDemandasComConcorrencia() {
    	createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        createDemand(demandService, "D2");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1, D2]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: [D2]", status);
        assertEquals(tick, 1);
        
        ticks(agencyService, 2);
        
        agencyService.finalizeDemandAtTheNextTick("D1");
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D2]\n" + "Queue: []", status);
        assertEquals(tick, 4);
        
    }
    @Test
    @Transactional
    public void testDuasDemandasComConcorrenciaFinalizaDemandaEmEspera() {
    	createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        createDemand(demandService, "D2");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1, D2]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: [D2]", status);
        assertEquals(tick, 1);
        
        ticks(agencyService, 2);
        
        agencyService.finalizeDemandAtTheNextTick("D2");
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 4);
        
    }
    
    @Test
    @Transactional
    public void testDuasDemandasSemConcorrenciaComEspaçoEntreElas() {
    	createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 1);
        
        ticks(agencyService, 3);
        
        agencyService.finalizeDemandAtTheNextTick("D1");
        
        ticks(agencyService, 2);
        
        createDemand(demandService, "D2");
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D2]", status);
        assertEquals(tick, 6);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D2]\n" + "Queue: []", status);
        assertEquals(tick, 7); 
        
    }
    @Test
    @Transactional
    public void testDuasDemandasCemConcorrenciaComInicioDiferente() {
    	createAtendee(atendeeService, "A1");
        createDemand(demandService, "D1");
        
        String status = agencyService.getStatus();
        int tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1]\n" + "Queue: [D1]", status);
        assertEquals(tick, 0);
        
        ticks(agencyService, 1);
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: []", status);
        assertEquals(tick, 1);
        
        ticks(agencyService, 3);
        
        createDemand(demandService, "D2");
        
        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D1]\n" + "Queue: [D2]", status);
        assertEquals(tick, 4);
        
        ticks(agencyService, 1);
        
        this.removeDemandOfTheAtendee("D1");
        
        ticks(agencyService, 1);

        status = agencyService.getStatus();
        tick = agencyService.getTick();
        assertEquals("Quantum: 3\n" + "Atendees: [A1->D2]\n" + "Queue: [D1]", status);
        assertEquals(tick, 6); 
        
    }
    
    
    
    private void ticks(AgencyService agencyService, int ticks) {
        for(int i = 0; i < ticks; i++){
            agencyService.increaseTick();
        }
    }
    //Tentando bolar um jeito de passar isso pro AgencyService
    public void removeDemandOfTheAtendee(String D) {
		Atendee atendee = atendeeService.getAtendeeByDemandName(D);
		Demand demand = atendee.getDemand();
		agencyService.finalizeDemandAtTheNextTick(D);
		createDemand(demandService, demand.getName());
    }

}
