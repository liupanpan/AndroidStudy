package com.mycompany.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rzw4v0 on 2015/10/22.
 */
public class FriendFragment extends Fragment {
    private String url = "www.baidu.com";
    View rootView;
    byte[] imageData = null;
    Button b;
    NetWorkSpeedInfo netWorkSpeedInfo = null;
    private final int UPDATE_SPEED = 1;// 进行中
    private final int UPDATE_DNOE = 0;// 完成下载
    private ImageView imageView;
    private long begin = 0;
    private Button startButton;
    private TextView connectionType, nowSpeed, avageSpeed;
    long tem = 0;
    long falg = 0;
    long numberTotal = 0;
    List<Long> list = new ArrayList<Long>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.page_03, container, false);

        imageView = (ImageView)rootView.findViewById(R.id.iv_needle);
        startButton = (Button)rootView.findViewById(R.id.start_button);
        connectionType = (TextView)rootView.findViewById(R.id.connection_type);
        nowSpeed = (TextView)rootView.findViewById(R.id.now_speed);
        avageSpeed = (TextView)rootView.findViewById(R.id.average_speed);
        // timer.schedule(task, 1000, 1000);
        netWorkSpeedInfo = new NetWorkSpeedInfo();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                list.clear();
                tem = 0;
                falg = 0;
                numberTotal = 0;

                new Thread() {
                    @Override
                    public void run() {
                        Log.i("开始", "**********开始  ReadFile*******");
                        imageData = ReadFile.getFileFromUrl(url, netWorkSpeedInfo);
                    }
                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        Log.i("开始", "**********开始  netWorkSpeedInfo1*******");
                        while (netWorkSpeedInfo.hadFinishedBytes < netWorkSpeedInfo.totalBytes) {
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(UPDATE_SPEED);
                        }
                        if (netWorkSpeedInfo.hadFinishedBytes == netWorkSpeedInfo.totalBytes) {
                            handler.sendEmptyMessage(UPDATE_SPEED);
                            netWorkSpeedInfo.hadFinishedBytes = 0;
                        }
                    }
                }.start();
            }
        });

        return rootView;
    }

    protected void startAnimation(double d) {
        AnimationSet animationSet = new AnimationSet(true);
        /**
         * 前两个参数定义旋转的起始和结束的度数，后两个参数定义圆心的位置
         */
        // Random random = new Random();
        int end = getDuShu(d);

        Log.i("", "********************begin:" + begin + "***end:" + end);
        RotateAnimation rotateAnimation = new RotateAnimation(begin, end, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
        rotateAnimation.setDuration(1000);
        animationSet.addAnimation(rotateAnimation);
        imageView.startAnimation(animationSet);
        begin = end;
    }

    public int getDuShu(double number) {
        double a = 0;
        if (number >= 0 && number <= 512) {
            a = number / 128 * 15;
        } else if (number > 521 && number <= 1024) {
            a = number / 256 * 15 + 30;
        } else if (number > 1024 && number <= 10 * 1024) {
            a = number / 512 * 5 + 80;
        } else {
            a = 180;
        }
        return (int) a;
    }

    private Handler handler = new Handler() {
        long tem = 0;
        long falg = 0;
        long numberTotal = 0;
        List<Long> list = new ArrayList<Long>();

        @Override
        public void handleMessage(Message msg) {
            int value = msg.what;
            switch (value) {
                case UPDATE_SPEED:
                    tem = netWorkSpeedInfo.speed / 1024;
                    list.add(tem);
                    Log.i("a", "tem****" + tem);
                    for (Long numberLong : list) {
                        numberTotal += numberLong;
                    }
                    falg = numberTotal / list.size();
                    numberTotal = 0;
                    nowSpeed.setText(tem + "kb/s");
                    avageSpeed.setText(falg + "kb/s");
                    startAnimation(Double.parseDouble(tem+""));
                    break;
                default:
                    break;
            }
        }
    };
}
