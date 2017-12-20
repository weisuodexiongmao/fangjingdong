package bwe.com.bawei.jdcom.home;


import bwe.com.bawei.jdcom.home.Bean.BannerBean;

/**
 * Created by Zhang on 2017/11/14.
 */

public interface BannerConstract {
    interface IBannerView{
        void ShowBanner(BannerBean bb);
        void ShowError(String e);
    }
    interface IBannerModel{
        void RequestData(String url, OnBannerRequest onBannerRequest);
    }
    interface OnBannerRequest{
        void OnSuccess(BannerBean bb);
        void OnError(String e);
    }
    interface IBannerPresenter{
        void LoadBan(String url);
    }
}
