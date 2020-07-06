package JDBC;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  create by : gy
 *  2020/7/5
 *  des:        druid连接mysql连接和关闭
**/

public class Druid {
    private static  DruidDataSource dataSource = null;

    public Druid(){
    }

    static {
        try{
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/gy?useSSL=false&allowPublicKeyRetrieval=true");
            dataSource.setUsername("root");
            dataSource.setPassword("873802894");
            dataSource.setInitialSize(10);//初始大小
            dataSource.setMaxActive(50);//最大
            dataSource.setMinIdle(10);//最小
            dataSource.setMaxWait(5000);//最大连接等待超时
            dataSource.setValidationQuery("SELECT 1");//验证数据库是否连接
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){

        return dataSource;
    }

    public static Connection getConnection(){
            try{
                if(dataSource!=null) return dataSource.getConnection();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return null;
    }

    public static void closeAll(Connection con, PreparedStatement prep, ResultSet rset) throws SQLException {
            try{
                if(rset!=null) rset.close();
            }catch (SQLException e){
                e.printStackTrace();
            }try {
                if (prep!=null) prep.close();
            }catch (SQLException e){
                e.printStackTrace();
            }try {
                if(con!=null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(rset!=null)
                rset.close();
                prep.close();
                con.close();
        }
    }

}
