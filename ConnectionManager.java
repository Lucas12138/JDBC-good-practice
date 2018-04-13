import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{

    private static ConnectionManager instance = null;

    private final String USERNAME = "*****";

    private final String PASSWORD = "*****";

    private final String M_CONN_STRING
        = "jdbc:mysql://rds-mysql-t*******************.amazonaws.com:3306/";

    private final String DBNAME = "*****";

    private Connection conn = null;

    private ConnectionManager()
    {
    }

    public static ConnectionManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private boolean openConnection()
    {
        try
        {
            conn = DriverManager.getConnection(M_CONN_STRING + DBNAME, USERNAME, PASSWORD);
            return true;
        }
        catch (SQLException e)
        {
            System.err.println(e);
            return false;
        }

    }

    public Connection getConnection()
    {
        if (conn == null)
        {
            if (openConnection())
            {
                System.out.println("Connection opened");
                return conn;
            }
            else
            {
                return null;
            }
        }
        return conn;
    }

    public void close()
    {
        System.out.println("Closing connection");
        try
        {
            conn.close();
            conn = null;
        }
        catch (Exception e)
        {
        }
    }

}