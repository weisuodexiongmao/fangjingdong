package bwe.com.bawei.jdcom.mine.model;

import com.bwie.testten.mine.LoginConstract;
import com.bwie.testten.mine.bean.LoginBean;
import com.bwie.testten.utils.ApiServer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/13.
 */

public class LoginModel implements LoginConstract.ILoginModel {
    @Override
    public void RequestData(String url, String mobile, String password, final LoginConstract.OnRequestListener onRequestListener) {

        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<LoginBean> getlg = apiServer.getlg("user/login", map);
        getlg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(loginBean.getMsg().equals("登录成功")){
                            LoginBean.DataBean data = loginBean.getData();
                            onRequestListener.OnSuccess(data);
                        }else{
                            onRequestListener.OnError(loginBean.getMsg());
                        }
                    }
                });
    }
}
