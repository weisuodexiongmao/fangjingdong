package bwe.com.bawei.jdcom.home.presenter;


import bwe.com.bawei.jdcom.home.BannerConstract;
import bwe.com.bawei.jdcom.home.Bean.BannerBean;
import bwe.com.bawei.jdcom.home.model.BannerModel;

/**
 * Created by Zhang on 2017/11/14.
 */

public class BannerPresenter implements BannerConstract.IBannerPresenter {
    BannerConstract.IBannerView iBannerView;
    BannerConstract.IBannerModel iBannerModel;

    public BannerPresenter(BannerConstract.IBannerView iBannerView) {
        this.iBannerView = iBannerView;
        iBannerModel = new BannerModel();
    }

    @Override
    public void LoadBan(String url) {
        iBannerModel.RequestData(url, new BannerConstract.OnBannerRequest() {
            @Override
            public void OnSuccess(BannerBean bb) {
                iBannerView.ShowBanner(bb);
            }

            @Override
            public void OnError(String e) {
                iBannerView.ShowError(e);
            }
        });
    }
}
