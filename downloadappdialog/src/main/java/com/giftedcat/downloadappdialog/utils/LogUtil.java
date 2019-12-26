package com.giftedcat.downloadappdialog.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;


/**
 * Description: 一个打印日志的工具类，在上线的时候将开关置为false
 * Created on 2018/4/11 11:38:42
 * author:Ahuangshang
 */

public class LogUtil {
    private static String customTagPrefix = "AA";
    private static boolean allow = true;
    private static CustomLogger customLogger;

    private LogUtil() {
    }

    public static String getCustomTagPrefix() {
        return customTagPrefix;
    }

    public static void setCustomTagPrefix(String customTagPrefix) {
        LogUtil.customTagPrefix = customTagPrefix;
    }

    public static void enableLog(boolean enable) {
        allow = enable;
    }

    @SuppressLint("DefaultLocale")
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(line:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        //tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = String.format(tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.d(tag, content);
            } else {
                Log.d(tag, content);
            }

        }
    }

    public static void d(String format, Object... args) {
        d(String.format(format, args));
    }

    public static void d(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.d(tag, content, tr);
            } else {
                Log.d(tag, content, tr);
            }

        }
    }

    public static void d(String format, Throwable tr, Object... args) {
        d(String.format(format, args), tr);
    }

    public static void e(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.e(tag, content);
            } else {
                e(tag, content);
            }

        }
    }

    public static void e(String format, Object... args) {
        e(String.format(format, args));
    }

    public static void e(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.e(tag, content, tr);
            } else {
                Log.e(tag, content, tr);
            }

        }
    }

    public static void e(String format, Throwable tr, Object... args) {
        e(String.format(format, args), tr);
    }

    public static void i(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.i(tag, content);
            } else {
                Log.i(tag, content);
            }

        }
    }

    public static void i(String format, Object... args) {
        i(String.format(format, args));
    }

    public static void i(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.i(tag, content, tr);
            } else {
                Log.i(tag, content, tr);
            }

        }
    }

    public static void i(String format, Throwable tr, Object... args) {
        i(String.format(format, args), tr);
    }

    public static void v(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.v(tag, content);
            } else {
                Log.v(tag, content);
            }

        }
    }

    public static void v(String format, Object... args) {
        v(String.format(format, args));
    }

    public static void v(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.v(tag, content, tr);
            } else {
                Log.v(tag, content, tr);
            }

        }
    }

    public static void v(String format, Throwable tr, Object... args) {
        v(String.format(format, args), tr);
    }

    public static void w(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, content);
            } else {
                Log.w(tag, content);
            }

        }
    }

    public static void w(String format, Object... args) {
        w(String.format(format, args));
    }

    public static void w(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, content, tr);
            } else {
                Log.w(tag, content, tr);
            }

        }
    }

    public static void w(String format, Throwable tr, Object... args) {
        w(String.format(format, args), tr);
    }

    public static void w(Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.w(tag, tr);
            } else {
                Log.w(tag, tr);
            }

        }
    }

    public static void wtf(String content) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, content);
            } else {
                Log.wtf(tag, content);
            }

        }
    }

    public static void wtf(String format, Object... args) {
        wtf(String.format(format, args));
    }

    public static void wtf(String content, Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, content, tr);
            } else {
                Log.wtf(tag, content, tr);
            }

        }
    }

    public static void wtf(String format, Throwable tr, Object... args) {
        wtf(String.format(format, args), tr);
    }

    public static void wtf(Throwable tr) {
        if (allow) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            if (customLogger != null) {
                customLogger.wtf(tag, tr);
            } else {
                Log.wtf(tag, tr);
            }

        }
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public interface CustomLogger {
        void d(String var1, String var2);

        void d(String var1, String var2, Throwable var3);

        void e(String var1, String var2);

        void e(String var1, String var2, Throwable var3);

        void i(String var1, String var2);

        void i(String var1, String var2, Throwable var3);

        void v(String var1, String var2);

        void v(String var1, String var2, Throwable var3);

        void w(String var1, String var2);

        void w(String var1, String var2, Throwable var3);

        void w(String var1, Throwable var2);

        void wtf(String var1, String var2);

        void wtf(String var1, String var2, Throwable var3);

        void wtf(String var1, Throwable var2);
    }

}
