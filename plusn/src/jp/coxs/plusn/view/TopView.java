package jp.coxs.plusn.view;

import java.util.List;
import java.util.Timer;

import jp.coxs.plusn.ScreenActivity;
import jp.coxs.plusn.bean.MapInfoBean;
import jp.coxs.plusn.bean.StageInfoBean;
import jp.coxs.plusn.timer.InvalidateScreenTimerTask;
import jp.coxs.plusn.util.FileUtil;
import jp.coxs.plusn.view.draw.DrawTopView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TopView extends View {
    // 画面座標幅
    private static final float SCREEN_W = 900;
    // 画面座標高
    private static final float SCREEN_H = 1600;

    private float diffX, diffY, diff;

    // スタートボタン位置
    private ScreenActivity screen;

    Context context = null;
    InvalidateScreenTimerTask invalidateTimerTask = null;
    Timer   invalidateTimer   = null;

    private FileUtil fileUtil = null;
    private List<MapInfoBean> mapInfoList = null;
    private int selectMapId = 0;
    boolean startDownFlg = false;

    private DrawTopView draw = null;

    private int clearType = 1;

    private int touchPointX = 0;
    private int touchPointY = 0;
    private int moveX = 0;
    private int moveY = 0;
    private int newmoveX = 0;
    private int newmoveY = 0;

    private boolean screenMoveFlg = false;

    private int changeScreenCount = 0;


    private boolean startFlg = false;
    private List<StageInfoBean> stageInfoList = null;
    /**
     * コンストラクター
     *
     * @param context
     */
    public TopView(Context context) {
        super(context);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public TopView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初期化
     *
     * @param context
     */
    private void init(Context context){
        screen = (ScreenActivity)context;
        // 座標係数を計算する
        float diffWidth  = screen.disp_w / SCREEN_W;
        float diffHeight = screen.disp_h / SCREEN_H;
        if (diffWidth > diffHeight) {
            diff = diffHeight;
            diffX = (screen.disp_w - (SCREEN_W * diff)) / 2;
        } else {
            diff = diffWidth;
            diffY = (screen.disp_h - (SCREEN_H * diff)) / 2;
        }

        fileUtil = new FileUtil(context);
        fileUtil.initFileUtil();

        // ステージ情報を読み込む
//        mapInfoList = fileUtil.getMapInfoList();
        // ステージ情報を読み込む
        stageInfoList = fileUtil.getStageInfoList();
        screen.setStageInfoList(stageInfoList);

        // 描画クラスを初期化する
        draw = new DrawTopView(screen.getResources());
        // 描画クラスに座標係数を設定する
        draw.setDiff(diff, diffX, diffY);


        // 画面描画リフレシュ：1秒/60回
        invalidateTimerTask = new InvalidateScreenTimerTask(context);
        invalidateTimer = new Timer(true);
        invalidateTimer.schedule(invalidateTimerTask, 16, 16);

    }

    /**
     * 画面を描画します
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas c) {
        // 描画クラスにキャンパスを設定する
        draw.Init(c);

        if (startFlg) {
            changeScreenCount += 30;
            if (changeScreenCount == 2040) {
                startFlg = false;
                draw.callback();
                screen.showStageSelectScreen();
            }
        }
        if (changeScreenCount > 450) {
            // 扉を描画する
            draw.drawDoor(changeScreenCount);
            // ローディングの文字
//            draw.drawLoading(changeScreenCount);
            draw.drawLoading(changeScreenCount, startFlg);
        } else {
            // マップを描画する
            draw.drawBackGround();
            // ステージを描画する
            draw.drawButton(startDownFlg);
            // 扉を描画する
            draw.drawDoor(changeScreenCount);
        }
//        draw.DrawMemory();
    }

    /**
     * 画面タッチイベント
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch(action){
        case MotionEvent.ACTION_DOWN:
            if (isIn(x, y, draw.getBounds("start"))) {
                startDownFlg = true;
            }
            break;
        case MotionEvent.ACTION_UP:
            // スタートボタンから放す
            if (isIn(x, y, draw.getBounds("start"))) {
                startFlg = true;
            }
            startDownFlg = false;
            break;
        case MotionEvent.ACTION_MOVE:
            break;
        }
        return true;
    }

    private int getLastMapId(List<MapInfoBean> mapInfoList2) {

        for (int i = 0; i < mapInfoList2.size(); i++) {
            if (!mapInfoList2.get(i).getShowFlg()) {
                return i;
            }
        }
        return 1;
    }

    /**
     * クリック位置チェック
     *
     * @param x
     * @param y
     * @param rect
     * @return
     */
    public boolean isIn(int x, int y, Rect rect){
        return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
    }

}
