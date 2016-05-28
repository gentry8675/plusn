package jp.coxs.plusn.view;

import java.util.List;
import java.util.Timer;

import jp.coxs.plusn.ScreenActivity;
import jp.coxs.plusn.bean.StageInfoBean;
import jp.coxs.plusn.timer.InvalidateScreenTimerTask;
import jp.coxs.plusn.util.FileUtil;
import jp.coxs.plusn.view.draw.DrawStageSelectView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class StageSelectView extends View {
    // 画面座標幅
    private static final float SCREEN_W = 900;
    // 画面座標高
    private static final float SCREEN_H = 1600;

    private float diffX, diffY, diff;

    // スタートボタン位置
    private ScreenActivity screen;

    Context context = null;
    InvalidateScreenTimerTask invalidateTimerTask = null;
    Timer invalidateTimer = null;

    private FileUtil fileUtil = null;
    private List<StageInfoBean> stageInfoList = null;
    private int selectStageId = 0;

    private int downStageId = 0;

//    boolean startDownFlg = false;

    private DrawStageSelectView draw = null;

    private int clearType = 1;

    private int touchPointY = 0;
    private int moveY = 0;

    private boolean screenMoveFlg = false;

    private String money;

//    private int startScreenCount = 420;

    private int changeScreenCount = 0;


    private boolean startScreenFlg = false;
    private boolean changeScreenFlg = false;

    private String nextScreen = "";
    private int stageIdNow = 0;
    private int maxStage = 0;

    private String button_name = "";

    private boolean buttonDownFlg = false;

    /**
     * コンストラクター
     *
     * @param context
     */
    public StageSelectView(Context context) {
        super(context);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public StageSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public StageSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初期化
     *
     * @param context
     */
    private void init(Context context) {
        screen = (ScreenActivity) context;
        // 座標係数を計算する
        float diffWidth = screen.disp_w / SCREEN_W;
        float diffHeight = screen.disp_h / SCREEN_H;
        if (diffWidth > diffHeight) {
            diff = diffHeight;
            diffX = (screen.disp_w - (SCREEN_W * diff)) / 2;
        } else {
            diff = diffWidth;
            diffY = (screen.disp_h - (SCREEN_H * diff)) / 2;
        }

        // ファイルを初期化する
        fileUtil = new FileUtil(context);

        stageIdNow = fileUtil.getStageIdNow();
//        stageIdNow = 196;

        // ステージ情報を読み込む
        stageInfoList = screen.getStageInfoList();
//        stageInfoList = fileUtil.getStageInfoList();
        if (stageIdNow < 196) {
            stageInfoList.get(stageIdNow - 1).setStageStatus(1);
        }
        if (stageIdNow > 1) {
            stageInfoList.get(stageIdNow - 2).setStageStatus(2);
        }
        screen.setStageInfoList(stageInfoList);

        selectStageId = screen.getStageId();

        moveY = screen.getMapMoveY();
        if (moveY == 0) {
            moveY = (int) ((((stageIdNow - 1) / 4) * 300) * diff);
        }

        money = fileUtil.getMoney();

        // 描画クラスを初期化する
        draw = new DrawStageSelectView(screen.getResources());
        // 描画クラスに座標係数を設定する
        draw.setDiff(diff, diffX, diffY);

        // 画面描画リフレシュ：1秒/60回
        invalidateTimerTask = new InvalidateScreenTimerTask(context);
        invalidateTimer = new Timer(true);
        invalidateTimer.schedule(invalidateTimerTask, 16, 16);

        startScreenFlg = true;
        changeScreenCount = 2040;

        int stageCount = stageInfoList.size();
        if (stageCount % 4 == 0) {
            maxStage = (int) ((stageCount * 75 + 20) * diff);
        } else {
            maxStage = (int) ((stageCount * 75 + 320) * diff);
        }
        if (moveY > maxStage - (int) (1200 * diff)) {
            moveY = maxStage - (int) (1200 * diff);
        }
    }

    /**
     * 画面を描画します
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas c) {
        // 描画クラスにキャンパスを設定する
        draw.Init(c);
//        moveY = screen.getMapMoveY();

        if (startScreenFlg) {
            changeScreenCount -= 30;
            if (changeScreenCount == 0) {
                startScreenFlg = false;
            }
        }

        if (changeScreenFlg) {
            changeScreenCount += 30;
            if (changeScreenCount == 2040) {
                changeScreenFlg = false;
                invalidateTimer.cancel();
                invalidateTimer = null;
                invalidateTimerTask = null;
                if ("PlayScreen".equals(nextScreen)) {
                    draw.callback();
                    screen.showPlayScreen(selectStageId);
                    selectStageId = 0;
                } else if ("ShopScreen".equals(nextScreen)) {
                    draw.callback();
                    screen.showShopScreen();
                }
            }
        }

        if (changeScreenCount > 450) {
            // 扉を描画する
            draw.drawDoor(changeScreenCount);
            // ローディングの文字
            draw.drawLoading(changeScreenCount, changeScreenFlg);
        } else {

            // マップを描画する
            draw.drawBackGround();

            // ステージを描画する
            draw.drawStage(stageInfoList, moveY, screen.getMapMoveY(), changeScreenCount, stageIdNow);

            // ステージ選択確認
            if (selectStageId != 0) {
                StageInfoBean stageinfo = null;
                stageinfo = stageInfoList.get(selectStageId - 1);
                // パネルを描画する
                draw.drawStagePanel(selectStageId, stageinfo, clearType);
                // 得点ランキングを描画する
                draw.drawScoreRanking(fileUtil.getStageRanking(selectStageId));
                // ボタンを描画する
                draw.drawButton(buttonDownFlg);
            }
            draw.drawFrame();

            draw.drawController(button_name, buttonDownFlg);

            draw.drawMoney(money);

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
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            downStageId = 0;
            // スタートボタンを押下
            if (isIn(x, y, draw.getBounds("start")) && selectStageId != 0) {
                button_name = "start";
                // ボタンを押下状態に設定
                buttonDownFlg = true;
            }

            if (isIn(x, y, draw.getBounds("shop"))) {
                button_name = "shop";
                buttonDownFlg = true;
            }

            if (isIn(x, y, draw.getBounds("last"))) {
                button_name = "last";
                buttonDownFlg = true;
            }

            if (isIn(x, y, draw.getBounds("next"))) {
                button_name = "next";
                buttonDownFlg = true;
            }

            if (isIn(x, y, draw.getBounds("pre"))) {
                button_name = "pre";
                buttonDownFlg = true;
            }

            if (isIn(x, y, draw.getBounds("first"))) {
                button_name = "first";
                buttonDownFlg = true;
            }

            if (selectStageId == 0) {
                for (int i = 0; i < stageInfoList.size(); i++) {
                    if (stageInfoList.get(i).getStageStatus() > 0 && isIn(x, y, draw.getBounds("stage" + (i + 1)))) {
                        downStageId = stageInfoList.get(i).getStageId();
                    }
                }
            }
            if (!screenMoveFlg) {
                touchPointY = y - moveY;
            }
            break;
        case MotionEvent.ACTION_UP:
            if ("shop".equals(button_name) && isIn(x,y,draw.getBounds("shop")) && buttonDownFlg) {
                changeScreenFlg = true;
                nextScreen = "ShopScreen";
            } else if (isIn(x, y, draw.getBounds("last")) && buttonDownFlg) {
                moveY = (int) ((stageIdNow - 1)  * 75 * diff);
                if (moveY > maxStage - (int) (1200 * diff)) {
                    moveY = maxStage - (int) (1200 * diff);
                }
                screen.setMapMoveY(moveY);
            } else if (isIn(x, y, draw.getBounds("first")) && buttonDownFlg) {
                moveY = 0;
                screen.setMapMoveY(moveY);
            } else if (isIn(x, y, draw.getBounds("pre")) && buttonDownFlg) {
                if (moveY > 1200 * diff) {
                    moveY -= 1200 * diff;
                } else {
                    moveY = 0;
                }
                screen.setMapMoveY(moveY);
            } else if (isIn(x, y, draw.getBounds("next")) && buttonDownFlg) {
                moveY  = moveY + (int)(1200 * diff);
                if (moveY > maxStage - (int) (1200 * diff)) {
                    moveY = maxStage - (int) (1200 * diff);
                }
                screen.setMapMoveY(moveY);
            } else {
                // ステージ選択
                if (selectStageId == 0 && isIn(x,y,draw.getBounds("stage_list_area")) ) {
                    for (int i = 0; i < stageInfoList.size(); i++) {
                        if (stageInfoList.get(i).getStageStatus() > 0 && isIn(x, y, draw.getBounds("stage" + (i + 1)))) {
                            if (stageInfoList.get(i).getStageId() == downStageId) {
                                selectStageId = stageInfoList.get(i).getStageId();
                            }
                        }
                    }
                } else if (isIn(x, y, draw.getBounds("start")) && selectStageId != 0 && buttonDownFlg) {
                    changeScreenFlg = true;
                    nextScreen = "PlayScreen";
                    // スタートボタンから放す
                    //                screen.showPlayScreen(selectStageId, 1);
                    //                selectStageId = 0;
                } else if (!isIn(x, y, draw.getBounds("stage_panel")) && selectStageId != 0) {
                    // ステージパネルを閉じる
                    clearType = 1;
                    selectStageId = 0;
                    buttonDownFlg = false;
                } else {
                }
            }
            // スタートボタンから放す
            if (!screenMoveFlg) {
                screen.setMapMoveY(moveY);
            }
            button_name = "";
            buttonDownFlg = false;
            break;
        case MotionEvent.ACTION_MOVE:

            if ("shop".equals(button_name) && isIn(x,y,draw.getBounds("shop"))) {
                buttonDownFlg = true;
            } else if ("last".equals(button_name) && isIn(x,y,draw.getBounds("last"))) {
                buttonDownFlg = true;
            } else if ("next".equals(button_name) && isIn(x,y,draw.getBounds("next"))) {
                buttonDownFlg = true;
            } else if ("pre".equals(button_name) && isIn(x,y,draw.getBounds("pre"))) {
                buttonDownFlg = true;
            } else if ("first".equals(button_name) && isIn(x,y,draw.getBounds("first"))) {
                buttonDownFlg = true;
            } else if ("start".equals(button_name) && isIn(x,y,draw.getBounds("start"))) {
                buttonDownFlg = true;
            } else {
                buttonDownFlg = false;
            }

            if (!screenMoveFlg && selectStageId == 0 && "".equals(button_name)) {
                if (y - touchPointY < 0) {
                    moveY = 0;
                } else if (y - touchPointY > (maxStage - (int) (1200 * diff))) {
                    moveY = maxStage - (int) (1200 * diff);
                } else {
                    moveY = y - touchPointY;
                }
            }
            break;
        }
        return true;
    }

    /**
     * クリック位置チェック
     *
     * @param x
     * @param y
     * @param rect
     * @return
     */
    public boolean isIn(int x, int y, Rect rect) {
        return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
    }

}
