import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// công ty
class Company {
    private String name;
    private String taxId;
    private double monthlyRevenue;

    public Company(String name, String taxId, double monthlyRevenue) {
        this.name = name;
        this.taxId = taxId;
        this.monthlyRevenue = monthlyRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
}
// nhân viên
class Employee {
    private int id;
    private String name;
    private String phoneNumber;
    private int workingDays;
    private double dailySalary;

    public Employee(int id, String name, String phoneNumber, int workingDays, double dailySalary) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
        this.dailySalary = dailySalary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public double getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(double dailySalary) {
        this.dailySalary = dailySalary;
    }

    public double calculateMonthlySalary() {
        return dailySalary * workingDays;
    }
}
// quản lí
class Manager extends Employee {
    private int subordinates; // cấp dưới

    public Manager(int id, String name, String phoneNumber, int workingDays, double dailySalary) {
        super(id, name, phoneNumber, workingDays, dailySalary);
        this.subordinates = 0;
    }

    public int getSubordinates() {
        return subordinates;
    }

    public void addSubordinate() {
        subordinates++;
    }
}
// giám đốc
class Director extends Employee {
    private double ownershipPercentage; // cổ phần

    public Director(int id, String name, String phoneNumber, int workingDays, double dailySalary) {
        super(id, name, phoneNumber, workingDays, dailySalary);
        this.ownershipPercentage = 0.0;
    }

    public double getOwnershipPercentage() {
        return ownershipPercentage;
    }

    public void setOwnershipPercentage(double ownershipPercentage) {
        if (ownershipPercentage >= 0 && ownershipPercentage <= 100) {
            this.ownershipPercentage = ownershipPercentage;
        } else {
            System.out.println("Ownership percentage must be between 0 and 100.");
        }
    }
}
