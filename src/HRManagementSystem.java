import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class HRManagementSystem {
    private Company company;
    private List<Employee> employees;

    public HRManagementSystem() {
        company = null;
        employees = new ArrayList<>();
    }
    // nhập thông tin công ty
    public void setCompany(String name, String taxId, double monthlyRevenue) {
        company = new Company(name, taxId, monthlyRevenue);
    }
    // thêm nhân viên
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    // xóa nhân viên
    public void removeEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employees.remove(employee);
                if (employee instanceof Manager) {
                    for (Employee e : employees) {
                        if (e instanceof Manager) {
                            Manager manager = (Manager) e;
                            if (manager.getSubordinates() > 0) {
                                manager.addSubordinate();
                            }
                        }
                    }
                }
                break;
            }
        }
    }
    // nâng nhân viên thành quản lý
    public void assignEmployeeToManager(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId && employee instanceof Manager) {
                Manager manager = (Manager) employee;
                manager.addSubordinate();
                break;
            }
        }
    }
    // in ra tất cả nhân viên
    public void printAllEmployees() {
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() +
                    ", Monthly Salary: " + employee.calculateMonthlySalary());
        }
    }
    // tính tổng lương
    public double calculateTotalSalary() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.calculateMonthlySalary();
        }
        return totalSalary;
    }
    // tìm nhân viên có lương cao nhất
    public Employee findHighestPaidEmployee() {
        Employee highestPaid = null; // trả về null khi không tìm thấy bất kỳ nhân viên nào trong danh sách employees
        double maxSalary = 0;
        for (Employee employee : employees) {
            if (employee.calculateMonthlySalary() > maxSalary) {
                maxSalary = employee.calculateMonthlySalary();
                highestPaid = employee;
            }
        }
        return highestPaid;
    }
    // tìm quản lí có nhiều cấp dưới
    public Manager findManagerWithMostSubordinates() {
        Manager mostSubordinatesManager = null; // in ra null khi mà không tìm thấy quản lí nào có nhân viên cấp dưới
        int maxSubordinates = 0;
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                Manager manager = (Manager) employee;
                if (manager.getSubordinates() > maxSubordinates) {
                    maxSubordinates = manager.getSubordinates();
                    mostSubordinatesManager = manager;
                }
            }
        }
        return mostSubordinatesManager;
    }
    // sắp xếp tên theo thứ tự
    public void sortEmployeesByName() {
        int n = employees.size();

        for (int i = 1; i < n; i++) {
            Employee current = employees.get(i);
            int j = i - 1;

            while (j >= 0 && employees.get(j).getName().compareTo(current.getName()) > 0) {
                employees.set(j + 1, employees.get(j));
                j--;
            }

            employees.set(j + 1, current);
        }
    }
    // sắp xếp lương tháng giảm dần
    public void sortEmployeesBySalaryDescending() {
        int n = employees.size();

        for (int i = 1; i < n; i++) {
            Employee current = employees.get(i);
            double currentSalary = current.calculateMonthlySalary();
            int j = i - 1;

            while (j >= 0 && employees.get(j).calculateMonthlySalary() < currentSalary) {
                employees.set(j + 1, employees.get(j));
                j--;
            }

            employees.set(j + 1, current);
        }
    }

    // tìm ra giám đốc nhiều cổ phần
    public Director findDirectorWithMostShares() {
        Director mostSharesDirector = null;
        double maxShares = 0;
        for (Employee employee : employees) {
            if (employee instanceof Director) {
                Director director = (Director) employee;
                if (director.getOwnershipPercentage() > maxShares) {
                    maxShares = director.getOwnershipPercentage();
                    mostSharesDirector = director;
                }
            }
        }
        return mostSharesDirector;
    }
    /// in ra thu nhập của giám đốc
    public void calculateAndPrintDirectorsIncome() {
        for (Employee employee : employees) {
            if (employee instanceof Director) {
                Director director = (Director) employee;
                double income = director.calculateMonthlySalary() +
                        (company.getMonthlyRevenue() * director.getOwnershipPercentage() / 100);
                System.out.println("Director: " + director.getName() + ", Monthly Income: " + income);
            }
        }
    }

    public static void main(String[] args) {
        HRManagementSystem hrSystem = new HRManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("HR Management System Menu:");
            System.out.println("1. Set Company Information");
            System.out.println("2. Add Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Assign Employee to Manager");
            System.out.println("5. Print All Employees");
            System.out.println("6. Calculate Total Salary");
            System.out.println("7. Find Highest Paid Employee");
            System.out.println("8. Find Manager with Most Subordinates");
            System.out.println("9. Sort Employees by Name");
            System.out.println("10. Sort Employees by Salary (Descending)");
            System.out.println("11. Find Director with Most Shares");
            System.out.println("12. Calculate and Print Directors' Income");
            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Company Name: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Enter Tax ID: ");
                    String taxId = scanner.nextLine();
                    System.out.print("Enter Monthly Revenue: ");
                    double monthlyRevenue = scanner.nextDouble();
                    hrSystem.setCompany(companyName, taxId, monthlyRevenue);
                    break;
                case 2:
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Employee Name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Working Days: ");
                    int workingDays = scanner.nextInt();
                    System.out.print("Enter Daily Salary: ");
                    double dailySalary = scanner.nextDouble();
                    System.out.println("Select Employee Type:");
                    System.out.println("1. Regular Employee");
                    System.out.println("2. Manager");
                    System.out.println("3. Director");
                    System.out.print("Enter Employee Type: ");
                    int employeeType = scanner.nextInt();
                    scanner.nextLine();

                    Employee newEmployee = null;
                    if (employeeType == 1) {
                        newEmployee = new Employee(employeeId, employeeName, phoneNumber, workingDays, dailySalary);
                    } else if (employeeType == 2) {
                        newEmployee = new Manager(employeeId, employeeName, phoneNumber, workingDays, dailySalary);
                    } else if (employeeType == 3) {
                        newEmployee = new Director(employeeId, employeeName, phoneNumber, workingDays, dailySalary);
                        System.out.print("Enter Ownership Percentage for Director: ");
                        double ownershipPercentage = scanner.nextDouble();
                        ((Director) newEmployee).setOwnershipPercentage(ownershipPercentage);
                    }
                    hrSystem.addEmployee(newEmployee);
                    break;
                case 3:
                    System.out.print("Enter Employee ID to Remove: ");
                    int empIdToRemove = scanner.nextInt();
                    hrSystem.removeEmployee(empIdToRemove);
                    break;
                case 4:
                    System.out.print("Enter Employee ID to Assign to Manager: ");
                    int empIdToAssign = scanner.nextInt();
                    hrSystem.assignEmployeeToManager(empIdToAssign);
                    break;
                case 5:
                    hrSystem.printAllEmployees();
                    break;
                case 6:
                    double totalSalary = hrSystem.calculateTotalSalary();
                    System.out.println("Total Salary for All Employees: " + totalSalary);
                    break;
                case 7:
                    Employee highestPaid = hrSystem.findHighestPaidEmployee();
                    System.out.println("Highest Paid Employee: " + highestPaid.getName() +
                            ", Monthly Salary: " + highestPaid.calculateMonthlySalary());
                    break;
                case 8:
                    Manager managerWithMostSubordinates = hrSystem.findManagerWithMostSubordinates();
                    System.out.println("Manager with Most Subordinates: " + managerWithMostSubordinates.getName() +
                            ", Subordinates: " + managerWithMostSubordinates.getSubordinates());
                    break;
                case 9:
                    hrSystem.sortEmployeesByName();
                    hrSystem.printAllEmployees();
                    break;
                case 10:
                    hrSystem.sortEmployeesBySalaryDescending();
                    hrSystem.printAllEmployees();
                    break;
                case 11:
                    Director directorWithMostShares = hrSystem.findDirectorWithMostShares();
                    System.out.println("Director with Most Shares: " + directorWithMostShares.getName() +
                            ", Ownership Percentage: " + directorWithMostShares.getOwnershipPercentage());
                    break;
                case 12:
                    hrSystem.calculateAndPrintDirectorsIncome();
                    break;
                case 13:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}