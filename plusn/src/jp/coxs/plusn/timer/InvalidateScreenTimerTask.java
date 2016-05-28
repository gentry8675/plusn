package jp.coxs.plusn.timer;

import java.util.TimerTask;

import jp.coxs.plusn.ScreenActivity;
import android.content.Context;
import android.os.Handler;

public class InvalidateScreenTimerTask extends TimerTask{

    Handler mHandler = new Handler();   //UI Threadへのpost用ハンドラ

    private int timeCount = 0;
    private Context context;
    private Handler handler;

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }
    public int getTimeCount() {
        return this.timeCount;
    }

    public InvalidateScreenTimerTask(Context context) {
        handler = new Handler();
        this.context = context;
    }

    @Override
    public void run() {
        // mHandlerを通じてUI Threadへ処理をキューイング
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 実行間隔分を加算処理
                timeCount++;
                ((ScreenActivity) context).InvalidatePlayScreen();
            }
        });
    }
}
