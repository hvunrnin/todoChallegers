package login.loginspring.controller;


public class TodoForm {
    private String id;
    private String content;
    private String date;
    private String goalId;

    private String orderNum;
    public String getContent() {
        return content;
    }
    public String getDate() {
        return date;
    }

    public String getGoalId() {
        return goalId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
