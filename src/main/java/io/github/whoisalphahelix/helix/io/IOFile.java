package io.github.whoisalphahelix.helix.io;

import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class IOFile extends File {
	
	private final IHelix helix;
	private String content;
	
	public IOFile(String pathname, IHelix helix) throws IOException {
		super(pathname);
		this.helix = helix;
		this.helix.ioHandler().createFile(this);
		this.content = this.helix.ioHandler().readFile(this);
	}
	
	public IOFile(String parent, String child, IHelix helix) throws IOException {
		super(parent, child);
		this.helix = helix;
		this.helix.ioHandler().createFile(this);
		this.content = this.helix.ioHandler().readFile(this);
	}
	
	public IOFile(File parent, String child, IHelix helix) throws IOException {
		super(parent, child);
		this.helix = helix;
		this.helix.ioHandler().createFile(this);
		this.content = this.helix.ioHandler().readFile(this);
	}
	
	public IOFile(URI uri, IHelix helix) throws IOException {
		super(uri);
		this.helix = helix;
		this.helix.ioHandler().createFile(this);
		this.content = this.helix.ioHandler().readFile(this);
	}

    public IOFile(String pathname) throws IOException {
        this(pathname, Helix.helix());
    }

    public IOFile(String parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public IOFile(File parent, String child) throws IOException {
        this(parent, child, Helix.helix());
    }

    public IOFile(URI uri) throws IOException {
        this(uri, Helix.helix());
    }

	public IOFile write(String toWrite) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this))) {
			this.content = this.content + System.lineSeparator() + toWrite;
			writer.write(this.content);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
