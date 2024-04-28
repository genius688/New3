package search;

import android.graphics.Bitmap;

public class itemClass {
    public Integer it_id;
    public String best_before;
    public Integer stg_id;
    public Boolean it_fav;
    public String it_name;
    public Bitmap it_img;
    public String description_;
    public Integer rom_id;

    public itemClass(Integer it_id, String best_before, Integer stg_id, Boolean it_fav, String it_name, Bitmap it_img, String description_, Integer rom_id){
        this.it_id = it_id;
        this.best_before = best_before;
        this.stg_id = stg_id;
        this.it_fav = it_fav;
        this.it_name = it_name;
        this.it_img = it_img;
        this.description_ = description_;
        this.rom_id = rom_id;
    }
}
