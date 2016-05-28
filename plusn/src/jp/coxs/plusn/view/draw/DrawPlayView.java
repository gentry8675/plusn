package jp.coxs.plusn.view.draw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.coxs.plusn.R;
import jp.coxs.plusn.bean.BallClearBean;
import jp.coxs.plusn.bean.PointBean;
import jp.coxs.plusn.bean.SkillBean;
import jp.coxs.plusn.bean.TargetInfoBean;
import jp.coxs.plusn.view.play.BallMap;
import jp.coxs.plusn.view.play.ClearHistory;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * PlayViewの描画クラス
 *
 * @author Gen
 */
public class DrawPlayView extends DrawView {
    // 画面座標幅
    //    private static final int SCREEN_W = 900;
    // 画面座標高
    //    private static final int SCREEN_H = 1600;

    // 座標係数
    //    private float diff = 0;
    //    private float diffX = 0;
    //    private float diffY = 0;

    private Drawable back_ground; // 背景
    private Drawable play_frame; // 背景

    // ボールを描く
    private Drawable ball1; // ボール１
    private Drawable ball2; // ボール２
    private Drawable ball3; // ボール３
    private Drawable ball4; // ボール４
    private Drawable ball5; // ボール５
    private Drawable ball6; // ボール６
    private Drawable ball7; // ボール７
    private Drawable ball8; // ボール８
    private Drawable ball9; // ボール９
    private Drawable ball0; // ボール０
    private Drawable coin; // ボールＮ

    private Drawable bomb; // 爆弾ボール

    private Drawable tile; // タイル

    private Drawable move; // 移動マック

//    private Drawable congratulations; // ゲームクリア
//    private Drawable gameover; // ゲームオーバー

    //    private Canvas can = null;

    //    private Drawable num_0;
    //    private Drawable num_1;
    //    private Drawable num_2;
    //    private Drawable num_3;
    //    private Drawable num_4;
    //    private Drawable num_5;
    //    private Drawable num_6;
    //    private Drawable num_7;
    //    private Drawable num_8;
    //    private Drawable num_9;
    //    private Drawable num_p;
    //    private Drawable num_t;
    //    private Drawable num_x;
    //    private Drawable num_s;
    //    private Drawable num_c;

//    private Drawable tree2x2; // 木
    private Drawable obstacle; // 木
    private Drawable nebula; // 木

    private Drawable combo_rainbow;
    private Drawable combo_text;

//    private Drawable map_button;
    private Drawable stage_list_button;
//    private Drawable back_button;
    private Drawable retry_button;
    private Drawable next_stage_button;
    private Drawable retira_button;
    private Drawable continue_button;
    private Drawable start_button;

    private Drawable explosion;

    private Drawable run_1;
    private Drawable robot_stand;
//    private Drawable run_2;
//    private Drawable run_3;
//    private Drawable run_4;
//    private Drawable run_5;
//    private Drawable run_6;
//    private Drawable run_7;
//    private Drawable run_8;

    private Drawable flowing_down_1;
    private Drawable flowing_down_2;
    private Drawable flowing_up_1;
    private Drawable flowing_up_2;
    private Drawable flowing_left_1;
    private Drawable flowing_left_2;
    private Drawable flowing_right_1;
    private Drawable flowing_right_2;

    private Drawable rocket_punch;

    private Drawable thunder;

    private Drawable hand;

    private Drawable selected;
    private Drawable batsu;

    private Drawable facebook;
    private Drawable twitter;

    // ゴールドのエリア
    private Drawable gold_area;

    // 一時停止ボタン
    private Drawable pause_button;
    private Drawable restart_button;


    private Drawable score_icon;

    private Drawable time_icon;

    // NEXTボール
    private Drawable next_text;
    private Drawable next_area;

    private Drawable combo_area;

    private Drawable stage_clear_text;
    private Drawable pause_text;
    private Drawable game_over_text;
    private Drawable clear_panel_xl;
    private Drawable clear_panel_l;
    private Drawable clear_panel_m;
    private Drawable clear_panel_s;
    private Drawable over_panel_m;

    private Drawable ice;

    private Drawable goal_flag;

    private List<Drawable> skillImageList = new ArrayList<Drawable>();
    /**
     * コンストラクター
     *
     * @param diff 座標係数
     * @param resources
     */
    public DrawPlayView(Resources resources) {

        super(resources);

        back_ground = resources.getDrawable(R.drawable.back_ground);
        play_frame = resources.getDrawable(R.drawable.play_frame);

        // ボールのresources
        ball1 = resources.getDrawable(R.drawable.ball1);
        ball2 = resources.getDrawable(R.drawable.ball2);
        ball3 = resources.getDrawable(R.drawable.ball3);
        ball4 = resources.getDrawable(R.drawable.ball4);
        ball5 = resources.getDrawable(R.drawable.ball5);
        ball6 = resources.getDrawable(R.drawable.ball6);
        ball7 = resources.getDrawable(R.drawable.ball7);
        ball8 = resources.getDrawable(R.drawable.ball8);
        ball9 = resources.getDrawable(R.drawable.ball9);
        coin = resources.getDrawable(R.drawable.coin);

        bomb = resources.getDrawable(R.drawable.bomb);

        // タイル
        tile = resources.getDrawable(R.drawable.tile);

        // 矢印
        move = resources.getDrawable(R.drawable.move);

        // コンボエリア
        combo_rainbow = resources.getDrawable(R.drawable.combo_rainbow);
        combo_text = resources.getDrawable(R.drawable.combo);

        stage_list_button = resources.getDrawable(R.drawable.stage_list_button);

        retry_button = resources.getDrawable(R.drawable.retry_button);
        next_stage_button = resources.getDrawable(R.drawable.next_stage_button);
        retira_button = resources.getDrawable(R.drawable.retira_button);
        continue_button = resources.getDrawable(R.drawable.continue_button);
        start_button = resources.getDrawable(R.drawable.start_button);

        explosion = resources.getDrawable(R.drawable.explosion);

        run_1 = resources.getDrawable(R.drawable.robot_run);
        robot_stand = resources.getDrawable(R.drawable.robot_stand);

        obstacle = resources.getDrawable(R.drawable.obstacle);
        nebula = resources.getDrawable(R.drawable.nebula);

        // 水流
        flowing_down_1 = resources.getDrawable(R.drawable.flowing_down_1);
        flowing_down_2 = resources.getDrawable(R.drawable.flowing_down_2);
        flowing_up_1 = resources.getDrawable(R.drawable.flowing_up_1);
        flowing_up_2 = resources.getDrawable(R.drawable.flowing_up_2);
        flowing_left_1 = resources.getDrawable(R.drawable.flowing_left_1);
        flowing_left_2 = resources.getDrawable(R.drawable.flowing_left_2);
        flowing_right_1 = resources.getDrawable(R.drawable.flowing_right_1);
        flowing_right_2 = resources.getDrawable(R.drawable.flowing_right_2);

        rocket_punch = resources.getDrawable(R.drawable.rocket_punch);

        hand = resources.getDrawable(R.drawable.hand);

        selected = resources.getDrawable(R.drawable.selected);

        batsu = resources.getDrawable(R.drawable.batsu);

        facebook = resources.getDrawable(R.drawable.facebook_ico);

        twitter = resources.getDrawable(R.drawable.twitter_ico);

        gold_area = resources.getDrawable(R.drawable.play_gold_area);

        pause_button = resources.getDrawable(R.drawable.pause_button);
        restart_button = resources.getDrawable(R.drawable.restart_button);

        score_icon = resources.getDrawable(R.drawable.score_icon);
        time_icon = resources.getDrawable(R.drawable.time_icon);

        next_text = resources.getDrawable(R.drawable.next_text);
        next_area = resources.getDrawable(R.drawable.next_area);

        combo_area = resources.getDrawable(R.drawable.combo_area);

        stage_clear_text = resources.getDrawable(R.drawable.stage_clear_text);
        pause_text = resources.getDrawable(R.drawable.pause_text);
        game_over_text = resources.getDrawable(R.drawable.game_over_text);

        over_panel_m = resources.getDrawable(R.drawable.over_panel_m);
        clear_panel_xl = resources.getDrawable(R.drawable.clear_panel_xl);
        clear_panel_l = resources.getDrawable(R.drawable.clear_panel_l);
        clear_panel_m = resources.getDrawable(R.drawable.clear_panel_m);
        clear_panel_s = resources.getDrawable(R.drawable.clear_panel_s);

        ice = resources.getDrawable(R.drawable.ice);

        goal_flag = resources.getDrawable(R.drawable.goal_flag);

    }

    /**
     * 描画キャンバスを設定します
     *
     * @param can キャンバス
     */
    public void setCanvas(Canvas can) {
        super.Init(can);
    }

    /**
     * 背景を描画します
     */
    public void drawBackGround(int stageNum) {
        back_ground.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        back_ground.draw(can);
        next_area.setBounds(calculateCoordX(30), calculateCoordY(440), calculateCoordX(870), calculateCoordY(500));
        next_area.draw(can);
        next_text.setBounds(calculateCoordX(30), calculateCoordY(425), calculateCoordX(230), calculateCoordY(500));
        next_text.draw(can);
    }

    /**
     * ボタンを描画します
     *
     * @param btnKbn
     * @param menuShowFlg
     */
    public void drawButton(String btnKbn, boolean menuShowFlg) {

        if (menuShowFlg) {
            restart_button.setBounds(calculateCoordX(740), calculateCoordY(40), calculateCoordX(860), calculateCoordY(160));
            restart_button.draw(can);
            rectMap.put("restart", new Rect(calculateCoordX(740), calculateCoordY(40), calculateCoordX(860), calculateCoordY(160)));
        } else {
            pause_button.setBounds(calculateCoordX(740), calculateCoordY(40), calculateCoordX(860), calculateCoordY(160));
            pause_button.draw(can);
            rectMap.put("menu", new Rect(calculateCoordX(740), calculateCoordY(40), calculateCoordX(860), calculateCoordY(160)));
        }
    }

    public void drawBall(int x, int y, int ballType) {
        x = (x * 120) + 30;
        y = (y * 120) + 530;
        drawBall(x, y, ballType, 120);
    }

    /**
     * ボールを描画します
     *
     * @param x 座標X
     * @param y 座標Y
     * @param ballType ボールの種類
     * @param can キャンバス
     */
//    public void drawBall(int x, int y, int ballType, String a) {
//        x = (x * 120) + 30;
//        y = (y * 120) + 530;
//        int animationType;
//        int colorType;
//        if (ballType > 100) {
//            animationType = ballType % 100;
//            colorType = ballType / 100;
//        } else {
//            animationType = 0;
//            colorType = ballType;
//        }
//        int left = calculateCoordX(x);
//        int top = calculateCoordY(y);
//        int right = calculateCoordX(x + 118);
//        int bottom = calculateCoordY(y + 118);
//        if (animationType == 1) {
//            left = calculateCoordX(x - 10);
//            top = calculateCoordY(y + 20);
//            right = calculateCoordX(x + 128);
//        } else if (animationType == 3) {
//            left = calculateCoordX(x + 10);
//            top = calculateCoordY(y - 20);
//            right = calculateCoordX(x + 108);
//        }
//
//        if (colorType == 1) {
//            ball1.setBounds(left, top, right, bottom);
//            ball1.draw(can);
//        } else if (colorType == 2) {
//            ball2.setBounds(left, top, right, bottom);
//            ball2.draw(can);
//        } else if (colorType == 3) {
//            ball3.setBounds(left, top, right, bottom);
//            ball3.draw(can);
//        } else if (colorType == 4) {
//            ball4.setBounds(left, top, right, bottom);
//            ball4.draw(can);
//        } else if (colorType == 5) {
//            ball5.setBounds(left, top, right, bottom);
//            ball5.draw(can);
//        } else if (colorType == 6) {
//            ball6.setBounds(left, top, right, bottom);
//            ball6.draw(can);
//        } else if (colorType == 7) {
//            ball7.setBounds(left, top, right, bottom);
//            ball7.draw(can);
//        } else if (colorType == 8) {
//            ball8.setBounds(left, top, right, bottom);
//            ball8.draw(can);
//        } else if (colorType == 9) {
//            ball9.setBounds(left, top, right, bottom);
//            ball9.draw(can);
//        } else if (colorType == 10) {
//            ball0.setBounds(left, top, right, bottom);
//            ball0.draw(can);
//        } else if (colorType == 11) {
//            coin.setBounds(left, top, right, bottom);
//            coin.draw(can);
//        } else if (colorType == 21) {
//            bomb.setBounds(left, top, right, bottom);
//            bomb.draw(can);
//        } else if (colorType == 31) {
//            tree1x1.setBounds(left, top, right, bottom);
//            tree1x1.draw(can);
//        } else if (colorType == 32) {
////            tree1x1.setBounds(left, top, right, bottom);
////            tree1x1.draw(can);
//        }
//    }

    /**
     * ボールを描画します
     *
     * @param x 座標X
     * @param y 座標Y
     * @param ballType ボールの種類
     * @param can キャンバス
     */
    public void drawBall(int x, int y, int ballType, int size) {
        int animationType;
        int colorType;
        if (ballType > 100) {
            animationType = ballType % 100;
            colorType = ballType / 100;
        } else {
            animationType = 0;
            colorType = ballType;
        }
        int left = calculateCoordX(x + 1);
        int top = calculateCoordY(y + 1);
        int right = calculateCoordX(x + size - 1);
        int bottom = calculateCoordY(y + size - 1);
        if (animationType == 1) {
            left = calculateCoordX(x - 10);
            top = calculateCoordY(y + 20);
            right = calculateCoordX(x + size + 9);
        } else if (animationType == 3) {
            left = calculateCoordX(x + 10);
            top = calculateCoordY(y - 20);
            right = calculateCoordX(x + size + 9);
        }

        if (colorType == 1) {
            ball1.setBounds(left, top, right, bottom);
            ball1.draw(can);
        } else if (colorType == 2) {
            ball2.setBounds(left, top, right, bottom);
            ball2.draw(can);
        } else if (colorType == 3) {
            ball3.setBounds(left, top, right, bottom);
            ball3.draw(can);
        } else if (colorType == 4) {
            ball4.setBounds(left, top, right, bottom);
            ball4.draw(can);
        } else if (colorType == 5) {
            ball5.setBounds(left, top, right, bottom);
            ball5.draw(can);
        } else if (colorType == 6) {
            ball6.setBounds(left, top, right, bottom);
            ball6.draw(can);
        } else if (colorType == 7) {
            ball7.setBounds(left, top, right, bottom);
            ball7.draw(can);
        } else if (colorType == 8) {
            ball8.setBounds(left, top, right, bottom);
            ball8.draw(can);
        } else if (colorType == 9) {
            ball9.setBounds(left, top, right, bottom);
            ball9.draw(can);
        } else if (colorType == 10) {
            ball0.setBounds(left, top, right, bottom);
            ball0.draw(can);
        } else if (colorType == 11) {
            ball1.setBounds(left, top, right, bottom);
            ball1.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 12) {
            ball2.setBounds(left, top, right, bottom);
            ball2.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 13) {
            ball3.setBounds(left, top, right, bottom);
            ball3.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 14) {
            ball4.setBounds(left, top, right, bottom);
            ball4.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 15) {
            ball5.setBounds(left, top, right, bottom);
            ball5.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 16) {
            ball6.setBounds(left, top, right, bottom);
            ball6.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 17) {
            ball7.setBounds(left, top, right, bottom);
            ball7.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 18) {
            ball8.setBounds(left, top, right, bottom);
            ball8.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 19) {
            ball9.setBounds(left, top, right, bottom);
            ball9.draw(can);
            ice.setBounds(left, top, right, bottom);
            ice.draw(can);
        } else if (colorType == 21) {
            bomb.setBounds(left, top, right, bottom);
            bomb.draw(can);
        } else if (colorType == 31) {
            obstacle.setBounds(left, top, right, bottom);
            obstacle.draw(can);
        } else if (colorType == 33) {
            nebula.setBounds(left, top, right, bottom);
            nebula.draw(can);
        }
    }

    /**
     * ボールの移動経路を描画します
     *
     * @param x
     * @param y
     * @param inType
     * @param outType
     */
    public void drawArrow(PointBean point) {

        int x = (point.getX() * 120) + 30;
        int y = (point.getY() * 120) + 530;
        int left = calculateCoordX(x);
        int top = calculateCoordY(y);
        int right = calculateCoordX(x + 120);
        int bottom = calculateCoordY(y + 120);

        move.setBounds(left, top, right, bottom);
        move.draw(can);
    }

    /**
     * タイルを描画します
     *
     * @param x 座標X
     * @param y 座標Y
     * @param tileType ボールの種類
     */
    public void drawTile(int x, int y, int tileType) {
        int left = calculateCoordX((x * 120) + 30);
        int top = calculateCoordY((y * 120) + 530);
        int right = calculateCoordX((x * 120) + 120 + 30);
        int bottom = calculateCoordY((y * 120) + 120 + 530);
        if (tileType > -1) {
            tile.setBounds(left, top, right, bottom);
            tile.draw(can);
        }

        if (tileType == -5) {
            right = calculateCoordX((x * 120) + 240 + 30);
            bottom = calculateCoordY((y * 120) + 240 + 530);

//            tree2x2.setBounds(left, top, right, bottom);
//            tree2x2.draw(can);
        }
        if (tileType == -6) {
            tile.setBounds(left, top, right, bottom);
            tile.draw(can);
            //            tree1x1.setBounds(left, top, right, bottom);
            //            tree1x1.draw(can);
        }
    }

    //
    //    /**
    //     * 座標を計算します
    //     *
    //     * @param coordValue 座標
    //     * @return 結果座標
    //     */
    //    private int calculateCoordX(int coordValue) {
    //        return (int) ((coordValue * diff) + diffX);
    //    }
    //
    //    /**
    //     * 座標を計算します
    //     *
    //     * @param coordValue 座標
    //     * @return 結果座標
    //     */
    //    private int calculateCoordY(int coordValue) {
    //        return (int) ((coordValue * diff) + diffY);
    //    }

    /**
     * NEXTボールを描画します
     *
     * @param x 座標X
     * @param y 座標Y
     * @param ballType ボールの種類
     * @param can キャンバス
     */
    public void drawNextBall(int ballType1, int ballType2, int ballType3) {

        drawBall(250, 420, ballType1, 80);
        drawBall(350, 420, ballType2, 80);
        drawBall(450, 420, ballType3, 80);
    }

    /**
     * 得点を描画します
     *
     * @param score
     */
    public void drawScore(int score) {

        gold_area.setBounds(calculateCoordX(290), calculateCoordY(50), calculateCoordX(640), calculateCoordY(100));
        gold_area.draw(can);
        score_icon.setBounds(calculateCoordX(290), calculateCoordY(30), calculateCoordX(430), calculateCoordY(100));
        score_icon.draw(can);

        String sScore = String.valueOf(score);

        drawNumberString(sScore, 620 - (sScore.length() * 30), 50, 30);
    }

    /**
     * コンボエリアを描画します
     *
     * @param comboCount
     * @param timeCount
     */
    public void drawComboArea(int comboCount, int timeCount) {
        combo_area.setBounds(calculateCoordX(30), calculateCoordY(500), calculateCoordX(870), calculateCoordY(530));
        combo_area.draw(can);
        ClipDrawable abc = new ClipDrawable(combo_rainbow, Gravity.LEFT, 1);
        abc.setLevel(100 * (100 - timeCount));
        abc.setBounds(calculateCoordX(30), calculateCoordY(500), calculateCoordX(870), calculateCoordY(530));
        abc.draw(can);
    }

    public int drawGameClearText(int score, int enball, Map<String, Integer> bonusMap, int count, int stageId) {
        // 移動速度
        count += 70;

        ////////
        //タイトル
        int start = 1500;
        int top = 250;

        int titleTop = start - count;
        if (titleTop < top) {
            titleTop = top;
        }
        // ステージクリア文字
        stage_clear_text.setBounds(calculateCoordX(30), calculateCoordY(titleTop), calculateCoordX(870), calculateCoordY(titleTop + 250));
        stage_clear_text.draw(can);
        start += 500;
        top += 250;

        ////////
        //ヘッダ
        int headerTop = start - count;
        if (headerTop < top) {
            headerTop = top;
        }
        clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(headerTop), calculateCoordX(870), calculateCoordY(headerTop + 50));
        clear_panel_s.draw(can);
        drawText("　　　　 RESULT　POINT", 150, headerTop + 40, diff * 40, Color.BLUE);
        coin.setBounds(calculateCoordX(730), calculateCoordY(headerTop + 5), calculateCoordX(770), calculateCoordY(headerTop + 45));
        coin.draw(can);
        start += 500;
        top += 50;

        ////////
        //スコア
        int scoreTop = start - count;
        if (scoreTop < top) {
            scoreTop = top;
        }
        clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(scoreTop), calculateCoordX(870), calculateCoordY(scoreTop + 50));
        clear_panel_s.draw(can);
        drawText("SCORE : ", 150, scoreTop + 40, diff * 40, Color.BLUE);
        drawNumberString(String.valueOf(score), 450 - (String.valueOf(score).length() * 30), scoreTop + 5, 30);
        drawNumberString(String.valueOf(score), 620 - (String.valueOf(score).length() * 30), scoreTop + 5, 30);
        drawNumberString(String.valueOf(enball), 770 - (String.valueOf(enball).length() * 30), scoreTop + 5, 30);

        start += 500;
        top += 50;

        ////////
        //プレイ時間
        int timeTop = start - count;
        if (timeTop < top) {
            timeTop = top;
        }
        clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(timeTop), calculateCoordX(870), calculateCoordY(timeTop + 50));
        clear_panel_s.draw(can);
        drawText("TIME : ", 150, timeTop + 40, diff * 40, Color.BLUE);
        drawPlayTime(bonusMap.get("playTimeCount"), 450, timeTop + 5, 30);
        String timeBonus = "0";
        if (bonusMap.get("playTimeCount") < 60) {
            timeBonus = "2000";
        } else if (bonusMap.get("playTimeCount") < 120) {
            timeBonus = "1500";
        } else if (bonusMap.get("playTimeCount") < 180) {
            timeBonus = "1000";
        } else if (bonusMap.get("playTimeCount") < 240) {
            timeBonus = "500";
        }
        drawNumberString(timeBonus, 620 - (timeBonus.length() * 30), timeTop + 5, 30);
        drawNumberString(timeBonus.substring(0, timeBonus.length() - 2), 770 - ((timeBonus.length() - 2) * 30), timeTop + 5, 30);

        start += 500;
        top += 50;

        ////////
        //ステップ数
        if (bonusMap.get("stepCount") > 0) {
            int stepTop = start - count;
            if (stepTop < top) {
                stepTop = top;
            }
            clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(stepTop), calculateCoordX(870), calculateCoordY(stepTop + 50));
            clear_panel_s.draw(can);
            drawText("STEP : ", 150, stepTop + 40, diff * 40, Color.BLUE);
            // 実績
            drawNumberString(String.valueOf(bonusMap.get("stepCount")), 450 - (String.valueOf(bonusMap.get("stepCount")).length() * 30), stepTop + 5, 30);
            String stepScore = String.valueOf(bonusMap.get("stepCount") * 200);
            // 得点
            drawNumberString(stepScore, 620 - (stepScore.length() * 30), stepTop + 5, 30);
            // 金
            drawNumberString(stepScore.substring(0, stepScore.length() - 2), 770 - ((stepScore.length() - 2) * 30), stepTop + 5, 30);
            start += 500;
            top += 50;
        }
        if (bonusMap.get("all") > 0) {
            int allClearTop = start - count;
            if (allClearTop < top) {
                allClearTop = top;
            }

            clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(allClearTop), calculateCoordX(870), calculateCoordY(allClearTop + 50));
            clear_panel_s.draw(can);

            drawText("ALL CLEAR : ", 150, allClearTop + 40, diff * 40, Color.BLUE);

            String allClearScore = String.valueOf(bonusMap.get("all"));

            drawNumberString(allClearScore, 620 - (allClearScore.length() * 30), allClearTop + 5, 30);

            drawNumberString(allClearScore.substring(0, allClearScore.length() - 2), 770 - ((allClearScore.length() - 2) * 30), allClearTop + 5, 30);
            start += 500;
            top += 50;
        }
        if (bonusMap.get("bombCount") > 0) {
            int itemTop = start - count;
            if (itemTop < top) {
                itemTop = top;
            }

            clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(itemTop), calculateCoordX(870), calculateCoordY(itemTop + 50));
            clear_panel_s.draw(can);

            drawText("ITEM :  ", 150, itemTop + 40, diff * 40, Color.BLUE);

            String bombCount = "x" + bonusMap.get("bombCount");
            bomb.setBounds(calculateCoordX(450 - (bombCount.length() * 30) - 50), calculateCoordY(itemTop), calculateCoordX(450 - (bombCount.length() * 30)), calculateCoordY(itemTop + 50));
            bomb.draw(can);
            drawNumberString(bombCount, 450 - (bombCount.length() * 30), itemTop + 5, 30);
            String itemSrore = String.valueOf(bonusMap.get("bombCount") * 1000);
            drawNumberString(itemSrore, 620 - (itemSrore.length() * 30), itemTop + 5, 30);
            drawNumberString(itemSrore.substring(0, itemSrore.length() - 2), 770 - ((itemSrore.length() - 2) * 30), itemTop + 5, 30);
            start += 500;
            top += 50;
        }

        ///////
        //合計
        int totalTop = start - count;
        if (totalTop < top) {
            totalTop = top;
        }
        clear_panel_s.setBounds(calculateCoordX(30), calculateCoordY(totalTop), calculateCoordX(870), calculateCoordY(totalTop + 50));
        clear_panel_s.draw(can);

        drawText("TOTAL :  ", 150, totalTop + 40, diff * 40, Color.BLUE);

        String totalScore = String.valueOf(bonusMap.get("totalPoint"));
        drawNumberString(totalScore, 620 - (totalScore.length() * 30), totalTop + 5, 30);

        int p = ((bonusMap.get("totalPoint") - score) / 100) + enball;
        drawNumberString(String.valueOf(p), 770 - (String.valueOf(p).length() * 30), totalTop + 5, 30);
        start += 500;
        top += 50;

        ///////
        //ボタン
        // TODO:
        int mapButtonTop = start - count;
        if (mapButtonTop < top) {
            mapButtonTop = top;
        }
        clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(mapButtonTop), calculateCoordX(870), calculateCoordY(mapButtonTop + 100));
        clear_panel_m.draw(can);

        // マップボタン
        stage_list_button.setBounds(calculateCoordX(200), calculateCoordY(mapButtonTop + 10), calculateCoordX(700), calculateCoordY(mapButtonTop + 90));
        stage_list_button.draw(can);
        rectMap.put("map", new Rect(calculateCoordX(200), calculateCoordY(mapButtonTop + 10), calculateCoordX(700), calculateCoordY(mapButtonTop + 90)));
        start += 500;
        top += 100;

        ///////
        //リトライボタン
        int retryButtonTop = start - count;
        if (retryButtonTop < top) {
            retryButtonTop = top;
        }
        clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(retryButtonTop), calculateCoordX(870), calculateCoordY(retryButtonTop + 100));
        clear_panel_m.draw(can);

        // リトライ
        retry_button.setBounds(calculateCoordX(200), calculateCoordY(retryButtonTop + 10), calculateCoordX(700), calculateCoordY(retryButtonTop + 90));
        retry_button.draw(can);
        rectMap.put("retry", new Rect(calculateCoordX(200), calculateCoordY(retryButtonTop + 10), calculateCoordX(700), calculateCoordY(retryButtonTop + 90)));
        start += 500;
        top += 100;

        if (stageId < 195) {

            ///////
            //次期ステージボタン
            int nextButtonTop = start - count;
            if (nextButtonTop < top) {
                nextButtonTop = top;
            }
            clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(nextButtonTop), calculateCoordX(870), calculateCoordY(nextButtonTop + 100));
            clear_panel_m.draw(can);

            // 次期ステージボタン
            next_stage_button.setBounds(calculateCoordX(200), calculateCoordY(nextButtonTop + 10), calculateCoordX(700), calculateCoordY(nextButtonTop + 90));
            next_stage_button.draw(can);
            rectMap.put("next", new Rect(calculateCoordX(200), calculateCoordY(nextButtonTop + 10), calculateCoordX(700), calculateCoordY(nextButtonTop + 90)));

            start += 500;
            top += 100;
        }

        ///////
        //SNS
        int snsTop = start - count;
        if (snsTop < top) {
            snsTop = top;
        }
        clear_panel_l.setBounds(calculateCoordX(30), calculateCoordY(snsTop), calculateCoordX(870), calculateCoordY(snsTop + 150));
        clear_panel_l.draw(can);

        facebook.setBounds(calculateCoordX(300), calculateCoordY(snsTop + 15), calculateCoordX(420), calculateCoordY(snsTop + 135));
        facebook.draw(can);
        rectMap.put("facebook", new Rect(calculateCoordX(300), calculateCoordY(snsTop + 15), calculateCoordX(420), calculateCoordY(snsTop + 135)));

        twitter.setBounds(calculateCoordX(480), calculateCoordY(snsTop + 15), calculateCoordX(600), calculateCoordY(snsTop + 135));
        twitter.draw(can);
        rectMap.put("twitter", new Rect(calculateCoordX(480), calculateCoordY(snsTop + 15), calculateCoordX(600), calculateCoordY(snsTop + 135)));

        return count;
    }

    public void drawGameOverText() {

        game_over_text.setBounds(calculateCoordX(30), calculateCoordY(250), calculateCoordX(870), calculateCoordY(500));
        game_over_text.draw(can);
        over_panel_m.setBounds(calculateCoordX(30), calculateCoordY(500), calculateCoordX(870), calculateCoordY(600));
        over_panel_m.draw(can);
        over_panel_m.setBounds(calculateCoordX(30), calculateCoordY(600), calculateCoordX(870), calculateCoordY(700));
        over_panel_m.draw(can);

        stage_list_button.setBounds(calculateCoordX(200), calculateCoordY(510), calculateCoordX(700), calculateCoordY(590));
        stage_list_button.draw(can);
        rectMap.put("map", new Rect(calculateCoordX(200), calculateCoordY(510), calculateCoordX(700), calculateCoordY(590)));
        retry_button.setBounds(calculateCoordX(200), calculateCoordY(610), calculateCoordX(700), calculateCoordY(690));
        retry_button.draw(can);
        rectMap.put("retry", new Rect(calculateCoordX(200), calculateCoordY(610), calculateCoordX(700), calculateCoordY(690)));

    }


    public void initSkillImage(List<SkillBean> skillList) {

        for (int i = 0; i < skillList.size(); i++) {
            int skillImageId = resources.getIdentifier(skillList.get(i).getSkillImageName(), "drawable", "jp.coxs.plusn");
            skillImageList.add(resources.getDrawable(skillImageId));
        }

    }

    /**
     * スキルエリアを描きます
     */
    public void drawSkill(List<SkillBean> skillList) {

        for (int i = 0; i < skillList.size(); i++) {
            Rect rect = new Rect(calculateCoordX(75 + (i * 210)), calculateCoordY(1440), calculateCoordX(75 + (i * 210) + 120), calculateCoordY(1560));

            skillImageList.get(i).setBounds(rect);
            skillImageList.get(i).draw(can);

            String skillcount = String.valueOf(skillList.get(i).getSkillCount());

            drawNumberString(skillcount, 75 + (i * 210) + 120 - (skillcount.length() * 40), 1500, 40);

            rectMap.put("skill_" + (i + 1), rect);
        }
    }

    /**
     * スキルボタンの範囲を取得します。
     *
     * @param skillNum
     * @return
     */

    public void drawComboText(int comboCount, int x, int y, int size) {

        String combo = String.valueOf(comboCount);
        drawNumberString(combo, 100, 500 - (size / 2), size);
        combo_text.setBounds(calculateCoordX(100 + (size * combo.length())), calculateCoordY(500 - (size / 2)), calculateCoordX(100 + (size * (5 + combo.length()))), calculateCoordY(500 + size));
        combo_text.draw(can);
    }

    public void drawFrame() {
        play_frame.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        play_frame.draw(can);
    }

    public void drawStep(int stepCount) {
        if (stepCount < 0) {
            stepCount = 0;
        }
        gold_area.setBounds(calculateCoordX(40), calculateCoordY(150), calculateCoordX(260), calculateCoordY(200));
        gold_area.draw(can);
        hand.setBounds(calculateCoordX(40), calculateCoordY(130), calculateCoordX(110), calculateCoordY(200));
        hand.draw(can);

        String step = String.valueOf(stepCount);
        drawNumberString(step, 240 - (step.length() * 20), 150, 30);
    }

    public int drawTargetProgress(int targetCount1, int targetTotal, int timecount) {

        Paint backPaint = new Paint();
        backPaint.setColor(Color.DKGRAY);
        backPaint.setAlpha(230);
        RectF backRect = new RectF(calculateCoordX(30), calculateCoordY(230), calculateCoordX(870), calculateCoordY(430));
        can.drawRoundRect(backRect, 5, 5, backPaint);

        int strId = resources.getIdentifier("target", "string", "jp.coxs.plusn");
        drawText(resources.getString(strId), 30, 270, 50 * diff, Color.WHITE);
//        Paint paint2 = new Paint();
//        paint2.setColor(Color.RED);
//        can.drawRect(new Rect(calculateCoordX(860), calculateCoordY(200), calculateCoordX(870), calculateCoordY(410)), paint2);
//        can.drawRect(new Rect(calculateCoordX(800), calculateCoordY(200), calculateCoordX(870), calculateCoordY(240)), paint2);

        goal_flag.setBounds(new Rect(calculateCoordX(790), calculateCoordY(230), calculateCoordX(870), calculateCoordY(430)));
        goal_flag.draw(can);

        float f1 = targetCount1;
        float f2 = targetTotal;

        int asd = 740;
        if (f1 < f2) {
            asd = 580;
        } else {
            Paint infoText = new Paint();
            infoText.setColor(Color.RED);
            infoText.setTextSize(80 * diff);
            can.drawText("BOUNS TIME", calculateCoordX(200), calculateCoordY(380), infoText);
        }

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        can.drawRect(new Rect(calculateCoordX(30), calculateCoordY(420), calculateCoordX(870), calculateCoordY(430)), paint);

        int d = (int) (f1 / f2 * asd);

        if (d > asd) {
            d = asd;
        }
        int count = (timecount / 4) % 8;
        int top = 300;
        if (count > 3) {
            top += (8 - count);
        } else {
            top += count;
        }
        Rect rect = new Rect(calculateCoordX(timecount+30), calculateCoordY(top), calculateCoordX(130 + timecount), calculateCoordY(top + 120));
        timecount++;
        if (timecount > d) {
            timecount = d;
            robot_stand.setBounds(rect);
            robot_stand.draw(can);
        } else {
            run_1.setBounds(rect);
            run_1.draw(can);
        }

        return timecount;
    }

    public void drawTarget(int targetType, int targetCount, int targetTotal) {

        String target = String.valueOf(targetCount + "/" + targetTotal);
        drawNumberString(target, 390, 220, 40);

        String total = String.valueOf(targetTotal);
        int makeRight = 360;
        Rect rect = new Rect(calculateCoordX(makeRight - 80), calculateCoordY(210), calculateCoordX(makeRight), calculateCoordY(290));

        if (targetType == 0) {
            score_icon.setBounds(calculateCoordX(makeRight - 160), calculateCoordY(210), calculateCoordX(makeRight), calculateCoordY(290));
            score_icon.draw(can);
        } else if (targetType == 1) {
            ball1.setBounds(rect);
            ball1.draw(can);
        } else if (targetType == 2) {
            ball2.setBounds(rect);
            ball2.draw(can);
        } else if (targetType == 3) {
            ball3.setBounds(rect);
            ball3.draw(can);
        } else if (targetType == 4) {
            ball4.setBounds(rect);
            ball4.draw(can);
        } else if (targetType == 5) {
            ball5.setBounds(rect);
            ball5.draw(can);
        } else if (targetType == 6) {
            ball6.setBounds(rect);
            ball6.draw(can);
        } else if (targetType == 7) {
            ball7.setBounds(rect);
            ball7.draw(can);
        } else if (targetType == 8) {
            ball8.setBounds(rect);
            ball8.draw(can);
        } else if (targetType == 9) {
            ball9.setBounds(rect);
            ball9.draw(can);
        } else if (targetType == 10) {
            coin.setBounds(rect);
            coin.draw(can);
        }
    }

    public void drawMenu(TargetInfoBean targetInfoBean, ClearHistory clearHistory, boolean ritaiaFlg, int StageId) {
        // TODO:
        Paint backPaint = new Paint();
        backPaint.setColor(Color.GRAY);
        backPaint.setAlpha(240);
        Rect backRect = new Rect(0, 0, 1300, 1700);
        can.drawRect(backRect, backPaint);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        clear_panel_l.setBounds(calculateCoordX(30), calculateCoordY(30), calculateCoordX(670), calculateCoordY(250));
        clear_panel_l.draw(can);
        drawNumberString(String.valueOf(StageId), 200, 80, 80);
        ////////
        //タイトル
        int top = 250;
        // ステージ中断文字
        pause_text.setBounds(calculateCoordX(30), calculateCoordY(top), calculateCoordX(870), calculateCoordY(top + 250));
        pause_text.draw(can);
        top += 250;

        // クリア目標を描画
        clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(top), calculateCoordX(870), calculateCoordY(top + 100));
        clear_panel_m.draw(can);

        int strId = resources.getIdentifier("target", "string", "jp.coxs.plusn");
        drawText(resources.getString(strId), 120, top + 40, diff * 40, Color.BLUE);

//        String clearInfo = stageInfoBean.getTargetInfoList().get(playType - 1).getInfo();
        int stringId = resources.getIdentifier(targetInfoBean.getInfo(), "string", "jp.coxs.plusn");
        List<String> strList = makeString(resources.getString(stringId), 26);
        for (int i = 0; i< strList.size(); i++) {
//            drawText(strList.get(i),                   280, 660 + (i * 50), 30 * diff, Color.WHITE);
            drawText(strList.get(i), 240, top + 40 + (i * 50), diff * 40, Color.BLUE);
        }

//        if (targetInfoBean.getInfo().length() > 13) {
//            drawText(targetInfoBean.getInfo().substring(0, 13), 240, top + 40, diff * 40, Color.BLUE);
//            drawText(targetInfoBean.getInfo().substring(13, targetInfoBean.getInfo().length()), 240, top + 90, diff * 40, Color.BLUE);
//        } else {
//            drawText(targetInfoBean.getInfo(), 240, top + 40, diff * 40, Color.BLUE);
//        }
        top += 100;

        clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(top), calculateCoordX(870), calculateCoordY(1170));
        clear_panel_xl.draw(can);
        // クリア履歴を描画
        if (clearHistory.getHistory1().size() > 0) {
            drawText("NEW", 120, top + 60, diff * 40, Color.BLUE);
            List<BallClearBean> ballClearBeanList = clearHistory.getHistory1();

            top += 10;
            int sumValue = drawHistory(ballClearBeanList, top);

//            int bottom = (ballClearBeanList.size() * 40);

            String strValue = ":  " + String.valueOf(sumValue);

            //            drawNumberString(strValue, 500 , ((top + bottom) /2 ) - 30 , 40);
            drawNumberString(strValue, 500, top + 10, 30);
            top += (ballClearBeanList.size() * 40) + 20;

            can.drawRect(calculateCoordX(50), calculateCoordY(top), calculateCoordX(850), calculateCoordY(top + 2), paint);
        }

        if (clearHistory.getHistory2().size() > 0) {
            List<BallClearBean> ballClearBeanList = clearHistory.getHistory2();
            drawText("2", 120, top + 60, diff * 40, Color.BLUE);
            top += 20;
            int sumValue = drawHistory(ballClearBeanList, top);
//            int sumValue = 0;
//            for (int i = 0; i < ballClearBeanList.size(); i++) {
//                BallClearBean ballClearBean = ballClearBeanList.get(i);
//                for (int j = 0; j < ballClearBean.getBallList().size(); j++) {
//                    if (ballClearBean.getBallList().get(j).getValue() == 1) {
//                        ball1.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball1.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 2) {
//                        ball2.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball2.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 3) {
//                        ball3.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball3.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 4) {
//                        ball4.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball4.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 5) {
//                        ball5.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball5.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 6) {
//                        ball6.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball6.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 7) {
//                        ball7.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball7.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 8) {
//                        ball8.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball8.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 9) {
//                        ball9.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball9.draw(can);
//                    }
//                }
//                sumValue += ballClearBean.getSumValue();
//            }

//            int bottom = (ballClearBeanList.size() * 40);

            String strValue = ":  " + String.valueOf(sumValue);

            drawNumberString(strValue, 500, top + 10, 30);

            top += (ballClearBeanList.size() * 40) + 20;

            can.drawRect(calculateCoordX(50), calculateCoordY(top), calculateCoordX(850), calculateCoordY(top + 2), paint);
        }

        if (clearHistory.getHistory3().size() > 0) {
            List<BallClearBean> ballClearBeanList = clearHistory.getHistory3();
            drawText("3", 120, top + 60, diff * 40, Color.BLUE);
            top += 20;
            int sumValue = drawHistory(ballClearBeanList, top);
//            int sumValue = 0;
//            for (int i = 0; i < ballClearBeanList.size(); i++) {
//                BallClearBean ballClearBean = ballClearBeanList.get(i);
//                for (int j = 0; j < ballClearBean.getBallList().size(); j++) {
//                    if (ballClearBean.getBallList().get(j).getValue() == 1) {
//                        ball1.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball1.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 2) {
//                        ball2.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball2.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 3) {
//                        ball3.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball3.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 4) {
//                        ball4.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball4.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 5) {
//                        ball5.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball5.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 6) {
//                        ball6.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball6.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 7) {
//                        ball7.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball7.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 8) {
//                        ball8.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball8.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 9) {
//                        ball9.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball9.draw(can);
//                    }
//                }
//                sumValue += ballClearBean.getSumValue();
//            }

//            int bottom = (ballClearBeanList.size() * 40);

            String strValue = ":  " + String.valueOf(sumValue);

            drawNumberString(strValue, 500, top + 10, 30);

            top += (ballClearBeanList.size() * 40) + 20;
            can.drawRect(calculateCoordX(50), calculateCoordY(top), calculateCoordX(850), calculateCoordY(top + 2), paint);
        }

        if (clearHistory.getHistory4().size() > 0) {
            List<BallClearBean> ballClearBeanList = clearHistory.getHistory4();
            drawText("4", 120, top + 60, diff * 40, Color.BLUE);
            top += 20;
            int sumValue = drawHistory(ballClearBeanList, top);
//            int sumValue = 0;
//            for (int i = 0; i < ballClearBeanList.size(); i++) {
//                BallClearBean ballClearBean = ballClearBeanList.get(i);
//                for (int j = 0; j < ballClearBean.getBallList().size(); j++) {
//                    if (ballClearBean.getBallList().get(j).getValue() == 1) {
//                        ball1.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball1.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 2) {
//                        ball2.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball2.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 3) {
//                        ball3.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball3.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 4) {
//                        ball4.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball4.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 5) {
//                        ball5.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball5.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 6) {
//                        ball6.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball6.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 7) {
//                        ball7.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball7.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 8) {
//                        ball8.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball8.draw(can);
//                    } else if (ballClearBean.getBallList().get(j).getValue() == 9) {
//                        ball9.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
//                        ball9.draw(can);
//                    }
//                }
//                sumValue += ballClearBean.getSumValue();
//            }

//            int bottom = (ballClearBeanList.size() * 40);

            String strValue = ":  " + String.valueOf(sumValue);

            drawNumberString(strValue, 500, top + 10, 30);
            top += (ballClearBeanList.size() * 40) + 20;
            can.drawRect(calculateCoordX(50), calculateCoordY(top), calculateCoordX(850), calculateCoordY(top + 2), paint);
        }

        clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(1170), calculateCoordX(870), calculateCoordY(1270));
        clear_panel_m.draw(can);
        if (ritaiaFlg) {
            // リタイアボタン
            retira_button.setBounds(calculateCoordX(200), calculateCoordY(1180), calculateCoordX(700), calculateCoordY(1260));
            retira_button.draw(can);
        } else {
            // マップボタン
            stage_list_button.setBounds(calculateCoordX(200), calculateCoordY(1180), calculateCoordX(700), calculateCoordY(1260));
            stage_list_button.draw(can);
        }
        rectMap.put("map", new Rect(calculateCoordX(200), calculateCoordY(1180), calculateCoordX(700), calculateCoordY(1260)));
        top += 100;

        clear_panel_m.setBounds(calculateCoordX(30), calculateCoordY(1270), calculateCoordX(870), calculateCoordY(1370));
        clear_panel_m.draw(can);
        // マップボタン
        continue_button.setBounds(calculateCoordX(200), calculateCoordY(1280), calculateCoordX(700), calculateCoordY(1360));
        continue_button.draw(can);
        rectMap.put("back", new Rect(calculateCoordX(200), calculateCoordY(1280), calculateCoordX(700), calculateCoordY(1360)));
    }

    private int drawHistory(List<BallClearBean> ballClearBeanList, int  top) {
        int sumValue = 0;
        for (int i = 0; i < ballClearBeanList.size(); i++) {
            BallClearBean ballClearBean = ballClearBeanList.get(i);
            List<PointBean> boolList = new ArrayList<PointBean>();
            for (int j = 0; j < ballClearBean.getBallList().size(); j++) {
                if (ballClearBean.getBallList().get(j).getValue() < 20) {
                    boolList.add(ballClearBean.getBallList().get(j));
                }
            }
            for (int j = 0; j < boolList.size(); j++) {
                if (boolList.get(j).getValue()% 10 == 1) {
                    ball1.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball1.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 2) {
                    ball2.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball2.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 3) {
                    ball3.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball3.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 4) {
                    ball4.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball4.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 5) {
                    ball5.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball5.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 6) {
                    ball6.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball6.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 7) {
                    ball7.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball7.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 8) {
                    ball8.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball8.draw(can);
                } else if (boolList.get(j).getValue()% 10 == 9) {
                    ball9.setBounds(calculateCoordX(200 + (j * 40)), calculateCoordY(top + (i * 40)), calculateCoordX(200 + (j * 40) + 40), calculateCoordY(top + (i * 40) + 40));
                    ball9.draw(can);
                }
            }
            sumValue += ballClearBean.getSumValue();
        }
        return sumValue;
    }

    public void drawExplosion(List<PointBean> pointList, int count) {

        for (int i = 0; i < pointList.size(); i++) {

            int left = calculateCoordX((pointList.get(i).getX() * 120) - 90 + (120 - count));
            int top = calculateCoordY((pointList.get(i).getY() * 120) + 410 + (120 - count));
            int right = calculateCoordX((pointList.get(i).getX() * 120) + 270 - (120 - count));
            int bottom = calculateCoordY((pointList.get(i).getY() * 120) + 770 - (120 - count));
            explosion.setBounds(left, top, right, bottom);
            explosion.draw(can);
        }
    }

    /**
     * プレイ時間を描く
     *
     * @param playTimeCount
     */
    public void drawPlayTime(int playTimeCount) {

        gold_area.setBounds(calculateCoordX(290), calculateCoordY(150), calculateCoordX(640), calculateCoordY(200));
        gold_area.draw(can);
        time_icon.setBounds(calculateCoordX(290), calculateCoordY(130), calculateCoordX(360), calculateCoordY(200));
        time_icon.draw(can);

        drawPlayTime(playTimeCount, 620, 150, 30);
    }

    /**
     * プレイ時間を描く
     *
     * @param playTimeCount
     */
    private void drawPlayTime(int playTimeCount, int x, int y, int size) {

        int minete = playTimeCount / 60;

        int seound = playTimeCount % 60;

        String time = minete + ":";
        if (seound < 10) {
            time += "0" + seound;
        } else {
            time += seound;
        }
        drawNumberString(time, x - (time.length() * size), y, size);
    }

    public void drawSkillPanel(SkillBean skillBean) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(220);
        Rect rectL = new Rect(0, 0, calculateCoordX(30), 2000);
        Rect rectT = new Rect(calculateCoordX(30), 0, calculateCoordX(870), calculateCoordY(530));
        Rect rectR = new Rect(calculateCoordX(870), 0, 2000, 2000);
        Rect rectB = new Rect(calculateCoordX(30), calculateCoordY(1570), calculateCoordX(870), 2000);
        can.drawRect(rectL, paint);
        can.drawRect(rectT, paint);
        can.drawRect(rectR, paint);
        can.drawRect(rectB, paint);

        Paint skillPanel = new Paint();
        skillPanel.setColor(Color.WHITE);
        RectF rectF = new RectF(calculateCoordX(150), calculateCoordY(150), calculateCoordX(750), calculateCoordY(500));
        can.drawRoundRect(rectF, 25, 25, skillPanel);

        Paint skillPanelButton1 = new Paint();
        skillPanelButton1.setColor(Color.GREEN);
        Paint skillPanelButton2 = new Paint();
        skillPanelButton2.setColor(Color.YELLOW);
        Paint buttonText = new Paint();
        buttonText.setColor(Color.BLACK);
        buttonText.setTextSize(40 * diff);

        int SureId = resources.getIdentifier("skill_sure", "string", "jp.coxs.plusn");

        int backId = resources.getIdentifier("skill_back", "string", "jp.coxs.plusn");

        int notEnoughId = resources.getIdentifier("skill_notenough", "string", "jp.coxs.plusn");

//        int StringId = resources.getIdentifier(skillBean.getSkillInfo(), "string", "jp.coxs.plusn");

        if (skillBean.getSkillCount() == 0) {
            can.drawText(resources.getString(notEnoughId), calculateCoordX(200), calculateCoordY(350), buttonText);
            // 戻るボタン
            RectF rectButton2 = new RectF(calculateCoordX(500), calculateCoordY(350), calculateCoordX(700), calculateCoordY(450));
            can.drawRoundRect(rectButton2, 25, 25, skillPanelButton2);
            can.drawText(resources.getString(backId), calculateCoordX(550), calculateCoordY(400), buttonText);
            rectMap.put("skill_back", new Rect(calculateCoordX(500), calculateCoordY(350), calculateCoordX(700), calculateCoordY(450)));

        } else if (!"0".equals(skillBean.getSkillType())) {
            // 発動ボタン
            RectF rectButton1 = new RectF(calculateCoordX(200), calculateCoordY(350), calculateCoordX(400), calculateCoordY(450));
            can.drawRoundRect(rectButton1, 25, 25, skillPanelButton1);
            can.drawText(resources.getString(SureId), calculateCoordX(250), calculateCoordY(400), buttonText);
            rectMap.put("skill_use", new Rect(calculateCoordX(200), calculateCoordY(350), calculateCoordX(400), calculateCoordY(450)));

            if ("0".equals(skillBean.getSkillStartFlg())) {
                RectF rectButton12 = new RectF(calculateCoordX(200), calculateCoordY(350), calculateCoordX(400), calculateCoordY(450));
                can.drawRoundRect(rectButton12, 25, 25, paint);
            }

            // 戻るボタン
            RectF rectButton2 = new RectF(calculateCoordX(500), calculateCoordY(350), calculateCoordX(700), calculateCoordY(450));
            can.drawRoundRect(rectButton2, 25, 25, skillPanelButton2);
            can.drawText(resources.getString(backId), calculateCoordX(550), calculateCoordY(400), buttonText);
            rectMap.put("skill_back", new Rect(calculateCoordX(500), calculateCoordY(350), calculateCoordX(700), calculateCoordY(450)));
        } else {
            RectF rectButton2 = new RectF(calculateCoordX(350), calculateCoordY(350), calculateCoordX(550), calculateCoordY(450));
            can.drawRoundRect(rectButton2, 25, 25, skillPanelButton2);
            can.drawText(resources.getString(backId), calculateCoordX(400), calculateCoordY(400), buttonText);
            rectMap.put("skill_back", new Rect(calculateCoordX(350), calculateCoordY(350), calculateCoordX(550), calculateCoordY(450)));
        }

        for (int i = 0; i < skillBean.getSkillTargetList().size(); i++) {
            drawArrow(skillBean.getSkillTargetList().get(i));
        }

        int skillImageId = resources.getIdentifier(skillBean.getSkillImageName(), "drawable", "jp.coxs.plusn");

        Drawable skill = resources.getDrawable(skillImageId);

        skill.setBounds(new Rect(calculateCoordX(200), calculateCoordY(190), calculateCoordX(320), calculateCoordY(310)));
        skill.draw(can);
        Paint infoText = new Paint();
        infoText.setColor(Color.BLACK);
        infoText.setTextSize(30 * diff);

        // TODO:
        int StringId = resources.getIdentifier(skillBean.getSkillInfo(), "string", "jp.coxs.plusn");

        List<String> strList = makeString(resources.getString(StringId), 24);
        for (int i = 0; i< strList.size(); i++) {
            can.drawText(strList.get(i), calculateCoordX(340), calculateCoordY(230+(i * 30)), infoText);
        }

//        if (skillBean.getSkillInfo().length() > 12) {
//            can.drawText(skillBean.getSkillInfo().substring(0, 12), calculateCoordX(340), calculateCoordY(230), infoText);
//            if (skillBean.getSkillInfo().length() > 24) {
//                can.drawText(skillBean.getSkillInfo().substring(12, 24), calculateCoordX(340), calculateCoordY(260), infoText);
//                can.drawText(skillBean.getSkillInfo().substring(24, skillBean.getSkillInfo().length()), calculateCoordX(340), calculateCoordY(290), infoText);
//            } else {
//                can.drawText(skillBean.getSkillInfo().substring(12, skillBean.getSkillInfo().length()), calculateCoordX(340), calculateCoordY(260), infoText);
//            }
//        } else {
//            can.drawText(skillBean.getSkillInfo(), calculateCoordX(340), calculateCoordY(230), infoText);
//        }
    }

    public Rect getBounds(String string) {
        return rectMap.get(string);
    }

    public void drawSkillAnimation(SkillBean skillBean, BallMap ballMap, int timeCount, int skillId) {
        if ("1".equals(skillBean.getSkillId())) {

            float count = timeCount;

            int x = 400 - (int) ((count / 50) * 230);
            ;
            int y = 600 - (int) ((count / 50) * 480);

            drawNumberString("+5", x, y, 50);

        } else if ("2".equals(skillBean.getSkillId())) {

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            float count = timeCount;

            int alpha = 0;
            if (timeCount < 30) {
                alpha = (int) ((count / 30) * 255);
            } else {
                alpha = (int) (((60 - count) / 30) * 255);
            }
            paint.setAlpha(alpha);

            RectF rectF = new RectF(calculateCoordX(30), calculateCoordY(560), calculateCoordX(870), calculateCoordY(1400));
            can.drawRoundRect(rectF, 25, 25, paint);

            skillImageList.get(skillId).setBounds(calculateCoordX(250), calculateCoordY(200), calculateCoordX(650), calculateCoordY(600));
            skillImageList.get(skillId).draw(can);

        } else if ("3".equals(skillBean.getSkillId())) {
            List<PointBean> ballList = skillBean.getSkillTargetList();

            for (int i = 0; i < ballList.size(); i++) {

                int x = (skillBean.getSkillTargetList().get(i).getX() * 120) + 30;
                int y = (skillBean.getSkillTargetList().get(i).getY() * 120) + 530 - 1000;
                int yball = y;

                yball += 1000;
                drawBall(x, yball, ballList.get(i).getValue(), 120);

                y += (timeCount * 10);

                rocket_punch.setBounds(calculateCoordX(x - 60), calculateCoordY(y - 120), calculateCoordX(x + 180), calculateCoordY(y + 120));
                rocket_punch.draw(can);
            }

        } else if ("4".equals(skillBean.getSkillId())) {

            List<PointBean> ballList = skillBean.getSkillTargetList();

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            float count = timeCount;

            int toX = 390;
            int toY = 480;

            for (int i = 0; i < ballList.size(); i++) {
                int fromX = (ballList.get(i).getX() * 120) + 30;
                int fromY = (ballList.get(i).getY() * 120) + 530;
                int x = 0;
                int y = 0;
                x = fromX - (int) ((count / 100) * (fromX - toX));
                y = fromY - (int) ((count / 100) * (fromY - toY));

                drawBall(x, y, ballList.get(i).getValue(), 120);
            }

            skillImageList.get(skillId).setBounds(calculateCoordX(250), calculateCoordY(200), calculateCoordX(650), calculateCoordY(600));
            skillImageList.get(skillId).draw(can);

        } else if ("5".equals(skillBean.getSkillId())) {
            int x = (skillBean.getSkillTargetList().get(0).getX() * 120) + 30;
            int y = (skillBean.getSkillTargetList().get(0).getY() * 120) + 530 - 1000;
            int yball = y;

            if (timeCount < 120) {
                yball += 1000;
            } else {
                yball += ((220 - timeCount) * 10);
            }
            drawBall(x, yball, skillBean.getSkillTargetList().get(0).getValue(), 120);

            if (timeCount < 100) {
                y += (timeCount * 10);
            } else if (timeCount < 120) {
                y += 1000;
            } else {
                y += ((220 - timeCount) * 10);
            }

            skillImageList.get(skillId).setBounds(calculateCoordX(x - 60), calculateCoordY(y - 120), calculateCoordX(x + 180), calculateCoordY(y + 120));
            skillImageList.get(skillId).draw(can);


        } else {
            //            Paint infoText = new Paint();
            //            infoText.setColor(Color.BLACK);
            //            infoText.setTextSize(60 * diff);
            //
            //            can.drawText("スキルアニメション", calculateCoordX(200), calculateCoordY(900), infoText);
            //
            //            drawNumberString(String.valueOf(timeCount), 200, 1000, 60);
        }
    }

    public void drawStepMove(List<PointBean> fromPointList, List<PointBean> toPointList, BallMap ballMap, int timeCount) {

        for (int i = 0; i < fromPointList.size(); i++) {
            PointBean fromPoint = fromPointList.get(i);
            PointBean toPoint = toPointList.get(i);

            int fromX = (fromPoint.getX() * 120) + 30;
            int fromY = (fromPoint.getY() * 120) + 530;

            int toX = (toPoint.getX() * 120) + 30;
            int toY = (toPoint.getY() * 120) + 530;

            int diffx = (toPoint.getX() - fromPoint.getX()) * timeCount * 4;
            int diffy = (toPoint.getY() - fromPoint.getY()) * timeCount * 4;

            drawBall(fromX + diffx, fromY + diffy, ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()), 120);
            //            drawBall(fromPoint.getX(), fromPoint.getY(), ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()));
        }
    }

    public void drawStepStack(List<PointBean> fromPointList, BallMap ballMap, int timeCount) {

        boolean moveFlg = false;
        for (int i = 1; i < fromPointList.size(); i++) {
            PointBean fromPoint = fromPointList.get(i);
            PointBean toPoint = fromPointList.get(i - 1);
            // TO位置のボール値が0の場合のみ移動可能
            if (ballMap.getMapValue(toPoint.getX(), toPoint.getY()) == 0) {
                moveFlg = true;
            }
            int fromX = (fromPoint.getX() * 120) + 30;
            int fromY = (fromPoint.getY() * 120) + 530;
            if (moveFlg) {
                int diffx = (toPoint.getX() - fromPoint.getX()) * timeCount * 4;
                int diffy = (toPoint.getY() - fromPoint.getY()) * timeCount * 4;
                drawBall(fromX + diffx, fromY + diffy, ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()), 120);
            } else {
                drawBall(fromX, fromY, ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()), 120);
                drawBall(fromX, fromY + 120, ballMap.getMapValue(toPoint.getX(), toPoint.getY()), 120);
            }
        }
    }

    public void drawStepStack(List<PointBean> fromPointList, List<PointBean> toPointList, BallMap ballMap, int timeCount) {

//        for (int i = 0; i < fromPointList.size(); i++) {
//            PointBean fromPoint = fromPointList.get(i);
//            fromPoint.setValue(ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()));
//        }
        boolean moveFlg = false;
        for (int i = 0; i < fromPointList.size(); i++) {
            PointBean fromPoint = fromPointList.get(i);
            PointBean toPoint = toPointList.get(i);
            // TO位置のボール値が0の場合のみ移動可能
            if (ballMap.getMapValue(toPoint.getX(), toPoint.getY()) == 0) {
                moveFlg = true;
            }
            int fromX = (fromPoint.getX() * 120) + 30;
            int fromY = (fromPoint.getY() * 120) + 530;
            if (moveFlg) {
                int diffx = (toPoint.getX() - fromPoint.getX()) * timeCount * 4;
                int diffy = (toPoint.getY() - fromPoint.getY()) * timeCount * 4;
                drawBall(fromX + diffx, fromY + diffy, ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()), 120);
            } else {
                drawBall(fromX, fromY, ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()), 120);
            }
        }
    }

    public void drawEnBall(int enball) {

        gold_area.setBounds(calculateCoordX(40), calculateCoordY(50), calculateCoordX(260), calculateCoordY(100));
        gold_area.draw(can);
        coin.setBounds(calculateCoordX(40), calculateCoordY(30), calculateCoordX(110), calculateCoordY(100));
        coin.draw(can);
        String gold = String.valueOf(enball);

        drawNumberString(gold, 240 - (gold.length() * 20), 50, 30);

    }

    public int drawbounsText(int bounsCount) {
        Paint skillPanel = new Paint();
        skillPanel.setColor(Color.WHITE);
        skillPanel.setAlpha(127);
        RectF rectF = new RectF(calculateCoordX(150), calculateCoordY(800), calculateCoordX(750), calculateCoordY(1000));
        can.drawRoundRect(rectF, 25, 25, skillPanel);

        Paint infoText = new Paint();
        infoText.setColor(Color.RED);
        infoText.setTextSize(80 * diff);
        can.drawText("BOUNS TIME", calculateCoordX(200), calculateCoordY(900), infoText);
        bounsCount++;
        return bounsCount;
    }

    public void drawStepTile(List<PointBean> list, List<PointBean> list2, int timeCount) {

        for (int i = 0; i < list.size(); i++) {

            PointBean fromPoint = list.get(i);
            PointBean toPoint   = list2.get(i);
            int x = fromPoint.getX();
            int y = fromPoint.getY();
            int left = calculateCoordX((x * 120) + 30);
            int top = calculateCoordY((y * 120) + 530);
            int right = calculateCoordX((x * 120) + 120 + 30);
            int bottom = calculateCoordY((y * 120) + 120 + 530);
            if (timeCount < 15) {
                if (fromPoint.getX() > toPoint.getX()) {
                    flowing_left_1.setBounds(left, top, right, bottom);
                    flowing_left_1.draw(can);
                } else if (fromPoint.getX() < toPoint.getX()) {
                    flowing_right_1.setBounds(left, top, right, bottom);
                    flowing_right_1.draw(can);
                } else if (fromPoint.getY() > toPoint.getY()) {
                    flowing_up_1.setBounds(left, top, right, bottom);
                    flowing_up_1.draw(can);
                } else if (fromPoint.getY() < toPoint.getY()) {
                    flowing_down_1.setBounds(left, top, right, bottom);
                    flowing_down_1.draw(can);
                }
            } else {
                if (fromPoint.getX() > toPoint.getX()) {
                    flowing_left_2.setBounds(left, top, right, bottom);
                    flowing_left_2.draw(can);
                } else if (fromPoint.getX() < toPoint.getX()) {
                    flowing_right_2.setBounds(left, top, right, bottom);
                    flowing_right_2.draw(can);
                } else if (fromPoint.getY() > toPoint.getY()) {
                    flowing_up_2.setBounds(left, top, right, bottom);
                    flowing_up_2.draw(can);
                } else if (fromPoint.getY() < toPoint.getY()) {
                    flowing_down_2.setBounds(left, top, right, bottom);
                    flowing_down_2.draw(can);
                }
            }
        }
    }

    public void drawStepStackTile(List<PointBean> list, int timeCount) {

        for (int i = 1; i < list.size(); i++) {

            PointBean fromPoint = list.get(i);
            PointBean toPoint   = list.get(i - 1);

            int x = fromPoint.getX();
            int y = fromPoint.getY();
            int left = calculateCoordX((x * 120) + 30);
            int top = calculateCoordY((y * 120) + 530);
            int right = calculateCoordX((x * 120) + 120 + 30);
            int bottom = calculateCoordY((y * 120) + 120 + 530);

            if (timeCount < 15) {
                if (fromPoint.getX() > toPoint.getX()) {
                    flowing_left_1.setBounds(left, top, right, bottom);
                    flowing_left_1.draw(can);
                } else if (fromPoint.getX() < toPoint.getX()) {
                    flowing_right_1.setBounds(left, top, right, bottom);
                    flowing_right_1.draw(can);
                } else if (fromPoint.getY() > toPoint.getY()) {
                    flowing_up_1.setBounds(left, top, right, bottom);
                    flowing_up_1.draw(can);
                } else if (fromPoint.getY() < toPoint.getY()) {
                    flowing_down_1.setBounds(left, top, right, bottom);
                    flowing_down_1.draw(can);
                }
            } else {
                if (fromPoint.getX() > toPoint.getX()) {
                    flowing_left_2.setBounds(left, top, right, bottom);
                    flowing_left_2.draw(can);
                } else if (fromPoint.getX() < toPoint.getX()) {
                    flowing_right_2.setBounds(left, top, right, bottom);
                    flowing_right_2.draw(can);
                } else if (fromPoint.getY() > toPoint.getY()) {
                    flowing_up_2.setBounds(left, top, right, bottom);
                    flowing_up_2.draw(can);
                } else if (fromPoint.getY() < toPoint.getY()) {
                    flowing_down_2.setBounds(left, top, right, bottom);
                    flowing_down_2.draw(can);
                }
            }
        }
    }

    public int drawTutorialPlay(int tutorialStep, int stageId, int animationCount) {
        animationCount++;
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(220);
        //        Rect rectL = new Rect(0, 0, calculateCoordX(30), 2000);
        //        Rect rectT = new Rect(calculateCoordX(30), 0, calculateCoordX(870), calculateCoordY(530));
        //        Rect rectR = new Rect(calculateCoordX(870), 0, 2000, 2000);
        //        Rect rectB = new Rect(calculateCoordX(30), calculateCoordY(1570), calculateCoordX(870), 2000);
        //        can.drawRect(rectL, paint);
        //        can.drawRect(rectT, paint);
        //        can.drawRect(rectR, paint);
        //        can.drawRect(rectB, paint);

        Paint skillPanel = new Paint();
        skillPanel.setColor(Color.WHITE);
        skillPanel.setTextSize(40 * diff);
//        RectF rectF = new RectF(calculateCoordX(150), calculateCoordY(150), calculateCoordX(750), calculateCoordY(500));
//        can.drawRoundRect(rectF, 25, 25, skillPanel);

        if (stageId == 1 && tutorialStep < 9) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 2 && tutorialStep < 5) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 10 && tutorialStep < 3) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 20 && tutorialStep < 2) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 40 && tutorialStep < 3) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 60 && tutorialStep < 2) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 80 && tutorialStep < 2) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 33 && tutorialStep < 5) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 100 && tutorialStep < 4) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 129 && tutorialStep < 5) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }
        if (stageId == 145 && tutorialStep < 3) {
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100), calculateCoordX(870), calculateCoordY(400));
            clear_panel_xl.draw(can);
        }

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40 * diff);

        // TODO:
        if (stageId == 1 && tutorialStep == 1) {
//            drawText("ボール３をボール７の隣まで移動", 150, 170, diff * 40, Color.BLUE);
//            drawText("しよう                        ", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 160) {
                animationCount = 80;
            }
            int time = animationCount % 80;

            int handLeft = 570;
            int handTop = 830;
            if (time < 20) {
                handLeft += (time * 6);
            } else if (time < 60) {
                handLeft = 690;
                handTop += ((time - 20) * 6);
            } else if (time < 80) {
                handLeft = 690 - ((time - 60) * 6);
                handTop = 1070;
            }

            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 1 && tutorialStep == 2) {

//            drawText("移動されたボールと隣接するボー", 150, 170, diff * 40, Color.BLUE);
//            drawText("ルが足し算で１０の倍数になった", 150, 220, diff * 40, Color.BLUE);
//            drawText("ら消せます　　　　　　　　　　", 150, 270, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 100) {
                animationCount = 40;
            }
            int time = animationCount % 20;

            if (time > 10) {
                Rect rect = new Rect(calculateCoordX(390), calculateCoordY(1010), calculateCoordX(630), calculateCoordY(1130));
                selected.setBounds(rect);
                selected.draw(can);
            }
            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }

        } else if (stageId == 1 && tutorialStep == 4) {
            //drawText("ボール２を１マス移動しよう", 150, 170, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_4), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 120) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            int handLeft = 450 + (time * 3);
            int handTop = 830;

            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 1 && tutorialStep == 5) {
//            drawText("ボールを移動のあと、消されない", 150, 170, diff * 40, Color.BLUE);
//            drawText("場合、ＮＥＸＴのボールがランダ", 150, 220, diff * 40, Color.BLUE);
//            drawText("ムで配置されます　　　　　　　", 150, 270, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_5), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 100) {
                animationCount = 40;
            }
            int time = animationCount % 20;

            if (time > 10) {
                Rect rect = new Rect(calculateCoordX(30), calculateCoordY(400), calculateCoordX(870), calculateCoordY(500));
                selected.setBounds(rect);
                selected.draw(can);
            }
            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 1 && tutorialStep == 6) {
//            drawText("移動ルート中にボールや障害物が", 150, 170, diff * 40, Color.BLUE);
//            drawText("ありましたら移動できません　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_6), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 280) {
                animationCount = 140;
            }
            int time = animationCount % 140;

            int handLeft = 330;
            int handTop = 950;
            if (time < 20) {
                handLeft -= (time * 6);
            } else if (time < 60) {
                handLeft = 210;
                handTop += ((time - 20) * 6);
            } else if (time < 80) {
                handLeft = 210 + ((time - 60) * 6);
                handTop = 1190;
            } else if (time < 140) {
                handLeft = 330;
                handTop = 1190;
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
            if (time > 80) {
                Rect rect1 = new Rect(calculateCoordX(390), calculateCoordY(1130), calculateCoordX(390 + 120), calculateCoordY(1130 + 120));
                batsu.setBounds(rect1);
                batsu.draw(can);
            }
            if (time > 100) {
                Rect rect2 = new Rect(calculateCoordX(510), calculateCoordY(1130), calculateCoordX(510 + 120), calculateCoordY(1130 + 120));
                batsu.setBounds(rect2);
                batsu.draw(can);
            }
            if (time > 120) {
                Rect rect3 = new Rect(calculateCoordX(510), calculateCoordY(1010), calculateCoordX(510 + 120), calculateCoordY(1010 + 120));
                batsu.setBounds(rect3);
                batsu.draw(can);
            }
            if (animationCount > 100) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }

        } else if (stageId == 1 && tutorialStep == 7) {
//            drawText("ボール４をボール６の下まで移動", 150, 170, diff * 40, Color.BLUE);
//            drawText("しよう　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_7), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 220) {
                animationCount = 110;
            }
            int time = animationCount % 110;

            int handLeft = 330;
            int handTop = 950;
            if (time < 10) {
                handLeft -= (time * 12);
            } else if (time < 30) {
                handLeft = 210;
                handTop -= ((time - 10) * 12);
            } else if (time < 70) {
                handLeft = 210 + ((time - 30) * 12);
                handTop = 710;
            } else if (time < 100) {
                handLeft = 690;
                handTop = 710 + ((time - 70) * 12);
            } else if (time < 110) {
                handLeft = 690 - ((time - 100) * 12);
                handTop = 1070;
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 1 && tutorialStep == 8) {
//            drawText("移動されたボールと隣接ボールが", 150, 170, diff * 40, Color.BLUE);
//            drawText("足し算で１０の倍数になったら消", 150, 220, diff * 40, Color.BLUE);
//            drawText("せます　　　　　　　　　　　　", 150, 270, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_8), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 100) {
                animationCount = 40;
            }
            int time = animationCount % 20;

            if (time > 10) {
                Rect rect = new Rect(calculateCoordX(270), calculateCoordY(1010), calculateCoordX(630), calculateCoordY(1130));
                selected.setBounds(rect);
                selected.draw(can);
            } else {
                Rect rect = new Rect(calculateCoordX(510), calculateCoordY(890), calculateCoordX(630), calculateCoordY(1130));
                selected.setBounds(rect);
                selected.draw(can);
            }
            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 1 && tutorialStep == 9) {
            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_1_9), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }
            if (animationCount == 40) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }

        } else if (stageId == 2 && tutorialStep == 1) {
//            drawText("ボール６を指定のマスまで移動し", 150, 170, diff * 40, Color.BLUE);
//            drawText("よう　　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_2_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            int handLeft = 450;
            int handTop = 1190;
//            if (time < 40) {
                handTop -= (time * 6);
//            }

            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 2 && tutorialStep == 2) {
//            drawText("合計３０以上で消去すると、爆弾", 150, 170, diff * 40, Color.BLUE);
//            drawText("が作られる　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_2_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 90) {
                animationCount = 45;
            }
            int time = animationCount % 45;

            if (time < 15) {
                selected.setBounds(calculateCoordX(270), calculateCoordY(890), calculateCoordX(510), calculateCoordY(1010));
                selected.draw(can);
            } else if (time < 30) {
                selected.setBounds(calculateCoordX(390), calculateCoordY(890), calculateCoordX(750), calculateCoordY(1010));
                selected.draw(can);
            } else if (time < 45) {
                selected.setBounds(calculateCoordX(390), calculateCoordY(650), calculateCoordX(510), calculateCoordY(1010));
                selected.draw(can);
            }

            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 2 && tutorialStep == 3) {
//            drawText("爆弾を指定のマスまで移動しよう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_2_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            int handLeft = 450;
            int handTop = 950;
//            if (time < 40) {
                handTop -= (time * 6);
//            }

            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 2 && tutorialStep == 4) {
//            drawText("爆弾は周囲のボールを全て消去す", 150, 170, diff * 40, Color.BLUE);
//            drawText("ることができます　　　　　　　", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_2_4), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                selected.setBounds(calculateCoordX(270), calculateCoordY(530), calculateCoordX(630), calculateCoordY(890));
                selected.draw(can);
            }

            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 2 && tutorialStep == 5) {

            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_2_5), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }
            if (animationCount > 39) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }

        } else if (stageId == 10 && tutorialStep == 1) {

//            drawText("スキルを選択しましょう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_10_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                Rect rect = new Rect(calculateCoordX(75), calculateCoordY(1440), calculateCoordX(75 + 120), calculateCoordY(1560));
                selected.setBounds(rect);
                selected.draw(can);
            }
        } else if (stageId == 10 && tutorialStep == 2) {
//            drawText("消されたいボールを選択してみま", 150, 170, diff * 40, Color.BLUE);
//            drawText("しょう　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_10_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

        } else if (stageId == 10 && tutorialStep == 3) {

            if (animationCount > 40) {
                animationCount = 40;
            }
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 10)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 10)));
            clear_panel_xl.draw(can);
//            drawText("選択されたボールを消去できます", 150, 170 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("発動ボタンを選択してみましょう", 150, 220 + (animationCount * 10), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_10_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 10), diff * 40, Color.BLUE);
            }

        } else if (stageId == 10 && tutorialStep == 4) {

            if (animationCount > 120) {
                animationCount = 120;
            }

            if (animationCount == 120) {
                clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (400)), calculateCoordX(870), calculateCoordY(400 + (400)));
                clear_panel_xl.draw(can);
//                drawText("ボールを自由に動かして消してみ", 150, 170 + (400), diff * 40, Color.BLUE);
//                drawText("ましょう　　　　　　　　　　　", 150, 220 + (400), diff * 40, Color.BLUE);

                List<String> strList = makeString(resources.getString(R.string.tutorial_play_10_4), 30);
                for (int i = 0; i< strList.size(); i++) {
                    drawText(strList.get(i), 150, 170+ (i*50) + (400), diff * 40, Color.BLUE);
                }
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (400)), calculateCoordX(700), calculateCoordY(380 + (400)));
                start_button.draw(can);
            }

        // スキル２
        } else if (stageId == 20 && tutorialStep == 1) {

//            drawText("スキルを選択しましょう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_20_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                Rect rect = new Rect(calculateCoordX(75 + 210), calculateCoordY(1440), calculateCoordX(75 + 120 + 210), calculateCoordY(1560));
                selected.setBounds(rect);
                selected.draw(can);
            }

        } else if (stageId == 20 && tutorialStep == 2) {

            if (animationCount > 40) {
                animationCount = 40;
            }
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 10)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 10)));
            clear_panel_xl.draw(can);
//            drawText("ボールの移動回数５回増えます　", 150, 170 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("発動ボタンを選択してみましょう", 150, 220 + (animationCount * 10), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_20_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 10), diff * 40, Color.BLUE);
            }

        } else if (stageId == 20 && tutorialStep == 3) {

            if (animationCount > 80) {
                animationCount = 80;
            }

            if (animationCount == 80) {
                clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (400)), calculateCoordX(870), calculateCoordY(400 + (400)));
                clear_panel_xl.draw(can);
//                drawText("ボールを自由に動かして消してみ", 150, 170 + (400), diff * 40, Color.BLUE);
//                drawText("ましょう　　　　　　　　　　　", 150, 220 + (400), diff * 40, Color.BLUE);

                List<String> strList = makeString(resources.getString(R.string.tutorial_play_20_3), 30);
                for (int i = 0; i< strList.size(); i++) {
                    drawText(strList.get(i), 150, 170+ (i*50) + (400), diff * 40, Color.BLUE);
                }
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (400)), calculateCoordX(700), calculateCoordY(380 + (400)));
                start_button.draw(can);
            }

        // スキル３
        } else if (stageId == 40 && tutorialStep == 1) {

//            drawText("スキルを選択しましょう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_40_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                Rect rect = new Rect(calculateCoordX(75 + 420), calculateCoordY(1440), calculateCoordX(75 + 120 + 420), calculateCoordY(1560));
                selected.setBounds(rect);
                selected.draw(can);
            }
        } else if (stageId == 40 && tutorialStep == 2) {
//            drawText("消されたいボールを選択してみま", 150, 170, diff * 40, Color.BLUE);
//            drawText("しょう　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_40_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

        } else if (stageId == 40 && tutorialStep == 3) {

            if (animationCount > 40) {
                animationCount = 40;
            }
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 10)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 10)));
            clear_panel_xl.draw(can);
//            drawText("選択されたボールと同じ数値のボ", 150, 170 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("ールをすべて消去できます　　　", 150, 220 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("発動ボタンを選択してみましょう", 150, 270 + (animationCount * 10), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_40_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 10), diff * 40, Color.BLUE);
            }

        } else if (stageId == 40 && tutorialStep == 4) {

            if (animationCount > 120) {
                animationCount = 120;
            }

            if (animationCount == 120) {
                clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (400)), calculateCoordX(870), calculateCoordY(400 + (400)));
                clear_panel_xl.draw(can);
//                drawText("ボールを自由に動かして消してみ", 150, 170 + (400), diff * 40, Color.BLUE);
//                drawText("ましょう　　　　　　　　　　　", 150, 220 + (400), diff * 40, Color.BLUE);

                List<String> strList = makeString(resources.getString(R.string.tutorial_play_40_4), 30);
                for (int i = 0; i< strList.size(); i++) {
                    drawText(strList.get(i), 150, 170+ (i*50) + (400), diff * 40, Color.BLUE);
                }
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (400)), calculateCoordX(700), calculateCoordY(380 + (400)));
                start_button.draw(can);
            }

        // スキル４
        } else if (stageId == 60 && tutorialStep == 1) {

//            drawText("スキルを選択しましょう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_60_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                Rect rect = new Rect(calculateCoordX(75 + 630), calculateCoordY(1440), calculateCoordX(75 + 120 + 630), calculateCoordY(1560));
                selected.setBounds(rect);
                selected.draw(can);
            }

        } else if (stageId == 60 && tutorialStep == 2) {

            if (animationCount > 40) {
                animationCount = 40;
            }
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 10)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 10)));
            clear_panel_xl.draw(can);
//            drawText("ランダムで障害物を３つ消去する", 150, 170 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("発動ボタンを選択してみましょう", 150, 220 + (animationCount * 10), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_60_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 10), diff * 40, Color.BLUE);
            }

        } else if (stageId == 60 && tutorialStep == 3) {

            if (animationCount > 120) {
                animationCount = 120;
            }

            if (animationCount == 120) {
                clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (400)), calculateCoordX(870), calculateCoordY(400 + (400)));
                clear_panel_xl.draw(can);
//                drawText("ボールを自由に動かして消してみ", 150, 170 + (400), diff * 40, Color.BLUE);
//                drawText("ましょう　　　　　　　　　　　", 150, 220 + (400), diff * 40, Color.BLUE);

                List<String> strList = makeString(resources.getString(R.string.tutorial_play_60_3), 30);
                for (int i = 0; i< strList.size(); i++) {
                    drawText(strList.get(i), 150, 170+ (i*50) + (400), diff * 40, Color.BLUE);
                }
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (400)), calculateCoordX(700), calculateCoordY(380 + (400)));
                start_button.draw(can);
            }

        // スキル５
        } else if (stageId == 80 && tutorialStep == 1) {

//            drawText("スキルを選択しましょう", 150, 170, diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_80_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }
            int time = animationCount % 40;

            if (time < 20) {
                Rect rect = new Rect(calculateCoordX(75 + 630), calculateCoordY(1440), calculateCoordX(75 + 120 + 630), calculateCoordY(1560));
                selected.setBounds(rect);
                selected.draw(can);
            }

        } else if (stageId == 80 && tutorialStep == 2) {

            if (animationCount > 40) {
                animationCount = 40;
            }
            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 10)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 10)));
            clear_panel_xl.draw(can);
//            drawText("すべてのボールをランダムで再配", 150, 170 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("置する　　　　　　　　　　　　", 150, 220 + (animationCount * 10), diff * 40, Color.BLUE);
//            drawText("発動ボタンを選択してみましょう", 150, 270 + (animationCount * 10), diff * 40, Color.BLUE);

            List<String> strList = makeString(resources.getString(R.string.tutorial_play_80_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 10), diff * 40, Color.BLUE);
            }

        } else if (stageId == 80 && tutorialStep == 3) {

            if (animationCount > 120) {
                animationCount = 120;
            }

            if (animationCount == 120) {
                clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (400)), calculateCoordX(870), calculateCoordY(400 + (400)));
                clear_panel_xl.draw(can);
//                drawText("ボールを自由に動かして消してみ", 150, 170 + (400), diff * 40, Color.BLUE);
//                drawText("ましょう　　　　　　　　　　　", 150, 220 + (400), diff * 40, Color.BLUE);
                List<String> strList = makeString(resources.getString(R.string.tutorial_play_80_3), 30);
                for (int i = 0; i< strList.size(); i++) {
                    drawText(strList.get(i), 150, 170+ (i*50) + (400), diff * 40, Color.BLUE);
                }

                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (400)), calculateCoordX(700), calculateCoordY(380 + (400)));
                start_button.draw(can);
            }

        } else if (stageId == 33 && tutorialStep == 1) {
//            drawText("障害物がないルートで、ボール７", 150, 170, diff * 40, Color.BLUE);
//            drawText("をボール３の隣に移動しましょう", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_33_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }


            if (animationCount == 480) {
                animationCount = 240;
            }
//            int time = animationCount % 40;

            int time = animationCount % 240;

            int handLeft = 210;
            int handTop = 830;
            if (time < 40) {
                handLeft = 210;
                handTop -= (time * 6);
            } else if (time < 140) {
                handLeft = 210 + ((time - 40) * 6);
                handTop = 590;
            } else if (time < 220) {
                handLeft = 810;
                handTop = 590 + ((time - 140) * 6);
            } else if (time < 240) {
                handLeft = 810 - ((time - 220) * 6);
                handTop = 1070;
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);

            if (time % 40 < 20) {
                selected.setBounds(calculateCoordX(390), calculateCoordY(530 + 720), calculateCoordX(510), calculateCoordY(890 + 480));
                selected.draw(can);
            }

//            if (animationCount > 39) {
//                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380 + 480));
//                continue_button.draw(can);
//            }

        } else if (stageId == 33 && tutorialStep == 3) {
//            drawText("爆弾を障害物の隣に移動して、障", 150, 170, diff * 40, Color.BLUE);
//            drawText("害物を壊しましょう　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_33_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }


            if (animationCount == 200) {
                animationCount = 100;
            }

            int time = animationCount % 100;

            int handLeft = 330;
            int handTop = 710;
            if (time < 20) {
                handLeft = 330;
                handTop -= (time * 6);
            } else if (time < 60) {
                handLeft = 330 + ((time - 20) * 6);
                handTop = 590;
            } else if (time < 100) {
                handLeft = 570;
                handTop = 590 + ((time - 60) * 6);
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);

        } else if (stageId == 33 && tutorialStep == 5) {

            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_33_5), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }

            if (animationCount > 39) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }

        } else if (stageId == 100 && tutorialStep == 1) {
//            drawText("ボール７をボール３の隣に移動し", 150, 170, diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_100_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 120) {
                animationCount = 60;
            }

            int time = animationCount % 60;

            int handLeft = 210;
            int handTop = 1190;
            if (time < 60) {
                handTop -= (time * 2);
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);

        } else if (stageId == 100 && tutorialStep == 3) {
//            drawText("ボール３が消去された際に、隣の", 150, 170, diff * 40, Color.BLUE);
//            drawText("スターダストが一緒に消去される", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_100_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }

            int time = animationCount % 40;

            if (time < 20) {
                selected.setBounds(calculateCoordX(150), calculateCoordY(770), calculateCoordX(270), calculateCoordY(890));
                selected.draw(can);
                selected.setBounds(calculateCoordX(270), calculateCoordY(890), calculateCoordX(390), calculateCoordY(1010));
                selected.draw(can);
            }
            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }

        } else if (stageId == 100 && tutorialStep == 4) {

            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_100_4), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }
            if (animationCount > 39) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }


        // 水流
        } else if (stageId == 129 && tutorialStep == 1) {
//            drawText("ボール６を下に１つマス移動しま", 150, 170, diff * 40, Color.BLUE);
//            drawText("しょう　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_129_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 120) {
                animationCount = 60;
            }

            int time = animationCount % 60;

            int handLeft = 210;
            int handTop = 830;
            if (time < 60) {
                handTop += (time * 2);
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);

        } else if (stageId == 129 && tutorialStep == 2) {
//            drawText("移動の後に矢印あるマスのボール", 150, 170, diff * 40, Color.BLUE);
//            drawText("が矢印の方向で１マス積める　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_129_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }

            int time = animationCount % 40;

            if (time < 20) {
                selected.setBounds(calculateCoordX(390), calculateCoordY(770), calculateCoordX(510), calculateCoordY(1250));
                selected.draw(can);
            }

            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 129 && tutorialStep == 3) {
//            drawText("ボール６を下に１つマス移動しま", 150, 170, diff * 40, Color.BLUE);
//            drawText("しょう　　　　　　　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_129_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 160) {
                animationCount = 80;
            }

            int time = animationCount % 80;

            int handLeft = 210;
            int handTop = 950;
            if (time < 80) {
                handLeft += (time * 6);
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);
        } else if (stageId == 129 && tutorialStep == 4) {
//            drawText("移動の後に矢印あるマスのボール", 150, 170, diff * 40, Color.BLUE);
//            drawText("が矢印の方向で１マス積める　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_129_4), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }

            int time = animationCount % 40;

            if (time < 20) {
                selected.setBounds(calculateCoordX(390), calculateCoordY(650), calculateCoordX(510), calculateCoordY(1250));
                selected.draw(can);
            }

            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 129 && tutorialStep == 5) {

            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_129_5), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }
            if (animationCount > 39) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }

        } else if (stageId == 145 && tutorialStep == 1) {
//            drawText("ボール４を凍り付いたボール６の", 150, 170, diff * 40, Color.BLUE);
//            drawText("隣に移動しましょう　　　　　　", 150, 220, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_145_1), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 160) {
                animationCount = 80;
            }

            int time = animationCount % 80;

            int handLeft = 570;
            int handTop = 1190;
            if (time < 80) {
                handTop -= (time * 3);
            }
            Rect rect = new Rect(calculateCoordX(handLeft), calculateCoordY(handTop), calculateCoordX(handLeft + 120), calculateCoordY(handTop + 120));
            hand.setBounds(rect);
            hand.draw(can);

        } else if (stageId == 145 && tutorialStep == 2) {
//            drawText("ボール６の氷を消去できる　　　", 150, 170, diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_145_2), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50), diff * 40, Color.BLUE);
            }

            if (animationCount == 80) {
                animationCount = 40;
            }

            int time = animationCount % 40;

            if (time < 20) {
                selected.setBounds(calculateCoordX(510), calculateCoordY(770), calculateCoordX(630), calculateCoordY(1010));
                selected.draw(can);
            }

            if (animationCount > 39) {
                continue_button.setBounds(calculateCoordX(200), calculateCoordY(300), calculateCoordX(700), calculateCoordY(380));
                continue_button.draw(can);
            }
        } else if (stageId == 145 && tutorialStep == 3) {

            if (animationCount > 40) {
                animationCount = 40;
            }

            clear_panel_xl.setBounds(calculateCoordX(30), calculateCoordY(100 + (animationCount * 5)), calculateCoordX(870), calculateCoordY(400 + (animationCount * 5)));
            clear_panel_xl.draw(can);
//            drawText("ボールを自由に動かして消してみ", 150, 170 + (animationCount * 5), diff * 40, Color.BLUE);
//            drawText("ましょう　　　　　　　　　　　", 150, 220 + (animationCount * 5), diff * 40, Color.BLUE);
            List<String> strList = makeString(resources.getString(R.string.tutorial_play_145_3), 30);
            for (int i = 0; i< strList.size(); i++) {
                drawText(strList.get(i), 150, 170+ (i*50) + (animationCount * 5), diff * 40, Color.BLUE);
            }
            if (animationCount > 39) {
                start_button.setBounds(calculateCoordX(200), calculateCoordY(300 + (animationCount * 5)), calculateCoordX(700), calculateCoordY(380 + (animationCount * 5)));
                start_button.draw(can);
            }
        }

        return animationCount;
    }

    public void callback() {
        super.callback();
        back_ground.setCallback(null);
        play_frame.setCallback(null);

        // ボールのresources
        ball1.setCallback(null);
        ball2.setCallback(null);
        ball3.setCallback(null);
        ball4.setCallback(null);
        ball5.setCallback(null);
        ball6.setCallback(null);
        ball7.setCallback(null);
        ball8.setCallback(null);
        ball9.setCallback(null);
        coin.setCallback(null);

        bomb.setCallback(null);

        // タイル
        tile.setCallback(null);

        // 矢印
        move.setCallback(null);

        // コンボエリア
        combo_rainbow.setCallback(null);
        combo_text.setCallback(null);

        stage_list_button.setCallback(null);

        retry_button.setCallback(null);
        next_stage_button.setCallback(null);
        retira_button.setCallback(null);
        continue_button.setCallback(null);
        start_button.setCallback(null);

        explosion.setCallback(null);

        run_1.setCallback(null);
        robot_stand.setCallback(null);

        obstacle.setCallback(null);
        nebula.setCallback(null);

        // 水流
        flowing_down_1.setCallback(null);
        flowing_down_2.setCallback(null);
        flowing_up_1.setCallback(null);
        flowing_up_2.setCallback(null);
        flowing_left_1.setCallback(null);
        flowing_left_2.setCallback(null);
        flowing_right_1.setCallback(null);
        flowing_right_2.setCallback(null);

        rocket_punch.setCallback(null);

        hand.setCallback(null);

        selected.setCallback(null);

        batsu.setCallback(null);

        facebook.setCallback(null);

        twitter.setCallback(null);

        gold_area.setCallback(null);

        pause_button.setCallback(null);
        restart_button.setCallback(null);

        score_icon.setCallback(null);
        time_icon.setCallback(null);

        next_text.setCallback(null);
        next_area.setCallback(null);

        combo_area.setCallback(null);

        stage_clear_text.setCallback(null);
        pause_text.setCallback(null);
        game_over_text.setCallback(null);

        over_panel_m.setCallback(null);
        clear_panel_xl.setCallback(null);
        clear_panel_l.setCallback(null);
        clear_panel_m.setCallback(null);
        clear_panel_s.setCallback(null);

        ice.setCallback(null);

        goal_flag.setCallback(null);

    }
}
