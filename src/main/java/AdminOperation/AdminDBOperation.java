
package AdminOperation;

import db_ops.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDBOperation
{
    public boolean isAdminExisted(String username,String password)
    {
        boolean flag=false;
        try
        {
           Statement st=new DBDriver().getDBStatement();
           String query = "select * from admin_info where password = '" + password + "' and username='"+username+"'";
           ResultSet rs=st.executeQuery(query);
           while(rs.next())
           {
               flag=true;
           }
        }
        catch(Exception ex)
        {
           System.out.println("Exception At AdminDBOperation Class in Function isAdminExisted()"+ex);      
        }
        return flag;
    }
     public ArrayList<String> isDataEdit(String user_name)
   {
     ArrayList<String> data=new ArrayList<String>();
     try
     {
           Statement st=new DBDriver().getDBStatement();
           String query = "select * from admin_info where username = '" + user_name + "'";
           ResultSet rs=st.executeQuery(query);
           while(rs.next())
           {
            //username, password
            String username =rs.getString("username");
            String password =rs.getString("password");
            data.add(username);//0 location of Array List
            data.add(password);//1 location of Array List
           }
           
     }
     catch(Exception ex)
     {
         System.out.println("Exception At AdminDBOperation Class in Function isDataEdit()"+ex);
     }
     return data;
   }
     boolean isProfileUpdate(String username,String password)
   {
       boolean flag=false;
       try
       {
          // password, user_name
           Statement st=new DBDriver().getDBStatement();
           String query = "UPDATE admin_info SET password = '"+password+"'WHERE username = '" + username + "'";
           int row=st.executeUpdate(query);
           if(row>0)
           {
               flag=true;
           }
       }
       catch(Exception ex)
       {
           System.out.println("Exception At AdminDBOperation Class in Function isProfileUpdate()"+ex);
       }
       return flag;
   }
}
