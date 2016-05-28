package jp.coxs.plusn.timer;

import java.util.TimerTask;

import android.os.Handler;

/**
 * コンボ時間計算用Task
 *
 * @author Gen
 *
 */
public class CountTimerTask extends TimerTask {
    private int timeCount = 0;
    private Handler handler;

    public int getTimeCount() {
        return this.timeCount;
    }

    public CountTimerTask() {
        handler = new Handler();
    }
    public void setTimeCont (int timeCount) {
        this.timeCount = timeCount;
    }

    @Override
    public void run() {
        // mHandlerを通じてUI Threadへ処理をキューイング
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 実行間隔分を加算処理
                timeCount++;
            }
        });
    }


}
