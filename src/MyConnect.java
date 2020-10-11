
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnect {
    private final String className = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/new_schema";
    private final String user = "root";
    private final String pass = "anhvaem200119";
    private final String table = "new_schema.e_v_dict";

    private Connection connection;

    private void connect() {
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("connect success!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection!");
        } catch (SQLException throwables) {
            System.out.println("Class not found!");
        }
    }

    private void showData(ResultSet rs) {
            try {
                while (rs.next()) {
                    System.out.println(rs.getInt(1)
                    + " " + rs.getString(2)
                    + " " + rs.getString(3));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    private ResultSet getData() {
        ResultSet rs = null;
        String sqlCommand = "SELECT * FROM " + table;
        Statement st ;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException throwables) {
            System.out.println("select error \n" + throwables.toString());
        }
        return rs;
    }

    private ResultSet getDataTarget(String word) {
        ResultSet rs = null;
        String sqlCommand = "SELECT * FROM " + table + " WHERE `word` = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, word);
            rs = pst.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    private void deleteWord(String word) {
        String sqlCommand = "delete from " + table + " where `word` = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, word);
            if (pst.executeUpdate() > 0) {
                System.out.println("delete success!");
            } else System.out.println("delete error \n");
        } catch (SQLException throwables) {
            System.out.println("delete error \n" + throwables.toString());
        }
    }

    private void insert(int idx, String word, String detail) {
        String sqlCommand = "INSERT INTO " + table + " VALUE(?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, idx);
            pst.setString(2, word);
            pst.setString(3, detail);
            if (pst.executeUpdate() > 0) {
                System.out.println("insert success!");
            } else System.out.println("insert error \n");
        } catch (SQLException throwables) {
            System.out.println("insert error \n" + throwables.toString());
        }
    }

    private void updateWord(String word, String detail) {
            String sqlCommand = "UPDATE " + table + " SET `detail` = ? WHERE `word` = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, detail);
            pst.setString(2, word);
            if (pst.executeUpdate() > 0) {
                System.out.println("update success!");
            } else System.out.println("upadte error \n");
        } catch (SQLException throwables) {
            System.out.println("update error \n" + throwables.toString());
        }
    }

    public static void main(String[] args) {
        MyConnect myConnect = new MyConnect();
        myConnect.connect();
    //    myConnect.showData(myConnect.getDataTarget("abette"));
        myConnect.deleteWord("abridging");
        myConnect.updateWord("hello", "xin chào hihihi");
    }
}
