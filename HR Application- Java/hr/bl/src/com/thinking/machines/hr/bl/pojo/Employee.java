package com.thinking.machines.hr.bl.pojo;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import java.math.*;

public class Employee implements EmployeeInterface
{
private String employeeId;
private String name;
private DesignationInterface designation;
private java.util.Date dateOfBirth;
private java.math.BigDecimal basicSalary;
private String gender;
private boolean isIndian;
private String PANNumber;
private String aadharCardNumber;
public Employee()
{
this.employeeId="";
this.name="";
this.designation=null;
this.dateOfBirth=null;
this.basicSalary=null;
this.gender="";
this.isIndian=false;
this.PANNumber="";
this.aadharCardNumber="";
}

public void setEmployeeId(java.lang.String employeeId)
{
this.employeeId=employeeId;
}

public java.lang.String getEmployeeId()
{
return this.employeeId;
}

public void setName(java.lang.String name)
{
this.name=name;
}

public java.lang.String getName()
{
return this.name;
}

public void setDesignation(DesignationInterface designation)
{
this.designation=designation;
}

public DesignationInterface getDesignation()
{
return this.designation;
}

public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}

public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}

public void setBasicSalary(java.math.BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}

public java.math.BigDecimal getBasicSalary()
{
return this.basicSalary;
}

public void setGender(GENDER gender)
{
if(gender==EmployeeInterface.MALE)
{
this.gender="M";
}
else
{
this.gender="F";
}
}

public String getGender()
{
if(this.gender=="M")
{
return "MALE";
}
return "FEMALE";
}

public void isIndian(boolean isIndian)
{
this.isIndian=isIndian;
}

public boolean isIndian()
{
return this.isIndian;
}

public void setPANNumber(java.lang.String PANNumber)
{
this.PANNumber=PANNumber;
}

public java.lang.String getPANNumber()
{
return this.PANNumber;
}

public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}

public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}

public boolean equals(Object object)
{
if(!(object instanceof EmployeeInterface)) return false;
EmployeeInterface employee=(EmployeeInterface)object;
return this.designation.getCode()==employee.getDesignation().getCode();
}
public int compareTo(EmployeeInterface employee)
{
return this.designation.getCode()-employee.getDesignation().getCode();
}

}