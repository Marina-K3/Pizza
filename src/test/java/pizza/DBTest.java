//package pizza;
//
//import static org.junit.Assert.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.aspectj.bridge.MessageUtil.fail;
//
//public class DBTest {
//    private Connection connection;
//
//    @Before
//    public void setUp() throws Exception {
//        String url = "jdbc:mysql://localhost:3306/pizza";
//        String user = "root";
//        String password = "Marin2003#";
//
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            fail("Unable to connect to database");
//        }
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        if (connection != null) {
//            connection.close();
//        }
//    }
//
//    @Test
//    public void testConnection() throws Exception {
//        assertTrue(connection.isValid(5));
//    }
//}
