package com.jtriemstra.test.integration1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FileGenerator  {

	public static void main(String[] args) throws IOException {
		String chars = "ABCDEFGHIJ KLMNOPQRST UVWXYZ";
		long charCount = Long.parseLong(args.length == 0 ? "100000000" : args[0]);
		FileWriter writer = new FileWriter(getFileName());
		Random r = new Random();
		
		for (long i=0; i<charCount; i++) {
			writer.append(chars.charAt(r.nextInt(chars.length())));
			if (i % 1000 == 0) {
				writer.append("\r\n");
			}
		}
		
		writer.close();	
	}
	
	private static String getFileName() {
		return "c:\\temp\\test_" + DateTimeFormatter.ofPattern("uuuuMMddHHmmss").format(LocalDateTime.now()) + ".txt";
	}

}
