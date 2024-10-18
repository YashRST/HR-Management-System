import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.math.*;
import java.lang.*;
import java.util.*;
import java.text.*;
class employeeFullTestCasepsp
{
public static void main(String ss[])
{
try
{
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
int choice=Keyboard.getInt();
DesignationManagerInterface designationManager=DesignationManager.getInstance();
EmployeeManagerInterface employeeManager=EmployeeManager.getInstance();
if(choice==1)
{
try
{
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
{}
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
{}
}
if(choice==2)
{
try
{

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

if(choice==3)
{
try
{
System.out.print("Employee ID : ");
employeeManager.delete(Keyboard.getString());
}catch(BLException blException)
{}
}

if(choice==4)
{
try
{
System.out.println("Number of Employees : "+employeeManager.getCount());
}catch(BLException blException)
{}
}

if(choice==5)
{
try
{
while(true)
{
System.out.println("1. Get all");
System.out.println("2. Get by employeeId");
System.out.println("3. Get by PAN Number ");
System.out.println("4. Get by Aadhar card number");
System.out.println("5. Get all with designation");
System.out.println("6. Exit");
int ch=Keyboard.getInt();
if(ch==1)
{
List<EmployeeInterface> employees=employeeManager.getAll();
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
}
if(ch==2)
{
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
}
if(ch==3)
{
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
}
if(ch==4)
{
System.out.print("Aadhar card number : ");
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
}
if(ch==5)
{
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
}
if(ch==6)
{
break;
}
}
}catch(BLException blException)
{}
}
if(choice==6)
{
try
{
while(true)
{
System.out.println("1. Employee ID");
System.out.println("2. PAN Number");
System.out.println("3. Aadhar Card Number");
System.out.println("4. Exit");
System.out.print("Enter Choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
System.out.print("Employee Id : ");
System.out.println("Exists : "+employeeManager.employeeIdExists(Keyboard.getString()));
}
if(ch==2)
{
System.out.print("PAN Number : ");
System.out.println("Exists : "+employeeManager.panNumberExists(Keyboard.getString()));
}
if(ch==3)
{
System.out.print("Aadhar Card Number : ");
System.out.println("Exists : "+employeeManager.aadharCardNumberExists(Keyboard.getString()));
}
if(ch==4)
{
break;
}
}
}catch(BLException blException)
{}
}

if(choice==7)
{
break;
}
}
}catch(BLException blException)
{
}
}
}