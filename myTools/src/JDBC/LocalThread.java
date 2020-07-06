package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *  create by : gy
 *  2020/7/6
 *  des:对于一些事务，如AB之间转账属于同一个事务，应该在同一个connection里进行，利用ThreadLocal复制一个
 *  connection，手动提交，对A和B的操作在同一次连接中，不存在AB两个连接中其中一个错误而另一个成功，
 *  service调用此层
**/
public class LocalThread {

    private static ThreadLocal<Connection> t = new ThreadLocal<>();

/**
 *  create by : gy
 *  2020/7/6
 *  des:从ThreadLocal中获取一个事务中的连接，如果没有，则从数据源新建一个并放置ThreadLocal中
**/
    public static Connection getConnection() throws SQLException {
        Connection con = t.get();
        if(con == null){
            con = Druid.getDataSource().getConnection();
            t.set(con);
        }
        return con;
    }

/**
 *  create by : gy
 *  2020/7/6
 *  des:开启事务
**/
    public static void beginTransaction() throws SQLException {
        Connection con = getConnection();
        con.setAutoCommit(false);
    }

/**
 *  create by : gy
 *  2020/7/6
 *  des:手动提交
**/
    public static void commitTransaction() throws SQLException {
        Connection con = getConnection();
        if(con != null)
        con.commit();

    }

/**
 *  create by : gy
 *  2020/7/6
 *  des:错误回滚
**/
    public static void rollbackTransaction() throws SQLException {
        Connection con = getConnection();
        if(con != null)
        con.rollback();

    }

/**
 *  create by : gy
 *  2020/7/6
 *  des:关闭connection，释放ThreadLocal
**/
    public static void close() throws SQLException {
        Connection con = getConnection();
        if(con!=null)
            con.close();
        t.remove();
        con = null;
    }
}
/**
 *  create by : gy
 *  2020/7/6
 *  des: 一个操作需要先begin事务，然后进行相关的数据库业务逻辑，成功则commit，期间报错则rollback，最后关闭连接
**/