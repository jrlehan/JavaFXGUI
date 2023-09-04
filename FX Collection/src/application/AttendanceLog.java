package application;
import java.util.*;
import java.io.*;
public class AttendanceLog {
	Vector<Log> logs;
	
	public static String removeLastChar(String s) {
	    return (s == null || s.length() == 0)
	      ? null 
	      : (s.substring(0, s.length() - 1));
	}
	
	Vector<String> print_collection() {
		Vector<StudentEntry> printCollection = new Vector<StudentEntry>();
		Vector<String> returnVal = new Vector<String>();
		for (Log l : logs) {
			boolean notAdded = true;
			for (StudentEntry pc : printCollection) {
				if (l.name.contains(pc.name)) {
					pc.addTimeDate("'" + l.time + " " + l.date + "'");
					notAdded = false;
					break;
				}
			};
			if (notAdded) {
				StudentEntry newPrint = new StudentEntry(l.name, "'" + l.time + " " + l.date + "'");
				printCollection.add(newPrint);
			}
		};
		
		System.out.println("** This is the entire Collection Data currently stored **");
		for (StudentEntry pc : printCollection) {
			System.out.print(pc.name + " ");
			System.out.println(pc.attendance.toString());
			returnVal.add(pc.name + " " + pc.attendance.toString());
		}
		
		return returnVal;
	}
	
	
	void load_log(String filename) {
		
		Scanner infile = null;
		try
		{
			infile = new Scanner(new FileReader(filename));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
			e.printStackTrace(); // prints error(s)
			return;
			// System.exit(0); // Exits entire program
		}
		
		while(infile.hasNextLine())
		{
			String line = infile.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(line);

			String name = "";
			String time = "";
			String date = "";
			
			// First and Last Name
			name += tokenizer.nextToken();
			name += " ";
			name += tokenizer.nextToken();
			name = removeLastChar(name);
			
			// Time and Date
			time += tokenizer.nextToken();
			date += tokenizer.nextToken();
			
			Log entry = new Log(name, time, date);
			logs.add(entry);
		}

		infile.close( );
	}
	
	int print_count() {
		System.out.println(logs.size());
		return logs.size();
	}

	public AttendanceLog() {
		this.logs = new Vector<Log>();
	}
	
	
}
