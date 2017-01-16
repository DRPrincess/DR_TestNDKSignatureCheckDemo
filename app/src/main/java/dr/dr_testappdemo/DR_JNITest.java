package dr.dr_testappdemo;

/**
 * Created by Administrator on 2017/1/12.
 *
 */

public class DR_JNITest {
    static{

        System.loadLibrary("DRPrincess");
    }


    public static  native String getSuccessKey(Object contextObject);
}





