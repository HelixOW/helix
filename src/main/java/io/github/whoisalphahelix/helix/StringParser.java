package io.github.whoisalphahelix.helix;

import java.util.*;

public class StringParser {
	private static final List<ParsedObject<?>> PARSED_OBJECTS = new ArrayList<>(Arrays.asList(
			(ParsedObject<UUID>) UUID::fromString,
			(ParsedObject<Boolean>) commandString -> {
				List<String> trueBools = Arrays.asList("true", "on", "t");
				List<String> falseBools = Arrays.asList("false", "off", "f");
				
				if(trueBools.contains(commandString))
					return true;
				else if(falseBools.contains(commandString))
					return false;
				else
					throw new IllegalArgumentException("Can't cast " + commandString + " to a boolean!");
			},
			(ParsedObject<Integer>) Integer::parseInt,
			(ParsedObject<Short>) Short::parseShort,
			(ParsedObject<Double>) Double::parseDouble,
			(ParsedObject<Float>) Float::parseFloat,
			(ParsedObject<Long>) Long::parseLong,
			(ParsedObject<Byte>) Byte::parseByte,
			(ParsedObject<Character>) commandString -> {
				if(commandString.length() == 1)
					return commandString.charAt(0);
				throw new IllegalArgumentException("Can't cast String with length of " + commandString.length() + " to char");
			},
			(ParsedObject<String>) commandString -> commandString
	));
	
	public final Object parseString(String arg) {
		for(ParsedObject<?> o : PARSED_OBJECTS)
			try {
				return o.fromString(arg);
			} catch(Exception ignored) {
			}
		
		return null;
	}
	
	public static List<ParsedObject<?>> getParsedObjects() {
		return PARSED_OBJECTS;
	}
}
