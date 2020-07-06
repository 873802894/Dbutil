package bean;

public class StudentBean {
    private  int sid;
    private  String sname;

    public  StudentBean(int sid,String sname){
        this.sid = sid;
        this.sname  = sname;
    }

    public StudentBean(){

    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
