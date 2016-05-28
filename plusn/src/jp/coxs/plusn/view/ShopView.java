package jp.coxs.plusn.view;

import java.util.List;
import java.util.Timer;

import jp.coxs.plusn.ScreenActivity;
import jp.coxs.plusn.bean.SkillBean;
import jp.coxs.plusn.timer.InvalidateScreenTimerTask;
import jp.coxs.plusn.util.FileUtil;
import jp.coxs.plusn.view.draw.DrawShopView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShopView extends View {
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
    private List<SkillBean> skillList = null;

    boolean startDownFlg = false;

    private DrawShopView draw = null;

    private int selectedSkillId = 0;
    private int selectedItemId = 0;

    private SkillBean selectSkill;
    private String money;

    private int changeScreenCount = 0;

    private boolean startScreenFlg = false;
    private boolean changeScreenFlg = false;

    private String button_name = "";

    private boolean buttonDownFlg = false;

    private int successPanelKbn = 0;

    /**
     * コンストラクター
     *
     * @param context
     */
    public ShopView(Context context) {
        super(context);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public ShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public ShopView(Context context, AttributeSet attrs, int defStyle) {
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

        // ファイルを初期化する
        fileUtil = new FileUtil(context);

        skillList = fileUtil.getSkillInfoList();

        selectSkill = skillList.get(0);

        money = fileUtil.getMoney();

        // 描画クラスを初期化する
        draw = new DrawShopView(screen.getResources());
        // 描画クラスに座標係数を設定する
        draw.setDiff(diff, diffX, diffY);
        draw.initSkillImage(skillList);

        // 画面描画リフレシュ：1秒/60回
        invalidateTimerTask = new InvalidateScreenTimerTask(context);
        invalidateTimer = new Timer(true);
        invalidateTimer.schedule(invalidateTimerTask, 16, 16);
        startScreenFlg = true;
        changeScreenCount = 2040;

    }

    /**
     * 画面を描画します
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas c) {
        // 描画クラスにキャンパスを設定する
        draw.Init(c);

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
                screen.showStageSelectScreen();
            }
        }

        if (changeScreenCount > 450) {
            // 扉を描画する
            draw.drawDoor(changeScreenCount);
            // ローディングの文字
//            draw.drawLoading(changeScreenCount);
            draw.drawLoading(changeScreenCount, changeScreenFlg);
        } else {
            // マップを描画する
            draw.drawBackGround();

            draw.drawFrame();

            draw.drawSkill(skillList);

            draw.drawItem(selectedSkillId);
            draw.drawSkillInfo(selectSkill);

            draw.drawController(button_name, buttonDownFlg);

            draw.drawSelected(selectedSkillId, selectedItemId);

            draw.drawMoney(money);

            if (successPanelKbn > 0) {
                draw.drawSuccessPanel(successPanelKbn);
            }

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
            if (successPanelKbn > 0) {
                if (isIn(x, y, draw.getBounds("ok"))) {
                    successPanelKbn = 0;
                }
            } else {
                if (isIn(x, y, draw.getBounds("stage"))) {
                    button_name = "stage";
                    buttonDownFlg = true;
                }
                if (isIn(x, y, draw.getBounds("buy"))) {
                    button_name = "buy";
                    buttonDownFlg = true;
                }

                for (int i = 0; i < skillList.size(); i++) {
                    if (isIn(x, y, draw.getBounds("skill_" + (i + 1)))) {
                        selectSkill = skillList.get(i);
                        selectedSkillId = i;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (isIn(x, y, draw.getBounds("item_" + (i + 1)))) {
                        selectedItemId = i;
                    }
                }
            }
            break;
        case MotionEvent.ACTION_UP:
            if (successPanelKbn == 0) {
                if ("stage".equals(button_name) && isIn(x, y, draw.getBounds("stage")) && buttonDownFlg) {
                    changeScreenFlg = true;
                }
                if ("buy".equals(button_name) && isIn(x, y, draw.getBounds("buy")) && buttonDownFlg) {
                    int iMoney = Integer.valueOf(money).intValue();
                    int price = 0;
                    boolean buyFlg = true;
                    if (selectedItemId == 0) {
                        price = 100;
                    } else if (selectedItemId == 1) {
                        price = 190;
                    } else if (selectedItemId == 2) {
                        price = 900;
                    } else if (selectedItemId == 3) {
                        price = 1700;
                    }
                    if (iMoney < price) {
                        buyFlg = false;
                    }
                    if (buyFlg) {
                        fileUtil.addMoney(0 - price);
                        fileUtil.buySkill(selectedSkillId, selectedItemId);
                        skillList = fileUtil.getSkillInfoList();
                        money = fileUtil.getMoney();
                        successPanelKbn = 1;
                    } else {
                        successPanelKbn = 2;
                    }
                }
            }
            buttonDownFlg = false;
            startDownFlg = false;
            break;
        case MotionEvent.ACTION_MOVE:
            if (successPanelKbn == 0) {
                if ("stage".equals(button_name) && isIn(x, y, draw.getBounds("stage"))) {
                    buttonDownFlg = true;
                } else if ("buy".equals(button_name) && isIn(x, y, draw.getBounds("buy"))) {
                    buttonDownFlg = true;
                } else {
                    buttonDownFlg = false;
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
    public boolean isIn(int x, int y, Rect rect){
        return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
    }

}
