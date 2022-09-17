
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class UserInfoService {

    public List<User> getUserInfoList() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        //接続文字列
        String url = "jdbc:postgresql://localhost:5432/zh";
        String user = "postgres";
        String password = "postgres";
        List<User> userList = new ArrayList<>();
        try {
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String sql = "SELECT * from user_info;";
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while (rset.next()) {

                User u = new User();

                u.setUserNo(rset.getString(1));
                u.setName(rset.getString(2));
                u.setSex(rset.getString(3));
                u.setNumber(rset.getString(4));
                u.setAddress(rset.getString(5));
                u.setBirth(rset.getString(6));
                userList.add(u);
                System.out.println(u.toString());

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

}
