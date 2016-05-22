package com.mycompany.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_address;
    private LinearLayout ll_friend;
    private LinearLayout ll_setting;
    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_address;
    private ImageView iv_friend;
    private ImageView iv_setting;
    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_address;
    private TextView tv_friend;
    private TextView tv_setting;
    // 4个Fragment
    private HomeFragment homeFragment;
    private AddressFragment addressFragment;
    private FriendFragment friendFragment;
    private SettingFragment settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);
        //初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0);
    }

    private void initFragment(int index){
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (addressFragment == null) {
                    addressFragment = new AddressFragment();
                    transaction.add(R.id.fl_content, addressFragment);
                } else {
                    transaction.show(addressFragment);
                }
                break;
            case 2:
                if (friendFragment == null) {
                    friendFragment = new FriendFragment();
                    transaction.add(R.id.fl_content, friendFragment);
                } else {
                    transaction.show(friendFragment);
                }
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.fl_content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;

            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }
    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (addressFragment != null) {
            transaction.hide(addressFragment);
        }
        if (friendFragment != null) {
            transaction.hide(friendFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    private void initEvent(){
        // 设置按钮监听
        ll_home.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    private void initView(){
        // 底部菜单4个Linearlayout
        this.ll_home = (LinearLayout) findViewById(R.id.ll_home);
        this.ll_address = (LinearLayout) findViewById(R.id.ll_address);
        this.ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
        this.ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
        // 底部菜单4个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_address = (ImageView) findViewById(R.id.iv_address);
        this.iv_friend = (ImageView) findViewById(R.id.iv_friend);
        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);
        // 底部菜单4个菜单标题
        this.tv_home = (TextView) findViewById(R.id.tv_home);
        this.tv_address = (TextView) findViewById(R.id.tv_address);
        this.tv_friend = (TextView) findViewById(R.id.tv_friend);
        this.tv_setting = (TextView) findViewById(R.id.tv_setting);
    }
    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_home:
                iv_home.setImageResource(R.drawable.bargraph32);
                tv_home.setTextColor(0xffff0000);// 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
                initFragment(0);
                break;
            case R.id.ll_address:
                iv_address.setImageResource(R.drawable.heart32);
                tv_address.setTextColor(0xffff0000);
                initFragment(1);
                break;
            case R.id.ll_friend:
                iv_friend.setImageResource(R.drawable.article32);
                tv_friend.setTextColor(0xffff0000);
                initFragment(2);
                break;
            case R.id.ll_setting:
                iv_setting.setImageResource(R.drawable.plus32);
                tv_setting.setTextColor(0xffff0000);
                initFragment(3);
                break;

            default:
                break;
        }
    }

    private void restartBotton(){
        // ImageView置为灰色
        iv_home.setImageResource(R.drawable.bargraph32);
        iv_address.setImageResource(R.drawable.heart32);
        iv_friend.setImageResource(R.drawable.article32);
        iv_setting.setImageResource(R.drawable.plus32);
        // TextView置为灰色
        tv_home.setTextColor(0xff000000);
        tv_address.setTextColor(0xff000000);
        tv_friend.setTextColor(0xff000000);
        tv_setting.setTextColor(0xff000000);
    }
}
