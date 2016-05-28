package jp.coxs.plusn.view.draw;

import java.util.List;

import jp.coxs.plusn.R;
import jp.coxs.plusn.bean.StageInfoBean;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * PlayViewの描画クラス
 *
 * @author Gen
 */
public class DrawStageSelectView extends DrawView {

    private Drawable backGround; // 背景
    private Drawable ad;

    private Drawable stage_clear_1; // ステージ1
    private Drawable stage_clear_2; // ステージ1
    private Drawable stage_clear_3; // ステージ1
    private Drawable stage_init_1; // ステージ1
    private Drawable stage_lock_1; // ステージ1

    private Drawable game_start_button;
//    private Drawable start_down;

    private Drawable coin;               // ボールＮ

    private Drawable stage_panel_1;

    // 枠
    private Drawable stage_frame;
    // ゴールドのエリア
    private Drawable gold_area;
    // ボタン
    private Drawable shop_button;
    // ボタン
    private Drawable last_button;
    // ボタン
    private Drawable next_button;
    // ボタン
    private Drawable first_button;
    // ボタン
    private Drawable pre_button;

    /**
     * コンストラクター
     *
     * @param diff 座標係数
     * @param resources
     */
    public DrawStageSelectView(Resources resources) {
        super(resources);

        backGround = resources.getDrawable(R.drawable.back_ground);

        stage_clear_1 = resources.getDrawable(R.drawable.stage_clear_1);
        stage_clear_2 = resources.getDrawable(R.drawable.stage_clear_2);
        stage_clear_3 = resources.getDrawable(R.drawable.stage_clear_3);
        stage_init_1 = resources.getDrawable(R.drawable.stage_init_1);
        stage_lock_1 = resources.getDrawable(R.drawable.stage_lock_1);

        game_start_button = resources.getDrawable(R.drawable.game_start_button);

        stage_panel_1 = resources.getDrawable(R.drawable.stage_panel);

        // コインのアイコン
        coin = resources.getDrawable(R.drawable.coin);

        // 枠
        stage_frame = resources.getDrawable(R.drawable.stage_frame);
        // ゴールドの表示枠
        gold_area = resources.getDrawable(R.drawable.gold_area);

        // ボタン
        shop_button  = resources.getDrawable(R.drawable.shop_button);
        last_button  = resources.getDrawable(R.drawable.last_button);
        next_button  = resources.getDrawable(R.drawable.next_button);
        pre_button   = resources.getDrawable(R.drawable.pre_button);
        first_button = resources.getDrawable(R.drawable.first_button);

    }

    /**
     * 描画キャンバスを設定します
     *
     * @param can キャンバス
     */
    public void Init(Canvas can) {
        super.Init(can);
        rectMap.put("stage_panel",     new Rect(calculateCoordX( 150), calculateCoordY( 550), calculateCoordX( 750), calculateCoordY(1250)));
        rectMap.put("start",           new Rect(calculateCoordX( 300), calculateCoordY(1000), calculateCoordX( 600), calculateCoordY(1150)));
        rectMap.put("stage_list_area", new Rect(calculateCoordX(  30), calculateCoordY( 230), calculateCoordX( 870), calculateCoordY(1370)));
    }

    /**
     * 背景を描画します
     */
    public void drawBackGround() {
//        can.drawColor(Color.GRAY);
        backGround.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        backGround.draw(can);
    }

    /**
     * ステージを描画します
     *
     * @param stageInfoList
     * @param moveY
     * @param moveYY
     * @param selectStageNum
     */
    public void drawStage(List<StageInfoBean> stageInfoList, int moveY, int moveYY, int count ,int selectStageId) {

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        int start = 0;
        int end = stageInfoList.size();
//        if (count > 0) {
//            start = selectStageId - 20;
//            if (start < 0) {
//                start = 0;
//            }
//            end = start + 40;
//        }

//        Log.d("test_log","count : " + count);
//        Log.d("test_log","selectStageId : " + selectStageId);
//        start = 70;
//        end = 80;
        for (int i = start; i < end; i++) {
            int x = i % 4;
            int y = i / 4;

            left = calculateCoordX(40 + (x * 220));
            top = calculateCoordY(1120 - (y * 300)) + moveY;
            right = calculateCoordX(200 + (x * 220));
            bottom = calculateCoordY(1360 - (y * 300)) + moveY;
            if (stageInfoList.get(i).getStageStatus() == 2) {
                int targetType = stageInfoList.get(i).getTargetInfo().getTargetType();
                if (targetType == 0) {
                    // クリアしたステージ
                    stage_clear_1.setBounds(left, top, right, bottom);
                    stage_clear_1.draw(can);
                } else if (targetType > 0 && targetType < 10) {
                    // クリアしたステージ
                    stage_clear_2.setBounds(left, top, right, bottom);
                    stage_clear_2.draw(can);
                } else if (targetType == 10) {
                    // クリアしたステージ
                    stage_clear_3.setBounds(left, top, right, bottom);
                    stage_clear_3.draw(can);
                }
                String stageId = String.valueOf(i + 1);
                drawNumberString(stageId, 120 + (x * 220) - (stageId.length() * 20), 1235 - (y * 300) + (int)(moveY/diff), 40);
                rectMap.put("stage" + (i + 1), new Rect(left, top, right, bottom));
            } else if (stageInfoList.get(i).getStageStatus() == 1) {
                // 未クリアのステージ
                stage_init_1.setBounds(left, top, right, bottom);
                stage_init_1.draw(can);
                String stageId = String.valueOf(i + 1);
                drawNumberString(stageId, 120 + (x * 220) - (stageId.length() * 20), 1235 - (y * 300) + (int)(moveY/diff), 40);
                rectMap.put("stage" + (i + 1), new Rect(left, top, right, bottom));
            } else {
                // 未開放のステージ
                stage_lock_1.setBounds(left, top, right, bottom);
                stage_lock_1.draw(can);
            }
        }
    }

    /**
     * 枠を描画します
     *
     */
    public void drawFrame() {
        stage_frame.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        stage_frame.draw(can);
    }

    /**
     * ボタンを描画します
     *
     * @param startDownFlg
     */
    public void drawButton(boolean startDownFlg) {
        if (!startDownFlg) {
            game_start_button.setBounds(calculateCoordX(300), calculateCoordY(1000), calculateCoordX(600), calculateCoordY(1150));
        } else {
            game_start_button.setBounds(calculateCoordX(350), calculateCoordY(1025), calculateCoordX(550), calculateCoordY(1125));
        }
        game_start_button.draw(can);
    }

    /**
     * 操作範囲を取得します。
     *
     * @param name 名称
     */
    public Rect getBounds(String name) {
        return rectMap.get(name);
    }

    public void drawAD() {
        ad.setBounds(calculateCoordX(30), calculateCoordY(0), calculateCoordX(870), calculateCoordY(200));
        ad.draw(can);
    }

    public void drawScoreRanking(List<String> scoreList) {
        if (scoreList.size() > 0) {
            drawNumberString(scoreList.get(0), 530 - (scoreList.get(0).length() * 30), 795, 30);
        }
        if (scoreList.size() > 1) {
            drawNumberString(scoreList.get(1), 530 - (scoreList.get(1).length() * 30), 860, 30);
        }
        if (scoreList.size() > 2) {
            drawNumberString(scoreList.get(2), 530 - (scoreList.get(2).length() * 30), 925, 30);
        }
    }

    public void drawStagePanel(int stageId, StageInfoBean stageInfoBean, int playType) {

        stage_panel_1.setBounds(calculateCoordX(200), calculateCoordY(450), calculateCoordX(700), calculateCoordY(1200));
        stage_panel_1.draw(can);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40 * diff);

        String clearInfo = stageInfoBean.getTargetInfoList().get(playType - 1).getInfo();
        int stringId = resources.getIdentifier(clearInfo, "string", "jp.coxs.plusn");
        List<String> strList = makeString(resources.getString(stringId), 22);
        for (int i = 0; i< strList.size(); i++) {
            drawText(strList.get(i),                   280, 660 + (i * 50), 30 * diff, Color.WHITE);
        }



//        if (clearInfo.length() > 22) {
//            drawText(clearInfo.substring(0, 11),                   280, 660, 30 * diff, Color.WHITE);
//            drawText(clearInfo.substring(11, 22),                  280, 710, 30 * diff, Color.WHITE);
//            drawText(clearInfo.substring(22, clearInfo.length()),  280, 760, 30 * diff, Color.WHITE);
//        } else if (clearInfo.length() > 11) {
//            drawText(clearInfo.substring(0, 11),                   280, 660, 30 * diff, Color.WHITE);
//            drawText(clearInfo.substring(11, clearInfo.length()),  280, 710, 30 * diff, Color.WHITE);
//        } else {
//            drawText(clearInfo.substring(0, clearInfo.length()),  280, 660, 30 * diff, Color.WHITE);
//        }

        drawNumberString(String.valueOf(stageId), 445 - (String.valueOf(stageId).length() * 30), 495, 60);
    }

//    public void drawStagePanel(int stageId, StageInfoBean stageInfoBean) {
//
//        stage_panel_1.setBounds(calculateCoordX(150), calculateCoordY(400), calculateCoordX(750), calculateCoordY(1200));
//        stage_panel_1.draw(can);
//
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(40 * diff);
//        String clearInfo = stageInfoBean.getTargetInfoList().get(0).getInfo();
//
//        if (clearInfo.length() > 9) {
//            can.drawText(clearInfo.substring(0, 9), calculateCoordX(250), calculateCoordY(745), paint);
//            can.drawText(clearInfo.substring(9, clearInfo.length()), calculateCoordX(250), calculateCoordY(790), paint);
//        } else {
//            can.drawText(clearInfo.substring(0, clearInfo.length()), calculateCoordX(250), calculateCoordY(745), paint);
//        }
//
//        drawNumberString(String.valueOf(stageId), 435 - (String.valueOf(stageId).length() * 25), 570, 50);
//    }

    public void drawController(String button_name, boolean buttonDownFlg) {

//        Paint backPaint = new Paint();
//        backPaint.setColor(Color.DKGRAY);
//        RectF backRect = new RectF(calculateCoordX(30), calculateCoordY(1400), calculateCoordX(870), calculateCoordY(1600));
//        can.drawRoundRect(backRect, 25, 25, backPaint);

//        Paint shopPaint = new Paint();
//        shopPaint.setColor(Color.GRAY);

        // ショップボタン
        if ("shop".equals(button_name) && buttonDownFlg) {
            shop_button.setBounds(calculateCoordX(730), calculateCoordY(230), calculateCoordX(870), calculateCoordY(370));
            shop_button.draw(can);
        } else {
            shop_button.setBounds(calculateCoordX(740), calculateCoordY(240), calculateCoordX(860), calculateCoordY(360));
            shop_button.draw(can);
        }
        rectMap.put("shop", new Rect(calculateCoordX(740), calculateCoordY(240), calculateCoordX(860), calculateCoordY(360)));

        // 最新ステージボタン
        if ("last".equals(button_name) && buttonDownFlg) {
            last_button.setBounds(calculateCoordX(65), calculateCoordY(1430), calculateCoordX(205), calculateCoordY(1570));
            last_button.draw(can);
        } else {
            last_button.setBounds(calculateCoordX(75), calculateCoordY(1440), calculateCoordX(195), calculateCoordY(1560));
            last_button.draw(can);
        }
        rectMap.put("last",  new Rect(calculateCoordX(75), calculateCoordY(1440), calculateCoordX(195), calculateCoordY(1560)));

        // 最新ステージボタン
        if ("next".equals(button_name) && buttonDownFlg) {
            next_button.setBounds(calculateCoordX(275), calculateCoordY(1430), calculateCoordX(415), calculateCoordY(1570));
            next_button.draw(can);
        } else {
            next_button.setBounds(calculateCoordX(285), calculateCoordY(1440), calculateCoordX(405), calculateCoordY(1560));
            next_button.draw(can);
        }
        rectMap.put("next",  new Rect(calculateCoordX(285), calculateCoordY(1440), calculateCoordX(405), calculateCoordY(1560)));

        // 最新ステージボタン
        if ("pre".equals(button_name) && buttonDownFlg) {
            pre_button.setBounds(calculateCoordX(485), calculateCoordY(1430), calculateCoordX(625), calculateCoordY(1570));
            pre_button.draw(can);
        } else {
            pre_button.setBounds(calculateCoordX(495), calculateCoordY(1440), calculateCoordX(615), calculateCoordY(1560));
            pre_button.draw(can);
        }
        rectMap.put("pre",  new Rect(calculateCoordX(495), calculateCoordY(1440), calculateCoordX(615), calculateCoordY(1560)));

        // 最新ステージボタン
        if ("first".equals(button_name) && buttonDownFlg) {
            first_button.setBounds(calculateCoordX(695), calculateCoordY(1430), calculateCoordX(835), calculateCoordY(1570));
            first_button.draw(can);
        } else {
            first_button.setBounds(calculateCoordX(705), calculateCoordY(1440), calculateCoordX(825), calculateCoordY(1560));
            first_button.draw(can);
        }
        rectMap.put("first",  new Rect(calculateCoordX(705), calculateCoordY(1440), calculateCoordX(825), calculateCoordY(1560)));

    }


    /**
     * お金を描画します
     *
     * @param Money
     */
    public void drawMoney(String Money) {

        gold_area.setBounds(calculateCoordX(230), calculateCoordY(240), calculateCoordX(530), calculateCoordY(300));
        gold_area.draw(can);
        coin.setBounds(calculateCoordX(230), calculateCoordY(230), calculateCoordX(300), calculateCoordY(300));
        coin.draw(can);

        drawNumberString(Money, 330, 250, 30);
    }
    public void callback() {
        super.callback();

        backGround.setCallback(null);

        stage_clear_1.setCallback(null);
        stage_clear_2.setCallback(null);
        stage_clear_3.setCallback(null);
        stage_init_1.setCallback(null);
        stage_lock_1.setCallback(null);

        game_start_button.setCallback(null);

        stage_panel_1.setCallback(null);

        // コインのアイコン
        coin.setCallback(null);

        // 枠
        stage_frame.setCallback(null);
        // ゴールドの表示枠
        gold_area.setCallback(null);

        // ボタン
        shop_button .setCallback(null);
        last_button .setCallback(null);
        next_button .setCallback(null);
        pre_button  .setCallback(null);
        first_button.setCallback(null);


    }
}
