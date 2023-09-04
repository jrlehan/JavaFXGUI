
public class Log {
	String name; 
	String time;
	String date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Log [name=" + name + ", time=" + time + ", date=" + date + "]";
	}
	
	public Log(String name, String time, String date) {
		super();
		this.name = name;
		this.time = time;
		this.date = date;
	}
	
	public int compareTime(Log rhs) {
		for (int i = 0; i < this.time.length(); ++i) {
			try {
				if (this.time.charAt(i) < rhs.time.charAt(i)) {
					//System.out.println("less " + this.time.charAt(i) + " " + rhs.time.charAt(i));
					return -1;
				}
				if (this.time.charAt(i) > rhs.time.charAt(i)) {
					//System.out.println("greater");
					return 1;
				}
			} catch (Exception e) {
				//System.out.println("Uneven length");
				return 0;
			}
		}
		//System.out.println("Equal");
		return 0;
	}
}
