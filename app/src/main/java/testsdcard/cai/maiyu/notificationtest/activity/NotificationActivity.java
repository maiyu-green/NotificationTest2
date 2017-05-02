package testsdcard.cai.maiyu.notificationtest.activity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import testsdcard.cai.maiyu.notificationtest.R;

/**
 * Created by maiyu on 2017/5/1.
 */

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        //取消通知，也可以在发送时设置点击后取消
        cancleNotification();


    }

    private void cancleNotification() {
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);//取消通知，参数是，发送时的id
    }
}
