package io.github.whoisalphahelix.helix.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class IOHandler {
	
	public static final String HOME = System.getProperty("user.home");
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public File createFile(File file) throws IOException {
		if(!file.exists() && !file.isDirectory()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

        if (!file.setExecutable(true))
            System.out.println("Unable to set File executable (" + file.getAbsolutePath() + ")");
        if (!file.setWritable(true)) System.out.println("Unable to set File writable (" + file.getAbsolutePath() + ")");
        if (!file.setReadable(true)) System.out.println("Unable to set File readable (" + file.getAbsolutePath() + ")");

		return file;
	}
	
	public File createFile(String path) throws IOException {
		return createFile(new File(path));
	}
	
	public String readFile(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.toURI())));
	}
	
	public File createFolder(File folder) {
		if(folder.isDirectory())
            if (!folder.mkdirs()) System.out.println("Unable to create folder (" + folder.getAbsolutePath() + ")");
		return folder;
	}
	
	public File createFolder(String folder) {
		return createFolder(new File(folder));
	}
	
	public File[] findFilesByExtension(File folder, String extension) {
		return Arrays.stream(Objects.requireNonNull(folder.listFiles())).filter(file ->
				file.getName().endsWith("." + extension)
		).toArray(File[]::new);
	}
	
	public File[] findFilesByExtension(String folder, String extension) {
		return findFilesByExtension(new File(folder), extension);
	}
}
