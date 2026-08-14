/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBEngine;

import java.util.ArrayList;


public class TemperedFieldNameIdentify 
{
     public ArrayList getTemperedFieldName(ArrayList originallist,ArrayList currentlist,ArrayList<String> column)
     {
        ArrayList temperedfield=new ArrayList();
         for (int i = 0; i < originallist.size(); i++) 
         {
           ArrayList orirow=(ArrayList)originallist.get(i);
           ArrayList currow=(ArrayList)currentlist.get(i);
            ArrayList result=new ArrayList();
            if(!orirow.equals(currow))
            {
                System.out.println("Modified Row :"+currow);
                String id = (String) currow.get(0);
                ArrayList fieldname=new ArrayList();
                ArrayList fieldvalue=new ArrayList();
                for (int j = 1; j < orirow.size(); j++) 
                {
                    String orivalue = (String) orirow.get(j);
                    String curvalue = (String)  currow.get(j);
                    if(!orivalue.equals(curvalue))
                    {
                     fieldname.add(column.get(j));
                     fieldvalue.add(orivalue);
                    }
                }
                result.add(id);
                result.add(fieldname);
                result.add(fieldvalue);
                temperedfield.add(result);
            }
         }    
              
                
        return temperedfield;        
     }
}
