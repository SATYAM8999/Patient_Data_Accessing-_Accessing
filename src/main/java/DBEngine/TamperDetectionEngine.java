
package DBEngine;

import AdminOperation.DBTemperDetectionFrame;
import MailSender.SendEmail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TamperDetectionEngine extends Thread
{
    public String clientname=null;
    public String tablename=null;
    public boolean flag;

    public void run()
    {
      DBEngineSupporter dnes=new DBEngineSupporter();
      ArrayList<String> column_name=dnes.getColumnNames(tablename);
      System.out.println("Table Names :"+tablename);
       System.out.println("Column Names :"+column_name);
       ArrayList<String> original_data=dnes.getCompleteTableDataforRoot(tablename, column_name.size());
        String email_id=dnes.get_client_mailid(clientname);
        System.out.println("CLIENT MAIL ID :"+email_id);
        while(flag)
        {
            try
            {
                Thread.sleep(5000);
                 Date dt = new Date();
                 SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:YYYY hh:mm:ss");
                 String current_time = sdf.format(dt);
                 ArrayList creadential=new CulpritFinder().getDBMCredentials();
                 String username=(String) creadential.get(0);
                 String password=(String) creadential.get(1);
                 if(!username.equals("root"))
                 {
                     ArrayList used_data = dnes.getCompleteTableDataforCulprit(tablename, column_name.size(),username,password);
                     if (!original_data.equals(used_data)) 
                     {
                          ArrayList result=new TemperedFieldNameIdentify().getTemperedFieldName(original_data, used_data, column_name);
                          for (int i = 0; i < result.size(); i++)
                          {
                              ArrayList tempered_row = (ArrayList) result.get(i);
                              String id = (String) tempered_row.get(0);
                              ArrayList field_name = (ArrayList) tempered_row.get(1);
                              ArrayList field_value = (ArrayList) tempered_row.get(2);
                              String pkey_field = column_name.get(0);
                              if (new DBRestorer().isDBRestored(pkey_field, id, field_name, field_value, tablename)) 
                              {
                                  String message1 = "ALRET !!! TAMPER HAS BEEN DETECTED AT TABLE : " + tablename.toUpperCase() + " of client : " + clientname.toUpperCase() + " at time : " + current_time;
                                  String message2 = "By the DB Manager "+username.toUpperCase()+ " On primary key " + column_name.get(0).toUpperCase() + " : " + id + " at following : " + field_name + " DATA BASE SUCCESSFULLY RESTORED !!!";

                                  String finaltextmessage = message1 +"\n"+ message2;
                                  String temp = DBTemperDetectionFrame.jTextArea1.getText();
                                  temp = temp + "\n" + finaltextmessage + "\n" + "-------------------------------------------------------------------------------------------" + "\n";
                                  DBTemperDetectionFrame.jTextArea1.setText(temp);
                                  String emailmessage1="Dear "+clientname.toUpperCase()+"\n";
                                  String emailmessage2=finaltextmessage;
                                  String emailmessage3="Thanks & Regards \n AUTOMATIC DATA TAMPERING SYSTEM";
                                  
                                  String finalemailmessage=emailmessage1+"\n"+emailmessage2+"\n\n"+emailmessage3;
                                  new SendEmail().isEmailSent(finalemailmessage, email_id);
                          }
                        
                          }
                          } else
                     {
                         System.out.println("DataBase Visited Table " + tablename + " For Client " + clientname + " At " + current_time + " Data Is Safe \n");

                     }
                 }
                
            }
            catch(Exception ex)
            {
                System.out.println("Exception At TamperDetectionEngine Class in Function run()"+ex);
            }
        }
    }
    
}
