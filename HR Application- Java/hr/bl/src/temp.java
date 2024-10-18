private String name;
private DesignationInterface designation;
private java.util.Date dateOfBirth;
private BigDecimal basicSalary;
private String gender;
private boolean isIndian;
private String PANNumber;
private String aadharCardNumber;

try
{

BLException blException=new BLException();
if(employee==null) blException.addException("employee","  Invalid employee");

name=employee.getName();
designation=employee.getDesignation();
dateOfBirth=employee.getDateOfBirth();
basicSalary=employee.getBasicSalary();
gender=emplyee,getGender();
isIndian=employee.isIndian(); 
PANNumber=employee.getPANNumber();
aadharCardNumber=employee.getAadharCardNumber();

if(designation==null) blException.addException("designation","Invalid designation");
if(employee.getEmployeeId()!=null) blExcepption.addException("employeeId","employeeId Should not be passed");
List<EmployeeInterface> blEmployees=getAll();
boolean panNumberFound=false;
boolean aadharNumberFound=false;
DesinationManagerInterface designationManager=new DesignationManager();
if(!designationManager.codeExists(designation.getCode())) blException.addException("designation","Designation do not Exists");
for(EmployeeInterface blEmployee:blEmployees)
{
if(!panNumberfound && panPANNumber.equalsIgnoreCase(blEmployee.getPANNumber()))
{
panNumberfound=true;
break;
}
}
for(EmployeeInterface blEmployee:blEmployees)
{
if(!aadharNumberFound && aadharCardNumber.equals(blEmployee.getAadharCardNumber()))
{
aadharCardNumber=true;
break;
}
}
if(aadharCardFound || panNumberFound) blException.addException("employee","Invalid Employee "); 
if(blExcpetion.getExceptionCount()!=0) throw blException;

EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
POJOCopier.copy(employeeDTO,employee);
employeeDTO.setDesignationCode(employee.getDesignation().getCode());
employeeDAO.add(employeeDTO);
EmployeeInterface newEmployee=new Employee();
POJOCopier(newEmployee,employeeDTO);
newEmployee.setDesignation(employee.getDesignation());


// ADD DATA TO MAP ACC. TO CODE
if(codeWiseEmployee.containsKey(newEmployee.getDesignation().getCode()))
{
List<EmployeeInterface> employeePOJOList=codeWiseEmployee.get(newEmployee.getDesignation().getCode());
employeePOJOList.add(newEmployee);
}
else
{
List<EmployeeInterface> employeePOJOList=new LinkedList<>();
employeePOJOList.add(newEmployee);
codeWiseEmployee.put(newEmployee.getDesignation().getCode(),employeePOJOList);
}
// ADD DATA TO MAP ACC. TO TITLE
if(titleWiseEmployee.containsKey(designationTitle))
{
List<EmployeeInterface> employeePOJOList=titleWiseEmployee.get(newEmployee.getDesignation().getTitle());
employeePOJOList.add(newEmployee);
}
else
{
List<EmployeeInterface> employeePOJOList=new LinkedList<>();
employeePOJOList.add(newEmployee);
titleWiseEmployee.put(newEmployee.getDesignation().getTitle(),employeePOJOList);
}
codeWiseOrderedEmployeeList.add(newEmployee);
titleWiseOrderedEmployeeList.add(newEmployee);
Collections.sort(codeWiseOrderedEmployeeList);
Collections.sort(titleWiseOrderedEmployeeList,new Comparator<EmployeeInterface>(){
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
System.out.println(daoException);
}
