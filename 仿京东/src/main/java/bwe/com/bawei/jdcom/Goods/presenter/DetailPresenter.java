package bwe.com.bawei.jdcom.Goods.presenter;


import bwe.com.bawei.jdcom.Goods.GoodConstract;
import bwe.com.bawei.jdcom.Goods.bean.AddBean;
import bwe.com.bawei.jdcom.Goods.bean.DetailsBean;
import bwe.com.bawei.jdcom.Goods.model.GoodModel;

/**
 * Created by Zhang on 2017/11/16.
 */

public class DetailPresenter implements GoodConstract.IDetailPresenter {
    GoodConstract.IDetailsView iDetailsView;
    GoodConstract.IGoodModel iGoodModel;

    public DetailPresenter(GoodConstract.IDetailsView iDetailsView) {
        this.iDetailsView = iDetailsView;
        iGoodModel = new GoodModel();
    }

    @Override
    public void LoadDetails(String url, int pid) {
        iGoodModel.requestDetails(url, pid, new GoodConstract.OnDetailsListener() {
            @Override
            public void onSuccess(DetailsBean list) {
                iDetailsView.ShowList(list);
            }

            @Override
            public void onError(String e) {
                iDetailsView.ShowError(e);
            }
        });
    }

    @Override
    public void LoadAdd(String url, int uid, int pid, int sellerid) {
        iGoodModel.requestAdd(url, uid, pid, sellerid, new GoodConstract.OnAddListener() {
            @Override
            public void onSuccess(AddBean addBean) {
                iDetailsView.AddShop(addBean);
            }

            @Override
            public void onError(String e) {
                iDetailsView.AddError(e);
            }
        });
    }
}
