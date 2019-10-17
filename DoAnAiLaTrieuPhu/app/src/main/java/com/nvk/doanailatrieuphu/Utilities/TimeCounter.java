package com.nvk.doanailatrieuphu.Utilities;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimeCounter extends CountDownTimer {
    private TextView tvTimer;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCounter(long millisInFuture, long countDownInterval, TextView tvTimer) {
        super(millisInFuture, countDownInterval);
        this.tvTimer = tvTimer;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
        tvTimer.setText(countTime+"s");
    }

    @Override
    public void onFinish() {
        tvTimer.setText("0s");
    }
}
