package bwe.com.bawei.jdcom.classify.presenter;


import java.util.List;

import bwe.com.bawei.jdcom.classify.ClassifyConstract;
import bwe.com.bawei.jdcom.classify.bean.OneBean;
import bwe.com.bawei.jdcom.classify.bean.TwoBean;
import bwe.com.bawei.jdcom.classify.model.LeftModel;

/**
 * Created by Zhang on 2017/11/14.
 */

public class LeftPresenter implements ClassifyConstract.IClassifyPresenter {
    ClassifyConstract.IClassifyView iClassifyView;
    ClassifyConstract.IClassifyModel iClassifyModel;

    public LeftPresenter(ClassifyConstract.IClassifyView iClassifyView) {
        this.iClassifyView = iClassifyView;
        iClassifyModel = new LeftModel();
    }

    @Override
    public void LoadList(String url) {
        iClassifyModel.OnRequestsListener(url, new ClassifyConstract.OnRequestListener() {
            @Override
            public void OnSuccess(List<OneBean.DataBean> list) {
                iClassifyView.ShowList(list);
            }

            @Override
            public void OnError(String e) {
                iClassifyView.ShowError(e);
            }
        });
    }

    @Override
    public void LoadRight(String url, int cid) {
        iClassifyModel.OnRightData(url, cid, new ClassifyConstract.OnRightListener() {
            @Override
            public void OnSuccess(List<TwoBean.DataBean> list) {
                iClassifyView.ShowRight(list);
            }

            @Override
            public void OnError(String e) {
                iClassifyView.ShowError(e);
            }
        });
    }
}
