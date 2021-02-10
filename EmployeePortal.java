/**
 * Java 2 Python: Employee Portal
 * Russell Wadsworth, CSIS2450
 * Using the employee portal to:
 * 1. Search for salary information for a specific employee
 * 2. Print out an average salary report for a specific job type
 * 3. Print out the number of employees in all departments -/
 * 4. Print out oldest employee and newest employee (determined by empID#)
 */

package java2Python;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class EmployeePortal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Employee[] empList = new Employee[25];
		Scanner s1 = new Scanner(System.in);
		int option = 0;
		final String[] dept = new String[] {
				"Character Performer",
				"Custodian",
				"Foods",
				"Attractions",
				"Groundskeeping",
				"Concierge"
		};
		
		String filename = "C:\\Users\\russe\\eclipse-workspace\\2450_JavaToPython\\src\\java2Python\\Book1.csv";
		File main = new File(filename);
		readFile(empList, main);
		
		do {
			menu();
			String opt = s1.nextLine();
			option = Integer.parseInt(opt);
			if (option == 1) {
				// Find an employee salary
				System.out.println("Please state the name of the employee:");
				opt = s1.nextLine();
				boolean go = false;
				for (int i = 0; i < empList.length; i++) {
					if (empList[i].getName().contains(opt)) {
						System.out.println(empList[i].toString());
						go = true;
					}
				}
				if (!go) {
					System.err.println("Input error: Cannot match entry with employee name in file.");
				}
			}
			else if (option == 2) {
				// Print out salary report for department
				getSalaryReport(empList, dept);
			}
			else if (option == 3) {
				// Number of employees in all departments
				int[] emps = departmentCount(empList);
				System.out.println("Department Counts");
				for (int i = 0; i < dept.length; i++) {
					System.out.println(dept[i] + ": " + emps[i]);
				}
			}
			else if (option == 4) {
				// Print out oldest employee and newest employee
				getOldAndNewEmp(empList);
			}
			else if (option == 5) {
				System.exit(0);
			}
			else {
				System.err.println("Input Error. Please try again.");
			}
			System.out.println();
		} while (option != 5);
	}

	/**
	 * Reads from file to populate database of employees
	 * @param empList list of employees in WDW system
	 * @param main File that program reads from
	 * @throws IOException input I/O errors throw this error
	 */
	public static void readFile(Employee[] empList, File main) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(main));
			String line = "";
			int count = 0;
			String csv = ",";
			while ((line = br.readLine()) != null) {
				String[] buff = line.split(csv);
				empList[count] = new Employee(buff[0], buff[1], buff[2], Integer.parseInt(buff[3]), Integer.parseInt(buff[4]));
				count++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Error.");
		}
	}

	/**
	 * Sorts employee IDs and finds the smallest (oldest) and largest (newest) ones.
	 * Prints out corresponding employees with ID.
	 * @param empList list of employees in WDW system
	 */
	public static void getOldAndNewEmp(Employee[] empList) {
		int[] ids = new int[25];
		for (int i = 0; i < 25; i++) {
			ids[i] = empList[i].getID();
		}
		Arrays.sort(ids);
		int x = 0;
		int y = 0;
		for (int i = 0; i < 25; i++) {
			if (ids[0] == empList[i].getID())
				x = i;
			if (ids[24] == empList[i].getID())
				y = i;
		}
		System.out.println("Oldest Employee: " + empList[x].toString());
		System.out.println("Newest Employee: " + empList[y].toString());
	}

	/**
	 * Grabs a salary report, averaging out the different salaries in every department.
	 * @param empList list of employees in WDW system
	 * @param dept The park department, from Foods to Attractions.
	 */
	public static void getSalaryReport(Employee[] empList, final String[] dept) {
		double[] salaries = new double[] { 0, 0, 0, 0, 0, 0 };
		int[] iters = new int[] { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < empList.length; i++) {
			for (int j = 0; j < dept.length; j++) {
				if (empList[i].getJobTitle().equals(dept[j])){
					salaries[j] = salaries[j] + empList[i].getSalary();
					iters[j] = iters[j] + 1;
					j = dept.length;
				}
			}
		}
		
		int i = 0;
		while (i != 6) {
			salaries[i] = salaries[i] / iters[i];
			i++;
		}
		
		i = 0;
		System.out.println("Salaries for Each Department:");
		while (i != 6) {
			System.out.printf(dept[i] + ": $%.2f\n", salaries[i]);
			i++;
		}
	}

	/**
	 * Prints out menu options in application.
	 */
	public static void menu() {
		System.out.println("WDW Employee Portal\nPlease Type Number & Make a Decision:");
		System.out.println("1: Find an employee salary");
		System.out.println("2: Find average salary for department");
		System.out.println("3: Find department employee numbers");
		System.out.println("4: Find oldest and newest employee");
		System.out.println("5: Quit");
	}

	/**
	 * Counts every employee in each department.
	 * @param empList list of employees in WDW system
	 * @return finalized list of numbers corresponding with department 
	 */
	public static int[] departmentCount(Employee[] empList) {
		int[] numberEmps = new int[6];
		for (int i = 0; i < empList.length; i++) {
			switch(empList[i].getJobTitle()) {
			case "Character Performer":
				numberEmps[0] = numberEmps[0] + 1;
				break;
			case "Custodian":
				numberEmps[1] = numberEmps[1] + 1;
				break;
			case "Foods":
				numberEmps[2] = numberEmps[2] + 1;
				break;
			case "Attractions":
				numberEmps[3] = numberEmps[3] + 1;
				break;
			case "Groundskeeping":
				numberEmps[4] = numberEmps[4] + 1;
				break;
			case "Concierge":
				numberEmps[5] = numberEmps[5] + 1;
				break;
			default:
				System.err.println("Critical Error loading information.");
			}
		}
		return numberEmps;
	}

}
