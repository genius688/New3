package image_submit;

public class item {
    byte[] item_file;
    String item_title;
    String item_layout;
    String item_rson;
    String item_date;
    String item_description;
    Boolean item_star;
    String item_type;
    item(byte[] f, String s1, String s2, String s3, String s4, String s5, Boolean b, String item_type){
        this.item_file = f;
        this.item_title = s1;
        this.item_layout = s2;
        this.item_rson = s3;
        this.item_date = s4;
        this.item_description = s5;
        this.item_star = b;
        this.item_type = item_type;
    }
}
