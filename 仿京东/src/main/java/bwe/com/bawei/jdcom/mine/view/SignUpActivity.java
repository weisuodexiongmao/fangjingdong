package bwe.com.bawei.jdcom.mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.testten.R;
import com.bwie.testten.mine.SignUpConstract;
import com.bwie.testten.mine.presenter.SignUpPresenter;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpConstract.ISignUpView{

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.signup_user)
    EditText signupUser;
    @BindView(R.id.signup_pwd)
    EditText signupPwd;
    @BindView(R.id.signup_again_pwd)
    EditText signupAgainPwd;
    @BindView(R.id.signup_btn)
    Button signupBtn;
    private SignUpPresenter signUpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        signUpPresenter = new SignUpPresenter(this);
    }
    //13369874520
    @OnClick({R.id.login_back, R.id.signup_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:

                finish();
                break;
            case R.id.signup_btn:
                String name = signupUser.getText().toString().trim();
                String pwd = signupPwd.getText().toString().trim();
                String againPwd = signupAgainPwd.getText().toString().trim();
                signUpPresenter.onSignUp(Api.UserURL,name,pwd,againPwd);
                break;
        }
    }

    @Override
    public void ShowSign() {

        Toasts.showLong(this,"注册成功");
        
    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(this,e);
        Log.e("哈哈哈哈啊哈哈哈哈哈哈哈",e);
    }
}
