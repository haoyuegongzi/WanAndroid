package com.wanandroid.common.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author chen1
 * @date 2017/7/20
 * 收集crash日志
 */

public class ImproveCrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    public static ImproveCrashHandler INSTANCE;

    private ImproveCrashHandler(){

    }

    public static ImproveCrashHandler getInstance(){
        if (INSTANCE == null) {
            synchronized (ImproveCrashHandler.class){
                if (INSTANCE == null) {
                    INSTANCE=new ImproveCrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void initVariable(Context context){
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //异常写入stringBuilder
        Writer writer = new StringWriter( );
        PrintWriter printWriter = new PrintWriter( writer );
        throwable.printStackTrace ( printWriter );
        //异常原因写入stringBuilder
        Throwable cause = throwable.getCause ( );
        if ( cause != null ) {
            cause.printStackTrace ( printWriter );
        }
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                printWriter.print("App Version: ");
                printWriter.print(pi.versionName + "_");
                printWriter.println(pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //android版本号
        printWriter.print("OS Version: ");
        printWriter.print(Build.VERSION.RELEASE + "_");
        printWriter.println(Build.VERSION.SDK_INT);

        //手机制造商
        printWriter.print("Vendor: ");
        printWriter.println(Build.MANUFACTURER);

        //手机型号
        printWriter.print("Model: ");
        printWriter.println(Build.MODEL);

        //cpu架构
        printWriter.print("CPU ABI: ");
        printWriter.println(Build.CPU_ABI + "_" + Build.CPU_ABI2 + "_" + Build.BRAND);

        printWriter.close ( );
        String result = writer.toString ( );

        Log.i(getClass().getSimpleName(), "uncaughtException: result ===" + result);
        //上传到服务器
        postService(result);
        //保存本地
//        save(result);
    }

    /**
     * 上传到服务器
     */
    private void postService(String log){
       // TODO: 2017/7/20  
    }

    /**
     * 保存本地
     */
    private void save(String log){
        try {
            long timestamp = System.currentTimeMillis();
            String time = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA).format(new Date());
            String fileName = "LOG" + " - " + time + " - " + timestamp + ".txt";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Log.e("tags", Environment.getExternalStorageDirectory().getAbsolutePath());
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "CrashLog";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(path + File.separator + fileName);
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(log.getBytes());
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            Log.e("", "an error occured while writing file...", e);
        }
    }

}
