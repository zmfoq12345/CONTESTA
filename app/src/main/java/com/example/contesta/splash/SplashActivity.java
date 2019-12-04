package com.example.contesta.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.contesta.R;
import com.example.contesta.main.MainActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_background);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);

    }

    private class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

}
//
//public class SplashActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = new Intent(this, MainActivity.class);
//        long time = 0;
//        try {
//            getHashKey(getApplicationContext());
//            startActivity(intent);
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        finish();
//    }
//
//    @Nullable
//    public static String getHashKey(Context context) {
//        final String TAG = "KeyHash";
//        String keyHash = null;
//        try {
//            PackageInfo info =
//                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
//
//            for (Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                keyHash = new String(Base64.encode(md.digest(), 0));
//                Log.d(TAG, keyHash);
//            }
//        } catch (Exception e) {
//            Log.e("name not found", e.toString());
//        }
//
//        if (keyHash != null) {
//            return keyHash;
//        } else {
//            return null;
//        }
//    }
//}