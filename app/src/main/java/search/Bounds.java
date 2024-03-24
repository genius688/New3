package search;

public class Bounds {

    private int index;
    private int top;  //初始位置
    private int height;
    private int targetTop;
    private int currentTop;  //现在位置
    private int lastCurrentTop;   //前一个动画结束后的位置

    public Bounds(int index, int bottom) {
        this.index = index;
        this.top = bottom;
    }

    public Bounds(int index) {
        this.index = index;
    }

    public int getTop() {
        return top;
    }

    public Bounds setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTargetTop() {
        return targetTop;
    }

    public void setTargetTop(int targetTop) {
        this.targetTop = targetTop;
    }

    public int getHeight() {
        return height;
    }

    public Bounds setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getCurrentTop() {
        return currentTop;
    }

    public void setCurrentTop(int currentTop) {
        this.currentTop = currentTop;
    }

    public int getLastCurrentTop() {
        return lastCurrentTop;
    }

    public void setLastCurrentTop(int lastCurrentTop) {
        this.lastCurrentTop = lastCurrentTop;
    }
}