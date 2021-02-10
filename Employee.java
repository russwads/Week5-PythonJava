package java2Python;

public class Employee {
	private String firstname;
	private String lastname;
	private String jobtitle;
	private int employeeID = 8173672;
	private int salary = 99999;
		
	/**
	 * Constructor. MUST contain a first name, last name, job title, employee ID, and salary.
	 * Received through a .csv file read into the system.
	 * @param x firstname
	 * @param y lastname
	 * @param z jobtitle
	 * @param a employeeID
	 * @param b salary
	 */
	public Employee(String x, String y, String z, int a, int b) {
		this.firstname = x;
		this.lastname = y;
		this.jobtitle = z;
		this.employeeID = a;
		this.salary = b;
	}

	@Override
	public String toString() {
		return firstname + " " + lastname + ", " + jobtitle + ", $" + salary + "/yr";
	}
	
	/**
	 * Returns the first and last name as one entity.
	 * @return name in following format: "Scott Russell"
	 */
	public String getName() {
		return firstname + " " + lastname;
	}
	
	/**
	 * Returns salary information.
	 * @return integer (ex. 50000)
	 */
	public int getSalary() {
		return salary;
	}
	
	/**
	 * Returns job title for employee.
	 * @return title such as "Character Performer"
	 */
	public String getJobTitle() {
		return jobtitle;
	}
	
	/**
	 * Return employee ID.
	 * @return integer in format of 891####
	 */
	public int getID() {
		return employeeID;
	}
}
