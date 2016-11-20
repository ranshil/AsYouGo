import java.sql.*;
import java.util.Properties;

public class DB {

    public Connection conn = null;

    public DB() {
        try{
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","Aa123456");
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
