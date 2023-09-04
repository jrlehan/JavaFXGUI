package application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.util.Vector;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class SampleController {
	Vector<?> query = new Vector<String>();
	String previousQuery = "Z";
	
	AttendanceLog currentLog = new AttendanceLog();
	StudentRoster currentRoster = new StudentRoster();
	String logFileName = "";
	String rosterFileName = "";
	AttendanceApp currentApp;
	
	boolean logLoaded = false;
	boolean rosterLoaded = false;
	
	String date5 = "";
	Student student3;
	
	@FXML private TextArea inputOutput;
	
	@FXML private void initialize() {
		inputOutput.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            if (e.getCode().equals(KeyCode.ENTER)) {
	                switch (previousQuery) {
	                case "H":
	                	Student student = new Student(inputOutput.getParagraphs().get(1).toString());
	            		query = currentApp.list_all_times_checking_in_and_out(student);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "I":
	                	String date = inputOutput.getParagraphs().get(1).toString();
	            		query = currentApp.list_all_times_checked_in(date);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "J":
	                	String timestamp = inputOutput.getParagraphs().get(1).toString();
	            		query = currentApp.list_students_late_to_class(timestamp);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "K":
	            		String date2 = inputOutput.getParagraphs().get(1).toString();
	            		query = currentApp.get_first_student_to_enter(date2);
	            		inputOutput.appendText("Sucessfully queried" + "\n");
	            		break;
	                case "L":
	                	Student student2 = new Student(inputOutput.getParagraphs().get(1).toString());
	            		query = currentApp.print_attendance_data_for_student(student2);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "isPresent":
	                	student3 = new Student(inputOutput.getParagraphs().get(1).toString());
	                	inputOutput.appendText("Please input a date in the format 'MM/D/YYYY': " + "\n");
	            		previousQuery = "M";
	            		break;
	                case "M":
	                	String date3 = inputOutput.getParagraphs().get(3).toString();
	            		String returnVal = String.valueOf(currentApp.is_present(student3, date3));
	            		Vector<String >temp = new Vector<String>();
	            		temp.add(returnVal);
	            		query = temp;
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "N":
	                	String date4 = inputOutput.getParagraphs().get(1).toString();
	                	query = currentApp.list_all_students_checked_in(date4);
	                	inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "allStudentsBefore":
	                	date5 = inputOutput.getParagraphs().get(1).toString();
	                	inputOutput.appendText("Please input a time in the format 'HH:MM:SS': " + "\n");
	            		previousQuery = "O";
	            		break;
	                case "O":
	                	String time = inputOutput.getParagraphs().get(3).toString();
	            		query = currentApp.list_all_students_checked_in_before(date5, time);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                case "P":
	                	Integer days = Integer.valueOf(inputOutput.getParagraphs().get(1).toString());
	            		query = currentApp.list_students_attendance_count(days);
	            		inputOutput.appendText("Sucessfully queried");
	            		break;
	                default:
	                	inputOutput.setText("Not a valid time to input text" + "\n");
	                }
	            }
	        }
	    });
	}
	
	@FXML private void setupEventHandlers() {
	    inputOutput.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	        	System.out.println("Key Event");
	            if (e.getCode().equals(KeyCode.ENTER)) {
	                switch (previousQuery) {
	                case "H":
	                	Student student = new Student(inputOutput.getParagraphs().get(1).toString());
	            		query = currentApp.list_all_times_checking_in_and_out(student);
	            		inputOutput.appendText("Sucessfully queried");
	                default:
	                	inputOutput.setText("Not a valid time to input text" + "\n");
	                }
	            }
	        }
	    });
	}
	
	public void load_log(ActionEvent e) {
		inputOutput.setText("Please select the filename for the attendance data. " + "\n");
		
		Window window = ((Node) e.getTarget()).getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		try {
			File file = fileChooser.showOpenDialog(window);
			currentLog = new AttendanceLog();
			logFileName = file.toString();
			currentLog.load_log(file.toString());
			inputOutput.appendText("Valid file sucessfully loaded." + "\n");
			logLoaded = true;
		} catch (Exception E) {
			inputOutput.appendText("Valid file not selected." + "\n");
			logLoaded = false;
		}
		
	}
	
	public void print_collection_log(ActionEvent e) {
		inputOutput.setText("** This is the entire Collection Data currently stored **" + "\n");
		Vector<String> printer = currentLog.print_collection();
		for (String s : printer) {
			inputOutput.appendText(s + "\n");
		}
	}
	
	public void print_count_log(ActionEvent e) {
		Integer count = ((Integer) currentLog.print_count());
		inputOutput.setText(count.toString());
	}
	
	public void load_roster(ActionEvent e) {
		inputOutput.setText("Please select the filename for the swipe data. " + "\n");
		
		Window window = ((Node) e.getTarget()).getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		try {
			File file = fileChooser.showOpenDialog(window);
			currentRoster = new StudentRoster();
			currentRoster.load_roster(file.toString());
			rosterFileName = file.toString();
			inputOutput.appendText("Valid file sucessfully loaded." + "\n");
			rosterLoaded = true;
		} catch (Exception E) {
			inputOutput.appendText("Valid file not selected." + "\n");
			rosterLoaded = false;
		}
	}
	
	public void print_collection_roster(ActionEvent e) {
		inputOutput.setText("** Those students on roster **" + "\n");
		Vector<String> printer = currentRoster.print_collection();
		for (String s : printer) {
			inputOutput.appendText(s + "\n");
		}
	}
	
	public void print_count_roster(ActionEvent e) {
		Integer count = ((Integer) currentRoster.print_count());
		inputOutput.setText(count.toString());
	}
	
	public void list_students_not_in_class(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		query = currentApp.list_students_not_in_class();
		previousQuery = "G";
		inputOutput.setText("Sucessfully queried");
	}
	
	public void list_all_times_checking_in_and_out(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a student name in the format 'Last_Name, First_Name': " + "\n");
		previousQuery = "H";
	}
	
	public void list_all_times_checked_in(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a date in the format 'MM/D/YYYY': " + "\n");
		previousQuery = "I";
	}
	
	public void list_students_late_to_class(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a timestamp in the format 'HH:MM:SS, MM/D/YYYY': " + "\n");
		previousQuery = "J";
	}
	
	public void get_first_student_to_enter(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a date in the format 'MM/D/YYYY': " + "\n");
		previousQuery = "K";
	}
	
	public void print_attendance_data_for_student(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a student name in the format 'Last_Name, First_Name': " + "\n");
		previousQuery = "L";
	}
	
	public void is_present(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a student name in the format 'Last_Name, First_Name': " + "\n");
		previousQuery = "isPresent";
	}
	
	public void list_all_students_checked_in(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a date in the format 'MM/D/YYYY': " + "\n");
		previousQuery = "N";
	}
	
	public void list_all_students_check_in_before(ActionEvent e) {
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a date in the format 'MM/D/YYYY': " + "\n");
		previousQuery = "allStudentsBefore";
	}
	
	public void list_students_attendance_count(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		inputOutput.setText("Please input a number of days: " + "\n");
		previousQuery = "P";
	}
	
	public void print_query(ActionEvent e) {
		if (!(logLoaded && rosterLoaded)) {
			inputOutput.setText("Please load log and roster files first");
			return;
		}
		
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		switch (previousQuery) {
		case "G":
			inputOutput.setText("** Students missing in class **" + "\n");
			break;
		case "H":
			inputOutput.setText("****** List all swipe in and out for a student *******" + "\n");
			break;
		case "I":
			inputOutput.setText("****** Check in times for all students who attended******" + "\n");
			break;
		case "J":
			inputOutput.setText("****** Students that arrived late ******" + "\n");
			break;
		case "K":
			break;
		case "L":
			inputOutput.setText("********* Looking up Student Attendance Data ***********" + "\n");
			break;
		case "M":
			inputOutput.setText("****** Looking to see if Student was present on date ******" + "\n");
			break;
		case "N":
			inputOutput.setText("****** Students present on this date ******" + "\n");
			break;
		case "O":
			inputOutput.setText("****** Those present on date & before a time assigned ******" + "\n");
			break;
		case "P":
			inputOutput.setText("**** Those who attended classes ****" + "\n");
			break;
		case "Q":
			break;
		default:
			inputOutput.setText("No previous query to print" + "\n");
		}
		Vector<String> printer = currentApp.print_query_list(query);
		for (String s : printer) {
			inputOutput.appendText(s + "\n");
		}
	}
	
	public void print_count(ActionEvent e) {
		currentApp = new AttendanceApp(rosterFileName, logFileName);
		String counter = currentApp.print_count(query);
		inputOutput.setText(counter + "\n");
	}
	
	public void exit(ActionEvent e) {
		Platform.exit();
	}
}
