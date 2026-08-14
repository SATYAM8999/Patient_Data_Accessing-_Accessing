
package db_ops;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBDriver 
{ 
       Statement st;
    public Connection con;
    public Statement getDBStatement()
    {  
        try
        {
           
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/db_security_tbm",
        "root",
        "NewPassword@123");
     
        st=con.createStatement();   
       
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            System.out.println("Exception At DBDriver Class in Function getDBStatement()"+ex);
        }
        return st;
    }
    
    
    public Statement getCulpritDBStatement(String username,String password)
    {  
        try
        {
           
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/db_security_tbm",
        username,
        password);
     
        st=con.createStatement();   
       
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            System.out.println("Exception At DBDriver Class in Function getCulpritDBStatement()"+ex);
        }
        return st;
    }
}
