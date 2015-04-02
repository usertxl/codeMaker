package org.gaofeng.common.db;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  

import org.gaofeng.common.CommonFunction;
import org.gaofeng.common.properties.PropertiesTool;
/**
 * 数据库操作对象
 * @author gf
 *
 */
public class DBHelper extends CommonFunction{  
    public static final String url = PropertiesTool.URL;  
    public static final String name = getName(PropertiesTool.TYPE); ;  
    public static final String user = PropertiesTool.USER;  
    public static final String password = PropertiesTool.PASSWORD;  
    public static final int poolSize=PropertiesTool.POOLSIZE;
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) throws ClassNotFoundException, SQLException {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
    }  
  
    /**
     * 获得数据库连接名
     * @param type
     * @return
     */
    private static String getName(String type) {
		if(MYSQL.equals(type)){
			return "com.mysql.jdbc.Driver";
		}else if(ORACLE.equals(type)){
			return "oracle.jdbc.driver.OracleDriver";
		}
		return null;
	}

    /**
     * 释放数据库连接
     * @throws SQLException
     */
	public void close() throws SQLException {  
            this.conn.close();  
            this.pst.close();  
    }  
    
}  