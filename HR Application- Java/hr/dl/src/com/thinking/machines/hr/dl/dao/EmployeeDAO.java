package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class EmployeeDAO implements EmployeeDAOInterface
{

public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Invalid employee details. Employee can not be null.");
if(employeeDTO.getEmployeeId()!=0) throw new DAOException("Invalid employee ID. Employee ID should be zero while adding and employee detail.");
String vEmployeeId;
String vName=employeeDTO.getName();
if(vName==null || vName.trim().length()==0) throw new DOAException("No employee name found. Need employee name.");
vName=vName.trim();
int vDesignationCode=employeeDTO.getDesignationCode();
boolean designationCodeExists=new DesignationDAO().exists(vDesignationCode);
if(designationCodeExists==false) throw new DAOException("Invalid designation code : "+vDesignationCode);
java.util.Date vDateOfBirth=employeeDTO.getDateOfBirth();
if(vDateOfBirth==null) throw new DAOException("No date of birth found. Need date if birth");
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
String vGender=employeeDTO.getGender();
if(vGender==null || vGender.trim().length()==0) throw new DAOException("Invalid gender"); 
boolean vIsIndian=employeeDTO.isIndian();
String vPANNumber=employeeDTO.getPANNumber();
if(vPANNumber==null || vPANNumber.trim().length()==0) throw new DAOException("Invalid PAN number.");
vPANNumber=vPANNumber.trim();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
if(vAadharCardNumber==null || vAadharCardNumber.trim().length()==0) throw new DAOException("Invalid Aadhar Card Number.");
vAadharCardNumber=vAadharCardNumber.trim();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
long lastGeneratedEmployeeId=10000;
int count=0;
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(0);
lastGeneratedEmployeeId=Long.parseLong(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
long vNewEmployeeId;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
if(count==0)
{
vNewEmployeeId=lastGeneratedEmployeeId+1;
vEmployeeId="EMP"+vNewEmployeeId;
randomAccessFile.writeBytes(vEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(sdf.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPANNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%10d\n%10d\n",vNewEmployeeId,count+1));
randomAccessFile.close();
employeeDTO.setEmployeeId(vEmployeeId);
return;
}
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
boolean panNumberFound=false;
boolean aadharCardNumberFound=false;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=7;i++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
if(panNumberFound==false) panNumberFound=vPANNumber.equalsIgnoreCase(fPANNumber);
if(aadharCardNumberFound==false)
aadharCardNumberFound=vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber);
if(panNumberFound && aadharCardNumberFound) break;
} 
if(panNumberFound==false && aadharCardNumberFound==true)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number "+vAadharCardNumber+" exists.");
} 
if(panNumberFound==true && aadharCardNumberFound==false)
{
randomAccessFile.close();
throw new DAOException("PAN Number "+vPANNumber+" exists.");
} 
if(panNumberFound && aadharCardNumberFound)
{
randomAccessFile.close();
throw new DAOException("PAN Number "+vPANNumber+" and Aadhar card number "+vAadharCardNumber+" exists.");
}
vNewEmployeeId=lastGeneratedEmployeeId+1;
vEmployeeId="EMP"+vNewEmployeeId;
randomAccessFile.writeBytes(vEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(sdf.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(employeeDTO.getBasicSalary().toString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPANNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%10d\n%10d\n",vNewEmployeeId,count+1));
randomAccessFile.close();
employeeDTO.setEmployeeId(vEmployeeId);
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
}

public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Invalid employee details. Employee details can not be null");
String vEmployeeId=employeeDTO.getEmployeeId();
if(vEmployeeId==null || vEmployeeId.trim().length()==0) throw new DAOException("Invalid employee details: Invalid Employee ID");
vEmployeeId=vEmployeeId.trim();
String vName=employeeDTO.getName();
if(vName==null || vName.trim().length()==0) throw new DAOException("Invalid employee details: Invalid Employee name");
int vDesignationCode=employeeDTO.getDesignationCode();
boolean designationCodeExists=new DesignationDAO().exists(vDesignationCode);
if(designationCodeExists==false) throw new DAOException("Invalid employee details: Invalid designation code : "+vDesignationCode);
java.util.Date vDateOfBirth=employeeDTO.getDateOfBirth();
if(vDateOfBirth==null) throw new DAOException("Invalid employee details: No date of birth found. Need date if birth");
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
String vGender=employeeDTO.getGender();
if(vGender==null || vGender.trim().length()==0) throw new DAOException("Invalid employee details: Invalid gender"); 
boolean vIsIndian=employeeDTO.isIndian();
String vPANNumber=employeeDTO.getPANNumber();
if(vPANNumber==null || vPANNumber.trim().length()==0) throw new DAOException("Invalid employee details: Invalid PAN number.");
vPANNumber=vPANNumber.trim();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
if(vAadharCardNumber==null || vAadharCardNumber.trim().length()==0) throw new DAOException("Invalid employee details: Invalid Aadhar Card Number.");
vAadharCardNumber=vAadharCardNumber.trim();
try
{
boolean employeeIdFound;
String panNumberFoundAgainst;
String aadharCardNumberFoundAgainst;
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
throw new DAOException("Invalid employee id : "+vEmployeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
employeeIdFound=false;
panNumberFoundAgainst=null;
aadharCardNumberFoundAgainst=null;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
int i;
long recordToUpdateIsAtPosition=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{ 
if(employeeIdFound==false) recordToUpdateIsAtPosition=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine().trim();
for(i=1;i<=6;i++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
if(!employeeIdFound) employeeIdFound=vEmployeeId.equalsIgnoreCase(fEmployeeId);
if(panNumberFoundAgainst==null && vPANNumber.equalsIgnoreCase(fPANNumber)) panNumberFoundAgainst=fEmployeeId;
if(aadharCardNumberFoundAgainst==null && vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber)) aadharCardNumberFoundAgainst=fEmployeeId;
if(employeeIdFound && panNumberFoundAgainst!=null && aadharCardNumberFoundAgainst!=null) break;
} 
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id : "+vEmployeeId);
}
boolean panNumberFound=(panNumberFoundAgainst!=null && vEmployeeId.equals(panNumberFoundAgainst)==false);
boolean aadharCardNumberFound=(aadharCardNumberFoundAgainst!=null && vEmployeeId.equals(aadharCardNumberFoundAgainst)==false);
if(panNumberFound==false && aadharCardNumberFound==true)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number "+vAadharCardNumber+" exists.");
} 
if(panNumberFound==true && aadharCardNumberFound==false)
{
randomAccessFile.close();
throw new DAOException("PAN Number "+vPANNumber+" exists.");
} 
if(panNumberFound && aadharCardNumberFound)
{
randomAccessFile.close();
throw new DAOException("PAN Number "+vPANNumber+" and Aadhar card number"+vAadharCardNumber+" exists.");
}
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.writeBytes(vEmployeeId);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(vName);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(vDesignationCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(sdf.format(vDateOfBirth));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(vBasicSalary.toPlainString());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(vGender);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(vIsIndian));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(vPANNumber);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(vAadharCardNumber);
tmpRandomAccessFile.writeBytes("\n");
randomAccessFile.seek(recordToUpdateIsAtPosition);
for(i=1;i<=9;i++) randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(recordToUpdateIsAtPosition);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(recordToUpdateIsAtPosition+tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}

public void delete(String employeeId) throws DAOException
{
if(employeeId==null || employeeId.trim().length()==0) throw new DAOException("Invalid employee ID.");
try
{
boolean employeeIdFound;
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
throw new DAOException("Invalid employee id : "+employeeId);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
employeeIdFound=false;
String fEmployeeId;
int i;
long recordToDeleteIsAtPosition=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
recordToDeleteIsAtPosition=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine().trim();
if(fEmployeeId.equals(employeeId.trim()))
{
employeeIdFound=true;
break;
}
for(i=1;i<=8;i++) randomAccessFile.readLine();
} 
if(!employeeIdFound)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id : "+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(recordToDeleteIsAtPosition);
for(i=1;i<=9;i++) randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(recordToDeleteIsAtPosition);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(recordToDeleteIsAtPosition+tmpRandomAccessFile.length());
count--;
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
if(employeeId==null || employeeId.trim().length()==0) throw new DAOException("Invalid employee ID");
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid employee id. : "+employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+employeeId);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+employeeId);
}
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fGender;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim();
if(fEmployeeId.equals(employeeId.trim()))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine().trim());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine().trim()));
try
{
employeeDTO.setDateOfBirth(sdf.parse(randomAccessFile.readLine().trim()));
}catch(ParseException parseException)
{
// no code required, as date in file will be of valid format
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine().trim()));
fGender=randomAccessFile.readLine().trim();
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) // we can also go for else, instead of if as there are only 2 states
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine().trim()));
employeeDTO.setPANNumber(randomAccessFile.readLine().trim());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine().trim());
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid employee id. : "+employeeId);
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
if(panNumber==null || panNumber.trim().lenght()==0) throw new DAOException("Invalid PAN number.");
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid PAN Number : "+panNumber);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
throw new DAOException("Invalid PAN Number : "+panNumber);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
throw new DAOException("Invalid PAN Number : "+panNumber);
}
panNumber=panNumber.trim();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine().trim();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine().trim());
try
{
fDateOfBirth=sdf.parse(randomAccessFile.readLine().trim());
}catch(ParseException parseException)
{
fDateOfBirth=null;
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine().trim());
fGender=randomAccessFile.readLine().trim();
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine().trim());
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) 
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(fIsIndian);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid PAN Number : "+panNumber);
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
}

public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null || aadharCardNumber.trim().length()==0) throw new DAOException("Invalid aadhar card number.");
aadharCardNumber=aadharCardNumber.trim();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid Aadhar Card Number : "+aadharCardNumber);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
throw new DAOException("Invalid aadharCardNumber : "+aadharCardNumber);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
throw new DAOException("Invalid aadharCardNumber : "+aadharCardNumber);
}
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine().trim();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine().trim());
try
{
fDateOfBirth=sdf.parse(randomAccessFile.readLine().trim());
}catch(ParseException parseException)
{
fDateOfBirth=null;
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine().trim());
fGender=randomAccessFile.readLine().trim();
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine().trim());
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
if(aadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) 
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(fIsIndian);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid aadharCardNumber : "+aadharCardNumber);
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
}

public List<EmployeeDTOInterface> getAll() throws DAOException
{
List<EmployeeDTOInterface> employees;
employees=new LinkedList<>();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine().trim();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine().trim());
String dob=randomAccessFile.readLine().trim();
try
{
fDateOfBirth=sdf.parse(dob);
}catch(ParseException parseException)
{
fDateOfBirth=null;
}
String num=randomAccessFile.readLine().trim();
fBasicSalary=new BigDecimal(num);
fGender=randomAccessFile.readLine().trim();
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine().trim());
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) 
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(fIsIndian);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
employees.add(employeeDTO);
}
randomAccessFile.close();
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
return employees;
}

public List<EmployeeDTOInterface> getByDesignation(int designationCode) throws DAOException
{
if(new DesignationDAO().exists(designationCode)==false)
{ 
throw new DAOException("Invalid designation code : "+designationCode);
}
List<EmployeeDTOInterface> employees;
employees=new LinkedList<>();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
int i;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim().trim();
fName=randomAccessFile.readLine().trim();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(fDesignationCode!=designationCode)
{
for(i=1;i<=6;i++) randomAccessFile.readLine().trim();
continue;
} 
try
{
fDateOfBirth=sdf.parse(randomAccessFile.readLine().trim());
}catch(ParseException parseException)
{
fDateOfBirth=null; 
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine().trim());
fGender=randomAccessFile.readLine().trim();
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine().trim());
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) 
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(fIsIndian);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
employees.add(employeeDTO);
}
randomAccessFile.close();
}catch(IOException ioException)
{
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
return employees;
}

public List<EmployeeDTOInterface> getByDateOfBirth(java.util.Date dateOfBirth) throws DAOException
{
if(dateOfBirth==null) throw new DAOException("Invalid date: "+dateOfBirth);
List<EmployeeDTOInterface> employees;
employees=new LinkedList<>();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
java.util.Date fDateOfBirth;
BigDecimal fBasicSalary;
String fGender;
boolean fIsIndian;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
int i;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine().trim();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine().trim());
try
{
fDateOfBirth=sdf.parse(randomAccessFile.readLine().trim());
}catch(ParseException parseException)
{
fDateOfBirth=null; 
} 
if(!(fDateOfBirth.getDate()==dateOfBirth.getDate() &&
fDateOfBirth.getMonth()==dateOfBirth.getMonth() &&
fDateOfBirth.getYear()==dateOfBirth.getYear()))
{
for(i=1;i<=5;i++) randomAccessFile.readLine();
continue;
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine().trim());
fGender=randomAccessFile.readLine().trim();
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine().trim());
fPANNumber=randomAccessFile.readLine().trim();
fAadharCardNumber=randomAccessFile.readLine().trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
if(fGender.equals("M"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
} 
if(fGender.equals("F")) 
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.isIndian(fIsIndian);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
employees.add(employeeDTO);
}
randomAccessFile.close();
}catch(IOException ioException)
{ 
randomAccessFile.close();
throw new DAOException(ioException.getMessage());
}
return employees;
}

public int getCount() throws DAOException
{
int count=0;
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return count;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return count;
}
randomAccessFile.readLine();
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}

public boolean employeeIdExists(String employeeId) throws DAOException
{
if(employeeId==null || employeeId.trim().length()==0) throw new DAOException("Invalid employee ID: "+employeeId);
employeeId=employeeId.trim();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
return false;
} 
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{ 
if(randomAccessFile.readLine().trim().equals(employeeId))
{
randomAccessFile.close();
return true;
}
for(i=1;i<=8;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}

public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber==null || panNumber.trim().length()==0) throw new DAOException("Invalid PAN number: "+panNumber);
panNumber=panNumber.trim();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
return false;
} 
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=7;i++) randomAccessFile.readLine();
if(randomAccessFile.readLine().trim().equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null || aadharCardNumber.trim().length()==0) throw new DAOException("Invalid aadhar card number: "+aadharCardNumber);
aadharCardNumber=aadharCardNumber.trim();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
randomAccessFile.close();
return false;
} 
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=8;i++) randomAccessFile.readLine();
if(randomAccessFile.readLine().trim().equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}
}
