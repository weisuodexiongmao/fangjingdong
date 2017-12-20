package bwe.com.bawei.jdcom.classify;


import java.util.List;

import bwe.com.bawei.jdcom.classify.bean.OneBean;
import bwe.com.bawei.jdcom.classify.bean.TwoBean;

/**
 * Created by Zhang on 2017/11/13.
 */

public interface ClassifyConstract {
    interface IClassifyView{
        void ShowList(List<OneBean.DataBean> list);
        void ShowRight(List<TwoBean.DataBean> list);
        void ShowError(String e);
    }
    interface IClassifyModel{
        void OnRequestsListener(String url, OnRequestListener onRequestListener);
        void OnRightData(String url, int cid, OnRightListener onRightListener);
    }
    interface OnRightListener{
        void OnSuccess(List<TwoBean.DataBean> list);
        void OnError(String e);
    }
    interface OnRequestListener{
        void OnSuccess(List<OneBean.DataBean> list);
        void OnError(String e);
    }
    interface IClassifyPresenter{
        void LoadList(String url);
        void LoadRight(String url, int cid);
    }
}
