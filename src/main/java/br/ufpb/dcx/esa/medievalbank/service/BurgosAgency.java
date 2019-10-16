package br.ufpb.dcx.esa.medievalbank.service;


public class BurgosAgency {

	private static String name;
	private static String manager;


	public static void setName(String string) {
		BurgosAgency.name = string;
	}

	public static String getName() {
		return BurgosAgency.name;
	}

	public static void setManager(String string) {
		BurgosAgency.manager = string;
	}

	public static String getManager() {
		return BurgosAgency.manager;
	}
	
}
