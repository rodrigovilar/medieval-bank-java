package br.ufpb.dcx.esa.medievalbank.service;

public class AgencyServiceTestHelper {

    static  void increaseTick(AgencyService agencyService, int quantity) {
        for (int i = 0; i < quantity ; i++) {
            agencyService.increaseTick();
        }
    }

}
