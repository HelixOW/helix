package io.github.whoisalphahelix.helix.handlers;

import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.annotations.Random;
import io.github.whoisalphahelix.helix.annotations.Singleton;
import io.github.whoisalphahelix.helix.reflection.SaveField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@RequiredArgsConstructor
public class AnnotationHandler {
	
	private final IHelix helix;
	
	public void createSingletons() {
		this.helix.reflections2().getTypesAnnotatedWith(Singleton.class).stream().filter(aClass -> {
			try {
				return aClass.getDeclaredConstructor() != null;
			} catch(NoSuchMethodException e) {
				e.printStackTrace();
				return false;
			}
		}).forEach(singletonClass -> Arrays.stream(singletonClass.getDeclaredFields()).filter(field
				-> field.isAnnotationPresent(Singleton.class))
				.filter(field -> field.getType().equals(singletonClass)).map(SaveField::new).forEach(field -> {
					try {
						field.set(null, singletonClass.getDeclaredConstructor().newInstance());
					} catch(ReflectiveOperationException e) {
						e.printStackTrace();
					}
				}));
	}
	
	public void randomizeFields() {
		this.helix.reflections2().getTypesAnnotatedWith(Random.class).stream().filter(aClass -> {
			try {
				return aClass.getDeclaredConstructor() != null;
			} catch(NoSuchMethodException e) {
				e.printStackTrace();
				return false;
			}
		}).forEach(aClass -> {
			try {
				randomizeFields(aClass.getDeclaredConstructor().newInstance());
			} catch(ReflectiveOperationException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void randomizeFields(Object o) {
		Arrays.stream(o.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Random.class))
				.map(SaveField::new).forEach(randomField -> {
			Random r = randomField.asNormal().getAnnotation(Random.class);
			
			switch(randomField.asNormal().getType().getName().toLowerCase()) {
				case "string":
					randomField.set(o, this.helix.utilHandler().getStringUtil().generateRandomString(r.max()));
					break;
				case "double":
					randomField.set(o, ThreadLocalRandom.current().nextDouble(r.min(), r.max()));
					break;
				case "float":
					randomField.set(o, ThreadLocalRandom.current().nextDouble((r.min() < Float.MIN_VALUE ? Float.MIN_VALUE : r.min()), (r.max() > Float.MAX_VALUE ? Float.MAX_VALUE : r.max())));
					break;
				case "int":
				case "integer":
					randomField.set(o, ThreadLocalRandom.current().nextInt(r.min(), r.max()));
					break;
				case "long":
					randomField.set(o, ThreadLocalRandom.current().nextLong(r.min(), r.max()));
					break;
				case "short":
					randomField.set(o, ThreadLocalRandom.current().nextInt((r.min() < Short.MIN_VALUE ? Short.MIN_VALUE : r.min()), (r.max() > Short.MAX_VALUE ? Short.MAX_VALUE : r.max())));
					break;
				case "boolean":
					randomField.set(o, ThreadLocalRandom.current().nextBoolean());
					break;
				case "uuid":
					randomField.set(o, UUID.randomUUID());
					break;
			}
		});
	}
}
