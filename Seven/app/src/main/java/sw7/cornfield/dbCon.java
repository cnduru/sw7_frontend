package sw7.cornfield;
import java.sql.*;

/**
 * Created by Casper on 15-09-2014.
 */
public class dbCon {
    String url;
    public Connection DBC;
    public dbCon() {
        //url = "jdbc:postgresql://pghost:5432/pgdatabase" + "?sslfactory=org.postgresql.ssl.NonValidatingFactory" +"&ssl=true";
        //url = "jdbc:postgresql://localhost:5432/cornfieldDB";
        url = "jdbc:postgresql://192.168.1.206:5432/cornfieldDB";
        //url = "jdbc:postgresql://127.0.0.1:32/cornfieldDB";
        try {
            Class.forName("org.postgresql.Driver");
            DBC = DriverManager.getConnection(url, "postgres", "casper");
        } catch(Exception e) {
            System.out.println("Connection didnt go so good");
            System.out.println(e.toString());
        }
    }



    public void Close() throws SQLException {
        DBC.close();
    }
}
