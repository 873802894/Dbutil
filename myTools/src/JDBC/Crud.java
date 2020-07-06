package JDBC;

import bean.StudentBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  create by : gy
 *  2020/7/5
 *  des:crud封装成通用
**/
public class Crud {

    public Crud(){
    }

/**
 *  create by : gy
 *  2020/7/5
 *  des:QueryRunner() 如果无参数，则需要手动事务提交    ；  如果有参数，则自动事务提交
 *  这里是数据库原子操作，为自动提交，事实上都应该手动提交，这样可以快速找到错误的地方
 *  dao调用此层
**/
    private QueryRunner qr = new QueryRunner(Druid.getDataSource());

/**
 *  create by : gy
 *  2020/7/5
 *  des:接受sql语句和参数进行"添加","删除","修改";
 *      根据返回值判断是否操作成功：-1失败 ； 1成功;
 *      一般用此操作一次sql
**/
    public int addDelEdit(String sql,Object[] param){
        int result = -1;
        try{
            result =qr.update(sql,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
        return result;
    }

/**
 *  create by : gy
 *  2020/7/5
 *  des:接受sql语句和参数进行ID查找，结果是一个对象，用list保存并返回
**/
    public List<StudentBean> findByID(String sql, Object id) {
        List<StudentBean> list = new ArrayList<>();
        try {
            StudentBean student =qr.query(sql, new BeanHandler<>(StudentBean.class),id);
            list.add(student);
        } catch (SQLException e){
            e.printStackTrace();
        }
         return list;
    }

/**
 *  create by : gy
 *  2020/7/5
 *  des:接受sql语句进行全部查找，结果是一个对象集List(Object)，直接返回List
**/
    public List<StudentBean> findAll(String sql){
        List<StudentBean> list = new ArrayList<>();
        try{
            list=  qr.query(sql, new BeanListHandler<>(StudentBean.class));
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return list;
    }


/**
 *  create by : gy
 *  2020/7/6
 *  des:接受增删查改的sql语句和参数数组，进行批量处理，
**/
    public boolean batch(String sql,Object[][] param){
        try{
            qr.batch(sql,param);
            return  true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        }
}
