package com.unifonic.assignment.util;

import java.util.Random;

public class Utilties {
	public static String generatRandomCode(){
		StringBuilder salt = new StringBuilder();
		Random random = new Random();
		while (salt.length()< 4) {
			int index = (int) (random.nextFloat() * 10);
			salt.append(index);
		}
		return salt.toString();
	}

}
