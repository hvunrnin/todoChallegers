package login.loginspring.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "todos")
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private Integer goalId;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer orderNum;
    private String content;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int isRepeatMon;
    private int isRepeatTue;
    private int isRepeatWed;
    private int isRepeatThu;
    private int isRepeatFri;
    private int isRepeatSat;
    private int isRepeatSun;
    private int isChecked;

    private String repeat_monthly;

    public String getRepeat_monthly() {
        return repeat_monthly;
    }

    public void setRepeat_monthly(String repeat_monthly) {
        this.repeat_monthly = repeat_monthly;
    }

    public Integer getId() {return id;}

    public String getUserId() {return userId;}

    public Integer getGoalId() {return goalId;}

    public Date getDate() {return date;}

    public Integer getOrderNum() {return orderNum;}

    public String getContent() {return content;}

    public Date getStartDate() {return startDate;}

    public Date getEndDate() {return endDate;}

    public int getIsRepeatMon() {return isRepeatMon;}

    public int getIsRepeatTue() {return isRepeatTue;}

    public int getIsRepeatWed() {return isRepeatWed;}

    public int getIsRepeatThu() {return isRepeatThu;}

    public int getIsRepeatFri() {return isRepeatFri;}

    public int getIsRepeatSat() {return isRepeatSat;}

    public int getIsRepeatSun() {return isRepeatSun;}

    public int getIsChecked() {return isChecked;}

    public void setId(Integer id) {this.id = id;}

    public void setUserId(String userId) {this.userId = userId;}

    public void setGoalId(Integer goalId) {this.goalId = goalId;}

    public void setDate(Date date) {this.date = date;}

    public void setOrderNum(Integer orderNum) {this.orderNum = orderNum;}

    public void setContent(String content) {this.content = content;}

    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public void setIsRepeatMon(int isRepeatMon) {this.isRepeatMon = isRepeatMon;}

    public void setIsRepeatTue(int isRepeatTue) {this.isRepeatTue = isRepeatTue;}

    public void setIsRepeatWed(int isRepeatWed) {this.isRepeatWed = isRepeatWed;}

    public void setIsRepeatThu(int isRepeatThu) {this.isRepeatThu = isRepeatThu;}

    public void setIsRepeatFri(int isRepeatFri) {this.isRepeatFri = isRepeatFri;}

    public void setIsRepeatSat(int isRepeatSat) {this.isRepeatSat = isRepeatSat;}

    public void setIsRepeatSun(int isRepeatSun) {this.isRepeatSun = isRepeatSun;}

    public void setIsChecked(int isChecked) {this.isChecked = isChecked;}



    public String toString(){
        String result = "id:" + this.id +"\n" + "userId:"+this.userId+"\n"+"goalId:"+this.goalId+"\n"
                +"date:"+this.date+"\n"+"orderNum:"+this.orderNum+"\n"+"content:"+this.content+"\n"+
                "startDate:"+this.startDate+"\n"+"endDate:"+this.endDate+"\n"+"isRepeatMon:"+this.isRepeatMon+'\n'+
                "isRepeatTue:"+this.isRepeatTue+"\n"+"isRepeatWed:"+this.isRepeatWed+"\n"+"isRepeatThu:"+this.isRepeatThu+"\n"+
                "isRepeatFri:"+this.isRepeatFri+"\n"+"isRepeatSat:"+this.isRepeatSat+"\n"+"isRepeatSun:"+this.isRepeatSun+"\n"+
                "isChecked:"+this.isChecked+"\n";
        return result;
    }
}
