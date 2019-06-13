package io.github.whoisalphahelix.helix.io;

import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class YamlFile extends IOFile {
	
	private final Yaml yamlSaver = new Yaml();
	private final Map<String, Object> yaml = new ConcurrentHashMap<>();
	
	public YamlFile(String pathname, IHelix helix) throws IOException {
		super(pathname, helix);
	}
	
	public YamlFile(String parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
	}
	
	public YamlFile(File parent, String child, IHelix helix) throws IOException {
		super(parent, child, helix);
	}
	
	public YamlFile(URI uri, IHelix helix) throws IOException {
		super(uri, helix);
	}

    public YamlFile(String pathname) throws IOException {
        this(pathname, Helix.helix());
    }

    public YamlFile(String parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public YamlFile(File parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public YamlFile(URI uri) throws IOException {
        this(uri, Helix.helix());
    }

	public YamlFile setValue(String path, Object obj) {
		yaml.put(path, obj);
		return this;
	}
	
	public YamlFile setDefaultValue(String path, Object obj) {
		if(!yaml.containsKey(path))
			yaml.put(path, obj);
		return this;
	}
	
	public YamlFile update() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this))) {
			this.yamlSaver.dump(this.yaml, writer);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
