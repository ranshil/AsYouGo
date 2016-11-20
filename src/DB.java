import java.sql.*;
import java.util.Properties;

public class DB {

    public Connection conn = null;

    public DB() {
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/Crawler";
//            conn = DriverManager.getConnection(url, "root", "admin213");
//            System.out.println("conn built");

            String url = "jdbc:postgresql://localhost:5432/postgres";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","1234");
            //props.setProperty("ssl","true");
            conn = DriverManager.getConnection(url, props);

//            String url = "jdbc:postgresql://localhost:5432/AsYouGo?user=fred&password=secret&ssl=true";
//            Connection conn = DriverManager.getConnection(url);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet runSql(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }

    public boolean runSql2(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.execute(sql);
    }

    protected void finalize() throws Throwable {
        if( conn != null || !conn.isClosed() ){
            conn.close();
        }
    }
}
