package login.loginspring.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class updateMember {
    @Id
    private String UpdateName; //업데이트 이름
    private String UpdateImg; //업데이트 프로필사진
    private String UpdateMessage; //업데이트 상태메시지


    public String getUpdateName() {
        return UpdateName;
    }

    public void setUpdateName(String updateName) {
        UpdateName = updateName;
    }

    public String getUpdateImg() {
        return UpdateImg;
    }

    public void setUpdateImg(String updateImg) {
        UpdateImg = updateImg;
    }

    public String getUpdateMessage() {
        return UpdateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        UpdateMessage = updateMessage;
    }
}
