package br.ufpb.dcx.esa.medievalbank.service;


public class BurgosAgency {

	private static String name;
	private static String manager;


	public static void setName(String nome) {
		BurgosAgency.name = nome;
	}

	public static String getName() {
		return BurgosAgency.name;
	}

	public static void setManager(String manager) {
		BurgosAgency.manager = manager;
	}

	public static String getManager() {
		return BurgosAgency.manager;
	}
	
}
