
package client_operation;

import db_ops.DBDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientDBOperations
{
    //client_name, type, type_name, email_id, mobile_no, password, user_name
   public boolean isClientRegistered(String client_name,String type,String type_name,String email_id, String mobile_no, String password,String user_name)
    {
       boolean flag=false;
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Insert Into client_info values('"+client_name+"','"+type+"','"+type_name+"','"+email_id+"','"+mobile_no+"','"+password+"','"+user_name+"')";
        
        int row=st.executeUpdate(query);
        if(row>0)
            flag=true;
        }
        catch(SQLException ex)
        {
            System.out.println("Exception At ClientDBOperations Class in Function isClientRegistered()"+ex);
        }
       return flag;
    } 
    public boolean isClientExisted(String user_name,String password)
    {
        boolean flag=false;
        try
        {
           Statement st=new DBDriver().getDBStatement();
           String query = "select * from client_info where password = '" + password + "' and user_name='"+user_name+"'";
           ResultSet rs=st.executeQuery(query);
           while(rs.next())
           {
               flag=true;
           }
        }
        catch(SQLException ex)
        {
           System.out.println("Exception At ClientDBOperations Class in Function isClientExisted()"+ex);      
        }
        return flag;
    }
    public ArrayList<String> isDataEdit(String client_username)
   {
     ArrayList<String> client_data=new ArrayList<>();
     try
     {
           Statement st=new DBDriver().getDBStatement();
           String query = "select * from client_info where user_name = '" + client_username + "'";
           ResultSet rs=st.executeQuery(query);
           while(rs.next())
           {
            //client_name, type, type_name, email_id, mobile_no, password, user_name
            String client_name =rs.getString("client_name");
            String type =rs.getString("type");
            String type_name =rs.getString("type_name");
            String email_id =rs.getString("email_id");
            String mobile_no =rs.getString("mobile_no");
            String password =rs.getString("password");
            String user_name =rs.getString("user_name");
            client_data.add(client_name);// 0 location of Array List
            client_data.add(type);//1 location of Array List
            client_data.add(type_name);// 2 location of Array List
            client_data.add(email_id);//3 location of Array List
            client_data.add(mobile_no);// 4 location of Array List
            client_data.add(password);//5 location of Array List
            client_data.add(user_name);//6 location of Array List
           }
           
     }
     catch(SQLException ex)
     {
         System.out.println("Exception At ClientDBOperations Class in Function isDataEdit()"+ex);
     }
     return client_data;
   }
     boolean isClientDataUpdate(String client_name,String type,String type_name,String email_id, String mobile_no, String password,String user_name)
   {
       boolean flag=false;
       try
       {
           //client_name, type, type_name, email_id, mobile_no, password, user_name
           Statement st=new DBDriver().getDBStatement();
           String query = "UPDATE client_info SET client_name = '"+client_name+"', type = '"+type+"' ,type_name = '"+type_name+"',email_id = '"+email_id+"' ,mobile_no = '"+mobile_no+"',password = '"+password+"'WHERE user_name = '" + user_name + "'";
           int row=st.executeUpdate(query);
           if(row>0)
           {
               flag=true;
           }
       }
       catch(SQLException ex)
       {
           System.out.println("Exception At DBUpdate Class in Function isStudentDataUpdate()"+ex);
       }
       return flag;
   }
    
}
