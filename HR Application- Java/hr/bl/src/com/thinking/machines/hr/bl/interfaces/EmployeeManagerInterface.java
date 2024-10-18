package com.thinking.machines.hr.bl.interfaces;

import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
public interface EmployeeManagerInterface
{
public void add(EmployeeInterface employee) throws BLException;
public void update(EmployeeInterface employee) throws BLException;
public void delete(String employeeId) throws BLException;
public EmployeeInterface getByEmployeeId(String employeeId) throws BLException;
public EmployeeInterface getByPANNumber(String panNumber) throws BLException;
public EmployeeInterface getByAadharCardNumber(String aadharCardNumber) throws BLException;
public List<EmployeeInterface> getAll() throws BLException;
public List<EmployeeInterface> getWithDesignation(DesignationInterface designation) throws BLException;
public int getCount() throws BLException;
public boolean employeeIdExists(String employeeId) throws BLException;
public boolean panNumberExists(String panNumber) throws BLException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException;
}