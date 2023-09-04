import java.util.*;
import java.text.*;
public class AttendanceApp {
	AttendanceLog swipeData;
	StudentRoster studentData;
	
	Vector<String> list_students_not_in_class() {
		Vector<String> missing = new Vector<String>();
		
		for (Student s : studentData.students) {
			boolean present = false;
			for (Log l : swipeData.logs) {
				if (l.name.contains(s.name)) {
					present = true;
					break;
				}
			}
			
			if (!present) {
				missing.add(s.name);
			}
		}
		
		return missing;
	}
	
	Vector<String> list_all_times_checking_in_and_out(Student student) {
		Vector<String> printCollection = new Vector<String>();
		
		for (Log l : swipeData.logs) {
			if (l.name.contains(student.name)) {
				printCollection.add(l.name + ", " + l.time + " " + l.date);
			}
		}
		
		return printCollection;
	}
	
	Vector<String> list_all_times_checked_in(String date) {
		Vector<Log> printCollection = new Vector<Log>();
		Vector<String> returnVal = new Vector<String>();
		for (Log l : swipeData.logs) {
			if (!(l.date.contains(date))) {
				continue;
			}
			
			boolean notAdded = true;
			for (int i = 0; i < printCollection.size(); ++i) {
				if (l.name.contains(printCollection.get(i).name)) {
					if (l.compareTime(printCollection.get(i)) == -1) {
						printCollection.remove(i);
						printCollection.add(l);
					}
					notAdded = false;
					break;
				}			
			}
			if (notAdded) {
				printCollection.add(l);
			}
		}
		
		for (Log pc : printCollection) {
			returnVal.add(pc.name + ", " + pc.time + " " + pc.date);
		}
		return returnVal;
	}
	
	Vector<String> list_students_late_to_class(String timestamp) {
		Vector<Log> printCollection = new Vector<Log>();
		Vector<String> returnVal = new Vector<String>();
		String startTime = timestamp.substring(0, 8);
		System.out.println(startTime);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			Date d = df.parse(startTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MINUTE, 50);
			String endTime = df.format(cal.getTime());
			//System.out.println(endTime);
			
			String date = timestamp.substring(10);
			//System.out.println(date);
			
			Log start = new Log("start", startTime, date);
			Log end = new Log("end", endTime, date);
			
			for (Log l : swipeData.logs) {
				if (!(l.date.contains(date))) {
					continue;
				}
				//System.out.println(l.time);
				if ((l.compareTime(start) == 1) && (l.compareTime(end) == -1)) {
					printCollection.add(l);
				}
				
			}
		} catch(Exception e) {
			System.out.println("Invalid timestamp entered");
			return new Vector<String>(); 
		}
		
		for (Log pc : printCollection) {
			returnVal.add(pc.name + ", " + pc.time + " " + pc.date);
		}
		
		return returnVal;
	}
	
	Vector<String> print_attendance_data_for_student(Student student) {
		StudentEntry returnVal = new StudentEntry(student.name);
		boolean found = false;
		for (Log l : swipeData.logs) {
			if (l.name.contains(student.name)) {
				returnVal.addTimeDate("'" + l.time + " " + l.date + "'");
				found = true;
			}
		}
		
		Vector<String> print = new Vector<String>();
		if (found) {
			print.add(returnVal.name + " " + returnVal.attendance.toString());
		} else {
			print.add("No student of this name in the attendance log");
		}
		
		return print;
	}
	
	boolean is_present(Student student, String date) {
		for (Log l : swipeData.logs) {
			if (!l.name.contains(student.name)) {
				continue;
			}
			if (l.date.contains(date)) {
				return true;
			}
		}
		return false;
	}
	
	Vector<String> list_all_students_checked_in(String date) {
		Vector<String> returnVal = new Vector<String>();
		Vector<Log> printCollection = new Vector<Log>();
		for (Log l : swipeData.logs) {
			if (!(l.date.contains(date))) {
				continue;
			}
			
			boolean notAdded = true;
			for (int i = 0; i < printCollection.size(); ++i) {
				if (l.name.contains(printCollection.get(i).name)) {
					if (l.compareTime(printCollection.get(i)) == -1) {
						printCollection.remove(i);
						printCollection.add(l);
					}
					notAdded = false;
					break;
				}			
			}
			if (notAdded) {
				printCollection.add(l);
			}
		}
		
		for (Log pc : printCollection) {
			returnVal.add(pc.name);
		}
		
		return returnVal;
	}
	
	Vector<String> list_all_students_checked_in_before(String date, String time) {
		Log test = new Log("test", time, date);
		Vector<String> returnVal = new Vector<String>();
		Vector<Log> printCollection = new Vector<Log>();
		for (Log l : swipeData.logs) {
			if (!(l.date.contains(date))) {
				continue;
			}
			
			boolean notAdded = true;
			for (int i = 0; i < printCollection.size(); ++i) {
				if (l.name.contains(printCollection.get(i).name)) {
					notAdded = false;
					break;
				}			
			}
			if (notAdded && (l.compareTime(test) == -1)) {
				printCollection.add(l);
			}
		}
		
		
		for (Log pc : printCollection) {
			returnVal.add(pc.name);
		}
		
		return returnVal;
	}
	
	Vector<String> list_students_attendance_count(Integer days) {
		Vector<String> returnVal = new Vector<String>();
		Vector<StudentEntry> printCollection = new Vector<StudentEntry>();
		for (Log l : swipeData.logs) {
			boolean notAdded = true;
			for (StudentEntry pc : printCollection) {
				if (l.name.contains(pc.name)) {
					boolean newDate = true;
					for (String a : pc.attendance) {
						if (a.contains(l.date)) {
							newDate = false;
						}
					}
					if (newDate) {
						pc.addTimeDate(l.date);
					}
					notAdded = false;
					break;
				}
			};
			if (notAdded) {
				StudentEntry newPrint = new StudentEntry(l.name, l.date);
				printCollection.add(newPrint);
			}
		};
		
		System.out.println("** This is the entire Collection Data currently stored **");
		for (StudentEntry pc : printCollection) {
			if (pc.attendance.size() >= days) {
				returnVal.add(pc.name + " " + pc.attendance.toString());
			}
		}
		
		return returnVal;
	}
	
	Vector<String> get_first_student_to_enter(String date) {
		Vector<String> returnVal = new Vector<String>();
		Log returnLog = swipeData.logs.get(0);
		for (Log l : swipeData.logs) {
			if (!(l.date.contains(date))) {
				continue;
			}
			
			if (returnLog.compareTime(l) == 1) {
				returnLog = l;
			}	
		}
		
		returnVal.add("**** First student to enter on " + date + " ****");
		returnVal.add(returnLog.name);
		return returnVal;
	}
	
	void print_query_list(Vector<?> query) {
		for (var t : query) {
			System.out.println(t.toString());
		}
	}
	
	void print_count(Vector<?> query) {
		System.out.println("There were " + query.size() + " records this query");
	}
	
	public AttendanceApp(String rosterFilename, String swipeFilename) {
		super();
		swipeData = new AttendanceLog();
		swipeData.load_log(swipeFilename);
		studentData = new StudentRoster();
		studentData.load_roster(rosterFilename);
	}
}
