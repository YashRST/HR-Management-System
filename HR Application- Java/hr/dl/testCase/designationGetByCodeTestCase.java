import com.thinking.machines.tmcommon.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.util.*;
class designationGetByCodeTestCasepsp
{
public static void main(String ss[])
{
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAOInterface designationDAO=new DesignationDAO();

System.out.println("------------------------- List ------------------------");
List<DesignationDTOInterface> designations=designationDAO.getAll();
for(DesignationDTOInterface designation:designations)
{
System.out.println(designation.getCode()+" -> "+designation.getTitle());
}

System.out.println("-----------------------Using GetByCode--------------------------------");
System.out.print("Enter Code : ");
DesignationDTOInterface designation=designationDAO.getByCode(Keyboard.getInt());
System.out.println(designation.getCode()+" -> "+designation.getTitle());

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}