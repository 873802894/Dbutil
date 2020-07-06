package test;

import JDBC.LocalThread;
import bean.AccountBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountTest {


    public AccountBean queryAccount(int cardid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection con = LocalThread.getConnection();
        AccountBean account= queryRunner.query(con,"select * from account where cardid=?",new BeanHandler<AccountBean>(AccountBean.class),cardid);
        return account;
    }

    public void updateAccount(AccountBean account) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection con = LocalThread.getConnection();
        queryRunner.update(con,"update account set balance =? where cardid=?",new Object[]{account.getBalance(),account.getCardid()});
    }

    public void transfer(int fromcard,int tocard,int money) throws SQLException {
        try {
            LocalThread.beginTransaction();
            AccountBean from = queryAccount(fromcard);
            AccountBean to = queryAccount(tocard);
            from.setCardid(fromcard);
            to.setCardid(tocard);

            if (from.getBalance() >= money) {
                from.setBalance(from.getBalance() - money);
                to.setBalance(to.getBalance() + money);

                updateAccount(from);
                updateAccount(to);
                System.out.println("成功");
                LocalThread.commitTransaction();
            } else {
                System.out.println("余额不足");
            }
        }catch (Exception e){
            System.out.println("系统异常,操作失败");
            LocalThread.rollbackTransaction();
            e.printStackTrace();
        }finally {
            LocalThread.close();
        }

    }

}
