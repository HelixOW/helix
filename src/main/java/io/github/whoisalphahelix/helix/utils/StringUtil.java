package io.github.whoisalphahelix.helix.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StringUtil {
	
	public String generateRandomString(int size) {
		return generateRandomString(size, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	
	public String generateRandomString(int size, char... pool) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {
			char c = pool[ThreadLocalRandom.current().nextInt(pool.length)];
			sb.append(c);
		}
		return sb.toString();
	}
	
	public String generateRandomString(int size, String pool) {
		return generateRandomString(size, pool.toCharArray());
	}
	
	public boolean isLong(String s) {
		Scanner sc = new Scanner(s.trim());
		
		if(!sc.hasNextLong()) return false;
		
		sc.nextLong();
		return !sc.hasNext();
	}
	
	public boolean isDouble(String s) {
		Scanner sc = new Scanner(s.trim());
		
		if(!sc.hasNextDouble()) return false;
		
		sc.nextDouble();
		return !sc.hasNext();
	}

	public List<String> upperEverything(Collection<String> collection) {
		return collection.stream().map(String::toUpperCase).collect(Collectors.toList());
	}

	public List<String> lowerEverything(Collection<String> collection) {
		return collection.stream().map(String::toLowerCase).collect(Collectors.toList());
	}
	
	public boolean matches(String str, String... possible) {
		return Arrays.asList(possible).contains(str);
	}
	
	public String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}
	
	public String getAfterLast(String text, String regex) {
		String[] sp = text.split(regex);
		
		return sp[sp.length - 1];
	}
}
