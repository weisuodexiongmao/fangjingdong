package bwe.com.bawei.jdcom.utils;

import com.bwie.testten.Goods.bean.AddBean;
import com.bwie.testten.Goods.bean.DetailsBean;
import com.bwie.testten.Goods.bean.GoodBean;
import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.classify.bean.TwoBean;
import com.bwie.testten.home.Bean.BannerBean;
import com.bwie.testten.mine.bean.LoginBean;
import com.bwie.testten.mine.bean.SignUpBean;
import com.bwie.testten.shopcar.bean.DeleteBean;
import com.bwie.testten.shopcar.bean.ShopCarBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {
    @POST
    Observable<SignUpBean> getsup(@Url String url, @QueryMap Map<String, String> map);
    @POST
    Observable<LoginBean> getlg(@Url String url, @QueryMap Map<String, String> map);

    @GET("product/getCatagory")
    Observable<OneBean> getonedata();

    @GET("ad/getAd")
    Observable<BannerBean> getbanner();

    @POST
    Observable<TwoBean> getRight(@Url String url, @QueryMap Map<String, Integer> map);

    @POST
    Observable<GoodBean> getgood(@Url String url, @QueryMap Map<String, Integer> map);

    @POST
    Observable<DetailsBean> getdetail(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<AddBean> getAdd(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ShopCarBean> getcatlist(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<DeleteBean> getdeta(@Url String url, @QueryMap Map<String, String> map);
}
