package bwe.com.bawei.jdcom.classify.bean;

/**
 * Created by Zhang on 2017/11/16.
 */

public class MessageEvent {
    private int cid;

    public MessageEvent(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
