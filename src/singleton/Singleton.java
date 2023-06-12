package singleton;

import java.util.ArrayList;
import java.util.List;

import dto.AccountDto;

public class Singleton {

	private static Singleton sc = null;
	public List<AccountDto> accountList = null;

	private Singleton() {
		accountList = new ArrayList<AccountDto>();
	}

	public static Singleton getInstance() {
		if (sc == null) {
			sc = new Singleton();
		}
		return sc;
	}

}
