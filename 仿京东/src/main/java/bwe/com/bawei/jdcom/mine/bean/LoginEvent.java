package bwe.com.bawei.jdcom.mine.bean;

/**
 * Created by Zhang on 2017/11/17.
 */

public class LoginEvent {
    private String username;

    public LoginEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
