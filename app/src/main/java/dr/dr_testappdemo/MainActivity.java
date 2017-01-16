package dr.dr_testappdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv_test);
        Button btn = (Button) findViewById(R.id.btn_test);
        Button btn_testJava = (Button) findViewById(R.id.btn_test_java);
        Button btn_testNDK = (Button) findViewById(R.id.btn_test_ndk);

        tv.setText("博主怎么能够这么美");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟连接服务器
                if(key != null && key.equals("服务器通信秘钥"))
                {
                    Toast.makeText(MainActivity.this,"服务器连接成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"服务器连接失败",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_testJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(JavaValidateSign())
                {
                    Toast.makeText(MainActivity.this,"Java层签名验证通过",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Java层签名验证失败",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_testNDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                key = DR_JNITest.getSuccessKey(MainActivity.this);
                Toast.makeText(MainActivity.this,key,Toast.LENGTH_SHORT).show();




            }
        });


    }


    /**
     * md5编码
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }



    /**
     * 验证是否是合法的签名
     * @return
     */
    private boolean JavaValidateSign(){

        boolean isValidated  = false;
        try {
            //得到签名
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;

            //将签名文件MD5编码一下
            String signStr = md5(signs[0].toCharsString());

            //将应用现在的签名MD5值和我们正确的MD5值对比
            return signStr.equals("这里写正确的签名的MD5加密后的字符串");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return isValidated;
    }
}
