
package client_operation;

import db_ops.DBDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataView_Download 
{
    public ArrayList<String> getClientData(String clientname)
    {
        ArrayList<String> client_data=new ArrayList<String>();
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Select * from client_data_info where username='"+clientname+"'";
        
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           String filename=rs.getString("file_name");
           client_data.add(filename);
        }   
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DataView_Download Class in Function isClientDataUpdate()"+ex);
        }
       return client_data;
    }
    public String[] getColumnNames(String tablename)
    {
        String column_name[]=null;
        try
        {
          Statement st=new DBDriver().getDBStatement();
          String query="Select * from "+tablename;
            
          ResultSet rs=st.executeQuery(query);
            
          ResultSetMetaData rsmd=rs.getMetaData();
          int no_columns=rsmd.getColumnCount();
           
          column_name=new String[no_columns];
          for (int i = 0; i < no_columns; i++) 
            {
                column_name[i]=rsmd.getColumnName(i+1);
               
            }
   
            
        }catch(SQLException ex)
        {
            System.out.println("Exception At DataView_Download Class in Function getColumnNames()"+ex);
        }
       return column_name;
    }
    public String[][] getTableData(String table_name,int columncount)
    {
        String [][]datamatrix=null;
        try
        {
              Statement st1=new DBDriver().getDBStatement();
              Statement st2=new DBDriver().getDBStatement();
              String query="Select *from "+table_name;
              ResultSet rs1=st1.executeQuery(query);
              ResultSet rs2=st2.executeQuery(query);
              int rowcount=0;
              while(rs1.next())
                  rowcount++;
              datamatrix=new String[rowcount][columncount];
              int i=0;
              while(rs2.next())
              {
                   for(int j=0;j<columncount;j++)
                   {
                       datamatrix[i][j]=rs2.getString(j+1);
                    
                   }
                   i++;
              }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception At Class ClientViewDownloadDBO in Method getTableData()"+e);
        
        }
        return datamatrix;
    }
}
