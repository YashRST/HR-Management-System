import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.util.*;
class designationAddTestCasepsp
{
public static void main(String ss[])
{
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();
System.out.print("Enter a designation Title : ");
String desTitle=Keyboard.getString();
designationDTO.setTitle(desTitle);
designationDAO.add(designationDTO);
List<DesignationDTOInterface> designations=designationDAO.getAll();
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