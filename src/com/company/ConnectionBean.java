package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class ConnectionBean {

    private static Connection connection;
    private static final int timeout = 2;

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
        String nomeDB = "Todo";
        String host = "127.0.0.1:3306";
        String username = "cele";
        String password = "cele";



        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            path = "jdbc:mysql://" + host + "/" + nomeDB;

            Properties connectionProps = new Properties();
            connectionProps.put("user", "cele");
            connectionProps.put("password", "cele");
            connectionProps.put("useSSL","false");
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
        statement.execute(command);
        statement.close();
    }

    /*public static void eseguiComandoNoSynchro(String comando) throws SQLException {

        boolean connessione_aperta = false;

        connessione_aperta = openConnection();


        if(connessione_aperta == false){

            return;
        }

        Statement statement;

        statement = connection.createStatement();
        statement.execute(comando);
        statement.close();
    }
    public static void eseguiComandi(ArrayList<String> comandi) throws SQLException {

        boolean connessione_aperta = false;

        connessione_aperta = openConnection();


        if(connessione_aperta == false){

            return;
        }


        Statement statement;
        statement = connection.createStatement();

        for(String comando : comandi){

            statement.execute(comando);
        }

        statement.close();
    }

    */

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