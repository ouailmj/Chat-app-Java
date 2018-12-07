package provider;

import java.sql.Connection;
import java.sql.ResultSet;

public class ApiCrud {

    public ApiCrud(){
        System.out.println("connection base de donnée");
    }

    public String[] get_data_user(String username, String password){

        DB_connection db = new DB_connection();
        Connection con = db.get_Connection();
        String[] data = new String[8];
        try{
            String query = "select * from public.user where username='" + username + "' AND password='" + password + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            if(rs.next()){
                    data[0] = rs.getString("id");
                    data[1] = rs.getString("username");
                    data[2] = rs.getString("password");
                    data[3] = rs.getString("email");
                    data[4] = rs.getString("nom");
                    data[5] = rs.getString("sexe");
                    data[6] = rs.getString("photo");
                    data[7] = rs.getString("birth");
                return data;
            }
            else{
                return null;
            }
        }catch (Exception e){
                System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args){
        ApiCrud crud = new ApiCrud();
        System.out.println(crud.get_data_user("ouail","dqouail1"));
    }

}
