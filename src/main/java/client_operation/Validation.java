
package client_operation;


public class Validation 
{
   public String isEmailValid(String email_id) 
   {
      String result="";
      if(!email_id.contains("@") && !email_id.contains("."))
        result=" . And @ Both Are Missing in Your Email Id!!!";
      else if(!email_id.contains("@"))
        result="@ is Missing in Your Email Id!!!";
      else if(!email_id.contains("."))
        result=". is Both Are Missing in Your Email Id!!!";      
      else
          result="perfect";
      
     return result;
   }
   
   public String isMobileNoValid(String mobile_no)
   {
      String result="";
      if(mobile_no.isEmpty())
        result="Please Enter Mobile Number!!!";
      else if(mobile_no.length()!=10)
        result="Entered Mobile Number Does Not contain 10 Digits!!! \n Please Re Enter It";
      else if(!isDigits(mobile_no))
        result="Entered Mobile Number Does Not contain all Digits!!! \n Please Re Enter It"; 
      else 
        result="perfect";
      
    return result;  
   }
   
   
   boolean isDigits(String mobile_num)
   {
       boolean flag=false;
       try
       {
          Long.parseLong(mobile_num);
          flag=true;
       }catch(Exception ex)
       {
           System.out.println("Exception At Validation Class in Function isDigits()"+ex);
       }
       return flag;
   }
}