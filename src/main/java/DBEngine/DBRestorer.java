package DBEngine;

import db_ops.DBDriver;
import java.sql.Statement;
import java.util.ArrayList;

public class DBRestorer {

    public boolean isDBRestored(String pkey_field, String pkey_value, ArrayList tamper_columns, ArrayList orgdata, String tableName) {
        boolean flag = false;
        try {

            Statement st = new DBDriver().getDBStatement();
            String query1 = "update " + tableName + " set ";
            String query2 = "";
            for (int i = 0; i < tamper_columns.size(); i++) {
                String column_names = (String) tamper_columns.get(i);
                String column_org_value = (String) orgdata.get(i);
                String temp = column_names + "='" + column_org_value + "',";
                query2 = query2 + temp;

            }
            query2 = query2.substring(0, query2.length() - 1);
            String query3 = " where " + pkey_field + " = '" + pkey_value + "'";
            String final_query = query1 + query2 + query3;
            int nor = st.executeUpdate(final_query);
            if (nor > 0) {
                flag = true;
            }

        } catch (Exception e) {
            System.out.println("Exception at class DBRestorer and in method  isDBRestored() " + e);
        }
        return flag;
    }

}
