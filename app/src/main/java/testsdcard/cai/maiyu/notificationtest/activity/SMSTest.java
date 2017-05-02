package testsdcard.cai.maiyu.notificationtest.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import testsdcard.cai.maiyu.notificationtest.R;

public class SMSTest extends AppCompatActivity {

    //定义动作
    private final static String SEND_ACTION      = "send";
    private final static String DELIVERED_ACTION = "delivered";

    @ViewInject(R.id.send_notice)
    private Button btnSendNtf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

    }

    @OnClick(R.id.send_notice)
    public void sendNotification(View view){



    }

    private void sendSms(String receiver, String text) {
        //获取SmsManager管理器
        SmsManager s = SmsManager.getDefault();
        //创建PendingIntent对象：4个参数：context,0,intent,flag
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SEND_ACTION),
                PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED_ACTION),
                PendingIntent.FLAG_CANCEL_CURRENT);
        // 发送完成
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "发送成功!", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Send Failed because generic failure cause.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "Send Failed because service is currently unavailable.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Send Failed because no pdu provided.", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Send Failed because radio was explicitly turned off.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getBaseContext(), "Send Failed.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SEND_ACTION));

        // 通知你对方接收成功
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "Delivered Success!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getBaseContext(), "Delivered Failed!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED_ACTION));

        // 发送短信，sentPI和deliveredPI将分别在短信发送成功和对方接受成功时被广播
        s.sendTextMessage(receiver, null, text, sentPI, deliveredPI);
    }





}
