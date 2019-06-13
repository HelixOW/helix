package io.github.whoisalphahelix.helix.reflection.exceptions;

import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.reflection.SaveConstructor;
import io.github.whoisalphahelix.helix.reflection.SaveField;
import io.github.whoisalphahelix.helix.reflection.SaveMethod;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LogExceptionHandler implements ExceptionHandler {
	
	private final IHelix helix;

    public LogExceptionHandler() {
        this(Helix.helix());
    }

	@Override
	public SaveMethod noSuchMethod(Class<?> where, String name, Class<?>... parameterClasses) {
		this.helix.logger().log(Level.SEVERE, "Unable to find " + name +
				"(" + this.getClassArrayString(parameterClasses) + ") inside " + where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchMethod(Class<?> where, Class<?> type) {
		this.helix.logger().log(Level.SEVERE, "Unable to find method returning " + type.getName() + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchMethod(Class<?> where, Class<?>... parameterTypes) {
		this.helix.logger().log(Level.SEVERE, "Unable to find method with parameters "
				+ Arrays.stream(parameterTypes).map(Class::getName).collect(Collectors.toList()) + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchMethod(Class<?> where, String name) {
		this.helix.logger().log(Level.SEVERE, "Unable to find method with name " + name + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchPrivateMethod(Class<?> where, String name, Class<?>... parameterClasses) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private " + name + "("
				+ this.getClassArrayString(parameterClasses) + ") inside " + where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchPrivateMethod(Class<?> where, Class<?> type) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private method returning " + type.getName() + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchPrivateMethod(Class<?> where, Class<?>... parameterTypes) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private method with parameters "
				+ Arrays.stream(parameterTypes).map(Class::getName).collect(Collectors.toList()) + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveMethod noSuchPrivateMethod(Class<?> where, String name) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private method with name " + name + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveConstructor noSuchConstructor(Class<?> where, Class<?>... parameterClasses) {
		this.helix.logger().log(Level.SEVERE, "Unable to find " + where.getName() + "("
				+ this.getClassArrayString(parameterClasses) + ")");
		return null;
	}

	@Override
	public SaveConstructor noSuchPrivateConstructor(Class<?> where, Class<?>... parameterClasses) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private " + where.getName() + "("
				+ this.getClassArrayString(parameterClasses) + ")");
		return null;
	}

	@Override
	public SaveField noSuchField(Class<?> where, String name) {
		this.helix.logger().log(Level.SEVERE, "Unable to find " + name + " inside " + where.getName());
		return null;
	}

	@Override
	public SaveField noSuchField(Class<?> where, Class<?> type) {
		this.helix.logger().log(Level.SEVERE, "Unable to find type " + type.getName() + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public SaveField noSuchPrivateField(Class<?> where, String name) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private " + name + " inside " + where.getName());
		return null;
	}

	@Override
	public SaveField noSuchPrivateField(Class<?> where, Class<?> type) {
		this.helix.logger().log(Level.SEVERE, "Unable to find private type " + type.getName() + " inside "
				+ where.getName());
		return null;
	}

	@Override
	public Class<?> noSuchClass(String name) {
		this.helix.logger().log(Level.SEVERE, "Unable to find " + name);
		return null;
	}

	private String getClassArrayString(Class<?>... classes) {
		StringBuilder builder = new StringBuilder();

		for(Class<?> clazz : classes)
			builder.append(clazz.getName()).append(", ");

		return builder.toString();
	}
}
