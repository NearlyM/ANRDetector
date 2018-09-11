package com.nel.anrdetector;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Description :
 * CreateTime : 2018/9/11 16:28
 *
 * @author ningerlei@danale.com
 * @version <v1.0>
 */
public class ANRWatchDog extends Thread {

    private static final int MESSAGE_WATCHDOG_TIME_TICK = 0;
    private static final int ANR_TIME_OUT = 2000;

    private static int timeTick = 0;
    private static int lastTimeTick = timeTick - 1;

    @SuppressLint("HandlerLeak")
    private Handler watchDogHandle = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeTick++;
            timeTick = timeTick % Integer.MAX_VALUE;
        }
    };

    @Override
    public void run() {
        super.run();
        while (true) {
            watchDogHandle.sendEmptyMessage(MESSAGE_WATCHDOG_TIME_TICK);
            try {
                Thread.sleep(ANR_TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (timeTick == lastTimeTick) {
                throw new ANRException();
            } else {
                lastTimeTick = timeTick;
            }
        }
    }
}
