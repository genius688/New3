package in_out_rcd;

public class inoutClass {

    String time;
    String it_name;
    String user;
    String where;
    boolean inout;
    Integer Data;
    inoutClass(String time, String it_name, String user, String where, boolean inout, Integer Data){
        this.time = time;
        this.inout = inout;
        this.it_name = it_name;
        this.Data = Data;
        this.where = where;
        this.user = user;
    }
}
