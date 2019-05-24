package io.github.whoisalphahelix.helix.utils;

import io.github.whoisalphahelix.helix.handlers.UtilHandler;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class ArrayUtil {
	
	private final UtilHandler utilHandler;
	
	public String[] replaceInArray(String pattern, String replace, String... array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = array[i].replace(pattern, replace);
		}
		return array;
	}
	
	public <T> List<T> getTypesOf(Class<T> clazzType, Collection<Object> list) {
		return this.getTypesOf(clazzType, list.toArray());
	}
	
	public <T> List<T> getTypesOf(Class<T> clazzType, Object... list) {
		return (List<T>) Arrays.stream(list).filter(o -> o.getClass().isInstance(clazzType))
				.collect(Collectors.toList());
	}
	
	public void checkForLength(int min, int max, Collection<Object> list) {
		this.checkForLength(min, max, list.toArray());
	}
	
	public void checkForLength(int min, int max, Object... array) {
		if(array.length < min || array.length > max)
			throw new ArrayIndexOutOfBoundsException("You have to at least parse " + min + " arguments and up to "
					+ max);
	}
	
	@SafeVarargs
	public final <T> T[] replaceInArray(T obj, int pos, T... array) {
		if(pos > array.length - 1)
			return array;
		
		array[pos] = obj;
		
		return array;
	}
	
	public double min(double... a) {
		return Arrays.stream(a).min().orElse(a[0]);
	}
	
	public int min(int... a) {
		return Arrays.stream(a).min().orElse(a[0]);
	}
	
	public long min(long... a) {
		return Arrays.stream(a).min().orElse(a[0]);
	}
	
	public double max(double... a) {
		return Arrays.stream(a).max().orElse(a[0]);
	}
	
	public int max(int... a) {
		return Arrays.stream(a).max().orElse(a[0]);
	}
	
	public long max(long... a) {
		return Arrays.stream(a).max().orElse(a[0]);
	}
	
	public double sum(double... a) {
		return Arrays.stream(a).sum();
	}
	
	public int sum(int... a) {
		return Arrays.stream(a).sum();
	}
	
	public long sum(long... a) {
		return Arrays.stream(a).sum();
	}
	
	public double[] trim(int decimal, double... a) {
		for(int i = 0; i < a.length; i++)
			a[i] = getUtilHandler().getMathUtil().trim(a[i], decimal);
		
		return a;
	}
	
	public double[][] trim(int decimal, double[]... coordinates) {
		for(int i = 0; i < coordinates.length; i++) {
			coordinates[i] = new double[]{
					getUtilHandler().getMathUtil().trim(coordinates[i][0], decimal),
					getUtilHandler().getMathUtil().trim(coordinates[i][1], decimal),
					getUtilHandler().getMathUtil().trim(coordinates[i][2], decimal)};
		}
		return coordinates;
	}
	
	public <T> List<T> merge(List<List<T>> list) {
		return list.stream().reduce((l1, l2) -> {
			l1.addAll(l2);
			return l1;
		}).orElse(new ArrayList<>());
	}
	
	public <A, B> B[] map(A[] arr, Function<A, B> mapper) {
		return (B[]) Arrays.stream(arr).map(mapper).toArray();
	}
}
