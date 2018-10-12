package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DB_Connector {

    private static Connection connection;
    private static final int timeout = 2;
    
    //change username and password
    private static final String MYSQL_USERNAME = "cele";
    private static final String MYSQL_PASSWORD = "cele";
    private static final String host = "127.0.0.1:3306";
    

    private static boolean openConnection() {

        if(connection != null){
            try {
                if(connection.isValid(timeout)){

                    return true;
                }
            } catch (SQLException ex) { //problems with connection

                System.out.println(ex.getMessage());
            }
        }

        String path;
        String db_name = "Todo";
        


        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            path = "jdbc:mysql://" + host + "/" + db_name;

            Properties connectionProps = new Properties();

            connectionProps.put("user", MYSQL_USERNAME);
            connectionProps.put("password", MYSQL_PASSWORD);

            connectionProps.put("serverTimezone","UTC");

            connection = DriverManager.getConnection(path, connectionProps);
            

            return true;
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return false;
        }
    }

    public static void runCommand(String command) throws SQLException {

        boolean connection_open = false;

        connection_open = openConnection();


        if(connection_open == false){

            return;
        }


        Statement statement;

        statement = connection.createStatement();
        System.out.println(command);
        statement.execute(command);
        statement.close();
    }


    public static int insertAndRetreiveIntKey(String command) throws SQLException {

        boolean connection_open = false;

        connection_open = openConnection();


        if(connection_open == false){

            return -1;
        }

        int key = -1;

        Statement statement;

        statement = connection.createStatement();

        statement.execute(command, Statement.RETURN_GENERATED_KEYS);

        ResultSet r = statement.getGeneratedKeys();

        while(r.next()) {
            key = r.getInt(1);
        }

        r.close();
        statement.close();

        return key;
    }

    public static ResultSet executeQuery(String query) {

        boolean connection_open = false;

        connection_open = openConnection();

        if(connection_open == false){

            System.out.println("problems with connection");
            return null;
        }

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {

            result = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public static void closeConnection() {

        try {
            if(connection != null){
                if(!connection.isClosed()) {

                    connection.close();
                }
            }
        }
        catch(SQLException ex) {
        }
    }


}