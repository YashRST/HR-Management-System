package com.thinking.machines.hr.bl.exceptions;
import java.util.*;

public class BLException extends Exception
{

private String genericException;
private Map<String,String> exceptions;

public BLException()
{
this("");
}

public BLException(String genericException)
{
this.setGenericException(genericException);
this.exceptions=new HashMap<String,String>();
}

public boolean hasGenericException()
{
return this.genericException.length()!=0;
}

public String getGenericException()
{
return this.genericException;
}

public void setGenericException(String genericException)
{
if(genericException==null) this.genericException="";
else this.genericException=genericException.trim();
}

public void addException(String property,String exception)
{
if(exception==null || exception.trim().length()==0) this.exceptions.remove(property.trim());
else this.exceptions.put(property.trim(),exception.trim());
}

public void removeException(String property)
{
if(property==null || property.trim().length()==0) return;
this.exceptions.remove(property.trim());
}

public boolean hasException(String property)
{
return this.exceptions.containsKey(property);
}

public String getException(String property)
{
String exception=this.exceptions.get(property);
return (exceptions==null)?"":exception;
}

public void clearExceptions()
{
this.exceptions.clear();
this.genericException="";
}

public int getExceptionCount()
{
return (genericException.length()!=0)?this.exceptions.size()+1:this.exceptions.size();
}

public Set<String> getProperties()
{
return this.exceptions.keySet();
}

public List<String> getException()
{
List<String> exceptions=new ArrayList<String>(this.exceptions.values());
if(genericException.length()!=0) exceptions.add(0,genericException);
return exceptions;
}
}
