package login.loginspring.controller;

public class GoalForm {

    private String category;
    private String id;
    private String delete;

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }
}
