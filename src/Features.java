package com.insight.pyamo;

import java.io.*;
import java.util.*;
import java.util.HashMap;

public class Features {

	String path = null;
	Map<String, LinkedList<String>> relations = new HashMap<String, LinkedList<String>>();
	ReadDataCreateMap rdcm = null;
	FileInputStream inputStream;
	Scanner sc;

	public Features(String path, ReadDataCreateMap rdcm) throws Exception {
		this.path = path;
		this.rdcm = rdcm;
		relations = rdcm.splitAdd();
	}
// Implementing the first feature
	
	public void firstFeature(String path) throws Exception {

		int cnt = 0;
		String line;
		String[] splitted;
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw); 	// creates the output file "output1.txt"
		inputStream = new FileInputStream(this.path);	// To get the input from "stream_pyment.txt" file
		sc = new Scanner(inputStream);
		sc.nextLine();
		try {
			while (sc.hasNextLine()) 
			{
				line = sc.nextLine();
				splitted = line.split(",");
				
	// Check if the id1 is the key in hashmap			
				if (relations.containsKey(splitted[1].replaceAll("\\s+", ""))) 
				{
					LinkedList<String> list = relations.get(splitted[1].replaceAll("\\s+", ""));
					// If yes then search for id2 in the values of the key "id1" and make the value of "cnt" as 1
					if (list.contains(splitted[2].replaceAll("\\s+", ""))) 
					{
						cnt = 1;
					}
				}
	// Check if the id2 is the key in hashmap when the value of "cnt" is not equal to 1	
				if (relations.containsKey(splitted[2].replaceAll("\\s+", "")) && cnt != 1) 
				{
					// If yes then search for id1 in the value of the key "id2" and make the value of "cnt" as 1
					LinkedList<String> list = relations.get(splitted[2].replaceAll("\\s+", ""));
					if (list.contains(splitted[1].replaceAll("\\s+", "")))
					{
						cnt = 1;
					}
				}
			}
			// If the value of "cnt" = 1, the payment is "trusted" else it is "unverified"
			if (cnt == 1)
				bw.write("trusted\n");
			else
				bw.write("unverified\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				fw.close();
			if (bw != null)
				bw.close();
			if (inputStream != null)
				inputStream.close();
			if (sc != null)
				sc.close();
		}

	}
	
	
	// Implementation of the second featrue
	
	public void secondFeature(String path) throws Exception{
		int cnt=0;
		String line;
		String[] splitted;
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);       // creates the output file "output2.txt"
		inputStream = new FileInputStream(this.path);     // To get the input from "stream_pyment.txt" file
		sc = new Scanner(inputStream);
		sc.nextLine();
		try{
			while(sc.hasNextLine()){
				line = sc.nextLine();
				splitted = line.split(","); 
			
			// Check if the id1 is the key in hashmap			

				if (relations.containsKey(splitted[1].replaceAll("\\s+", ""))) 
				{
					// Check for the values in hash map where key = "id1"
					LinkedList<String> list = relations.get(splitted[1].replaceAll("\\s+", ""));
					int len = list.size();
					
					
					// For all the values in the key "id1", search in the values of the corresponding key
					// If id2 is found in these new keys, make the value of variable "cnt" = 2
					while(len > 0)
					{
						int count = 0;					
						String newkey1 = relations.get(splitted[1].replaceAll("\\s+", "")).get(count);
							if (relations.containsKey(newkey1.replaceAll("\\s+", ""))) 
							{
								LinkedList<String> list2 = relations.get(newkey1.replaceAll("\\s+", ""));
								if (list2.contains(splitted[2].replaceAll("\\s+", ""))) 
								{
									cnt = 2;
								}
							}
						count++;
						len--;
					}		
							
				}
					
		// Check if the id2 is the key in hashmap
				
				if (relations.containsKey(splitted[2].replaceAll("\\s+", "")) && cnt != 2) 
				{
					// Check for the values in hash map where key = "id2"

					LinkedList<String> list = relations.get(splitted[2].replaceAll("\\s+", ""));
					int len = list.size();
					
					// For all the values in the key "id2", search in the values of the corresponding key
					// If id1 is found in these new keys, make the value of variable "cnt" = 2
					while(len > 0)
					{
						int count = 0;					
						String newkey2 = relations.get(splitted[2].replaceAll("\\s+", "")).get(count);
							if (relations.containsKey(newkey2.replaceAll("\\s+", ""))) 
							{
								LinkedList<String> list3 = relations.get(newkey2.replaceAll("\\s+", ""));
								if (list3.contains(splitted[1].replaceAll("\\s+", ""))) 
								{
									cnt = 2;
								}
							}
						count = count++;
						len--;
					}
				}
			}
		
				if (cnt == 2)
					bw.write("trusted\n");
				else
					bw.write("unverified\n");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fw != null)
					fw.close();
				if (bw != null)
					bw.close();
				if (inputStream != null)
					inputStream.close();
				if (sc != null)
					sc.close();
			}

	}
	
	
	// Implementhing the third feature for friends level upto 3 and 4 using the same logic as used in feature 2
	
	public void thirdFeature(String path) throws Exception{
		int cnt=0;
		String line;
		String[] splitted;
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		inputStream = new FileInputStream(this.path);
		sc = new Scanner(inputStream);
		sc.nextLine();
		try{
			while(sc.hasNextLine()){
				line = sc.nextLine();
				splitted = line.split(","); 
				
				if (relations.containsKey(splitted[1].replaceAll("\\s+", ""))) 
				{
					LinkedList<String> list = relations.get(splitted[1].replaceAll("\\s+", ""));
					int len = list.size();
					
					while(len > 0)
					{
						int count = 0;					
						String newkey1 = relations.get(splitted[1].replaceAll("\\s+", "")).get(count);
						String newkey2 = relations.get(newkey1.replaceAll("\\s+", "")).get(count);
						
						if (relations.containsKey(newkey2.replaceAll("\\s+", ""))) 
							{
								LinkedList<String> list2 = relations.get(newkey2.replaceAll("\\s+", ""));
								if (list2.contains(splitted[2].replaceAll("\\s+", ""))) 
								{
									cnt = 3;
								}
							}
						
						
						String newkey3 = relations.get(newkey2.replaceAll("\\s+", "")).get(count);
						
						if (relations.containsKey(newkey3.replaceAll("\\s+", ""))) 
							{
								LinkedList<String> list2 = relations.get(newkey3.replaceAll("\\s+", ""));
								if (list2.contains(splitted[2].replaceAll("\\s+", ""))) 
								{
									cnt = 4;
								}
							}
						
						count++;
						len--;
					}		
							
				}
					
				
				if (relations.containsKey(splitted[2].replaceAll("\\s+", "")) && cnt != 2) 
				{
					LinkedList<String> list = relations.get(splitted[2].replaceAll("\\s+", ""));
					int len = list.size();
					while(len > 0)
					{
						int count = 0;					
						String otherkey1 = relations.get(splitted[2].replaceAll("\\s+", "")).get(count);
						String otherkey2 = relations.get(otherkey1.replaceAll("\\s+", "")).get(count);

							if (relations.containsKey(otherkey2.replaceAll("\\s+", ""))) 
							{
								LinkedList<String> list3 = relations.get(otherkey2.replaceAll("\\s+", ""));
								if (list3.contains(splitted[1].replaceAll("\\s+", ""))) 
								{
									cnt = 3;
								}
							}
							
							
							String otherkey3 = relations.get(otherkey2.replaceAll("\\s+", "")).get(count);
							
							if (relations.containsKey(otherkey3.replaceAll("\\s+", ""))) 
								{
									LinkedList<String> list2 = relations.get(otherkey3.replaceAll("\\s+", ""));
									if (list2.contains(splitted[2].replaceAll("\\s+", ""))) 
									{
										cnt = 4;
									}
								}
							
						count = count++;
						len--;
					}
				}
			}
		
				if (cnt == 3 || cnt == 4)
					bw.write("trusted\n");
				else
					bw.write("unverified\n");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fw != null)
					fw.close();
				if (bw != null)
					bw.close();
				if (inputStream != null)
					inputStream.close();
				if (sc != null)
					sc.close();
			}

	}	
}
