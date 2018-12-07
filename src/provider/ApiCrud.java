package provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public boolean add_data_user(String username, String password , String email) {
        DB_connection db = new DB_connection();
        Connection con = db.get_Connection();
        try {
            String query = "INSERT INTO public.user (username,password,email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
            PreparedStatement post = con.prepareStatement(query);
            post.executeUpdate();
            post.close();
            db.close_Connection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public static void main(String[] args){
        ApiCrud crud = new ApiCrud();
        System.out.println(crud.add_data_user("aniss","dqouail1","aniss@gmail.com"));
    }

}
