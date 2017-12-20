package bwe.com.bawei.jdcom.mine.presenter;

import com.bwie.testten.mine.LoginConstract;
import com.bwie.testten.mine.bean.LoginBean;
import com.bwie.testten.mine.model.LoginModel;

/**
 * Created by Zhang on 2017/11/13.
 */

public class LoginPresenter implements LoginConstract.ILoginPresenter {
    LoginConstract.ILoginView iLoginView;
    LoginConstract.ILoginModel iLoginModel;

    public LoginPresenter(LoginConstract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModel();
    }

    @Override
    public void onSignUp(String url, String mobile, String password) {
        iLoginModel.RequestData(url, mobile, password, new LoginConstract.OnRequestListener() {
            @Override
            public void OnSuccess(LoginBean.DataBean db) {
                iLoginView.showLogin(db);
            }

            @Override
            public void OnError(String e) {
                iLoginView.showerroe(e);
            }
        });
    }
}
