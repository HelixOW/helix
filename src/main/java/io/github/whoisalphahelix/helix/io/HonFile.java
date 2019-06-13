package io.github.whoisalphahelix.helix.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.hon.Hon;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class HonFile extends IOFile {
	
	private Hon hon;
	
	public HonFile(String pathname, IHelix helix) throws IOException {
		super(pathname, helix);
		this.hon = new Hon(helix, readArray());
	}
	
	public HonFile(String parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
		this.hon = new Hon(helix, readArray());
	}
	
	public HonFile(File parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
		this.hon = new Hon(helix, readArray());
	}
	
	public HonFile(URI uri, IHelix helix) throws IOException {
		super(uri, helix);
		this.hon = new Hon(helix, readArray());
	}

    public HonFile(String pathname) throws IOException {
        this(pathname, Helix.helix());
    }

    public HonFile(String parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public HonFile(File parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public HonFile(URI uri) throws IOException {
        this(uri, Helix.helix());
    }

	private JsonArray readArray() {
		JsonArray array = new JsonArray();
		
		if(getContent().isEmpty() || !(getContent().startsWith("[") || getContent().endsWith("]"))) {
			array.add(new JsonObject());
			
			return array;
		}
		
		array = getHelix().ioHandler().getGson().fromJson(getContent(), JsonArray.class);
		
		return array;
	}
	
	public HonFile update() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this))) {
			writer.write(this.hon.toJson());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
