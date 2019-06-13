package io.github.whoisalphahelix.helix.reflection;

import io.github.whoisalphahelix.helix.Cache;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.MultiKeyMap;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ReflectiveCache implements Cache {

    private final MultiKeyMap<SaveMethod> methodMap = new MultiKeyMap<>();
    private final MultiKeyMap<SaveMethod> privateMethodMap = new MultiKeyMap<>();

    private final MultiKeyMap<SaveField> fieldMap = new MultiKeyMap<>();
    private final MultiKeyMap<SaveField> privateFieldMap = new MultiKeyMap<>();

    private final MultiKeyMap<SaveConstructor> constructorMap = new MultiKeyMap<>();
    private final MultiKeyMap<SaveConstructor> privateConstructorMap = new MultiKeyMap<>();

    private final MultiKeyMap<Class<?>> typeMap = new MultiKeyMap<>();
	
	public ReflectiveCache(IHelix helix) {
		helix.cacheHandler().addCache(this);
	}

    public MultiKeyMap<SaveMethod> methods() {
		return this.methodMap;
	}

    public MultiKeyMap<SaveMethod> privateMethods() {
		return this.privateMethodMap;
	}

    public MultiKeyMap<SaveField> fields() {
		return this.fieldMap;
	}

    public MultiKeyMap<SaveField> privateFields() {
		return this.privateFieldMap;
	}

    public MultiKeyMap<SaveConstructor> constructors() {
		return this.constructorMap;
	}

    public MultiKeyMap<SaveConstructor> privateConstructors() {
		return this.privateConstructorMap;
	}

    public MultiKeyMap<Class<?>> types() {
		return this.typeMap;
	}
	
	@Override
	public boolean clear() {
		this.methods().clear();
		this.privateMethods().clear();
		
		this.fields().clear();
		this.privateFields().clear();
		
		this.constructors().clear();
		this.privateConstructors().clear();
		
		this.types().clear();
		
		return true;
	}
	
	@Override
	public String log() {
		return "Reflective Cache cleared!";
	}
}
