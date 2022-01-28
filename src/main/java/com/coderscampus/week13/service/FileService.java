package com.coderscampus.week13.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileService {
	private String fileName;
	
	public FileService () {
		// this is just a blank no arg constructor because we also
		//  have a 1-arg constructor explicitly defined (below)
	}
	
	public FileService (String fileName) {
		this.fileName = fileName;
	}
	
	public List<String> readFile () throws IOException {
		return Files.readAllLines(Paths.get(fileName));
	}
}
