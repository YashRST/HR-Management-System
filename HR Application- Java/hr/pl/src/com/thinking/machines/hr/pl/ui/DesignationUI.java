package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;

public class DesignationUI
{

public void add()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
DesignationInterface addDesignation=new Designation();
System.out.print("Enter Designation title : ");
addDesignation.setTitle(Keyboard.getString());
designationManager.add(addDesignation);
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
DesignationManagerInterface designationManager=DesignationManager.getInstance();
DesignationInterface designation=new Designation();
System.out.print("Designation Code : ");
designation.setCode(Keyboard.getInt());
System.out.print("Designation title : ");
designation.setTitle(Keyboard.getString());
designationManager.update(designation);
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void delete()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.print("Designation Code : ");
designationManager.delete(Keyboard.getInt());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getCount()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.println("Number of designation : "+designationManager.getCount());
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
DesignationManagerInterface designationManager=DesignationManager.getInstance();
List<DesignationInterface> designations=designationManager.getDesignations(DesignationInterface.CODE);
for(DesignationInterface designation:designations) System.out.println("Code : "+designation.getCode()+", Title : "+designation.getTitle());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getByTitle()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.println("Title : ");
DesignationInterface designation=designationManager.getByTitle(Keyboard.getString());
System.out.println("Code : "+designation.getCode()+"\n"+"Title : "+"\n"+designation.getTitle());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getByCode()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.println("Code : ");
DesignationInterface designation=designationManager.getByCode(Keyboard.getInt());
System.out.println("Code : "+designation.getCode()+"\n"+"Title : "+"\n"+designation.getTitle());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void isAttachedToAnEmployee()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.println("Code : ");
int code=Keyboard.getInt();
boolean is=designationManager.isAttachedToAnEmployee(code);
if(is)
{
System.out.println("Code : "+code+" is attached to employee/employees");
System.out.println("Do you want to know number of employees attached to this designation code ");
System.out.println("1. Yes "+"\n"+"2. No");
System.out.print("Enter Choice : ");
int cho=Keyboard.getInt();
if(cho==1)
{
int num=designationManager.getEmployeesCountWithDesignation(code);
System.out.println("Code "+code+" is attached to "+num+" employees.");
}
}
else
{
System.out.println("Code : "+code+" is not attached to employee/employees");
}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void getEmployeeCountWithDesignation(int code)
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.println("Code "+code+" is attached to "+designationManager.getEmployeesCountWithDesignation(code)+" employees.");
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void codeExists()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.print("Code : ");
boolean exists=designationManager.codeExists(Keyboard.getInt());
if(exists)
{
System.out.println("Code exists.");
}
else
{
System.out.println("Code do not exists.");
}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

public void titleExists()
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
System.out.print("Title : ");
boolean exists=designationManager.titleExists(Keyboard.getString());
if(exists)
{
System.out.println("Title exists");
}
else
{
System.out.println("Title do not exists");
}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

}