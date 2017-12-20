package bwe.com.bawei.jdcom.Goods.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwe.com.bawei.jdcom.Goods.GoodConstract;
import bwe.com.bawei.jdcom.Goods.bean.AddBean;
import bwe.com.bawei.jdcom.Goods.bean.DetailsBean;
import bwe.com.bawei.jdcom.Goods.bean.GoodBean;
import bwe.com.bawei.jdcom.utils.Api;
import bwe.com.bawei.jdcom.utils.RetroFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/16.
 */

public class GoodModel implements GoodConstract.IGoodModel {

    @Override
    public void requestData(String url, int pscid, int page, int sort, final GoodConstract.OnGoodListener onGoodListener) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pscid", pscid);
        map.put("page", page);
        map.put("sort", sort);
        final Observable<GoodBean> getgood = RetroFactory.getInstance().getgood(Api.GoodList, map);
        getgood.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGoodListener.onError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GoodBean goodBean) {
                        List<GoodBean.DataBean> data = goodBean.getData();
                        onGoodListener.onSuccess(data);
                    }
                });
    }

    @Override
    public void requestDetails(String url, int pid, final GoodConstract.OnDetailsListener onDetailsListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pid", pid+"");
        map.put("source","android");
        Observable<DetailsBean> getdetail = RetroFactory.getInstance().getdetail(Api.Details, map);
        getdetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onDetailsListener.onError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(DetailsBean goodBean) {
                        DetailsBean.DataBean list = goodBean.getData();
                        onDetailsListener.onSuccess(goodBean);
                    }
                });
    }

    @Override
    public void requestAdd(String url, int uid, int pid, int sellerid, final GoodConstract.OnAddListener onAddListener) {

        Map<String,String> map = new HashMap<>();
        map.put("uid",uid+"");
        map.put("pid",pid+"");
        map.put("sellerid",sellerid+"");
        map.put("source","android");
        final Observable<AddBean> add = RetroFactory.getInstance().getAdd(Api.ADSP, map);
        add.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onAddListener.onError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AddBean addBean) {
                        if(addBean.getMsg().equals("加购成功")){
                            onAddListener.onSuccess(addBean);
                        }else{
                            onAddListener.onError(addBean.getMsg());
                        }

                    }
                });
    }
}
