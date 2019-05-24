package io.github.whoisalphahelix.helix.utils;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class MathUtil {
	
	public byte toAngle(float v) {
		return (byte) ((int) (v * 256.0F / 360.0F));
	}
	
	public double toDelta(double v) {
		return ((v * 32) * 128);
	}
	
	public int floor(double toFloor) {
		int intFloor = (int) toFloor;
		return toFloor < (double) intFloor ? intFloor - 1 : intFloor;
	}
	
	public int toMultipleOfN(int val, int n) {
		return ((val / n) + 1) * n;
	}
	
	public double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}
	
	public double trim(double value, int precision) {
		String d = Double.toString(value).split("\\.")[1];
		
		StringBuilder trimmed = new StringBuilder();
		
		for(int i = 0; i < precision; i++)
			trimmed.append(d.charAt(i));
		
		return Double.valueOf(Double.toString(value).split("\\.")[0] + "." + trimmed.toString());
	}
	
	public boolean between(double min, double max, double value) {
		return min <= value && value <= max;
	}
	
	public int decimals(double value) {
		return Integer.parseInt(String.valueOf(value).split("\\.")[1]);
	}

	public int decimalAmount(double value) {
		return String.valueOf(value).split("\\.")[1].length();
	}
}
