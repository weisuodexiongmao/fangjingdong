package bwe.com.bawei.jdcom.shopcar;

import com.bwie.testten.shopcar.bean.DeleteBean;
import com.bwie.testten.shopcar.bean.ShopCarBean;

import java.util.List;

/**
 * Created by Zhang on 2017/11/18.
 */

public interface ShopCarConstract {
    interface ICarView{
        void ShowList(List<ShopCarBean.DataBean> list);
        void ShowEnd();
        void ShowError(String e);
        void ShowDelete(DeleteBean db);
        void ShowDeleteError(String e);
    }

    interface ICarModel{
        void OnRequsetData(String url, int uid, OnCarListener onCarListener);
        void OnDeleteData(String url, String pid, int uid, OnDeleteListener onDeleteListener);
    }
    interface OnCarListener{
        void OnSuccess(List<ShopCarBean.DataBean> list);
        void OnDataEnd();
        void OnError(String e);
    }
    interface OnDeleteListener{
        void OnSuccess(DeleteBean db);
        void OnError(String e);
    }
    interface ICarPresenter{
        void LoadList(String url, int uid);
        void LoadDelete(String url, String pid, int uid);
    }
}
