package provider;

import java.sql.Connection;
import java.sql.ResultSet;

public class ApiCrud {

    public ApiCrud(){
        System.out.println("connection base de donnée");
    }

    public boolean Verification_data_user(String username,String password){
        DB_connection db = new DB_connection();
        Connection con = db.get_Connection();
        try{
            String query = "select * from public.user where username='" + username + "' AND password='" + password + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            if(rs.next()){
                return true;
            }
            else
                return false;

        }catch (Exception e){
                System.out.println(e);
                return false;
        }
    }

    public static void main(String[] args){
        ApiCrud crud = new ApiCrud();
        System.out.println(crud.Verification_data_user("ouail","ouail1"));
    }

}
