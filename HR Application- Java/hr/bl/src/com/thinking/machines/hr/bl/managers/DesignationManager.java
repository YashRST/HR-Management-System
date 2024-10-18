package com.thinking.machines.hr.bl.managers;

import com.thinking.machines.common.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;


public class DesignationManager implements DesignationManagerInterface
{

Map<Integer,DesignationInterface> codeWiseMap;
Map<String,DesignationInterface> titleWiseMap;
List<DesignationInterface> codeWiseOrderedList;
List<DesignationInterface> titleWiseOrderedList;

private static DesignationManagerInterface designationManager;
  
private DesignationManager()
{
}

public static DesignationManagerInterface getInstance() throws BLException
{
if(designationManager==null)
{
designationManager=new DesignationManager();
((DesignationManager)designationManager).populateDataStructures();
}
return designationManager;
}

private void populateDataStructures() throws BLException
{
codeWiseMap=new HashMap<>();
titleWiseMap=new HashMap<>();
codeWiseOrderedList=new LinkedList<>();
titleWiseOrderedList=new LinkedList<>();
try
{  
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
List<DesignationDTOInterface> dlDesignations=designationDAO.getAll();
DesignationInterface designation;
int code;
String title;
for(DesignationDTOInterface dlDesignation:dlDesignations)
{
designation=new Designation();
POJOCopier.copy(designation,dlDesignation);
code=dlDesignation.getCode();
title=dlDesignation.getTitle();
codeWiseMap.put(new Integer(code),designation);
titleWiseMap.put(title.toUpperCase(),designation);
codeWiseOrderedList.add(designation);
titleWiseOrderedList.add(designation);
}
Collections.sort(codeWiseOrderedList);
Collections.sort(titleWiseOrderedList,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
String rightTitle=right.getTitle().toUpperCase();
String leftTitle=left.getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}

public void add(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null) blException.addException("designation","Invalid designation");
String title=designation.getTitle();
if(title==null || title.trim().length()==0 || title.length()>=35) blException.addException("title"," Invalid title");
List<DesignationInterface> blDesignations=getDesignations();
boolean found=false;
for(DesignationInterface blDesignation:blDesignations)
{
String designationTitle=blDesignation.getTitle();
if(designationTitle.equals(title))
{
found=true;
break;
}
}
if(found) blException.addException("title","Title exists");
if(blException.getExceptionCount()!=0)
{
List<String> exception=blException.getException();
Set<String> property=blException.getProperties();
Object[] arr=property.toArray();
for(int i=0;i<exception.size();i++) System.out.println(arr[i]+" : "+exception.get(i));
throw blException;
}
DesignationDTOInterface designationDTO=new DesignationDTO();
try
{
POJOCopier.copy(designationDTO,designation);
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.add(designationDTO);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
DesignationInterface newDesignation=new Designation();
POJOCopier.copy(newDesignation,designationDTO);
int code=newDesignation.getCode();
codeWiseMap.put(new Integer(code),newDesignation);
titleWiseMap.put(title,newDesignation);
codeWiseOrderedList.add(newDesignation);
titleWiseOrderedList.add(newDesignation);
Collections.sort(codeWiseOrderedList);
Collections.sort(titleWiseOrderedList,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
String rightTitle=right.getTitle().toUpperCase();
String leftTitle=left.getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}

public void update(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null) blException.addException("designation"," Invalid designation");
String title=designation.getTitle();
int code=designation.getCode();
if(code<=0) blException.addException("code"," Invalid designation code");
if(title==null || title.trim().length()==0 || title.length()>=35) blException.addException("title"," Invalid title");
List<DesignationInterface> blDesignations=getDesignations();
boolean titleFound=false;
boolean codeFound=false;
for(DesignationInterface blDesignation:blDesignations)
{
int designationCode=blDesignation.getCode();
if(designationCode==code)
{
codeFound=true;
break;
}
}
if(!codeFound) blException.addException("code"," Invalid code : code does not exists");
for(DesignationInterface blDesignation:blDesignations)
{
String designationTitle=blDesignation.getTitle();
if(designationTitle.equals(title))
{
titleFound=true;
break;
}
}
if(titleFound) blException.addException("title","Invalid title : title exists");
if(blException.getExceptionCount()!=0)
{
List<String> exception=blException.getException();
Set<String> property=blException.getProperties();
Object[] arr=property.toArray();
for(int i=0;i<exception.size();i++) System.out.println(arr[i]+" : "+exception.get(i));
throw blException;
}
DesignationDTOInterface designationDTO=new DesignationDTO();
POJOCopier.copy(designationDTO,designation);
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.update(designationDTO);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
DesignationInterface newDesignation=new Designation();
POJOCopier.copy(newDesignation,designation);
int index=0;
for(DesignationInterface dlDesignation:codeWiseOrderedList)
{
int designationCode=dlDesignation.getCode();
if(code==designationCode)
{
dlDesignation.setTitle(title);
break;
}
index++;
}
codeWiseMap.put(code,newDesignation);
titleWiseMap.put(title,newDesignation);
for(DesignationInterface dlDesignation:titleWiseOrderedList)
{
int dlCode=dlDesignation.getCode();
if(dlCode==code)
{
dlDesignation.setTitle(title);
break;
}
}
}

public void delete(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0) blException.addException("code","Invalid designation code");
if(isAttachedToAnEmployee(code)) blException.addException("code"," Attached to an employee"); 
if(blException.getExceptionCount()!=0)
{
List<String> exception=blException.getException();
Set<String> property=blException.getProperties();
Object[] arr=property.toArray();
for(int i=0;i<exception.size();i++) System.out.println(arr[i]+" : "+exception.get(i));
throw blException;
}
String title="";
int index=0;
for(DesignationInterface dlDesignation:codeWiseOrderedList)
{
int designationCode=dlDesignation.getCode();
if(code==designationCode)
{
break;
}
index++;
}
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.delete(code);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
codeWiseMap.remove(code);
titleWiseMap.remove(title);
codeWiseOrderedList.remove(index);
index=0;
for(DesignationInterface blDesignation:titleWiseOrderedList)
{
int blCode=blDesignation.getCode();
if(code==blCode)
{
break;
}
index++;
}
titleWiseOrderedList.remove(index);
}

public int getCount()
{
int size=codeWiseMap.size();
return size;
}

public List<DesignationInterface> getDesignations(DesignationInterface.ATTRIBUTE ...orderBy)
{
List<DesignationInterface> list;
DesignationInterface designation;
list=new LinkedList<>();
if(orderBy.length==0 || orderBy [0]==DesignationInterface.TITLE)
{
for(DesignationInterface d:titleWiseOrderedList)
{
designation=new Designation();
POJOCopier.copy(designation,d);
list.add(designation);
}
}
else
{
for(DesignationInterface d:codeWiseOrderedList)
{
designation=new Designation();
POJOCopier.copy(designation,d);
list.add(designation);
}
}
return list;
}

public DesignationInterface getByCode(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0) blException.addException("code","Invalid designation code");
DesignationInterface designation=new Designation();
boolean found=false;
for(DesignationInterface blDesignation:codeWiseOrderedList)
{
int designationCode=blDesignation.getCode();
if(code==designationCode)
{
POJOCopier.copy(designation,blDesignation);
found=true;
break;
}
}
if(!found) blException.addException("code","Invalid designation code : code not found");
if(blException.getExceptionCount()!=0)
{
List<String> exception=blException.getException();
Set<String> property=blException.getProperties();
Object[] arr=property.toArray();
for(int i=0;i<exception.size();i++) System.out.println(arr[i]+" : "+exception.get(i));
throw blException;
}
return designation;
}

public DesignationInterface getByTitle(String title) throws BLException
{
BLException blException=new BLException();
if(title==null || title.trim().length()==0 || title.length()>=35) blException.addException("title","Invalid designation title");
DesignationInterface designation=new Designation();
boolean found=false;
for(DesignationInterface blDesignation:titleWiseOrderedList)
{
String designationTitle=blDesignation.getTitle();
if(title.equals(designationTitle))
{
POJOCopier.copy(designation,blDesignation);
found=true;
break;
}
}
if(!found) blException.addException("title","Invalid designation title : title not found");
if(blException.getExceptionCount()!=0)
{
List<String> exception=blException.getException();
Set<String> property=blException.getProperties();
Object[] arr=property.toArray();
for(int i=0;i<exception.size();i++) System.out.println(arr[i]+" : "+exception.get(i));
throw blException;
}
return designation;
}

public boolean codeExists(int code)
{
if(code<=0) return false;
boolean found=false;
for(DesignationInterface blDesignation:codeWiseOrderedList)
{
int designationCode=blDesignation.getCode();
if(code==designationCode)
{
found=true;
break;
}
}
return found;
}

public boolean titleExists(String title)
{
if(title==null || title.trim().length()==0 || title.length()>=35) return false;
boolean found=false;
for(DesignationInterface blDesignation:titleWiseOrderedList)
{
String designationTitle=blDesignation.getTitle();
if(title.equals(designationTitle))
{
found=true;
break;
}
}
return found;
}

public boolean isAttachedToAnEmployee(int code)
{
EmployeeManagerInterface employeeManager=null;
boolean isAttached=false;
try
{
employeeManager=EmployeeManager.getInstance();
isAttached=((EmployeeManager)employeeManager).isDesignationAllocated(getByCode(code));
}catch(BLException blException)
{
List<String> list=blException.getException();
for(int i=0;i<list.size();i++) System.out.println(list.get(i)+"\n");
}
return isAttached;
}

public int getEmployeesCountWithDesignation(int code)
{
DesignationInterface designation=new Designation();
designation.setCode(code);
EmployeeManagerInterface employeeManager;
int size=0;
try
{
List<EmployeeInterface> list;
employeeManager=EmployeeManager.getInstance();
if(!((EmployeeManager)employeeManager).isDesignationAllocated(getByCode(code))) return 0;
list=((EmployeeManager)employeeManager).getWithDesignation(designation);
size=list.size();
}catch(BLException blException)
{
List<String> exception=blException.getException();
for(int i=0;i<exception.size();i++) System.out.println(exception.get(i)+"\n");
}
return size;
}

}										