package com.coderscampus.week13;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.coderscampus")
public class Week13Application {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Week13Application.class, args);
		
		// example of how to parse a CSV file 
		Reader in = new FileReader("recipes.txt");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT
											   .withIgnoreSurroundingSpaces()
											   .withHeader()
											   .withSkipHeaderRecord()
											   .parse(in);
		for (CSVRecord record : records) {
			int cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
			boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
		}
	}

}
