import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.util.*;
class designationExistsTestCasepsp
{
public static void main(String ss[])
{
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();
System.out.println("1. Enter code ");
System.out.println("2. Enter title ");
System.out.print("Enter your Choice  : ");
int ch=Keyboard.getInt();
if(ch==1)
{
System.out.print("Enter code : ");
System.out.println("Exists : "+designationDAO.exists(Keyboard.getInt()));
}
if(ch==2)
{
System.out.print("Enter title : ");
System.out.println("Exists : "+designationDAO.exists(Keyboard.getString()));
}
System.out.println("Number of designations : "+designationDAO.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}