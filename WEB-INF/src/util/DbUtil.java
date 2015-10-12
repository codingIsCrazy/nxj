/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
        public static final String MASTER = "gwb"; // 主库名字
        public static final String SLAVE = "gwb";
        public static Context ctx;
        static {
                try {
                        Context initContext = new InitialContext();
                        ctx = (Context) initContext.lookup("java:comp/env");
                } catch (NamingException e) {
                        e.printStackTrace();
                }
        }

        public static Connection getConnection(String dbName) {
                try {
                    DataSource ds = (DataSource) ctx.lookup("jdbc/" + dbName);
                    Connection conn = ds.getConnection();
                    return conn;
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (NamingException e) {
                        e.printStackTrace();
                }
                return null;
        }

        public final static void close(Statement stmt) {
                if (stmt == null)
                        return;
                try {
                        stmt.close();
                        stmt=null;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
        
        public final static void closePs(PreparedStatement stmt) {
            if (stmt == null)
                    return;
            try {
                    stmt.close();
                    stmt=null;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }

        public final static void close(ResultSet rs) {
                if (rs == null)
                        return;
                try {
                        rs.close();
                        rs=null;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public final static void close(Connection conn) {
                if (conn == null)
                        return;
                try {
                        conn.close();
                        conn=null;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                conn = null;
        }

        public final static void close(ResultSet rs, Statement stmt) {
                close(rs);
                close(stmt);
        }

        public final static void close(ResultSet rs, Statement stmt, Connection conn) {
                close(rs);
                close(stmt);
                close(conn);
        }
        
        public final static void close(PreparedStatement ps){
        	closePs(ps);
        }
}
