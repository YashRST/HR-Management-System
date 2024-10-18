package com.thinking.machines.hr.pl.ui;
import java.util.*;
import java.text.*;
import java.math.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;

public class EmployeeUI
{

public void add()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
DesignationManagerInterface designationManager=DesignationManager.getInstance();
EmployeeInterface employee=new Employee();
System.out.print("Name : ");
employee.setName(Keyboard.getString());
System.out.print("Designation Code : ");
employee.setDesignation(designationManager.getByCode(Keyboard.getInt()));
System.out.print("Date of Birth : ");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
try
{
employee.setDateOfBirth(sdf.parse(Keyboard.getString()));
}catch(ParseException pe)
{
System.out.println(pe);
}
System.out.print("Basic Salary : ");
BigDecimal salary=new BigDecimal(Keyboard.getString());
employee.setBasicSalary(salary);
System.out.println(salary);
System.out.println("1. Male"+"\n"+"2. Female");
System.out.print("Gender : ");
int g=Keyboard.getInt();
if(g==1)
{
employee.setGender(EmployeeInterface.MALE);
}
else
{
employee.setGender(EmployeeInterface.FEMALE);
}
System.out.println("1. Yes"+"\n"+"2. No");
System.out.println("Is Indian");
g=Keyboard.getInt();
if(g==1)
{
employee.isIndian(true);
}
else
{
employee.isIndian(false);
}
System.out.print("PAN Number : ");
employee.setPANNumber(Keyboard.getString());
System.out.print("Aadhar Card Number : ");
employee.setAadharCardNumber(Keyboard.getString());
employeeManager.add(employee);
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void update()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
DesignationManagerInterface designationManager=DesignationManager.getInstance();
EmployeeInterface employee=new Employee();
System.out.print("Employee Id : ");
employee.setEmployeeId(Keyboard.getString());
System.out.print("Name : ");
employee.setName(Keyboard.getString());
System.out.print("Designation Code : ");
employee.setDesignation(designationManager.getByCode(Keyboard.getInt()));
System.out.print("Date of Birth : ");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
try
{
employee.setDateOfBirth(sdf.parse(Keyboard.getString()));
}catch(ParseException pe)
{}
System.out.print("Basic Salary : ");
BigDecimal salary=new BigDecimal(Keyboard.getString());
employee.setBasicSalary(salary);
System.out.println("1. Male"+"\n"+"2. Female");
System.out.print("Gender : ");
int g=Keyboard.getInt();
if(g==1)
{
employee.setGender(EmployeeInterface.MALE);
}
else
{
employee.setGender(EmployeeInterface.FEMALE);
}
System.out.println("1. Yes"+"\n"+"2. No");
System.out.println("Is Indian");
g=Keyboard.getInt();
if(g==1)
{
employee.isIndian(true);
}
else
{
employee.isIndian(false);
}
System.out.print("PAN Number : ");
employee.setPANNumber(Keyboard.getString());
System.out.print("Aadhar Card Number : ");
employee.setAadharCardNumber(Keyboard.getString());
employeeManager.update(employee);
}catch(BLException blException)
{}
}

public void delete()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
employeeManager.delete(Keyboard.getString());
}catch(BLException blException)
{}
}

public void getCount()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.println("Number of designation : "+employeeManager.getCount());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getAll()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
List<EmployeeInterface> employees=employeeManager.getAll();
DesignationManagerInterface designationManager=DesignationManager.getInstance();
int i=1;
for(EmployeeInterface employee:employees)
{
System.out.println("_______ EMPLOYEE "+i+"________");
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation Title : "+employee.getDesignation().getTitle());
System.out.println("Date of Birth : "+employee.getDateOfBirth());
if(employee.getGender()=="M")
{
System.out.println("Gender : Male");
}
if(employee.getGender()=="F")
{
System.out.println("Gender : Female");
}
System.out.println("isIndian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
i++;
}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getByEmployeeId()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("Employee ID : ");
EmployeeInterface employee=employeeManager.getByEmployeeId(Keyboard.getString());
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation Title : "+employee.getDesignation().getTitle());
System.out.println("Date of Birth : "+employee.getDateOfBirth());
if(employee.getGender()=="M")
{
System.out.println("Gender : Male");
}
if(employee.getGender()=="F")
{
System.out.println("Gender : Female");
}
System.out.println("isIndian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getByPANNumber()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("PAN Number : ");
EmployeeInterface employee=employeeManager.getByPANNumber(Keyboard.getString());
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation Title : "+employee.getDesignation().getTitle());
System.out.println("Date of Birth : "+employee.getDateOfBirth());
if(employee.getGender()=="M")
{
System.out.println("Gender : Male");
}
if(employee.getGender()=="F")
{
System.out.println("Gender : Female");
}
System.out.println("isIndian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getByAadharCardNumber()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("Aadhar Card Number : ");
EmployeeInterface employee=employeeManager.getByAadharCardNumber(Keyboard.getString());
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation Title : "+employee.getDesignation().getTitle());
System.out.println("Date of Birth : "+employee.getDateOfBirth());
if(employee.getGender()=="M")
{
System.out.println("Gender : Male");
}
if(employee.getGender()=="F")
{
System.out.println("Gender : Female");
}
System.out.println("isIndian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}


public void getWithDesignation()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.print("Designation code : ");
List<EmployeeInterface> employees=employeeManager.getWithDesignation(designationManager.getByCode(Keyboard.getInt()));
int i=1;
for(EmployeeInterface employee:employees)
{
System.out.println("_______ EMPLOYEE "+i+"________");
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation Title : "+employee.getDesignation().getTitle());
System.out.println("Date of Birth : "+employee.getDateOfBirth());
if(employee.getGender()=="M")
{
System.out.println("Gender : Male");
}
if(employee.getGender()=="F")
{
System.out.println("Gender : Female");
}
System.out.println("isIndian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
i++;
}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void employeeIdExists()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("Employee Id : ");
System.out.println("Exists : "+employeeManager.employeeIdExists(Keyboard.getString()));
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void aadharCardNumberExists()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("Aadhar Card Number : ");
System.out.println("Exists : "+employeeManager.aadharCardNumberExists(Keyboard.getString()));
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void PANNumberExists()
{
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
System.out.print("PAN Number : ");
System.out.println("Exists : "+employeeManager.panNumberExists(Keyboard.getString()));
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

}