package bwe.com.bawei.jdcom.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwie.testten.R;
import com.bwie.testten.mine.bean.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class MeFragment extends Fragment {
    @BindView(R.id.tv_name_back)
    TextView tvNameBack;
    @BindView(R.id.rbspsc)
    RadioButton rbspsc;
    @BindView(R.id.rbdpsc)
    RadioButton rbdpsc;
    @BindView(R.id.rbwdzj)
    RadioButton rbwdzj;
    @BindView(R.id.rbwfk)
    RadioButton rbwfk;
    @BindView(R.id.rbdfh)
    RadioButton rbdfh;
    @BindView(R.id.rbdsh)
    RadioButton rbdsh;
    @BindView(R.id.rbdpj)
    RadioButton rbdpj;
    @BindView(R.id.rbtk)
    RadioButton rbtk;
    @BindView(R.id.rbyfk)
    RadioButton rbyfk;
    @BindView(R.id.rbczk)
    RadioButton rbczk;
    @BindView(R.id.rbdjq)
    RadioButton rbdjq;
    @BindView(R.id.rbhb)
    RadioButton rbhb;
    @BindView(R.id.rbjf)
    RadioButton rbjf;
    @BindView(R.id.shouhuodizhi)
    RadioButton shouhuodizhi;
    Unbinder unbinder;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.me, container, false);

        unbinder = ButterKnife.bind(this, v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("000")) {
                    Intent in = new Intent(getActivity(), LoginActivity.class);
                    startActivity(in);
                }
            }
        });
        tvNameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.equals("000")) {
                    Intent in = new Intent(getActivity(), ExitActivity.class);
                    getActivity().startActivity(in);
                } else {
                    Intent in = new Intent(getActivity(), LoginActivity.class);
                    startActivity(in);
                }
            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences user = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        name = user.getString("name", "000");
        if (!name.equals("000")) {
            tvNameBack.setText(name);
        } else {
            tvNameBack.setText("点击登录");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
