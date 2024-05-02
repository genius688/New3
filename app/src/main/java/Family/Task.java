package Family;

import java.util.Objects;
public class Task {
    private int id; // 用于存储UUID
    private String date;
    private String title;
    private String content;
    private String person;
    private int state;

    // 构造函数
    public Task(int id,String date, String title, String content, String person,int state) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.person = person;
        this.state=state;
    }

    // getter和setter方法
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getPerson() {
        return person;
    }
    public int getState() {
        return state;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public void setPerson(String person) {
        this.person = person;
    }
    public void setState(int state) {
        this.state=state;
    }
    // 可以添加其他getter和setter方法

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, person, date,state);
    }
}