package testsdcard.cai.maiyu.notificationtest.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import testsdcard.cai.maiyu.notificationtest.R;

public class MainActivity extends AppCompatActivity {

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

        //延迟的intent---PendingIntent
        //Context context, int requestCode,
        //Intent intent, @Flags int flags
        //4个参数：context,0,intent,确定PendingIntent的行为
        //PendingIntent.getActivity(),getBroadcast(),getService()等方法
        Intent intent = new Intent(this , NotificationActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , 0);


        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this)
                //设置标题
                .setContentTitle("你好6")
                //设置内容
                .setContentText("为什么你会好6呢？原因是....")
                //设置时间
                .setWhen(System.currentTimeMillis())
                //设置小图标
                .setSmallIcon(R.mipmap.p1)
                //设置大图标，当系统下拉时就会看到大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources() , R.mipmap.t2))
                //设置点击时跳转的PendingIntent
                .setContentIntent(pendingIntent)
                //设置点击通知后，通知取消，也可以在代码中去取消
               // .setAutoCancel(true)
               //设置发出通知时播放的音乐
               // .setSound(Uri.parse())
                //设置震动：参数一个long[]{}数组，里面可以依次设置：静止时长，震动时长，静止时长，震动时长....
                .setVibrate(new long[]{0, 1000 , 1000 , 1000})
                //设置闪烁灯：3个参数：颜色，灯亮时长，灯暗时长
                .setLights(Color.YELLOW , 1000 , 1000)
                //设置默认效果：不需要自己设置音乐铃声，等等
                //.setDefaults(NotificationCompat.DEFAULT_ALL)

                //设置超长文本
                .setStyle(new NotificationCompat.BigTextStyle().bigText("str"))

                //设置超大图片
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(
                        getResources() , R.mipmap.p1
                )))

                //设置通知的重要性：5个值，从低到高
                //public static final int PRIORITY_MIN = -2;    //特定效果才通知
                //public static final int PRIORITY_LOW = -1;    //缩小或者位置靠后
                //public static final int PRIORITY_DEFAULT = 0; //默认
                //public static final int PRIORITY_HIGH = 1;    //放大或者位置靠前
                //public static final int PRIORITY_MAX = 2;     //独立通知
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        //第一个参数id,第二个Notification对象
        manager.notify(1 , notification);

    }




}
