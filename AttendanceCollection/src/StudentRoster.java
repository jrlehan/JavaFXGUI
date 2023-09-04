import java.util.*;
import java.io.*;
public class StudentRoster {
	Vector<Student> students;
	
	void print_collection() {
		System.out.println("** Those students on roster **");
		for (Student s : students) {
			System.out.println(s.name);
		}
	}
	
	void load_roster(String filename) {
		
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

			String name = line;
			
			Student entry = new Student(name);
			students.add(entry);
		}

		infile.close( );
	}
	
	void print_count() {
		System.out.println(students.size());
	}

	public StudentRoster() {
		super();
		students = new Vector<Student>();
	}
	
	
}
