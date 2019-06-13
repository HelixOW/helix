package io.github.whoisalphahelix.helix.hon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.handlers.UtilHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
public class Hon {
	
	@Getter
	private final IHelix helix;
	private JsonObject head;
	private JsonArray arrayHead;
	
	public Hon(IHelix helix, JsonArray arrayHead) {
		this.helix = helix;
		
		this.arrayHead = arrayHead;
		this.head = (JsonObject) arrayHead.get(0);
	}
	
	public Hon(IHelix helix) {
		this.helix = helix;
		
		this.arrayHead = new JsonArray();
		this.arrayHead.add(new JsonObject());
		this.head = (JsonObject) arrayHead.get(0);
	}

    public Hon(JsonArray arrayHead) {
        this(Helix.helix(), arrayHead);
    }

    public Hon() {
        this(Helix.helix());
    }
	
	public Hon add(Object value) {
		JsonObject obj = new JsonObject();
		
		obj.add("class", new JsonPrimitive(value.getClass().getName()));
		obj.add("value", this.helix.ioHandler().getGson().toJsonTree(value));
		
		this.arrayHead.add(obj);
		return this;
	}
	
	public Hon remove(int index) {
		if(index == 0) return this;
		
		this.arrayHead.remove(index);
		
		return this;
	}
	
	public boolean has(int index) {
		return this.arrayHead.size() <= index;
	}
	
	public List<Object> getAll() {
		List<Object> objs = new LinkedList<>();
		
		if(this.arrayHead.size() == 1) return objs;
		
		for(int i = 1; i < this.arrayHead.size(); i++) {
			JsonObject obj = (JsonObject) this.arrayHead.get(i);
			
			try {
				Class<?> clss = Class.forName(obj.get("class").getAsString());
				objs.add(this.helix.ioHandler().getGson().fromJson(obj.get("value"), clss));
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return objs;
	}
	
	public Hon set(String path, Object value) {
		Map.Entry<List<JsonObject>, List<String>> paths = getJsonPath(path);
		JsonObject last = paths.getKey().get(paths.getKey().size() - 1);
		String[] pathArray = path.split("\\.");
		
		JsonObject wrapper = new JsonObject();
		
		wrapper.add("class", new JsonPrimitive(value.getClass().getName()));
		wrapper.add("value", this.helix.ioHandler().getGson().toJsonTree(value));
		
		last.add(pathArray[pathArray.length - 1], wrapper);
		
		this.head = paths.getKey().get(0);
		
		return this;
	}
	
	public Hon setDefault(String path, Object value) {
		if(!contains(path))
			return set(path, value);
		return this;
	}
	
	public <T> T get(String path) {
		if(!contains(path)) return null;
		
		Map.Entry<List<JsonObject>, List<String>> paths = getJsonPath(path);
		JsonObject last = paths.getKey().get(paths.getKey().size() - 1);
        JsonObject wrapper = last.getAsJsonObject(UtilHandler.strings().getAfterLast(path, "\\."));
		
		try {
			Class<T> valClass = (Class<T>) Class.forName(wrapper.get("class").getAsString());
			
			return this.helix.ioHandler().getGson().fromJson(wrapper.get("value"), valClass);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean contains(String path) {
		Map.Entry<List<JsonObject>, List<String>> paths = getJsonPath(path);
		
		for(int i = 0; i < paths.getValue().size(); i++) {
			if(!paths.getKey().get(i).has(paths.getValue().get(i)))
				return false;
		}
		return true;
	}
	
	private Map.Entry<List<JsonObject>, List<String>> getJsonPath(String path) {
		String[] pathArray = path.split("\\.");
		LinkedList<JsonObject> objects = new LinkedList<>();
		List<String> names = new LinkedList<>();
		
		objects.add(this.head);
		
		for(int i = 1; i < pathArray.length; i++) {
			if(objects.get(i - 1).has(pathArray[i - 1])) {
				objects.add(objects.get(i - 1).getAsJsonObject(pathArray[i - 1]));
			} else {
				objects.add(new JsonObject());
			}
		}
		
		for(int i = 0; i < objects.size() - 1; i++) {
			objects.get(i).add(pathArray[i], objects.get(i + 1));
			names.add(pathArray[i]);
		}
		
		names.add(pathArray[pathArray.length - 1]);
		
		return new AbstractMap.SimpleEntry<>(objects, names);
	}
	
	public String toJson() {
		this.arrayHead.set(0, this.head);
		return this.helix.ioHandler().getGson().toJson(this.arrayHead);
	}
}
