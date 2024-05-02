package in_out_rcd;

public class inoutClass {

    String day;
    String month;
    String time;
    String it_name;
    String user;
    String where;  //layout_name > room_name > stg_name


    inoutClass(String day, String month, String time, String it_name, String user, String where){
        this.day = day;
        this.month = month;
        this.time = time;
        this.it_name = it_name;
        this.where = where;
        this.user = user;
    }
}
