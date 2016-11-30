/* Author : Sameer Sinha
   Creating a hash map to store id1 as key and all the ids to whom id1 has made a payment 
   as value (in Linked List)
   The batch_payment.txt file has been used to create this hash map
*/
package com.insight.pyamo;

import java.io.*;
import java.util.HashMap;
import java.util.*;

public class ReadDataCreateMap {

	int count = 0;
	String path = null;
	Map<String, LinkedList<String>> relations = new HashMap<String, LinkedList<String>>();

	public ReadDataCreateMap(String path) {
		this.path = path;
	}

	public Map<String, LinkedList<String>> splitAdd() throws Exception {
		FileInputStream inputStream = new FileInputStream(this.path);
		Scanner sc = new Scanner(inputStream);
		sc.nextLine();
		try {
			
			// Splitting the text file on "," to filter out ids
			
			while (sc.hasNextLine()) 
			{
				String line = sc.nextLine();
				String[] splitted = line.split(",");
				while (!splitted[0].contains("//s+")) 
				{
					if (relations.containsKey(splitted[1].replaceAll("\\s+", ""))) 
					{
						LinkedList<String> list = relations.get(splitted[1].replaceAll("\\s+", ""));
						list.add(splitted[2].replaceAll("\\s+", ""));
						relations.put(splitted[1], list);
					} else 
					{
						LinkedList<String> list = new LinkedList<String>();
						list.add(splitted[2].replaceAll("\\s+", ""));
						relations.put(splitted[1].replaceAll("\\s+", ""), list);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}

		}
		return relations;
	}

}
