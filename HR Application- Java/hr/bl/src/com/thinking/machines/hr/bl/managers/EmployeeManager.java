package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;

public class EmployeeManager implements EmployeeManagerInterface
{

Map<String,EmployeeInterface> employeeIdWiseEmployee;
Map<String,List<EmployeeInterface>> designationTitleWiseEmployee;
List<EmployeeInterface> employeeIdWiseOrderedEmployeeList;
List<EmployeeInterface> designationTitleWiseOrderedEmployeeList;

private static EmployeeManagerInterface employeeManager;  

private EmployeeManager()
{
employeeManager=null;
}

public static EmployeeManagerInterface getInstance() throws BLException
{
if(employeeManager==null)
{
employeeManager=new EmployeeManager();
((EmployeeManager)employeeManager).populateDataStructures();
}
return employeeManager;
}

private void populateDataStructures() throws BLException
{
BLException blException=new BLException();
employeeIdWiseEmployee=new HashMap<>();
designationTitleWiseEmployee=new HashMap<>();
employeeIdWiseOrderedEmployeeList=new LinkedList<>();
designationTitleWiseOrderedEmployeeList=new LinkedList<>();

try
{  
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
List<EmployeeDTOInterface> employeeDTOList=employeeDAO.getAll(); 
DesignationManagerInterface designationManager=DesignationManager.getInstance();
for(EmployeeDTOInterface employeeDTO:employeeDTOList)
{
int designationCode=employeeDTO.getDesignationCode();
DesignationInterface designationPOJO=designationManager.getByCode(designationCode);
String designationTitle=designationPOJO.getTitle();
EmployeeInterface employeePOJO=new Employee();
POJOCopier.copy(employeePOJO,employeeDTO);
employeePOJO.setDesignation(designationPOJO);
if(employeeIdWiseEmployee.containsKey(employeePOJO.getEmployeeId()))
{
blException.addException("Employee ID ","EmployeeId exists ");
}
else
{
employeeIdWiseEmployee.put(employeePOJO.getEmployeeId(),employeePOJO);
}
// ADD DATA TO MAP ACC. TO TITLE
if(designationTitleWiseEmployee.containsKey(designationTitle))
{
List<EmployeeInterface> employeePOJOList=designationTitleWiseEmployee.get(designationTitle);
employeePOJOList.add(employeePOJO);
}
else
{
List<EmployeeInterface> employeePOJOList=new LinkedList<>();
employeePOJOList.add(employeePOJO);
designationTitleWiseEmployee.put(designationTitle,employeePOJOList);
}
employeeIdWiseOrderedEmployeeList.add(employeePOJO);
designationTitleWiseOrderedEmployeeList.add(employeePOJO);
Collections.sort(employeeIdWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightEmployeeId=right.getEmployeeId();
String leftEmployeeId=left.getEmployeeId();
return leftEmployeeId.compareTo(rightEmployeeId);
}
});
Collections.sort(designationTitleWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightTitle=right.getDesignation().getTitle().toUpperCase();
String leftTitle=left.getDesignation().getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}
}catch(DAOException daoException)
{
System.out.println("Populate DS");
}
}

public boolean isDesignationAllocated(DesignationInterface designation)
{
String title=designation.getTitle();
return designationTitleWiseEmployee.containsKey(title);
}




public void add(EmployeeInterface employee) throws BLException
{
try
{
System.out.println(employee.getBasicSalary().toString());
BLException blException=new BLException();
if(employee==null) blException.addException("employee","Invalid employee");
if(blException.getExceptionCount()!=0) throw blException;
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
POJOCopier.copy(employeeDTO,employee);
int code=employee.getDesignation().getCode();
String empId="";
try
{
employeeDTO.setDesignationCode(code);
employeeDAO.add(employeeDTO);
empId=employeeDTO.getEmployeeId();
}catch(DAOException daoException)
{
System.out.println(daoException);
}
employee.setEmployeeId(empId);
EmployeeInterface newEmployee=new Employee();
POJOCopier.copy(newEmployee,employee);
employeeIdWiseEmployee.put(empId,newEmployee);
if(designationTitleWiseEmployee.containsKey(newEmployee.getDesignation().getTitle()))
{
List<EmployeeInterface> employeesList=designationTitleWiseEmployee.get(newEmployee.getDesignation().getTitle());
employeesList.add(newEmployee);
designationTitleWiseEmployee.put(newEmployee.getDesignation().getTitle(),employeesList);
}
else
{
List<EmployeeInterface> employeesList=new LinkedList<>();
employeesList.add(newEmployee);
designationTitleWiseEmployee.put(newEmployee.getDesignation().getTitle(),employeesList);
}
employeeIdWiseOrderedEmployeeList.add(newEmployee);
designationTitleWiseOrderedEmployeeList.add(newEmployee);
Collections.sort(employeeIdWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightEmployeeId=right.getEmployeeId();
String leftEmployeeId=left.getEmployeeId();
return leftEmployeeId.compareTo(rightEmployeeId);
}
});
Collections.sort(designationTitleWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightTitle=right.getDesignation().getTitle().toUpperCase();
String leftTitle=left.getDesignation().getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}catch(BLException blException)
{
}
}

public void update(EmployeeInterface employee) throws BLException
{
try
{
BLException blException=new BLException();
if(employee==null) blException.addException("employee","Invalid employee");
if(blException.getExceptionCount()!=0) throw blException;
if(!employeeIdWiseEmployee.containsKey(employee.getEmployeeId())) blException.addException("employeeId","employeeId do not exists");
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
POJOCopier.copy(employeeDTO,employee);
int code=employee.getDesignation().getCode();
String empId="";
try
{
employeeDTO.setBasicSalary(employee.getBasicSalary());
employeeDTO.setDesignationCode(code);
employeeDAO.update(employeeDTO);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
EmployeeInterface newEmployee=new Employee();
POJOCopier.copy(newEmployee,employee);
employeeIdWiseEmployee.remove(empId);
employeeIdWiseEmployee.put(empId,newEmployee);
if(designationTitleWiseEmployee.containsKey(newEmployee.getDesignation().getTitle()))
{
List<EmployeeInterface> employeesList=designationTitleWiseEmployee.get(newEmployee.getDesignation().getTitle());
int index=0;
for(EmployeeInterface emp:employeesList)
{
if(newEmployee.getEmployeeId().equalsIgnoreCase(emp.getEmployeeId()))
{
employeesList.set(index,newEmployee);
}
index++;
}
designationTitleWiseEmployee.put(newEmployee.getDesignation().getTitle(),employeesList);
}

int index=0;
for(EmployeeInterface emp:employeeIdWiseOrderedEmployeeList)
{
if(newEmployee.getEmployeeId().equalsIgnoreCase(emp.getEmployeeId()))
{
employeeIdWiseOrderedEmployeeList.set(index,newEmployee);
}
index++;
}

index=0;
for(EmployeeInterface emp:designationTitleWiseOrderedEmployeeList)
{
if(newEmployee.getEmployeeId().equalsIgnoreCase(emp.getEmployeeId()))
{
designationTitleWiseOrderedEmployeeList.set(index,newEmployee);
}
index++;
}

Collections.sort(employeeIdWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightEmployeeId=right.getEmployeeId();
String leftEmployeeId=left.getEmployeeId();
return leftEmployeeId.compareTo(rightEmployeeId);
}
});
Collections.sort(designationTitleWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String rightTitle=right.getDesignation().getTitle().toUpperCase();
String leftTitle=left.getDesignation().getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}catch(BLException blException)
{
}
}

public void delete(String employeeId) throws BLException
{
BLException blException=new BLException();
if(!employeeIdExists(employeeId)) blException.addException("employeeId","employeeId do not Exists");
if(blException.getExceptionCount()!=0) throw blException;
List<EmployeeInterface> employees=getAll();
for(EmployeeInterface employee:employees)
{
String empId=employee.getEmployeeId();
if(employeeId.equals(empId))
{
employeeIdWiseEmployee.remove(employeeId);
designationTitleWiseEmployee.remove(employee.getDesignation().getTitle());
int i=0;
for(EmployeeInterface e:employeeIdWiseOrderedEmployeeList)
{
if(employeeId.equals(e.getEmployeeId()))
{
employeeIdWiseOrderedEmployeeList.remove(i);
}
i++;
}
i=0;
for(EmployeeInterface e:designationTitleWiseOrderedEmployeeList)
{
if(e.getEmployeeId().equals(employeeId))
{
designationTitleWiseOrderedEmployeeList.remove(i);
}
i++;
}
}
}
}

public EmployeeInterface getByEmployeeId(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId.length()==0) blException.addException("employeeId","Invalid employeeId");
if(blException.getExceptionCount()!=0) throw blException;
return employeeIdWiseEmployee.get(employeeId);
}

public EmployeeInterface getByPANNumber(String panNumber) throws BLException
{
BLException blException=new BLException();
EmployeeInterface getEmployee=null;
if(panNumber.length()==0) blException.addException("panNumber ","Invalid panNumber");
if(!panNumberExists(panNumber)) blException.addException("panNumber","panNumber do not exists"); 
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
String pNum=employee.getPANNumber().trim();
if(pNum.equalsIgnoreCase(panNumber))
{
getEmployee=employee;
break;
}
}
if(blException.getExceptionCount()!=0) throw blException;
return getEmployee;
}

public EmployeeInterface getByAadharCardNumber(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
EmployeeInterface getEmployee=null;
if(aadharCardNumber.length()==0) blException.addException("aadharCardNumber ","Invalid aadhar card number");
if(!aadharCardNumberExists(aadharCardNumber)) blException.addException("aadharCardNumber","aadharCardNumber do not exists"); 
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
String aNum=employee.getAadharCardNumber().trim();
if(aNum.equalsIgnoreCase(aadharCardNumber))
{
getEmployee=employee;
break;
}
}
if(blException.getExceptionCount()!=0) throw blException;
return getEmployee;
}

public List<EmployeeInterface> getAll() throws BLException
{
List<EmployeeInterface> tempEmployeeList=new LinkedList<>();
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
EmployeeInterface tempEmployee=new Employee();
POJOCopier.copy(tempEmployee,employee);
tempEmployeeList.add(tempEmployee);
}
return tempEmployeeList;
}

public List<EmployeeInterface> getWithDesignation(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null) blException.addException("designation ","Invalid Designation");
String title=designation.getTitle().trim();
if(blException.getExceptionCount()!=0) throw blException;
return designationTitleWiseEmployee.get(title);
}

public int getCount() throws BLException
{
int count=employeeIdWiseOrderedEmployeeList.size();
return count;
}

public boolean employeeIdExists(String employeeId) throws BLException
{
boolean found=false;
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
String empId=employee.getEmployeeId();
if(employeeId.equalsIgnoreCase(empId))
{
found=true;
break;
}
}
return found;
}

public boolean panNumberExists(String panNumber) throws BLException
{
BLException blException=new BLException();
if(panNumber.trim().length()==0) blException.addException("panNumber","Invalid panNumber");
boolean found=false;
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
String panNum=employee.getPANNumber().trim();
if(panNum.equalsIgnoreCase(panNumber)) 
{
found=true;
break;
}
}
if(blException.getExceptionCount()!=0) throw blException;
return found;
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
if(aadharCardNumber.trim().length()==0) blException.addException("aadharCardNumber","Invalid aadharCardNumber");
boolean found=false;
for(EmployeeInterface employee:employeeIdWiseOrderedEmployeeList)
{
String aadharCardNum=employee.getAadharCardNumber().trim();
if(aadharCardNum.equalsIgnoreCase(aadharCardNumber)) 
{
found=true;
break;
}
}
if(blException.getExceptionCount()!=0) throw blException;
return found;
}
}