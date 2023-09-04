package application;
import java.util.*;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "Z";
		Vector<?> query = new Vector<String>();
		String previousQuery = "Z";
		
		AttendanceLog currentLog = new AttendanceLog();
		StudentRoster currentRoster = new StudentRoster();
		String logFileName = "";
		String rosterFileName = "";
		AttendanceApp currentApp;
		
		Scanner myObj = new Scanner(System.in);
		
		while (!input.contains("0")) {
			System.out.println("Please select from the following menu options: ");
			System.out.println("0 - Exit Program");
			System.out.println("");
			System.out.println("Attendance Log Functions: ");
			System.out.println("A - Load Log");
			System.out.println("B - Print Collection");
			System.out.println("C - Print Count");
			System.out.println("");
			System.out.println("Student Roster Functions: ");
			System.out.println("D - Load Roster");
			System.out.println("E - Print Collection");
			System.out.println("F - Print Count");
			System.out.println("");
			System.out.println("Attendance App Functions: ");
			System.out.println("G - List Students Not In Class");
			System.out.println("H - List All Times Checking In And Out");
			System.out.println("I - List All Times Checked In");
			System.out.println("J - List Students Late To Class");
			System.out.println("K - Get First Student To Enter");
			System.out.println("L - Print Attendance Data For Student");
			System.out.println("M - Is Present");
			System.out.println("N - List All Students Checked In");
			System.out.println("O - List All Students Checked In Before");
			System.out.println("P - List Students Attendance Count");
			System.out.println("Q - Get First Student To Enter");
			System.out.println("R - Print Query List");
			System.out.println("S - Print Count");
			input = myObj.next();
			
			switch (input) {
			case "A":
				System.out.println("Please input the filename for the attendance data: ");
				logFileName = myObj.next();
				currentLog = new AttendanceLog();
				currentLog.load_log(logFileName);
				break;
			case "B":
				currentLog.print_collection();
				break;
			case "C":
				currentLog.print_count();
				break;
			case "D":
				System.out.println("Please input the filename for the roster data: ");
				rosterFileName = myObj.next();
				currentRoster = new StudentRoster();
				currentRoster.load_roster(rosterFileName);
				break;
			case "E":
				currentRoster.print_collection();
				break;
			case "F":
				currentRoster.print_count();
				break;
			case "G":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				query = currentApp.list_students_not_in_class();
				previousQuery = "G";
				break;
			case "H":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a student name in the format 'Last_Name, First_Name': ");
				Student student = new Student(myObj.next());
				query = currentApp.list_all_times_checking_in_and_out(student);
				previousQuery = "H";
				break;
			case "I":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date = myObj.next();
				query = currentApp.list_all_times_checked_in(date);
				previousQuery = "I";
				break;
			case "J":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a timestamp in the format 'HH:MM:SS, MM/D/YYYY': ");
				String timestamp = myObj.next();
				query = currentApp.list_students_late_to_class(timestamp);
				previousQuery = "J";
				break;
			case "K":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date2 = myObj.next();
				query = currentApp.get_first_student_to_enter(date2);
				previousQuery = "K";
				break;
			case "L":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a student name in the format 'Last_Name, First_Name': ");
				Student student2 = new Student(myObj.next());
				query = currentApp.print_attendance_data_for_student(student2);
				previousQuery = "L";
				break;
			case "M":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a student name in the format 'Last_Name, First_Name': ");
				Student student3 = new Student(myObj.next());
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date3 = myObj.next();
				String returnVal = String.valueOf(currentApp.is_present(student3, date3));
				Vector<String >temp = new Vector<String>();
				temp.add(returnVal);
				query = temp;
				previousQuery = "M";
				break;
			case "N":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date4 = myObj.next();
				query = currentApp.list_all_students_checked_in(date4);
				previousQuery = "N";
				break;
			case "O":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date5 = myObj.next();
				System.out.println("Please input a time in the fomrat 'HH:MM:SS': ");
				String time = myObj.next();
				query = currentApp.list_all_students_checked_in_before(date5, time);
				previousQuery = "O";
				break;
			case "P":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a number of days: ");
				Integer days = Integer.valueOf(myObj.next());
				query = currentApp.list_students_attendance_count(days);
				previousQuery = "P";
				break;
			case "Q":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				System.out.println("Please input a date in the format 'MM/D/YYYY': ");
				String date6 = myObj.next();
				query = currentApp.get_first_student_to_enter(date6);
				previousQuery = "Q";
				break;
			case "R":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				switch (previousQuery) {
				case "G":
					System.out.println("** Students missing in class **");
					break;
				case "H":
					System.out.println("****** List all swipe in and out for a student *******");
					break;
				case "I":
					System.out.println("****** Check in times for all students who attended******");
					break;
				case "J":
					System.out.println("****** Students that arrived late ******");
					break;
				case "K":
					break;
				case "L":
					System.out.println("********* Looking up Student Attendance Data ***********");
					break;
				case "M":
					System.out.println("****** Looking to see if Student was present on date ******");
					break;
				case "N":
					System.out.println("****** Students present on this date ******");
					break;
				case "O":
					System.out.println("****** Those present on date & before a time assigned ******");
					break;
				case "P":
					System.out.println("**** Those who attended classes ****");
					break;
				case "Q":
					break;
				default:
					System.out.println("No previous query to print");
				}
				currentApp.print_query_list(query);
				break;
			case "S":
				currentApp = new AttendanceApp(rosterFileName, logFileName);
				currentApp.print_count(query);
				break;
			default:
				System.out.println("Invalid input; please select an option from the menu");
			}
		}
	}

}
