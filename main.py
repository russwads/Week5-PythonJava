# Russell Wadsworth
# CSIS2450 Software Engineering
# Java to Python Exploration

# Employee in Walt Disney World employee portal
class Employee:
    # Employee constructor
    def __init__(self, first_name, last_name, job_title, employee_id, salary):
        self.first_name = first_name
        self.last_name = last_name
        self.job_title = job_title
        self.employeeID = employee_id
        self.salary = salary

    # Releases the employee information in easily identifiable string
    def to_string(self):
        return self.first_name + " " + self.last_name + ", " + self.job_title + ", $" + self.salary + "/yr"

    # Returns the first and last name as one entity.
    def get_name(self):
        return self.first_name + " " + self.last_name


# Initializing basic information
empList = []
dept = ["Character Performer", "Custodian", "Foods", "Attractions", "Groundskeeping", "Concierge"]
option = 0
# Opening and reading from file
f = open("C:\\Users\\russe\\eclipse-workspace\\2450_JavaToPython\\src\\java2Python\\Book1.csv")
line = 0
for x in f:
    a, b, c, d, e = x.split(",")
    # Removing file (.csv) errors I am unable to fix/clean
    if line == 0:
        empList.append(Employee(a[3:], b, c, d, e))
    elif line == 24:
        empList.append(Employee(a, b, c, d, e))
    else:
        empList.append(Employee(a, b, c, d, e[:-1]))
    line = line + 1
f.close()


# Prints out menu options in application.
def menu():
    print("\nWDW Employee Portal\nPlease Type Number & Make a Decision:")
    print("1: Find an employee salary")
    print("2: Find average salary for department")
    print("3: Find department employee numbers")
    print("4: Find oldest and newest employee")
    print("5: Quit")


# Finds employee with first name, and prints out basic information
def find():
    opt = input("Please state the name of the employee:")
    opt.lower()
    opt.capitalize()
    go = False  # Used to show either successful or unsuccessful location of employee
    for i in empList:
        buff = i.get_name()
        if buff.startswith(opt):
            print(i.to_string())
            go = True
    if not go:
        print("Input error: Cannot match entry with employee name in file.")


# Grabs a salary report, averaging out the different salaries in every department.
def salary_report():
    # Initializing lists for final salary calculations
    salaries = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
    iters = [0, 0, 0, 0, 0, 0]
    for i in empList:
        q = 0
        for j in dept:
            # Check for matching job title names
            if i.job_title == j:
                # Adds salaries to correct department
                salaries[q] = salaries[q] + float(i.salary)
                iters[q] = iters[q] + 1
            q = q + 1

    # Printing out salary average information
    i = 0
    prices = ": ${:.2f}"
    print("Salaries for Each Department:")
    while i != 6:
        salaries[i] = salaries[i] / iters[i]
        print(dept[i] + prices.format(salaries[i]))
        i = i + 1


# Counts every employee in each department.
def dept_count():
    # Initializing number of employees in each department
    numberemps = [0, 0, 0, 0, 0, 0]
    # Adds employee to each department accordingly
    for z in empList:
        if z.job_title == "Character Performer":
            numberemps[0] = numberemps[0] + 1
        elif z.job_title == "Custodian":
            numberemps[1] = numberemps[1] + 1
        elif z.job_title == "Foods":
            numberemps[2] = numberemps[2] + 1
        elif z.job_title == "Attractions":
            numberemps[3] = numberemps[3] + 1
        elif z.job_title == "Groundskeeping":
            numberemps[4] = numberemps[4] + 1
        elif z.job_title == "Concierge":
            numberemps[5] = numberemps[5] + 1
        else:  # Failsafe if data is incorrect or corrupt
            print("Critical Error loading information.")
    # Printing out report of department numbers
    print("Department Counts")
    j = 0
    stringer = ": {}"
    for i in dept:
        print(i + ": " + stringer.format(numberemps[j]))
        j = j + 1


# Sorts employee IDs and finds the smallest (oldest) and largest (newest) ones.
# Prints out corresponding employees with ID.
def old_new_emp():
    # Initializing all employee numbers
    ids = [0] * 25
    for x1 in range(25):
        ids[x1] = empList[x1].employeeID
    ids.sort()  # Used to sort all numbers from lowest to highest; lowest = oldest, highest = newest
    o = 0  # Oldest
    n = 0  # Newest
    # Assigning values accordingly
    for g in range(25):
        if ids[0] == empList[g].employeeID:
            o = g
        if ids[24] == empList[g].employeeID:
            n = g
    print("Oldest Employee: " + empList[o].to_string())
    print("Newest Employee: " + empList[n].to_string())


# Main
# Option loop: waits for user input
while option != "5":
    menu()
    option = input("Enter option number:")
    print("\n")
    if option == "1":
        find()
    elif option == "2":
        salary_report()
    elif option == "3":
        dept_count()
    elif option == "4":
        old_new_emp()
    elif option == "5":
        exit(0)
    else:
        print("Input Error. Please try again.\n")

# End of program