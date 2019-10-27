package com.nvk.doanailatrieuphu.Utilities;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Activity.HienThiCauHoiActivity;

import java.util.concurrent.TimeUnit;

public class TimeCounter extends CountDownTimer {
    private TextView tvTimer;
    private HienThiCauHoiActivity hienThiCauHoiActivity;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     * @param hienThiCauHoiActivity
     */

    public TimeCounter(long millisInFuture, long countDownInterval, TextView tvTimer, HienThiCauHoiActivity hienThiCauHoiActivity) {
        super(millisInFuture, countDownInterval);
        this.tvTimer = tvTimer;
        this.hienThiCauHoiActivity = hienThiCauHoiActivity;
    }

    // %02d : data: 1 -> format 01
    // %02d : data: 11 -> format 11


    // 1000ms = 1s
    @Override
    public void onTick(long millisUntilFinished) {// time truyền vào là 30000ms
        // 3 tham số : hours và minutes và seconds
        // seconds : (milis / 1000 ) % 60 = 30000 / 1000 = 30 %60 = 30s
        // minutes : (milis / 1000 *60) % 60) = 30000 / 1000 *60 = 1800s % 60 = 0
        // hours : (milis / 1000* 60* 60))% 24 = ...
        String countTime = String.format("%02d:%02d",
                //số này chuyển sang giây
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) ,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
        tvTimer.setText(countTime+"s");
    }

    @Override
    public void onFinish() {
        tvTimer.setText("0s");
//        hienThiCauHoiActivity.CheckHeart();
//        hienThiCauHoiActivity.ChuyenCauTiepTheo();
    }
}
