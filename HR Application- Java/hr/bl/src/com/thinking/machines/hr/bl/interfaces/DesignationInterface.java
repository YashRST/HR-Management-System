package com.thinking.machines.hr.bl.interfaces;
public interface DesignationInterface extends java.io.Serializable,Comparable<DesignationInterface>
{
public enum ATTRIBUTE{CODE,TITLE};
public static final ATTRIBUTE CODE=ATTRIBUTE.CODE;
public static final ATTRIBUTE TITLE=ATTRIBUTE.TITLE;
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}
