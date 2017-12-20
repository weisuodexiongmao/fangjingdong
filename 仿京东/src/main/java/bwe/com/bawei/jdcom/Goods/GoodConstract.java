package bwe.com.bawei.jdcom.Goods;


import java.util.List;

import bwe.com.bawei.jdcom.Goods.bean.AddBean;
import bwe.com.bawei.jdcom.Goods.bean.DetailsBean;
import bwe.com.bawei.jdcom.Goods.bean.GoodBean;

/**
 * Created by Zhang on 2017/11/16.
 */

public interface GoodConstract {
    interface IGoodView{
        void ShowList(List<GoodBean.DataBean> list);
        void ShowError(String e);
    }

    interface IDetailsView{
        void ShowList(DetailsBean list);
        void AddShop(AddBean addBean);
        void AddError(String e);
        void ShowError(String e);
    }

    interface IGoodModel{
        void  requestData(String url, int pscid, int page, int sort, OnGoodListener onGoodListener);
        void requestDetails(String url, int pid, OnDetailsListener onDetailsListener);
        void requestAdd(String url, int uid, int pid, int sellerid, OnAddListener onAddListener);
    }

    interface OnDetailsListener{
        void onSuccess(DetailsBean list);
        void onError(String e);
    }
    interface OnAddListener{
        void onSuccess(AddBean addBean);
        void onError(String e);
    }
    interface OnGoodListener{
        void onSuccess(List<GoodBean.DataBean> list);
        void onError(String e);
    }
    interface IGoodPresenter{
        void LoadList(String url, int pscid, int page, int sort);
        //
    }
    interface IDetailPresenter{
        void LoadDetails(String url, int pid);
        void LoadAdd(String url, int uid, int pid, int sellerid);
    }


}
