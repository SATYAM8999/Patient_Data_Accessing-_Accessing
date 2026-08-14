
package client_operation;

import db_ops.DBDriver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelOperation
{
   public ArrayList<String> getColumnName(String filepath)
   {
     ArrayList<String> column_name=new ArrayList<String>();
     try
     {
       File file=new File(filepath);  
       Workbook w=Workbook.getWorkbook(file);
       Sheet s=w.getSheet(0);
       int row=s.getRows();
       int column=s.getColumns();
         for (int i = 0; i <column; i++) 
         {
             Cell c=s.getCell(i, 0);
             String content=c.getContents().trim();
             column_name.add(content);
         }
         w.close();
     }
     catch(Exception ex)
     {
         System.out.println("Exception At ExcelOperation Class in Function getColumnName()"+ex);
     }
     return column_name;
   } 
   public boolean isTableCreated(String tablename,ArrayList<String> column_name)
   {
     boolean flag=false;
     try
     {
       Statement st=new DBDriver().getDBStatement();
       String query1="Create table IF NOT EXISTS "+tablename+"(";
         String query2="";
         for (int i = 0; i <column_name.size(); i++) 
         {
            String column_names=column_name.get(i);
            query2=query2+column_names+" VARCHAR(145),";
           
         }
         String query3="PRIMARY KEY("+column_name.get(0)+"))";     
         String Final_query=query1+query2+query3;
         st.executeUpdate(Final_query);
         flag=true;
     }
     catch(Exception ex)
     {
         System.out.println("Exception At ExcelOperation Class in Function isTableCreated()"+ex);
     }
     return flag;
   } 
    public boolean isDataInsert(String filepath,String tablename,ArrayList<String> column_name)
   {
     boolean flag=false;
     try
     {
      //Insert into stud(roll,name,percentage) values(?,?,?);
         String query1="Insert into "+tablename+"(";
         String query2="";
         String query3=") values(";
         for (int i = 0; i <column_name.size(); i++) 
         {
           String single_column_name=column_name.get(i);
           query2=query2+single_column_name+" ,";
           query3=query3+"? ,";
                   
         }
         String query2a=query2.substring(0, query2.length()-2);// for remove last "," 
         String query3a=query3.substring(0, query3.length()-2);// for remove last "," 
         String finalquery=query1+query2a+query3a+");";
        
         DBDriver Db=new DBDriver();
         Db.getDBStatement();
         Connection con=Db.con;
         PreparedStatement ps=con.prepareStatement(finalquery);
         
         File file = new File(filepath);
         Workbook w = Workbook.getWorkbook(file);
         Sheet s = w.getSheet(0);
         int row = s.getRows();
         int column = s.getColumns();
         
         for (int i = 1; i < row; i++) 
         {
           
             for (int j = 0; j < column; j++) 
             {
                 Cell c1 = s.getCell(j, i);
                 String content = c1.getContents().trim();
                 ps.setString(j+1, content);
             }
             ps.addBatch();
         }
         ps.executeBatch();
         w.close();
         con.close();
         flag=true;
         return flag;
     }
     catch(Exception ex)
     {
         System.out.println("Exception At ExcelOperation Class in Function isDataInsert()"+ex);
     }
     return flag;
   } 
     public boolean isClientDataUpdate(String user_name,String file_name,String date_time)
    {
        //username, file_name, date_ time
       boolean flag=false;
        try
        {
        Statement st=new DBDriver().getDBStatement();
        String query="Insert Into client_data_info values('"+user_name+"','"+file_name+"','"+date_time+"')";
        int row=st.executeUpdate(query);
        if(row>0)
            flag=true;
        }
        catch(SQLException ex)
        {
            System.out.println("Exception At ClientDBOperations Class in Function isClientDataUpdate()"+ex);
        }
       return flag;
    }
     public boolean isFileDownloaded(ArrayList download_data,String download_path)
  {
      boolean flag=false;
      try
      {
          File file=new File(download_path);
          WritableWorkbook wwb=Workbook.createWorkbook(file);
          WritableSheet wsh=wwb.createSheet("Sheet 1", 0);
          WritableFont cellfont=new WritableFont(WritableFont.TIMES,11);
          WritableCellFormat cellformat=new WritableCellFormat(cellfont);
          Label label=null;
          for (int i = 0; i <download_data.size(); i++) 
          {
           ArrayList singlerow=(ArrayList)download_data.get(i);
              for (int j = 0; j <singlerow.size(); j++) 
              {
               String cell_content=(String)singlerow.get(j);
               label=new Label(j,i,cell_content,cellformat);
               wsh.addCell(label);
               
              }
          }
          wwb.write();
          wwb.close();
          flag=true;
      }
      catch(Exception ex)
      {
          System.out.println("Exception at Classs ExcelOperations in a Method isFileDownloaded "+ex);
      }
        return flag;
  }
}
