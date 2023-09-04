import java.util.*;
public class StudentEntry {
	String name;
	Vector<String> attendance;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<String> getAttendance() {
		return attendance;
	}
	public void setAttendance(Vector<String> attendance) {
		this.attendance = attendance;
	}
	
	@Override
	public String toString() {
		return "StudentEntry [name=" + name + ", attendance=" + attendance + "]";
	}
	
	public StudentEntry(String name) {
		this.name = name;
		this.attendance = new Vector<String>();
	}
	
	public StudentEntry(String name, String timeDate) {
		super();
		this.name = name;
		this.attendance = new Vector<String>();
		this.attendance.add(timeDate);
	}
	
	public void addTimeDate(String timeDate) {
		this.attendance.add(timeDate);
	}
}
