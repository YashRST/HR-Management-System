package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.common.Keyboard;
//import java.util.Keyboard;
public class MainMenu
{
private DesignationUI designationUI;
private EmployeeUI employeeUI;
public MainMenu()
{
designationUI=null;
employeeUI=null;
}
public void show()
{
while(true)
{
System.out.println("1. Designation Master");
System.out.println("2. Employee Master");
System.out.println("3. Exit");
System.out.print("Enter choice : ");
int choice=Keyboard.getInt();

if(choice==1)
{
designationUI=new DesignationUI();
while(true)
{
System.out.println("1. Add Designation");
System.out.println("2. Update Designation");
System.out.println("3. Delete Designation");
System.out.println("4. Number of Designation");
System.out.println("5. Get Designation Info.");
System.out.println("6. Check Existance");
System.out.println("7. Exit");
System.out.print("Enter Choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
designationUI.add();
}
if(ch==2)
{
designationUI.update();
}
if(ch==3)
{
designationUI.delete();
}

if(ch==4)
{
designationUI.getCount();
}

if(ch==5)
{
while(true)
{
System.out.println("1. Get all designations");
System.out.println("2. Get designation by code");
System.out.println("3. Get designation by title");
System.out.println("4. Is attached to employee ");
System.out.println("5. Exit");
System.out.print("Enter choice : ");
int cho=Keyboard.getInt();
if(cho==1)
{
designationUI.getAll();
}
if(cho==2)
{
designationUI.getByCode();
}
if(cho==3)
{
designationUI.getByTitle();
}
if(cho==4)
{
designationUI.isAttachedToAnEmployee();
}
if(cho==5)
{
break;
}
}
}
if(ch==6)
{
while(true)
{
System.out.println("1. Code");
System.out.println("2. Title");
System.out.println("3. Exit");
System.out.println("Enter Choice : ");
int cho=Keyboard.getInt();
if(cho==1)
{
designationUI.codeExists();
}
if(cho==2)
{
designationUI.titleExists();
}
if(cho==3)
{
break;
}
}
}
if(ch==7)
{
break;
}
}
}


if(choice==2)
{
EmployeeUI employeeUI=new EmployeeUI();
while(true)
{
System.out.println("1. Add Employee");
System.out.println("2. Update Employee");
System.out.println("3. Delete Employee");
System.out.println("4. Get Number of Employees");
System.out.println("5. Get Employees Info. ");
System.out.println("6. Check Existance ");
System.out.println("7. Exit");
System.out.print("Enter choice : ");
int ch=Keyboard.getInt();

if(ch==1)
{
employeeUI.add();
}

if(ch==2)
{
employeeUI.update();
}

if(ch==3)
{
employeeUI.delete();
}

if(ch==4)
{
employeeUI.getCount();
}

if(ch==5)
{
while(true)
{
System.out.println("1. Get all");
System.out.println("2. Get by employeeId");
System.out.println("3. Get by PAN Number ");
System.out.println("4. Get by Aadhar card number");
System.out.println("5. Get all with designation");
System.out.println("6. Exit");
int cho=Keyboard.getInt();
if(cho==1)
{
employeeUI.getAll();
}
if(cho==2)
{
employeeUI.getByEmployeeId();
}
if(cho==3)
{
employeeUI.getByPANNumber();
}
if(cho==4)
{
employeeUI.getByAadharCardNumber();
}
if(cho==6)
{
break;
}
}
}

if(ch==6)
{
while(true)
{
System.out.println("1. Employee ID");
System.out.println("2. PAN Number");
System.out.println("3. Aadhar Card Number");
System.out.println("4. Exit");
System.out.print("Enter Choice : ");
int cho=Keyboard.getInt();
if(cho==1)
{
employeeUI.employeeIdExists();
}
if(cho==2)
{
employeeUI.PANNumberExists();
}
if(cho==3)
{
employeeUI.aadharCardNumberExists();
}
if(cho==4)
{
break;
}
}
}

if(choice==7)
{
break;
}
}
}

if(choice==3)
{
break;
}
}
}
}