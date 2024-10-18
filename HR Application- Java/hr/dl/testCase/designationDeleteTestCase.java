import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.util.*;
class designationDeleteTestCasepsp
{
public static void main(String ss[])
{
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();

System.out.println("------------------------- List before Deleting.------------------------");
List<DesignationDTOInterface> designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations)
{
System.out.println(designation.getCode()+" -> "+designation.getTitle());
}

System.out.print("Enter designation code : ");
designationDAO.delete(Keyboard.getInt());

System.out.println("------------------------- List after Deleting.------------------------");
designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations)
{
System.out.println(designation.getCode()+" -> "+designation.getTitle());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}