package chattingCircle;

public class ModuleItem {
    public enum Type { FIXED, CUSTOM }

    public final Type type;
    public final String imageUrl;
    public final String customText;
    public final String Title;
    public final String time;

    public ModuleItem(Type type, String imageUrl, String customText, String title, String Time) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.customText = customText;
        this.Title = title;
        this.time = Time;
    }
}