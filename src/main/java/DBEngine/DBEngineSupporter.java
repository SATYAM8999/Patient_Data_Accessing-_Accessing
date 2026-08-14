
package DBEngine;

import db_ops.DBDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBEngineSupporter 
{
   public ArrayList<String> getClientInfo()
    {
        ArrayList client_data=new ArrayList();
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Select * from client_data_info"; 
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
          ArrayList row=new ArrayList();
          row.add(rs.getString("username"));
          row.add(rs.getString("file_name"));
          client_data.add(row);
        }   
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DBEngineSupporter Class in Function getClientInfo()"+ex);
        }
       return client_data;
    } 
   public ArrayList<String> getColumnNames(String tablename)
    {
        
        ArrayList<String> columnname=new ArrayList<String>();
        
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Select * from "+ tablename;
      
        ResultSet rs=st.executeQuery(query);
  
        ResultSetMetaData rsmd=rs.getMetaData();
        int no_columns=rsmd.getColumnCount();
           
        
          for (int i = 0; i < no_columns; i++) 
            {
                String cn=rsmd.getColumnName(i+1);
                columnname.add(cn);
            }
   
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DBEngineSupporter Class in Function getColumnNames()"+ex);
        }
       return columnname;
    }
   
    public ArrayList<String> getCompleteTableDataforRoot(String tablename,int columncount)
    {
         ArrayList tabledata=new ArrayList();
        
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Select * from "+ tablename;
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           ArrayList rowdata=new ArrayList();
           for (int i = 1; i <= columncount; i++)
            {
                String data=rs.getString(i);
                rowdata.add(data);
            }
           tabledata.add(rowdata);
        }
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DBEngineSupporter Class in Function getCompleteTableData()"+ex);
        }
       return tabledata;
    }
   
   public ArrayList<String> getCompleteTableDataforCulprit(String tablename,int columncount,String username,String Password)
    {
         ArrayList tabledata=new ArrayList();
        
        try
        {
        Statement st=new DBDriver().getCulpritDBStatement(username, Password);
        String query="Select * from "+ tablename;
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           ArrayList rowdata=new ArrayList();
           for (int i = 1; i <= columncount; i++)
            {
                String data=rs.getString(i);
                rowdata.add(data);
            }
           tabledata.add(rowdata);
        }
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DBEngineSupporter Class in Function getCompleteTableData()"+ex);
        }
       return tabledata;
    }
   public String get_client_mailid(String client_name)
   {
       String email_id=null;
        
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Select * from client_info where user_name='"+client_name+"'";
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           email_id=rs.getString("email_id");
        }
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DBEngineSupporter Class in Function get_client_mailid()"+ex);
        }
       return email_id; 
   }
}
