package bwe.com.bawei.jdcom.mine;

import com.bwie.testten.mine.bean.LoginBean;

import java.util.List;

/**
 * Created by Zhang on 2017/11/13.
 */

public interface LoginConstract {

    interface ILoginView {
        void showLogin(LoginBean.DataBean db);
        void showerroe(String e);
    }

    interface ILoginModel {
        void RequestData(String url, String mobile, String password, OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess(LoginBean.DataBean db);
        void OnError(String e);
    }

    interface ILoginPresenter {
        void onSignUp(String url, String mobile, String password);
    }

}
