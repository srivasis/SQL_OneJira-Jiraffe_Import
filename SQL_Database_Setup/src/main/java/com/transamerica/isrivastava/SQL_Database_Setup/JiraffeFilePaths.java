package com.transamerica.isrivastava.SQL_Database_Setup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class JiraffeFilePaths {
	private ArrayList<String> filePaths;
	
	public JiraffeFilePaths(String firstLevelPath, String localDataPath) {
		long start = System.currentTimeMillis();
		System.out.println("\nBuilding Jiraffe Filepaths --------------------------------");
		this.filePaths = new ArrayList<>();
		buildFilePaths(firstLevelPath, localDataPath);
		System.out.println("Done Building Jiraffe Filepaths -------------- Time: " + (System.currentTimeMillis() - start));
	}

	public void buildFilePaths(String firstLevelPath, String localDataPath) {
		BufferedReader br;
		String lastDateEntry = "";

		try {
			br = new BufferedReader(new FileReader(localDataPath));
			lastDateEntry = br.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read from local file database.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No line to read.");
			e.printStackTrace();
		}
		
		// First Level -> Path to ONEJIRA
		File oneJiraFolder = new File(firstLevelPath);
		String[] files = oneJiraFolder.list();

		for (String firstLevelFile : files) {
			String[] newDate = firstLevelFile.split("-");
			if (lastDateEntry != null) {
				String[] oldDate = lastDateEntry.split("-");

				if (Integer.parseInt(newDate[0]) > Integer.parseInt(oldDate[0])) {
					recurse(firstLevelPath, firstLevelFile);
				} else if (Integer.parseInt(newDate[0]) == Integer.parseInt(oldDate[0])
						&& Integer.parseInt(newDate[1]) > Integer.parseInt(oldDate[1])) {
					recurse(firstLevelPath, firstLevelFile);
				} else if (Integer.parseInt(newDate[0]) == Integer.parseInt(oldDate[0])
						&& Integer.parseInt(newDate[1]) == Integer.parseInt(oldDate[1])
						&& Integer.parseInt(newDate[2]) > Integer.parseInt(oldDate[2])) {
					recurse(firstLevelPath, firstLevelFile);
				}
			} else {
				recurse(firstLevelPath, firstLevelFile);
			}
		}

		if (this.filePaths.size() > 0) {
			String lastDate = this.filePaths.get(this.filePaths.size() - 1).split("\\\\")[8];
			writeToStorageFile(lastDate, localDataPath);
		}
	}
	
	public void recurse(String firstLevelPath, String firstLevelFile) {
		// Second Level -> Date Onwards
		String secondLevelPath = firstLevelPath + "\\" + firstLevelFile;
		File files = new File(secondLevelPath);
		String[] jiraffeFiles = files.list();

		for (String secondLevelFile : jiraffeFiles) {
			if(secondLevelFile.contains("json")) {
				this.filePaths.add(secondLevelPath + "\\" + secondLevelFile);
			}
		}
	}
	
	// Writes the Last date read into local file database for future iterations
		public void writeToStorageFile(String entry, String localDataPath) {
			Writer writer = null;

			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localDataPath), "utf-8"));
				writer.write(entry);
			} catch (IOException ex) {
				System.out.println("Write to local file database failed.");
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
					/* ignore */}
			}
		}
	
	public ArrayList<String> getFilePaths() {
		return this.filePaths;
	}

}