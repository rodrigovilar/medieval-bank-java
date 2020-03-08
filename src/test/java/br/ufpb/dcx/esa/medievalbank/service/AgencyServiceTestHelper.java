package br.ufpb.dcx.esa.medievalbank.service;

import br.ufpb.dcx.esa.medievalbank.model.Atendee;

public class AgencyServiceTestHelper {

    static  void increaseTick(AgencyService agencyService, int quantity) {
        for (int i = 0; i < quantity ; i++) {
            agencyService.increaseTick();
        }
    }
    
	public static Atendee addAtendee(AgencyService service, String aName) {
		return addAtendee(service, aName, null);
	}

	static Atendee addAtendee(AgencyService service, String aName, String email) {
		return addAtendee(service, aName, email, null);
	}

	static Atendee addAtendee(AgencyService service, String aName, String email, String ssn) {
		Atendee atendee = new Atendee();
		atendee.setName(aName);
		atendee.setEmail(email);
		atendee.setSSN(ssn);
		return service.addAttendee(atendee);
	}

}
