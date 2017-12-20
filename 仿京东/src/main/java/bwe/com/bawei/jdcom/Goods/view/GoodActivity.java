package bwe.com.bawei.jdcom.Goods.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bwie.testten.Goods.GoodConstract;
import com.bwie.testten.Goods.adapter.GoodAdapter;
import com.bwie.testten.Goods.bean.GoodBean;
import com.bwie.testten.Goods.presenter.GoodPresenter;
import com.bwie.testten.R;
import com.bwie.testten.classify.bean.MessageEvent;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodActivity extends AppCompatActivity implements GoodConstract.IGoodView {

    @BindView(R.id.good_rcv)
    RecyclerView goodRcv;
    private GoodPresenter goodPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        goodPresenter = new GoodPresenter(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED , sticky = true)
    public void noEvent(MessageEvent messageEvent) {
        int cid = messageEvent.getCid();
        goodPresenter.LoadList(Api.BANNERURL,cid,1,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void ShowList(List<GoodBean.DataBean> list) {

        GoodAdapter goodAdapter = new GoodAdapter(list, GoodActivity.this);
        if(list!=null){
            goodRcv.setAdapter(goodAdapter);
            goodRcv.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(this, e);
        Log.e("哈哈哈哈哈哈哈哈哈哈哈哈", e);
    }
}
