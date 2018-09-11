package com.nel.anrdetector;

import android.os.Looper;

/**
 * Description :
 * CreateTime : 2018/9/11 16:36
 *
 * @author ningerlei@danale.com
 * @version <v1.0>
 */
public class ANRException extends RuntimeException {

    public ANRException() {
        super("应用程序无响应！");
        setStackTrace(Looper.getMainLooper().getThread().getStackTrace());
    }
}
