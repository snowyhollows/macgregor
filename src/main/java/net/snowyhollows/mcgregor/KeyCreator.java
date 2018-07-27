/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.mcgregor;

/**
 * @author efildre
 */
public class KeyCreator {
	private long i;
	private final String base;

	public KeyCreator(String base) {
		this.base = base;
	}

	public String create() {
		return base + ":" + ( i++ );
	}
}
