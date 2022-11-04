package dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class H2Connect {
    private final static String DB_URL = "jdbc:h2:~/parcial;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        //return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return DriverManager.getConnection(DB_URL);
    }

}
