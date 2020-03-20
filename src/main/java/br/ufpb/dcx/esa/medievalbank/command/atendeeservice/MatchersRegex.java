package br.ufpb.dcx.esa.medievalbank.command.atendeeservice;

import java.util.regex.Pattern;

import br.ufpb.dcx.esa.medievalbank.command.demand.Command;

public class MatchersRegex implements Command {
	private String email;
	@Override
	public Object run() {
		final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(this.email);
		return matcher.matches();
	}

}
