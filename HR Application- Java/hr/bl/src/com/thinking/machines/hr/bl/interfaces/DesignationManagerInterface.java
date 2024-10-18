package com.thinking.machines.hr.bl.interfaces;

import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;

public interface DesignationManagerInterface
{
public void add(DesignationInterface designation) throws BLException;
public void update(DesignationInterface desigantion) throws BLException;
public void delete(int code) throws BLException;
public int getCount();
public List<DesignationInterface> getDesignations(DesignationInterface.ATTRIBUTE ...orderBy);
public DesignationInterface getByCode(int code) throws BLException;
public DesignationInterface getByTitle(String title) throws BLException;
public boolean codeExists(int code);
public boolean titleExists(String title);
public boolean isAttachedToAnEmployee(int code);
public int getEmployeesCountWithDesignation(int code);
}
