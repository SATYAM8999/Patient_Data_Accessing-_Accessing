
package DBEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CulpritFinder 
{
  public ArrayList<String> getDBMCredentials() 
  { 
      ArrayList<String> credential=new ArrayList<String>();
      try
      {
        String logfilepath="C:\\Users\\PC\\AppData\\Roaming\\MySQL\\Workbench\\log\\wb.log";
        FileReader fr=new FileReader(logfilepath);
        BufferedReader br=new BufferedReader(fr);
        String line=null;
        while((line=br.readLine())!=null)
        {
            if(line.contains("Opened connection"))
            {
                if(line.contains("_"))
                {
                    String t1[]=line.split("Opened connection '");
                    
                    String t2[]=t1[1].split("_");
                    String name=t2[0];
                    
                    credential.add(name);
                    String firstcharacter=name.substring(0, 1);
                    String password=firstcharacter.toUpperCase()+name.substring(1, name.length())+"@123";
                    credential.add(password);
                    System.out.println(credential);
                }
                else
                {
                    credential.add("root");
                    credential.add("NewPassword@123");
                }
            }
            
        }
      }
      catch(Exception ex)
      {
       System.out.println("Exception At CulpritFinder Class in Function getDBMCredentials()"+ex);           
      }
      return credential;
  }
}
