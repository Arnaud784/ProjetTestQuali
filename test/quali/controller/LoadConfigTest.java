package quali.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import quali.model.Context;

public class LoadConfigTest {

	@Test
	public void loadConfig() {
		Context.getInstance().load();
		boolean result = Context.getInstance().getUsersList().size() > 0;
		assertEquals(true, result);
	}
}
