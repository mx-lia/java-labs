package by.maximchikova.DB;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class DBConfig {

    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&createDatabaseIfNotExist=true", DBInfo.DbUsername, DBInfo.DbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBConfig() throws ClassNotFoundException {
    }

    public void checkDBExists(String checkDbName) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            while (resultSet.next()){
                if(resultSet.getString(1).equals(checkDbName.toLowerCase()))
                    return;
            }
            createDb();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createDb() throws SQLException {
        String scriptSqlPath = "D:\\STUDY\\JAVA\\JSF\\createDB.sql";

        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            BufferedReader reader = new BufferedReader(new FileReader(scriptSqlPath));
            scriptRunner.runScript(reader);

            System.out.println("DB created");

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}

