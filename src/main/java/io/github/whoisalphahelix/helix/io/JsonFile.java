package io.github.whoisalphahelix.helix.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
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
@EqualsAndHashCode(callSuper = false)
@ToString
public class JsonFile extends IOFile {
	
	private JsonElement element;
	
	public JsonFile(String pathname, IHelix helix) throws IOException {
		super(pathname, helix);
		this.element = read();
	}
	
	public JsonFile(String parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
		this.element = read();
	}
	
	public JsonFile(File parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
		this.element = read();
	}
	
	public JsonFile(URI uri, IHelix helix) throws IOException {
		super(uri, helix);
		this.element = read();
	}

    public JsonFile(String pathname) throws IOException {
        this(pathname, Helix.helix());
    }

    public JsonFile(String parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public JsonFile(File parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public JsonFile(URI uri) throws IOException {
        this(uri, Helix.helix());
    }

	private JsonElement read() {
		if(getContent().isEmpty())
			return JsonNull.INSTANCE;
		
		return this.getHelix().ioHandler().getGson().fromJson(getContent(), JsonElement.class);
	}
	
	public JsonFile write(JsonElement element) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this))) {
			this.element = element;
			writer.write(this.getHelix().ioHandler().getGson().toJson(this.element));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
