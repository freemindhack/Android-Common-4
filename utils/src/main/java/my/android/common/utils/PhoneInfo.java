package my.android.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Retrieve phone info
 * Created by 14110105 on 2015/4/1.
 */
public class PhoneInfo {

    private static final String TAG = PhoneInfo.class.getSimpleName();
    private static final String FILE_MEMORY = "/proc/meminfo";
    private static final String FILE_CPU = "/proc/cpuinfo";
    public String mIMEI;
    public int mPhoneType;
    public int mSysVersion;
    public String mNetWorkCountryIso;
    public String mNetWorkOperator;
    public String mNetWorkOperatorName;
    public int mNetWorkType;
    public boolean mIsOnLine;
    public String mConnectTypeName;
    public long mFreeMem;
    public long mTotalMem;
    public String mCupInfo;
    public String mProductName;
    public String mModelName;
    public String mManufacturerName;

    /**
     * private constructor
     */
    private PhoneInfo() {

    }

    /**
     * get imei
     *
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        // check if has the permission
        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager()
                .checkPermission(Manifest.permission.READ_PHONE_STATE,
                        context.getPackageName())) {
            return manager.getDeviceId();
        } else {
            return null;
        }
    }

    /**
     * get phone type,like :GSM��CDMA��SIP��NONE
     *
     * @param context
     * @return
     */
    public static int getPhoneType(Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getPhoneType();
    }

    /**
     * get phone sys version
     *
     * @return
     */
    public static int getSysVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * get free memory of phone, in M
     *
     * @param context
     * @return
     */
//    public static long getFreeMem(Context context) {
//        ActivityManager manager = (ActivityManager) context
//                .getSystemService(Activity.ACTIVITY_SERVICE);
//        MemoryInfo info = new MemoryInfo();
//        manager.getMemoryInfo(info);
//        long free = info.availMem / 1024 / 1024;
//        return free;
//    }

    /**
     * get total memory of phone , in M
     *
     * @param context
     * @return
     */
    public static long getTotalMem(Context context) {
        try {
            FileReader fr = new FileReader(FILE_MEMORY);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split("\\s+");
            Log.w(TAG, text);
            return Long.valueOf(array[1]) / 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getCpuInfo() {
        try {
            FileReader fr = new FileReader(FILE_CPU);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
                Log.w(TAG, " .....  " + array[i]);
            }
            Log.w(TAG, text);
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get product name of phone
     *
     * @return
     */
    public static String getProductName() {
        return Build.PRODUCT;
    }

    /**
     * get model of phone
     *
     * @return
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * get Manufacturer Name of phone
     *
     * @return
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }



}
