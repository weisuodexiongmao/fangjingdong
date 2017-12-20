package bwe.com.bawei.jdcom.shopcar.model;

import com.bwie.testten.Goods.bean.DetailsBean;
import com.bwie.testten.shopcar.ShopCarConstract;
import com.bwie.testten.shopcar.bean.DeleteBean;
import com.bwie.testten.shopcar.bean.ShopCarBean;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.RetroFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/18.
 */

public class ShopCarModel implements ShopCarConstract.ICarModel {
    @Override
    public void OnRequsetData(String url, int uid, final ShopCarConstract.OnCarListener onCarListener) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid+"");
        map.put("source","android");
        Observable<ShopCarBean> getcatlist = RetroFactory.getInstance().getcatlist(Api.CarList, map);
        getcatlist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShopCarBean>() {
                    @Override
                    public void onCompleted() {
                        onCarListener.OnDataEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //onCarListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ShopCarBean shopCarBean) {
                        if(shopCarBean!=null){
                            List<ShopCarBean.DataBean> data = shopCarBean.getData();
                            onCarListener.OnSuccess(data);
                        }

                    }
                });
    }

    @Override
    public void OnDeleteData(String url,String pid, int uid , final ShopCarConstract.OnDeleteListener onDeleteListener) {

        Map<String,String> map = new HashMap<>();
        map.put("uid",uid+"");
        map.put("pid",pid);
        Observable<DeleteBean> getdeta = RetroFactory.getInstance().getdeta(Api.DELETE, map);
        getdeta.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeleteBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onDeleteListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(DeleteBean db) {
                        onDeleteListener.OnSuccess(db);
                    }
                });
    }
}
