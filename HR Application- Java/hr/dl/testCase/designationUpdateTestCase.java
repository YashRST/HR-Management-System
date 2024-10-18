import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.util.*;
class designationUpdateTestCasepsp
{
public static void main(String ss[])
{
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();
System.out.println("------------------------- List Before Updating------------------------");
List<DesignationDTOInterface> designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations)
{
System.out.println(designation.getCode()+" -> "+designation.getTitle());
}

System.out.print("Enter Designation Code : ");
int code=Keyboard.getInt();
System.out.print("Enter Designation Title : ");
String title=Keyboard.getString();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designationDAO.update(designationDTO);

System.out.println("------------------------- List after Adding->Updating.------------------------");
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