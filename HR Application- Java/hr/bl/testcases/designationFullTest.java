import com.thinking.machines.common.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
class designationFullTestpsp
{
public static void main(String ss[])
{
try
{
DesignationManagerInterface designationManager=DesignationManager.getInstance();
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
int choice=Keyboard.getInt();

if(choice==1)
{
try
{
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

if(choice==2)
{
DesignationInterface designation=new Designation();
try
{
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

if(choice==3)
{
try
{
System.out.print("Designation Code : ");
designationManager.delete(Keyboard.getInt());
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

if(choice==4)
{
System.out.println("Number of designation : "+designationManager.getCount());
}

if(choice==5)
{
try
{
while(true)
{
System.out.println("1. Get all designations");
System.out.println("2. Get designation by code");
System.out.println("3. Get designation by title");
System.out.println("4. Is attached to employee ");
System.out.println("5. Exit");
System.out.print("Enter choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
System.out.println("1. Code Wise list");
System.out.println("2. Title Wise list");
System.out.print("Enter Choice : ");
int cho=Keyboard.getInt();
if(cho==1)
{
List<DesignationInterface> designations=designationManager.getDesignations(DesignationInterface.CODE);
for(DesignationInterface designation:designations) System.out.println("Code : "+designation.getCode()+", Title : "+designation.getTitle());
}
if(cho==2)
{
List<DesignationInterface> designations=designationManager.getDesignations(DesignationInterface.TITLE);
for(DesignationInterface designation:designations) System.out.println("Code : "+designation.getCode()+", Title : "+designation.getTitle());
}
}
if(ch==2)
{
System.out.println("Code : ");
DesignationInterface designation=designationManager.getByCode(Keyboard.getInt());
System.out.println("Code : "+designation.getCode()+"\n"+"Title : "+"\n"+designation.getTitle());
}
if(ch==3)
{
System.out.println("Title : ");
DesignationInterface designation=designationManager.getByTitle(Keyboard.getString());
System.out.println("Code : "+designation.getCode()+"\n"+"Title : "+"\n"+designation.getTitle());
}
if(ch==4)
{
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
System.out.println("Code "+code+" is attached to "+designationManager.getEmployeesCountWithDesignation(code)+" employees.");
}
}
}
if(ch==5)
{
break;
}

}
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}

if(choice==6)
{
while(true)
{
System.out.println("1. Code");
System.out.println("2. Title");
System.out.println("3. Exit");
System.out.println("Enter Choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
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
}
if(ch==2)
{
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
}
if(ch==3)
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
}catch(BLException blException)
{
List<String> exceptions=blException.getException();
for(int i=0;i<exceptions.size();i++) System.out.println(exceptions.get(i));
}
}
}
