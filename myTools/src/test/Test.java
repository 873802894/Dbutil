package test;

import JDBC.Crud;
import bean.StudentBean;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {

        {
//        String sql = "insert into student(sid,sname) values(?,?);";
//        Object[] param = {2,"xxx"};
//        Crud crud = new Crud();
//        int res = crud.addDelEdit(sql,param);
//        System.out.println(res);


//            String sql = "select * from student where sid=?";
//            int sid=1;
//            Crud crud = new Crud();
//            List<StudentBean> list = new ArrayList<>();
//            list = crud.findByID(sql,sid);
//            System.out.println(list.get(0).getSid());
//            System.out.println(list.get(0).getSname());

//        String sql = "select * from student;";
//        Crud crud = new Crud();
//        List<StudentBean> list = new ArrayList<>();
//        list = crud.findAll(sql);
//        for(StudentBean n:list){
//            System.out.println(n.getSid()+n.getSname());
//        }

//            Crud crud = new Crud();
//            String sql = "delete from student where sid=?";
//            Object[][] param= new Object[1][];
//            for(int i=10;i<=20;i++) {
//                param[0][]
//            }
//            boolean flag=crud.batch(sql,param);
//            if(flag) System.out.println("chenggong");
//            else {
//                System.out.println("shibai");
//            }
//    }
        }

        AccountTest accountTest = new AccountTest();
        accountTest.transfer(1,2,2000);

    }
}