import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.tmcommon.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.text.*;

class EmployeeTestCasepsp
{
public static void main(String dd[])
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
while(true)
{
System.out.println("1. Add Employee");
System.out.println("2. Update Employee");
System.out.println("3. Delete Employee");
System.out.println("4. Get Employee Info.");
System.out.println("5. Check Existance");
System.out.println("6. Exit");
System.out.print("Enter your choice : ");
int choice=Keyboard.getInt();

if(choice==1)
{
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();
System.out.println("____Employee ADD Module____");

System.out.print("Name : ");
String name=Keyboard.getString();
employeeDTO.setName(name);
try
{
List<DesignationDTOInterface> designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations) System.out.println("Code : "+designation.getCode()+", Title : "+designation.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
System.out.print("Designation Code : ");
int designationCode=Keyboard.getInt();
employeeDTO.setDesignationCode(designationCode);
java.util.Date dob=null;
System.out.print("Date of Birth : ");
try
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
dob=sdf.parse(Keyboard.getString());
}catch(ParseException pe)
{
System.out.println(pe);
}
employeeDTO.setDateOfBirth(dob);
System.out.print("Salary : ");
BigDecimal basicSalary=new BigDecimal(Keyboard.getString());
employeeDTO.setBasicSalary(basicSalary);
System.out.println("1 -> Male"+"\n"+"2 -> Female");
System.out.print("Gender : ");
int gen=Keyboard.getInt();
if(gen==1)
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
if(gen==2)
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
if(gen!=1 && gen!=2) 
{
System.out.println("Invalid input");
continue;
}
System.out.println("1. Yes "+"\n"+"2. No");
System.out.print("Is indian : ");
int ch=Keyboard.getInt();
if(ch==1) 
{
employeeDTO.isIndian(true);
}else if(ch==2)
{
employeeDTO.isIndian(false);
}else
{
System.out.println("Invalid input");
continue;
}
System.out.print("PAN Number : ");
String PANNumber=Keyboard.getString();
employeeDTO.setPANNumber(PANNumber);
System.out.print("AadharCardNumber : ");
String aadharCardNumber=Keyboard.getString();
employeeDTO.setAadharCardNumber(aadharCardNumber);
try
{
employeeDAO.add(employeeDTO);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
System.out.println("Employee ADDED");
}

if(choice==2)
{
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();
System.out.println("____Employee UPDATE Module____");
System.out.println("Employee Id : ");
employeeDTO.setEmployeeId(Keyboard.getString());
System.out.print("Name : ");
String name=Keyboard.getString();
employeeDTO.setName(name);
try
{
List<DesignationDTOInterface> designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations) System.out.println("Code : "+designation.getCode()+", Title : "+designation.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
System.out.print("Designation Code : ");
int designationCode=Keyboard.getInt();
employeeDTO.setDesignationCode(designationCode);
java.util.Date dob;
System.out.print("Date of Birth : ");
try
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
dob=sdf.parse(Keyboard.getString());
}catch(ParseException pe)
{
dob=null;
}
employeeDTO.setDateOfBirth(dob);
System.out.print("Salary : ");
BigDecimal basicSalary=new BigDecimal(Keyboard.getString());
employeeDTO.setBasicSalary(basicSalary);
System.out.println("1 -> Male"+"\n"+"2 -> Female");
System.out.print("Gender : ");
int gen=Keyboard.getInt();
if(gen==1)
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
if(gen==2)
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
if(gen!=1 && gen!=2) 
{
System.out.println("Invalid input");
continue;
}
System.out.println("1. Yes "+"\n"+"2. No");
System.out.print("Is indian : ");
int ch=Keyboard.getInt();
if(ch==1) 
{
employeeDTO.isIndian(true);
}else if(ch==2)
{
employeeDTO.isIndian(false);
}else
{
System.out.println("Invalid input");
continue;
}
System.out.print("PAN Number : ");
String PANNumber=Keyboard.getString();
employeeDTO.setPANNumber(PANNumber);
System.out.print("AadharCardNumber : ");
String aadharCardNumber=Keyboard.getString();
employeeDTO.setAadharCardNumber(aadharCardNumber);
try
{
employeeDAO.update(employeeDTO);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
System.out.println("Employee UPDATED");
}

if(choice==3)
{
System.out.println("_____Employee DELETE Module______");
System.out.print("Employee ID : ");
String empId=Keyboard.getString();
try
{
employeeDAO.delete(empId);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}

if(choice==4)
{
while(true)
{
System.out.println("1. Get INFO  of all employees");
System.out.println("2. Get INFO by Employee ID");
System.out.println("3. Get INFO by Employee PAN Number");
System.out.println("4. Get INFO by Employee Aadhar Card Number ");
System.out.println("5. Get Employee with designation ");
System.out.println("6. Get Employee with DOB");
System.out.println("7. Number of Employees");
System.out.println("8. Exit");
System.out.print("Enter Choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
try
{
List<EmployeeDTOInterface> employees=employeeDAO.getAll();
int i=1;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTOInterface employee:employees)
{
System.out.println("_____Employee : "+i+"_______");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
i++;
}
}catch(DAOException daoException)
{}
}

if(ch==2)
{
try
{
int i=1;
System.out.print("Employee ID : ");
String empId=Keyboard.getString();
EmployeeDTOInterface employee=employeeDAO.getByEmployeeId(empId);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
}catch(DAOException daoException)
{}
}

if(ch==3)
{
try
{
int i=1;
System.out.print("PAN Number : ");
String panNumber=Keyboard.getString();
EmployeeDTOInterface employee=employeeDAO.getByPANNumber(panNumber);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
}catch(DAOException daoException)
{}
}
if(ch==4)
{
try
{
System.out.print("Aadhar Card Number : ");
String aadharCardNumber=Keyboard.getString();
EmployeeDTOInterface employee=employeeDAO.getByAadharCardNumber(aadharCardNumber);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
}catch(DAOException daoException)
{}
}
if(ch==5)
{
try
{
System.out.print("Designation Code : ");
int designationCode=Keyboard.getInt();
List<EmployeeDTOInterface> employees=employeeDAO.getByDesignation(designationCode);
int i=1;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTOInterface employee:employees)
{
System.out.println("_____Employee : "+i+"_______");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
i++;
}
}catch(DAOException daoException)
{}
}
if(ch==6)
{
try
{
System.out.print("Date of Birth : ");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String dob=Keyboard.getString();
List<EmployeeDTOInterface> employees=null;
try
{
employees=employeeDAO.getByDateOfBirth(sdf.parse(dob));
}catch(ParseException pe)
{
System.out.println(pe);
}
int i=1;
for(EmployeeDTOInterface employee:employees)
{
System.out.println("_____Employee : "+i+"_______");
System.out.println("Employee ID : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designaion Code : "+employee.getDesignationCode());
System.out.println("DOB : "+sdf.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Gende : "+employee.getGender());
System.out.println("Is indian : "+employee.isIndian());
System.out.println("PAN Number : "+employee.getPANNumber());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
i++;
}
}catch(DAOException daoException)
{
}
}

if(ch==7)
{
try
{
System.out.println("Number of Employees : "+employeeDAO.getCount());
}catch(DAOException daoException)
{
}
}
if(ch==8)
{
break;
}
}
}

if(choice==5)
{
while(true)
{
try
{
System.out.println("1. Employee Id ");
System.out.println("2. PAN Number  ");
System.out.println("3. Aadhar Card Number ");
System.out.println("4. Exit ");
System.out.print("Enter Choice : ");
int ch=Keyboard.getInt();
if(ch==1)
{
System.out.print("Employeee ID : ");
String employeeId=Keyboard.getString();
if(employeeDAO.employeeIdExists(employeeId))
{
System.out.println("Employee Id Exists ");
}
else
{
System.out.println("Employee Id do not exists");
}
}
if(ch==2)
{
System.out.print("PAN Number : ");
String panNumber=Keyboard.getString();
if(employeeDAO.panNumberExists(panNumber))
{
System.out.println("PAN Number Exists ");
}
else
{
System.out.println("PAN Number do not exists");
}
}
if(ch==3)
{
System.out.print("Aadhar Card Number : ");
String aadharCardNumber=Keyboard.getString();
if(employeeDAO.aadharCardNumberExists(aadharCardNumber))
{
System.out.println("Aadhar Card Number Exists ");
}
else
{
System.out.println("Aadhar Card Number do not exists");
}
}
if(ch==4)
{
break;
}
}catch(DAOException daoException)
{}
}
}

if(choice==6)
{
break;
}
//-------------------------------------------------------------
}
}
}


