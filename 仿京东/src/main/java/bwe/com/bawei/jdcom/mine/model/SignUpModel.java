package bwe.com.bawei.jdcom.mine.model;

import com.bwie.testten.mine.SignUpConstract;
import com.bwie.testten.mine.bean.SignUpBean;
import com.bwie.testten.utils.ApiServer;
import com.bwie.testten.utils.RetroFactory;

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

public class SignUpModel implements SignUpConstract.ISignUpModel {

    @Override
    public void RequestData(String url, String username, String password, String password_confirm, final SignUpConstract.OnRequestListener onRequestListener) {
        Map<String,String> map = new HashMap<>();
        map.put("mobile",username);
        map.put("password",password);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<SignUpBean> getsup = apiServer.getsup("user/reg", map);
        getsup.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SignUpBean signUpBean) {
                        if(signUpBean.getCode()==""+0){
                            onRequestListener.OnSuccess();
                        }else{
                            onRequestListener.OnError(signUpBean.getMsg());
                        }
                    }
                });
    }
}
