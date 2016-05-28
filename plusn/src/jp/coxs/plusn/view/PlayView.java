package jp.coxs.plusn.view;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import jp.coxs.plusn.ScreenActivity;
import jp.coxs.plusn.bean.BallClearBean;
import jp.coxs.plusn.bean.PointBean;
import jp.coxs.plusn.bean.SkillBean;
import jp.coxs.plusn.bean.StageInfoBean;
import jp.coxs.plusn.timer.CountTimerTask;
import jp.coxs.plusn.timer.InvalidateScreenTimerTask;
import jp.coxs.plusn.util.FileUtil;
import jp.coxs.plusn.view.draw.DrawPlayView;
import jp.coxs.plusn.view.play.BallMap;
import jp.coxs.plusn.view.play.ClearHistory;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 */
public class PlayView extends View {
    // 画面座標幅
    private static final float SCREEN_W = 900;
    // 画面座標高
    private static final float SCREEN_H = 1600;

    // 座標係数
    private float diff = 0;
    private float diffX = 0;
    private float diffY = 0;

    //
    private ScreenActivity screen;

    InvalidateScreenTimerTask invalidateTimerTask = null;
    CountTimerTask selectAnimationTimerTask = null;
    CountTimerTask comboTimerTask = null;
    CountTimerTask scoreTimerTask = null;
    CountTimerTask comboTextTimerTask = null;
    CountTimerTask bombTimerTask = null;
    CountTimerTask playTimerTask = null;
    CountTimerTask skillTimerTask = null;
    CountTimerTask stepTimerTask = null;
    Timer invalidateTimer = null;
    Timer animationTimer = null;
    Timer comboTimer = null;
    Timer scoreTimer = null;
    Timer skillTimer = null;
    Timer comboTextTimer = null;
    Timer bombTimer = null;
    Timer playTimer = null;
    Timer stepTimer = null;

    BallMap ballMap = null;

    private int selected = -1;
    private int moveType = -1;

    private String btnKbn = "START";

    private List<PointBean> rootList = null;

    // PlayView描画クラス
    DrawPlayView draw = null;

    // 得点
    private int score = 0;
    private int totalScore = 0;

    private int comboCount = 0;

    private boolean gameOverFlg = false;
    private boolean gameClearFlg = false;

    private FileUtil fileUtil = null;

    private int clearX = 500;
    private int clearY = 500;
    private String clearScore = "0";

    private List<SkillBean> skillList = null;
    private String skillType = "0";

    private int stepCount = 50;
    private int stepClear = 0;

    private int targetType = 0;
    private int targetCount = 0;
    private int targetTotal = 1;

    private boolean menuShowFlg = false;

    private List<PointBean> explosionPointList = null;

    private int playTimeCount = 0;
    private StageInfoBean stageInfo = null;
    private Map<String, Integer> bonusMap = new HashMap<String, Integer>();

    // 選択スキル番号
    private int selectSkillId = 0;

    private int nextBall1;
    private int nextBall2;
    private int nextBall3;

    private int runCount = 0;
    private int bounsCount = 0;

    private int enball = 0;

    private int changeScreenCount = 0;

    private boolean startScreenFlg = false;
    private boolean changeScreenFlg = false;

    private String nextScreen = "";

    private boolean nextFlg = false;

    private ClearHistory clearHistory = new ClearHistory();

    private int clearCount = 0;

    private boolean tutorialPlayFlg = false;

    private int tutorialStep = 0;
    private int tutorialAnimationCount = 0;

    /**
     * コンストラクター
     *
     * @param context
     */
    public PlayView(Context context) {
        super(context);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * コンストラクター
     *
     * @param context
     * @param attrs
     */
    public PlayView(Context context, AttributeSet attrs, int defStyle) {
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
        float diffWidth = screen.disp_w / SCREEN_W;
        float diffHeight = screen.disp_h / SCREEN_H;
        if (diffWidth > diffHeight) {
            diff = diffHeight;
            diffX = (screen.disp_w - (SCREEN_W * diff)) / 2;
        } else {
            diff = diffWidth;
            diffY = (screen.disp_h - (SCREEN_H * diff)) / 2;
        }

        fileUtil = new FileUtil(context);
        // ファイルを初期化する

//        stageInfo = fileUtil.getStageInfo(screen.getMapId(), screen.getStageId(), screen.getClearType());
        stageInfo = fileUtil.getStageInfo(screen.getStageId(), 1);
//        stageInfo = screen.getStageInfoList().get(screen.getStageId() -1);
        skillList = stageInfo.getStageSkillList();

//        Toast toast = Toast.makeText(screen,
//                "skillList.size() : " + skillList.size(), Toast.LENGTH_LONG);
//        toast.show();

        stepCount = stageInfo.getTargetInfoList().get(0).getStepCount();

        targetType = stageInfo.getTargetInfoList().get(0).getTargetType();

        targetTotal = stageInfo.getTargetInfoList().get(0).getTargetTotal();

        ballMap = new BallMap(stageInfo);
//        ballMap = new BallMap(screen.getStageId());
        rootList = new ArrayList<PointBean>();

        // 描画クラスを初期化する
        draw = new DrawPlayView(screen.getResources());
        // 座標係数を設定する
        draw.setDiff(diff, diffX, diffY);
        draw.initSkillImage(skillList);

        // 画面描画リフレシュ：1秒/60回
        invalidateTimerTask = new InvalidateScreenTimerTask(context);
        invalidateTimer = new Timer(true);
        invalidateTimer.schedule(invalidateTimerTask, 16, 16);

        explosionPointList = new ArrayList<PointBean>();

        startScreenFlg = true;
        changeScreenCount = 2040;
    }

    /**
     * Viewを描画します
     *
     * @param c
     *            キャンパス
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas c) {
        try {
            if (stepCount < 0) {
                stepCount = 0;
            }
        // 描画クラスにキャンパスを設定する
        draw.setCanvas(c);

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
                if ("StageSelectScreen".equals(nextScreen)) {
                    screen.showStageSelectScreen();
                } else if ("NextStage".equals(nextScreen)) {
                    screen.showNextStage();
                } else if ("PlayScreen".equals(nextScreen)) {
                    screen.showPlayScreen(screen.getStageId());
                }
            }
        }

        if (changeScreenCount > 450) {
            // 扉を描画する
            draw.drawDoor(changeScreenCount);
            // ローディングの文字
            draw.drawLoading(changeScreenCount, changeScreenFlg);
        } else {

            // 背景を描画
            // c.drawColor(Color.GRAY);
            draw.drawBackGround(screen.getStageId());
            // Playエリアのタイルを描画する
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    draw.drawTile(i, j, ballMap.getMapValue(i, j));
                }
            }
            draw.drawStepTile(stageInfo.getStepMoveFromPointList(), stageInfo.getStepMoveToPointList(), 0);
            draw.drawStepTile(stageInfo.getStepStackFromPointList(), stageInfo.getStepStackToPointList(), 0);

            // ボールの移動ルートを描画
            for (int i = 0; i < (rootList.size() - 1); i++) {
                if (rootList.get(i).isCanMove()) {
                    draw.drawArrow(rootList.get(i));
                }
            }
            // ボールを描画
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (selected != (i * 10 + j) || animationTimer == null) {
                        boolean flg = false;
                        for (int k = 0; k < stageInfo.getStepMoveFromPointList().size(); k++) {
                            PointBean p = stageInfo.getStepMoveFromPointList().get(k);
                            if (i == p.getX() && j == p.getY()) {
                                flg = true;
                            }
                        }
                        for (int k = 0; k < stageInfo.getStepStackFromPointList().size(); k++) {
                            PointBean fromPoint = stageInfo.getStepStackFromPointList().get(k);
//                            PointBean toPoint = stageInfo.getStepStackToPointList().get(k);
                            if (i == fromPoint.getX() && j == fromPoint.getY()) {
                                flg = true;
                            }
                        }
                        if (!flg || stepTimer == null) {
                            draw.drawBall(i, j, ballMap.getMapValue(i, j));
                        }
                    }
                }
            }
            if (stepTimer != null) {
                if (stageInfo.getStepMoveFromPointList().size() > 0) {
                    draw.drawStepTile(stageInfo.getStepMoveFromPointList(), stageInfo.getStepMoveToPointList(), stepTimerTask.getTimeCount());
                    draw.drawStepMove(stageInfo.getStepMoveFromPointList(), stageInfo.getStepMoveToPointList(), ballMap, stepTimerTask.getTimeCount());
                }
                if ( stageInfo.getStepStackFromPointList().size() > 0) {
                    draw.drawStepTile(stageInfo.getStepStackFromPointList(), stageInfo.getStepStackToPointList(), stepTimerTask.getTimeCount());
                    draw.drawStepStack(stageInfo.getStepStackFromPointList(), stageInfo.getStepStackToPointList(), ballMap, stepTimerTask.getTimeCount());
                }
                if (stageInfo.getStepMoveFromPointList().size() == 0 && stageInfo.getStepStackFromPointList().size() == 0) {
                    stepTimerTask.setTimeCont(31);
                }
                if (stepTimerTask.getTimeCount() > 30) {
                    stepTimer.cancel();
                    stepTimer = null;
                    stepTimerTask = null;

                    if (stageInfo.getStepMoveFromPointList().size() > 0) {
                        for (int i = 0; i < stageInfo.getStepMoveFromPointList().size(); i++) {
                            PointBean fromPoint = stageInfo.getStepMoveFromPointList().get(i);
                            fromPoint.setValue(ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()));
                        }
                        for (int i = 0; i < stageInfo.getStepMoveFromPointList().size(); i++) {
                            PointBean fromPoint = stageInfo.getStepMoveFromPointList().get(i);
                            PointBean toPoint = stageInfo.getStepMoveToPointList().get(i);
                            ballMap.setMapValue(toPoint.getX(), toPoint.getY(), fromPoint.getValue());
                        }
                    }

                    if ( stageInfo.getStepStackFromPointList().size() > 0) {
                        for (int i = 0; i < stageInfo.getStepStackFromPointList().size(); i++) {
                            PointBean fromPoint = stageInfo.getStepStackFromPointList().get(i);
                            fromPoint.setValue(ballMap.getMapValue(fromPoint.getX(), fromPoint.getY()));
                        }
                        for (int i = 0; i < stageInfo.getStepStackFromPointList().size(); i++) {
                            PointBean fromPoint = stageInfo.getStepStackFromPointList().get(i);
                            PointBean toPoint = stageInfo.getStepStackToPointList().get(i);
                            // TO位置のボール値が0の場合のみ移動可能
                            if (ballMap.getMapValue(toPoint.getX(), toPoint.getY()) == 0) {
                                ballMap.setMapValue(toPoint.getX(), toPoint.getY(), fromPoint.getValue());
                                ballMap.setMapValue(fromPoint.getX(), fromPoint.getY(), 0);
                            }
                        }
                    }
                    if (nextFlg) {
                        nextFlg = false;
                        ballMap.setBall(nextBall1);
                        ballMap.setBall(nextBall2);
                        ballMap.setBall(nextBall3);

                        nextBall1 = ballMap.getRanBall(stageInfo);
                        nextBall2 = ballMap.getRanBall(stageInfo);
                        nextBall3 = ballMap.getRanBall(stageInfo);
                        if (bounsCount == 0 && !gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
                            if (targetType == 0) {
                                targetCount = score;
                            } else if (targetType == 10) {
                                targetCount = enball;
                            }
                            if (null != playTimer) {
                                playTimer.cancel();
                                playTimer = null;
                                playTimerTask = null;
                            }
                            if (!gameClearFlg) {
                                Log.d("test_log","playTimeCount : " + this.playTimeCount);
                                bonusMap.put("playTimeCount", playTimeCount);
                            }
                            gameClearFlg = true;
//                            fileUtil.setStageStatus(screen.getMapId(), screen.getStageId(), screen.getClearType());
                            fileUtil.setStageStatus(screen.getStageId());
                            fileUtil.addMoney(((totalScore - score) / 100) + enball);
                            fileUtil.setStageRanking(screen.getStageId(), totalScore);
                        } else if (bounsCount == 0 && !gameClearFlg && !gameOverFlg &&  checkGameOver()) {
                            gameOverFlg = true;
                            if (null != comboTimer) {
                                comboTimer.cancel();
                                comboTimer = null;
                                comboTimerTask = null;
                            }
                            if (null != playTimer) {
                                playTimer.cancel();
                                playTimer = null;
                                playTimerTask = null;
                            }
                        }
                    }
                }
            }

            // 選択されたボールを描画
            if (selected > -1 && animationTimer != null) {
                int animationType = (selectAnimationTimerTask.getTimeCount() % 4) + 1;
                draw.drawBall(getLastPoint().getX(), getLastPoint().getY(), (moveType * 100) + animationType);
            }

            if (bombTimer != null) {
                draw.drawExplosion(explosionPointList, (bombTimerTask.getTimeCount() * 6));
                if (bombTimerTask.getTimeCount() > 20) {
                    bombTimer.cancel();
                    bombTimer = null;
                    bombTimerTask = null;
                }
            }

            // コンボエリアを描画
            if (checkCombo()) {
                draw.drawComboArea(comboCount, comboTimerTask.getTimeCount());
            } else {
                comboCount = 0;
                draw.drawComboArea(0, 100);
            }

            // 得点を描く
            if (scoreTimer != null) {
                draw.drawNumberString(clearScore, clearX, clearY - scoreTimerTask.getTimeCount(), 50);
                if (scoreTimerTask.getTimeCount() > 120) {
                    scoreTimer.cancel();
                    scoreTimer = null;
                    scoreTimerTask = null;
                    clearX = -1;
                    clearY = -1;
                    clearScore = "";
                }
            }

            if (comboTextTimer != null) {
                if (comboCount > 1) {
                    draw.drawComboText(comboCount, clearX, clearY - comboTextTimerTask.getTimeCount(), 50);
                }
                if (comboTextTimerTask.getTimeCount() > 120) {
                    comboTextTimer.cancel();
                    comboTextTimer = null;
                    comboTextTimerTask = null;
                }
            }

            // 得点を描画
            draw.drawScore(score);

            draw.drawEnBall(enball);

            if (targetCount >= targetTotal && stepClear == 0) {
                stepClear = stepCount;
            }
            if (stepClear > 0) {
                stepCount = stepClear;
//                Log.d("test_log","stepCount : " + stepCount);
            }
            draw.drawStep(stepCount);

            runCount = draw.drawTargetProgress(targetCount, targetTotal, runCount);
            if (!gameClearFlg && !gameOverFlg && runCount >= 740 && selected == -1) {
                if (!gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
                    if (targetType == 0) {
                        targetCount = score;
                    } else if (targetType == 10) {
                        targetCount = enball;
                    }
                    if (null != playTimer) {
                        playTimer.cancel();
                        playTimer = null;
                        playTimerTask = null;
                    }
                    if (!gameClearFlg) {
                        bonusMap.put("playTimeCount", playTimeCount);
                    }
                    gameClearFlg = true;
                    //                fileUtil.setStageStatus(screen.getMapId(), screen.getStageId(), screen.getClearType());
                    fileUtil.setStageStatus(screen.getStageId());
                    fileUtil.addMoney(((totalScore - score) / 100) + enball);
                    fileUtil.setStageRanking(screen.getStageId(), totalScore);
                }
            }

            draw.drawTarget(targetType, targetCount, targetTotal);

            if (nextBall1 > 0) {
                draw.drawNextBall(nextBall1, nextBall2, nextBall3);
            }

            //////////
            // プレイ時間を描画する
            if (!gameClearFlg && !gameOverFlg && null != playTimer) {
                playTimeCount = playTimerTask.getTimeCount();
            }
            draw.drawPlayTime(playTimeCount);

            //////////
            // スキルが選択された際に、スキルパネルを描画する
            if (selectSkillId > 0) {
                //            SkillBean skillBean = skillList.get(selectSkillId - 1);
                if (skillTimer != null) {
                    draw.drawSkillAnimation(skillList.get(selectSkillId - 1), ballMap, skillTimerTask.getTimeCount(), selectSkillId - 1);
                    if (skillTimerTask.getTimeCount() > skillList.get(selectSkillId - 1).getSkillAnimationTimeCount()) {
                        skillTimer.cancel();
                        skillTimer = null;
                        skillTimerTask = null;
                        overSkillAnimation(skillList.get(selectSkillId - 1));
                        if (!"0".equals(skillList.get(selectSkillId - 1).getSkillType())) {

                            // 目標リストを空にする
                            skillList.get(selectSkillId - 1).clearSkillTargetList();

                            if ("4".equals(skillList.get(selectSkillId - 1).getSkillId())) {
                                skillList.get(selectSkillId - 1).setSkillStartFlg("0");
                            } else if ("5".equals(skillList.get(selectSkillId - 1).getSkillId())) {
                                skillList.get(selectSkillId - 1).setSkillStartFlg("0");
                            }
                        }
                        fileUtil.setSkillCount(skillList);
                        selectSkillId = 0;
                    }
                } else {
                    draw.drawSkillPanel(skillList.get(selectSkillId - 1));
                }
            }

            if (targetCount >= targetTotal && bounsCount < 50 && !gameClearFlg && !gameOverFlg) {
                if (null != playTimer) {
                    playTimer.cancel();
                    playTimer = null;
                    playTimerTask = null;
                }
                //bounsCount = draw.drawbounsText(bounsCount);
            }

            //////////
            // ゲームクリアのパネルを描画する
            if (gameClearFlg) {
                clearCount = draw.drawGameClearText(score, enball, bonusMap, clearCount, screen.getStageId());
                if (clearCount > 9000) {
                    clearCount = 9000;
                }
            }

            //////////
            // ゲームオーバーのパネルを描画する
            if (gameOverFlg) {
                draw.drawGameOverText();
            }

            //////////
            // メニューパネルを描画する
            if (menuShowFlg) {
                if (playTimeCount > 0) {
                    draw.drawMenu(stageInfo.getTargetInfoList().get(0), clearHistory, true ,stageInfo.getStageId());
                } else {
                    draw.drawMenu(stageInfo.getTargetInfoList().get(0), clearHistory, false,stageInfo.getStageId());
                }
            }
            draw.drawFrame();

            // 表示ボタンを描画
            draw.drawButton(btnKbn, menuShowFlg);
            // スキルエリアを描画
            draw.drawSkill(skillList);

            if (tutorialStep < 99 && tutorialStep > 0 && !menuShowFlg) {
                // TODO:
                if (screen.getStageId() == 1 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    if (!gameClearFlg) {stepCount--;}
                    endCheck(4, 4);
                }
                if (screen.getStageId() == 1 && tutorialStep == 6 && tutorialAnimationCount == 0) {
                    ballMap.setMapValue(3, 4, 5);
                    ballMap.setMapValue(3, 5, 7);
                    ballMap.setMapValue(2, 4, 1);
                    nextBall1 = ballMap.getRanBall(stageInfo);
                    nextBall2 = ballMap.getRanBall(stageInfo);
                    nextBall3 = ballMap.getRanBall(stageInfo);
                }
                if (screen.getStageId() == 1 && tutorialStep == 9 && tutorialAnimationCount == 0) {
                    if (!gameClearFlg) {stepCount--;}
                    endCheck(4, 4);
                }
                if (screen.getStageId() == 1 && tutorialStep == 10 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 2 && tutorialStep == 3 && tutorialAnimationCount == 0) {
                    if (!gameClearFlg) {stepCount--;}
                    endCheck(3, 3);
                }
                if (screen.getStageId() == 2 && tutorialStep == 5 && tutorialAnimationCount == 0) {
                    if (!gameClearFlg) {stepCount--;}
                    endCheck(3, 1);
                }
                if (screen.getStageId() == 2 && tutorialStep == 6 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 10 && tutorialStep == 5 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 20 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 40 && tutorialStep == 5 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 60 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 80 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 33 && tutorialStep == 2 && tutorialAnimationCount == 0) {
                    endCheck(5, 4);
                    tutorialStep++;
                }
                if (screen.getStageId() == 33 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    endCheck(4, 2);
                    tutorialStep++;
                }
                if (screen.getStageId() == 33 && tutorialStep == 6 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 100 && tutorialStep == 2 && tutorialAnimationCount == 0) {
                    tutorialStep++;
                }
                if (screen.getStageId() == 100 && tutorialStep == 5 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 129 && tutorialStep == 3 && tutorialAnimationCount == 0) {
                    ballMap.setMapValue(1, 5, 8);
                    ballMap.setMapValue(4, 1, 6);
                    ballMap.setMapValue(3, 5, 2);
                    nextBall1 = ballMap.getRanBall(stageInfo);
                    nextBall2 = ballMap.getRanBall(stageInfo);
                    nextBall3 = ballMap.getRanBall(stageInfo);
                }
                if (screen.getStageId() == 129 && tutorialStep == 6 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                if (screen.getStageId() == 145 && tutorialStep == 4 && tutorialAnimationCount == 0) {
                    tutorialStep = 99;
                }
                tutorialAnimationCount = draw.drawTutorialPlay(tutorialStep, screen.getStageId(), tutorialAnimationCount);
            }

            draw.drawDoor(changeScreenCount);

            if (!tutorialPlayFlg && changeScreenCount == 0) {
                if (screen.getStageId() == 1
                 || screen.getStageId() == 2
                 || screen.getStageId() == 10
                 || screen.getStageId() == 20
                 || screen.getStageId() == 33
                 || screen.getStageId() == 40
                 || screen.getStageId() == 60
                 || screen.getStageId() == 80
                 || screen.getStageId() == 100
                 || screen.getStageId() == 129
                 || screen.getStageId() == 145
                ) {
                    tutorialPlayFlg = true;
                    tutorialStep = 1;
                } else {
                    tutorialStep = 99;
                }
            }
        }
//        draw.DrawMemory();
        //throw new Exception("test Message");
        } catch (Exception e) {

            Log.d("test_log",e.toString(),e.fillInStackTrace());
            e.printStackTrace();
            fileUtil.printException(e);

        }
    }

    /**
     * コンボの状態をチェックします
     *
     * @return
     */
    private boolean checkCombo() {
        if (comboTimer == null) {
            return false;
        }
        if (comboTimerTask.getTimeCount() > 100) {
            comboTimer.cancel();
            comboTimer = null;
            return false;
        }
        return true;
    }

    /**
     * 画面タッチイベント
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int ix = getIndex(x, 30, diffX);
        int jy = getIndex(y, 530, diffY);
        switch (action) {
        case MotionEvent.ACTION_DOWN:

            if (tutorialStep < 99) {

                if (!gameClearFlg && !gameOverFlg && !menuShowFlg && isIn(x, y, draw.getBounds("menu"))) {
                    if (null != playTimer) {
                        playTimer.cancel();
                        playTimer = null;
                        playTimerTask = null;
                    }
                    menuShowFlg = true;
                } else if (menuShowFlg && isIn(x, y, draw.getBounds("back"))) {
                    playTimerTask = new CountTimerTask();
                    playTimerTask.setTimeCont(playTimeCount);
                    // タイマーの初期化処理
                    playTimer = new Timer(true);
                    playTimer.schedule(playTimerTask, 1000, 1000);
                    menuShowFlg = false;
                } else if (menuShowFlg && isIn(x, y, draw.getBounds("restart"))) {
                    playTimerTask = new CountTimerTask();
                    playTimerTask.setTimeCont(playTimeCount);
                    // タイマーの初期化処理
                    playTimer = new Timer(true);
                    playTimer.schedule(playTimerTask, 1000, 1000);
                    menuShowFlg = false;
                } else if (menuShowFlg && isIn(x, y, draw.getBounds("map"))) {
                    changeScreenFlg = true;
                    nextScreen = "StageSelectScreen";
                }

//                if (tutorialStep = 1) {
//
//                    selectSkill(1);
//                }

                //
                tutorialControl(ix, jy);

            } else {
                if (null == stepTimer) {
                    if (selectSkillId > 0
                            && !"0".equals(skillList.get(selectSkillId - 1).getSkillType())
                            && !"0".equals(skillList.get(selectSkillId - 1).getSkillStartFlg())
                            && skillList.get(selectSkillId - 1).getSkillCount() > 0
                            && isIn(x, y, draw.getBounds("skill_use"))) {
                        SkillBean skillBean = skillList.get(selectSkillId - 1);
                        checkSkill(skillBean);
                        // タイマーの初期化処理
                        skillTimerTask = new CountTimerTask();
                        skillTimer = new Timer(true);
                        skillTimer.schedule(skillTimerTask, 14, 14);
                    } else if (selectSkillId > 0
                            && "0".equals(skillList.get(selectSkillId - 1).getSkillStartFlg())
                            && skillTimer == null
                            && skillList.get(selectSkillId - 1).getSkillCount() > 0
                            && isIn(x, y)) {
                        if (ballMap.getMapValue(ix, jy) > 0) {
                            if (!"4".equals(skillList.get(selectSkillId - 1).getSkillId()) || ballMap.getMapValue(ix, jy) < 20) {
                                PointBean point = new PointBean(ix, jy);
                                point.setValue(ballMap.getMapValue(ix, jy));
                                skillList.get(selectSkillId - 1).addSkillTarget(point);
                                skillList.get(selectSkillId - 1).setSkillStartFlg("1");
                            }
                        }
                    } else if (selectSkillId > 0
                            && skillList.get(selectSkillId - 1).getSkillTargetList().size() > 0
                            && "1".equals(skillList.get(selectSkillId - 1).getSkillStartFlg())
                            && skillList.get(selectSkillId - 1).getSkillCount() > 0
                            && skillTimer == null
                            && isIn(x, y)) {
                        //
                        if (skillList.get(selectSkillId - 1).getSkillTargetList().get(0).getX() == ix
                                && skillList.get(selectSkillId - 1).getSkillTargetList().get(0).getY() == jy) {
                            skillList.get(selectSkillId - 1).clearSkillTargetList();
                            skillList.get(selectSkillId - 1).setSkillStartFlg("0");

                        } else if (ballMap.getMapValue(ix, jy) > 0) {
                            if (!"4".equals(skillList.get(selectSkillId - 1).getSkillId()) || ballMap.getMapValue(ix, jy) < 20) {
                                PointBean point = new PointBean(ix, jy);
                                point.setValue(ballMap.getMapValue(ix, jy));
                                // 目標リストをクリアする
                                skillList.get(selectSkillId - 1).clearSkillTargetList();
                                skillList.get(selectSkillId - 1).addSkillTarget(point);
                                skillList.get(selectSkillId - 1).setSkillStartFlg("1");
                            }
                        }
                    } else if (selectSkillId > 0 && isIn(x, y, draw.getBounds("skill_back"))) {
                        if ("2".equals(skillList.get(selectSkillId - 1).getSkillType())) {
                            skillList.get(selectSkillId - 1).clearSkillTargetList();
                            skillList.get(selectSkillId - 1).setSkillStartFlg("0");
                        }
                        selectSkillId = 0;
                    } else if (selectSkillId > 0) {

                    } else if (selectSkillId == 0) {
                        if ((gameClearFlg || gameOverFlg) && isIn(x, y, draw.getBounds("map"))) {
                            changeScreenFlg = true;
                            nextScreen = "StageSelectScreen";

                        } else if ((gameClearFlg || gameOverFlg) && isIn(x, y, draw.getBounds("retry"))) {
//                            gameOverFlg = false;
//                            gameClearFlg = false;
//                            StageInfoBean stageInfo = fileUtil.getStageInfo(screen.getStageId(), 1);
//                            score = 0;
//                            targetCount = 0;
//                            playTimeCount = 0;
//                            bonusMap.put("playTimeCount", 0);
//                            stepCount = stageInfo.getTargetInfoList().get(0).getStepCount();
//                            targetType = stageInfo.getTargetInfoList().get(0).getTargetType();
//                            targetTotal = stageInfo.getTargetInfoList().get(0).getTargetTotal();
//                            if (null != comboTimer) {
//                                comboTimer.cancel();
//                                comboTimer = null;
//                                comboTimerTask = null;
//                            }
//                            bounsCount = 0;
//                            clearCount = 0;
//                            enball = 0;
                            ballMap = new BallMap(stageInfo);
                            changeScreenFlg = true;
                            nextScreen = "PlayScreen";
                        } else if (gameClearFlg && isIn(x, y, draw.getBounds("next"))) {
                            //                    invalidateTimer.cancel();
                            //                    invalidateTimer = null;
                            //                    invalidateTimerTask = null;
                            //                    screen.showNextStage();
                            changeScreenFlg = true;
                            nextScreen = "NextStage";
                        } else if (gameClearFlg && isIn(x, y, draw.getBounds("facebook"))) {


//                                try {
                                    String mFacebook;
                       //             mFacebook = "http://www.facebook.com/apps/application.php?id=167668986948234";

                                    mFacebook = "https://www.facebook.com/dialog/feed?app_id=167668986948234"
                                            + "&link=https://play.google.com/store/apps/details?id=jp.coxs.plusn&hl=ja"
                                            + "&display=popup"
                                            + "&caption=新感覚パズルゲーム「PLUSN」のステージ" + stageInfo.getStageId() + "で"+ bonusMap.get("totalPoint") +"ポイントを取得したよ。私と一緒に遊びましょう！"
                                            + "&ref=share"
                                            + "&picture=https://lh3.googleusercontent.com/Chvk3-qvbEYNjSfqtR7JSw1kKe55kxSADhS4Wq8rumkk6v9e94EKAS0EaBgIdO7n=w300-rw"
                                            + "&source=https://lh3.googleusercontent.com/Chvk3-qvbEYNjSfqtR7JSw1kKe55kxSADhS4Wq8rumkk6v9e94EKAS0EaBgIdO7n=w300-rw"
                                            + "&redirect_uri=https://play.google.com/store/apps/details?id=jp.coxs.plusn"
                                            + "&name=PLUSN"
                                            ;
//                                            + "&name=www.coxs.jp";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mFacebook));
//                                    Intent intent = new Intent(Intent.ACTION_SEND);
//                                    intent.setType("text/plain");
//                                    intent.putExtra(Intent.EXTRA_TEXT, "test");
//                                    intent.setPackage("com.facebook.katana");   // パッケージをそのまま指定
                                    screen.sns(intent);
//                                } catch (UnsupportedEncodingException e) {
//                                    e.printStackTrace();
//                                }

                        } else if (gameClearFlg && isIn(x, y, draw.getBounds("twitter"))) {
                            try {
                                String mTweet;
                                mTweet = "https://twitter.com/intent/tweet?text="
                                        + URLEncoder.encode("新感覚パズルゲーム「PLUSN」のステージ" + stageInfo.getStageId() + "で"+ bonusMap.get("totalPoint") +"ポイントを取得したよ。私と一緒に遊びましょう！", "UTF-8")
                                        + "&url=https://play.google.com/store/apps/details?id=jp.coxs.plusn&hl=ja"
//                                        + URLEncoder.encode("テスト", "UTF-8")
                                        ;
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTweet));
                                screen.sns(intent);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        } else if (!gameClearFlg && !gameOverFlg && !menuShowFlg && isIn(x, y, draw.getBounds("menu"))) {
                            if (null != playTimer) {
                                playTimer.cancel();
                                playTimer = null;
                                playTimerTask = null;
                            }
                            menuShowFlg = true;
                        } else if (menuShowFlg && isIn(x, y, draw.getBounds("back"))) {
                            playTimerTask = new CountTimerTask();
                            playTimerTask.setTimeCont(playTimeCount);
                            // タイマーの初期化処理
                            playTimer = new Timer(true);
                            playTimer.schedule(playTimerTask, 1000, 1000);
                            menuShowFlg = false;
                        } else if (menuShowFlg && isIn(x, y, draw.getBounds("restart"))) {
                            playTimerTask = new CountTimerTask();
                            playTimerTask.setTimeCont(playTimeCount);
                            // タイマーの初期化処理
                            playTimer = new Timer(true);
                            playTimer.schedule(playTimerTask, 1000, 1000);
                            menuShowFlg = false;
                        } else if (menuShowFlg && isIn(x, y, draw.getBounds("map"))) {

                            changeScreenFlg = true;
                            nextScreen = "StageSelectScreen";
                        } else if (!menuShowFlg && !gameClearFlg && !gameOverFlg && !"0".equals(skillType) && isIn(x, y)) {
                            // スキル発動エリアを選択する
                            if ("1".equals(skillType) && ballMap.getMapValue(ix, jy) != 0) {
                                ballMap.setMapValue(ix, jy, 0);
                                skillType = "0";
                            }
                        } else if (!menuShowFlg && !gameClearFlg && !gameOverFlg && "0".equals(skillType) && isIn(x, y)) {

                            // ボールありの場合、ボールを選択する
                            if (!gameClearFlg && canMove(ballMap.getMapValue(ix, jy))) {
                                if (playTimer == null && bounsCount == 0) {
                                    playTimerTask = new CountTimerTask();
                                    playTimerTask.setTimeCont(playTimeCount);
                                    // タイマーの初期化処理
                                    playTimer = new Timer(true);
                                    playTimer.schedule(playTimerTask, 1000, 1000);

                                    nextBall1 = ballMap.getRanBall(stageInfo);
                                    nextBall2 = ballMap.getRanBall(stageInfo);
                                    nextBall3 = ballMap.getRanBall(stageInfo);
                                }
                                rootList.add(new PointBean(ix, jy));
                                selected = ix * 10 + jy;

                                moveType = ballMap.getMapValue(ix, jy);
                                ballMap.setMapValue(ix, jy, 0);
                                if (animationTimer != null) {
                                    animationTimer.cancel();
                                    animationTimer = null;
                                }
                                selectAnimationTimerTask = new CountTimerTask();
                                // タイマーの初期化処理
                                animationTimer = new Timer(true);
                                animationTimer.schedule(selectAnimationTimerTask, 180, 180);
                            }

                        } else if (!menuShowFlg && !gameClearFlg && !gameOverFlg && skillList.size() > 0) {
                            for (int i = 0; i < skillList.size(); i++) {
                                if (isIn(x, y, draw.getBounds("skill_" + (i + 1)))) {
                                    selectSkill(i + 1);
                                }
                            }
                        }
                    }
                }
            }
            break;
        case MotionEvent.ACTION_UP:
            // TODO:
            if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 1) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 4 && getLastPoint().getY() == 4) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
//                    if (!gameClearFlg) {stepCount--;}
//                    endCheck(getLastPoint().getX(), getLastPoint().getY());
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(4, 2, 3);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 1 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 1) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 4 && screen.getStageId() == 1) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 4 && getLastPoint().getY() == 2) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(3, 2, 2);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 5 && screen.getStageId() == 1 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 6 && screen.getStageId() == 1 && tutorialAnimationCount > 100) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 7 && screen.getStageId() == 1) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 4 && getLastPoint().getY() == 4) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(2, 3, 4);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 8 && screen.getStageId() == 1 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 9 && screen.getStageId() == 1 && tutorialAnimationCount == 40) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 2) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 3 && getLastPoint().getY() == 3) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(3, 5, 6);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 2 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 3 && screen.getStageId() == 2) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 3 && getLastPoint().getY() == 1) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(3, 3, 21);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 2 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 5 && screen.getStageId() == 2 && tutorialAnimationCount == 40) {
                tutorialStep++;
                tutorialAnimationCount = 0;


            // ステージ１０　スキル１
            } else if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 10 && isIn(x, y, draw.getBounds("skill_1"))) {
                selectSkill(1);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 10) {
                if (selectSkillId > 0
                        && "0".equals(skillList.get(selectSkillId - 1).getSkillStartFlg())
                        && skillTimer == null
                        && skillList.get(selectSkillId - 1).getSkillCount() > 0
                        && isIn(x, y)) {
                    if (ballMap.getMapValue(ix, jy) > 0) {
                        if (!"4".equals(skillList.get(selectSkillId - 1).getSkillId()) || ballMap.getMapValue(ix, jy) < 20) {
                            PointBean point = new PointBean(ix, jy);
                            point.setValue(ballMap.getMapValue(ix, jy));
                            skillList.get(selectSkillId - 1).addSkillTarget(point);
                            skillList.get(selectSkillId - 1).setSkillStartFlg("1");
                            tutorialStep++;
                            tutorialAnimationCount = 0;
                        }
                    }
                }

            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 10 && tutorialAnimationCount > 39 && isIn(x, y, draw.getBounds("skill_use"))) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                SkillBean skillBean = skillList.get(selectSkillId - 1);
                checkSkill(skillBean);
                // タイマーの初期化処理
                skillTimerTask = new CountTimerTask();
                skillTimer = new Timer(true);
                skillTimer.schedule(skillTimerTask, 14, 14);
            } else if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 10 && tutorialAnimationCount == 120) {
                tutorialStep++;
                tutorialAnimationCount = 0;

            // ステージ２０　スキル２
            } else if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 20 && isIn(x, y, draw.getBounds("skill_2"))) {
                selectSkill(2);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 20 && tutorialAnimationCount > 39 && isIn(x, y, draw.getBounds("skill_use"))) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                SkillBean skillBean = skillList.get(selectSkillId - 1);
                checkSkill(skillBean);
                // タイマーの初期化処理
                skillTimerTask = new CountTimerTask();
                skillTimer = new Timer(true);
                skillTimer.schedule(skillTimerTask, 14, 14);
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 20 && tutorialAnimationCount == 80) {
                tutorialStep++;
                tutorialAnimationCount = 0;

            // ステージ４０　スキル３
            } else if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 40 && isIn(x, y, draw.getBounds("skill_3"))) {
                selectSkill(3);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 40) {
                if (selectSkillId > 0
                        && "0".equals(skillList.get(selectSkillId - 1).getSkillStartFlg())
                        && skillTimer == null
                        && skillList.get(selectSkillId - 1).getSkillCount() > 0
                        && isIn(x, y)) {
                    if (ballMap.getMapValue(ix, jy) > 0) {
                        if (!"4".equals(skillList.get(selectSkillId - 1).getSkillId()) || ballMap.getMapValue(ix, jy) < 20) {
                            PointBean point = new PointBean(ix, jy);
                            point.setValue(ballMap.getMapValue(ix, jy));
                            skillList.get(selectSkillId - 1).addSkillTarget(point);
                            skillList.get(selectSkillId - 1).setSkillStartFlg("1");
                            tutorialStep++;
                            tutorialAnimationCount = 0;
                        }
                    }
                }
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 40 && tutorialAnimationCount > 39 && isIn(x, y, draw.getBounds("skill_use"))) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                SkillBean skillBean = skillList.get(selectSkillId - 1);
                checkSkill(skillBean);
                // タイマーの初期化処理
                skillTimerTask = new CountTimerTask();
                skillTimer = new Timer(true);
                skillTimer.schedule(skillTimerTask, 14, 14);
            } else if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 40 && tutorialAnimationCount == 120) {
                tutorialStep++;
                tutorialAnimationCount = 0;

            // ステージ６０　スキル４
            } else if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 60 && isIn(x, y, draw.getBounds("skill_4"))) {
                selectSkill(4);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 60 && tutorialAnimationCount > 39 && isIn(x, y, draw.getBounds("skill_use"))) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                SkillBean skillBean = skillList.get(selectSkillId - 1);
                checkSkill(skillBean);
                // タイマーの初期化処理
                skillTimerTask = new CountTimerTask();
                skillTimer = new Timer(true);
                skillTimer.schedule(skillTimerTask, 14, 14);
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 60 && tutorialAnimationCount == 120) {
                tutorialStep++;
                tutorialAnimationCount = 0;

            // ステージ８０　スキル５
            } else if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 80 && isIn(x, y, draw.getBounds("skill_4"))) {
                selectSkill(4);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 80 && tutorialAnimationCount > 39 && isIn(x, y, draw.getBounds("skill_use"))) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                SkillBean skillBean = skillList.get(selectSkillId - 1);
                checkSkill(skillBean);
                // タイマーの初期化処理
                skillTimerTask = new CountTimerTask();
                skillTimer = new Timer(true);
                skillTimer.schedule(skillTimerTask, 14, 14);
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 80 && tutorialAnimationCount == 120) {
                tutorialStep++;
                tutorialAnimationCount = 0;






                // TODO:
//            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 33 && tutorialAnimationCount > 39) {
//                tutorialStep++;
//                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 33) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 5 && getLastPoint().getY() == 4) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(1, 3, 7);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 3 && screen.getStageId() == 33) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 4 && getLastPoint().getY() == 2) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(2, 2, 21);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();

            } else if (!menuShowFlg && tutorialStep == 5 && screen.getStageId() == 33 && tutorialAnimationCount == 40) {
                tutorialStep++;
                tutorialAnimationCount = 0;


            // スターダスト
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 100) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 1 && getLastPoint().getY() == 4) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(1, 5, 7);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 100 && tutorialAnimationCount > 39) {
                endCheck(1, 4);
                tutorialStep++;
                tutorialAnimationCount = 0;
            } else if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 100 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;


            // 水流
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 129) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 1 && getLastPoint().getY() == 3) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(1, 2, 6);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 129 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                nextFlg = false;

                stepTimerTask = new CountTimerTask();
                stepTimer = new Timer(true);
                stepTimer.schedule(stepTimerTask, 16, 16);

            } else if (!menuShowFlg && selected > -1 && tutorialStep == 3 && screen.getStageId() == 129) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 5 && getLastPoint().getY() == 3) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(1, 3, 6);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 129 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                endCheck(5, 3);
            } else if (!menuShowFlg && tutorialStep == 5 && screen.getStageId() == 129 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;


            // 凍り付け
            } else if (!menuShowFlg && selected > -1 && tutorialStep == 1 && screen.getStageId() == 145) {
                animationTimer.cancel();
                animationTimer = null;
                if (getLastPoint().getX() == 4 && getLastPoint().getY() == 3) {
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (!gameClearFlg) {stepCount--;}
                    tutorialStep++;
                    tutorialAnimationCount = 0;
                } else {
                    ballMap.setMapValue(4, 5, 4);
                }
                selected = -1;
                rootList = new ArrayList<PointBean>();
            } else if (!menuShowFlg && tutorialStep == 2 && screen.getStageId() == 145 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;
                endCheck(4, 3);
            } else if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 145 && tutorialAnimationCount > 39) {
                tutorialStep++;
                tutorialAnimationCount = 0;


            } else {
                if (animationTimer != null) {
                    animationTimer.cancel();
                    animationTimer = null;
                    ballMap.setMapValue(getLastPoint().getX(), getLastPoint().getY(), moveType);
                    if (selected != ((getLastPoint().getX() * 10) + getLastPoint().getY())) {
                        if (bounsCount == 0) {
                            if (!gameClearFlg) {stepCount--;}
                        }
                        endCheck(getLastPoint().getX(), getLastPoint().getY());
                    }
                    selected = -1;
                    rootList = new ArrayList<PointBean>();
                    //                }
                }
            }
            break;
        case MotionEvent.ACTION_MOVE:

            if (animationTimer != null) {
                if (ix >= 0 && jy >= 0) {
                    putRootList(ix, jy);
                }
            }
            break;
        }
        return true;
    }

    private void tutorialControl(int ix, int jy) {
        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 1 && ix == 4 && jy == 2) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);

                nextBall1 = 5;
                nextBall2 = 7;
                nextBall3 = 1;
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 4 && screen.getStageId() == 1 && ix == 3 && jy == 2) {

            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 7 && screen.getStageId() == 1 && ix == 2 && jy == 3) {

            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 2 && ix == 3 && jy == 5) {

            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 2 && ix == 3 && jy == 3) {

            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }

        // TODO:
        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 33 && ix == 1 && jy == 3) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);

                nextBall1 = 5;
                nextBall2 = 7;
                nextBall3 = 1;
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 33 && ix == 2 && jy == 2) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 100 && ix == 1 && jy == 5) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);

                nextBall1 = 2;
                nextBall2 = 8;
                nextBall3 = 6;
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }

        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 129 && ix == 1 && jy == 2) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);

                nextBall1 = 2;
                nextBall2 = 8;
                nextBall3 = 6;
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }

        if (!menuShowFlg && tutorialStep == 3 && screen.getStageId() == 129 && ix == 1 && jy == 3) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }

        if (!menuShowFlg && tutorialStep == 1 && screen.getStageId() == 145 && ix == 4 && jy == 5) {
            if (playTimer == null && bounsCount == 0) {
                playTimerTask = new CountTimerTask();
                playTimerTask.setTimeCont(playTimeCount);
                // タイマーの初期化処理
                playTimer = new Timer(true);
                playTimer.schedule(playTimerTask, 1000, 1000);

                nextBall1 = 5;
                nextBall2 = 7;
                nextBall3 = 1;
            }
            rootList.add(new PointBean(ix, jy));
            selected = ix * 10 + jy;

            moveType = ballMap.getMapValue(ix, jy);
            ballMap.setMapValue(ix, jy, 0);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            selectAnimationTimerTask = new CountTimerTask();
            // タイマーの初期化処理
            animationTimer = new Timer(true);
            animationTimer.schedule(selectAnimationTimerTask, 180, 180);
        }
    }

    private boolean canMove(int mapValue) {

        if (mapValue < 10 && mapValue > 0) {
            return true;
        } else if (mapValue == 21) {
            return true;
        }

        return false;
    }

    private void selectSkill(int i) {
        selectSkillId = i;
    }

    /**
     * スキル選択確認
     *
     * @param skillBean
     */
    private void checkSkill(SkillBean skillBean) {

        if ("3".equals(skillBean.getSkillId())) {
            skillBean.addSkillTarget(ballMap.getRandomBall());
            skillBean.addSkillTarget(ballMap.getRandomBall());
            skillBean.addSkillTarget(ballMap.getRandomBall());
            for (int i = 0; i < skillBean.getSkillTargetList().size(); i++) {
                PointBean bean = skillBean.getSkillTargetList().get(i);
                ballMap.setMapValue(bean.getX(), bean.getY(), 0);
            }
        }
        if ("4".equals(skillBean.getSkillId())) {

            List<PointBean> ballList = ballMap.getBallList(skillBean.getSkillTargetList().get(0).getValue());

            skillBean.clearSkillTargetList();

            for (int i = 0; i < ballList.size(); i++) {
                skillBean.addSkillTarget(ballList.get(i));
            }

            for (int i = 0; i < skillBean.getSkillTargetList().size(); i++) {
                PointBean bean = skillBean.getSkillTargetList().get(i);
                ballMap.setMapValue(bean.getX(), bean.getY(), 0);
            }
        }
        if ("5".equals(skillBean.getSkillId())) {
            ballMap.setMapValue(skillBean.getSkillTargetList().get(0).getX(), skillBean.getSkillTargetList().get(0).getY(), 0);
        }
    }
    private void overSkillAnimation(SkillBean skillBean) {

        if ("1".equals(skillBean.getSkillId())) {
            stepCount +=5;
        } else if ("2".equals(skillBean.getSkillId())) {
            List<PointBean> ballList = ballMap.getBallList();
            for (int i = 0; i < ballList.size(); i++) {
                ballMap.setMapValue(ballList.get(i).getX(), ballList.get(i).getY(), 0);
            }
            for (int i = 0; i < ballList.size(); i++) {
                ballMap.setBall(ballList.get(i).getX(), ballList.get(i).getY());
            }
        } else if ("3".equals(skillBean.getSkillId())) {

            for (int i = 0; i < skillBean.getSkillTargetList().size(); i++) {
                PointBean bean = skillBean.getSkillTargetList().get(i);
                ballMap.setMapValue(bean.getX(), bean.getY(), 0);
            }

            if (!gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
                if (targetType == 0) {
                    targetCount = score;
                } else if (targetType == 10) {
                    targetCount = enball;
                }
                if (null != playTimer) {
                    playTimer.cancel();
                    playTimer = null;
                    playTimerTask = null;
                }
                if (!gameClearFlg) {
                    Log.d("test_log","playTimeCount : " + this.playTimeCount);
                    bonusMap.put("playTimeCount", playTimeCount);
                }
                gameClearFlg = true;
                fileUtil.setStageStatus(screen.getStageId());
                fileUtil.addMoney(((totalScore - score) / 100) + enball);
                fileUtil.setStageRanking(screen.getStageId(), totalScore);
            }
        } else if ("4".equals(skillBean.getSkillId())) {
            //            List<PointBean> ballList = ballMap.getBallList(skillBean.getSkillTargetList().get(0).getValue());
            //            for (int i = 0; i < ballList.size(); i++) {
//            for (int i = 0; i < skillBean.getSkillTargetList().size(); i++) {
//                //                ballMap.setMapValue(ballList.get(i).getX(), ballList.get(i).getY(), 0);
//                PointBean bean = skillBean.getSkillTargetList().get(i);
//                ballMap.setMapValue(bean.getX(), bean.getY(), 0);
//            }

            if (!gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
                if (targetType == 0) {
                    targetCount = score;
                } else if (targetType == 10) {
                    targetCount = enball;
                }
                if (null != playTimer) {
                    playTimer.cancel();
                    playTimer = null;
                    playTimerTask = null;
                }
                if (!gameClearFlg) {
                    Log.d("test_log","playTimeCount : " + this.playTimeCount);
                    bonusMap.put("playTimeCount", playTimeCount);
                }
                gameClearFlg = true;
//                fileUtil.setStageStatus(screen.getMapId(), screen.getStageId(), screen.getClearType());
                fileUtil.setStageStatus(screen.getStageId());
                fileUtil.addMoney(((totalScore - score) / 100) + enball);
                fileUtil.setStageRanking(screen.getStageId(), totalScore);
            }
        } else if ("5".equals(skillBean.getSkillId())) {

            if (!gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
                if (targetType == 0) {
                    targetCount = score;
                } else if (targetType == 10) {
                    targetCount = enball;
                }
                if (null != playTimer) {
                    playTimer.cancel();
                    playTimer = null;
                    playTimerTask = null;
                }
                if (!gameClearFlg) {
                    Log.d("test_log","playTimeCount : " + this.playTimeCount);
                    bonusMap.put("playTimeCount", playTimeCount);
                }
                gameClearFlg = true;
//                fileUtil.setStageStatus(screen.getMapId(), screen.getStageId(), screen.getClearType());
                fileUtil.setStageStatus(screen.getStageId());
                fileUtil.addMoney(((totalScore - score) / 100) + enball);
                fileUtil.setStageRanking(screen.getStageId(), totalScore);
            }
        }
        int count = skillBean.getSkillCount();

        skillBean.setSkillCount(count - 1);
    }

    /**
     * ボールの移動軌跡を設定します。
     *
     * @param x
     * @param y
     * @return
     */
    private void putRootList(int x, int y) {
        // 現ポイント
        PointBean point = new PointBean(x, y);

        boolean addFlg = true;
        // 存在箇所
        int count = 0;

        for (int i = 0; i < rootList.size(); i++) {
            // Pointが既に存在している
            if (rootList.get(i).getX() == x && rootList.get(i).getY() == y) {
                addFlg = false;
                // 存在箇所
                count = i;
            }
        }
        // 最初と最後を除外する
        if (count != rootList.size() || rootList.size() > 0) {
            // Pointが存在の場合、PointまでListを退避する
            if (!addFlg) {
                List<PointBean> pointList1 = new ArrayList<PointBean>();
                for (int i = 0; i < count; i++) {
                    pointList1.add(rootList.get(i));
                }
                rootList = pointList1;
            }

            if (ballMap.getMapValue(x, y) != 0) {
                point.setCanMove(false);
            }
            // 周囲チェック
            if (rootList.size() > 0 && !checkAround(x, y)) {
                point.setCanMove(false);
            }
            // 現ポイントを追加する
            if (point.isCanMove()) {
                rootList.add(point);
            }
        }
    }

    private boolean checkAround(int x, int y) {
        if (x == (getLastPoint().getX() - 1) && y == (getLastPoint().getY())) {
            return true;
        }
        if (x == (getLastPoint().getX() + 1) && y == (getLastPoint().getY())) {
            return true;
        }
        if (x == (getLastPoint().getX()) && y == (getLastPoint().getY() - 1)) {
            return true;
        }
        if (x == (getLastPoint().getX()) && y == (getLastPoint().getY())) {
            return true;
        }
        if (x == (getLastPoint().getX()) && y == (getLastPoint().getY() + 1)) {
            return true;
        }
        return false;
    }

    private PointBean getLastPoint() {
        // 現ポイント
        PointBean lastPoint = null;

        for (int i = 0; i < rootList.size(); i++) {
            if (rootList.get(i).isCanMove()) {
                lastPoint = rootList.get(i);
            }
        }
        return lastPoint;
    }

    private boolean checkClear(boolean gameClearFlg) {

//        Log.d("test_log","bonusMap.get(totalPoint) : " + this.bonusMap.get("totalPoint"));
//        if (null == this.bonusMap.get("totalPoint") || 0 == this.bonusMap.get("totalPoint")) {
//            Log.d("test_log","gameClearFlg : " + this.gameClearFlg);
//        }
        bonusMap.put("stepCount", stepCount);
        bonusMap.put("all", 0);
        totalScore = score;
        int bombCount = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap.getMapValue(i, j) == 21) {
                    bombCount++;
                }
            }
        }
        bonusMap.put("bombCount", bombCount);

        int timeBonus = 0;
        if (playTimeCount < 60) {
            timeBonus = 2000;
        } else if (playTimeCount < 120) {
            timeBonus = 1500;
        } else if (playTimeCount < 180) {
            timeBonus = 1000;
        } else if (playTimeCount < 240) {
            timeBonus = 500;
        }
        boolean allClearFlg = true;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap.getMapValue(i, j) > 0 && ballMap.getMapValue(i, j) < 20) {
                    allClearFlg = false;
                }
            }
        }
        if (allClearFlg) {
            bonusMap.put("all", 5000);
            totalScore += 5000;
        }
        if (runCount == 740) {
            totalScore += (timeBonus);
            totalScore += (stepCount * 200);
            totalScore += (bombCount * 1000);
            bonusMap.put("totalPoint", totalScore);

            return true;
        }
        if (!allClearFlg) {
            return false;
        }
        totalScore += (timeBonus);
        totalScore += (stepCount * 200);
        totalScore += (bombCount * 1000);

        bonusMap.put("totalPoint", totalScore);

        return true;
    }

    /**
     * ボールの消去をチャックします
     *
     * @return
     */
    private boolean checkaddition(int x, int y) {
        // 消去リストを取得する
//        List<PointBean> clearPointList = ballMap.getClearBallList();

        List<BallClearBean> ballClearBeanList = new ArrayList<BallClearBean>();
        for (int i = 0; i < 7; i++) {
            BallClearBean ballClearBean = ballMap.getClearBallList(x, y, i, "x" );
            if (ballClearBean.getBallList().size() > 0 && checkInclude(ballClearBeanList, ballClearBean)) {
                ballClearBeanList.add(ballClearBean);
            }
        }
        for (int i = 0; i < 7; i++) {
            BallClearBean ballClearBean = ballMap.getClearBallList(x, y, i, "y" );
            if (ballClearBean.getBallList().size() > 0 && checkInclude(ballClearBeanList, ballClearBean)) {
                ballClearBeanList.add(ballClearBean);
            }
        }
        if (ballClearBeanList.size() > 0) {
            clearHistory.addClearHistory(ballClearBeanList);
        }

        List<PointBean> clearPointList = ballMap.removeDuplicatePoint(ballMap.getClearBallList(x, y));

        targetCount += ballMap.getTargetPointCount(clearPointList, targetType);

        ballMap.clearMap(clearPointList);

        // 消去した場合、trueをreturn
        if (clearPointList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkInclude(List<BallClearBean> ballClearBeanList, BallClearBean ballClearBean) {

        int count = 0;
        for (int i = 0; i < ballClearBeanList.size(); i++) {
            BallClearBean p = ballClearBeanList.get(i);
            count = 0;
            for (int j = 0; j < ballClearBean.getBallList().size(); j++) {
                for (int k = 0; k < p.getBallList().size(); k++) {
                    if (ballClearBean.getBallList().get(j).getValue() == p.getBallList().get(k).getValue()
                    && ballClearBean.getBallList().get(j).getX() == p.getBallList().get(k).getX()
                    && ballClearBean.getBallList().get(j).getY() == p.getBallList().get(k).getY()
                    ) {
                        count++;
                    }
                }
            }
            if (count == ballClearBean.getBallList().size()) {
                return false;
            }

        }
        return true;
    }

    /**
     * GAMEOVERをチェックします
     *
     * @return
     */
    private boolean checkGameOver() {
        if (stepCount == 0 && targetCount < targetTotal) {
            return true;
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap.getMapValue(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 選択位置を取得します。
     *
     * @param i
     * @param d
     * @param dif
     * @return
     */
    private int getIndex(int i, int d, float dif) {
        if (i < (((d + 120) * diff) + dif)) {
            return 0;
        } else if (i < (((d + 240) * diff) + dif)) {
            return 1;
        } else if (i < (((d + 360) * diff) + dif)) {
            return 2;
        } else if (i < (((d + 480) * diff) + dif)) {
            return 3;
        } else if (i < (((d + 600) * diff) + dif)) {
            return 4;
        } else if (i < (((d + 720) * diff) + dif)) {
            return 5;
        } else if (i < (((d + 840) * diff) + dif)) {
            return 6;
        }
        return -1;
    }

    /**
     * クリックチェック
     *
     * @param x
     * @param y
     * @param rect
     * @return
     */
    public boolean isIn(int x, int y, Rect rect) {
        return x > rect.left && x < rect.right && y > rect.top
                && y < rect.bottom;
    }

    /**
     * プレイ範囲クリックチェック
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isIn(int x, int y) {

        if (x < (( 30 * diff) + diffX) || x > (( 870 * diff) + diffX)
         || y < ((530 * diff) + diffY) || y > ((1370 * diff) + diffY)) {
            return false;
        }

        return true;
    }

    /**
     * 得点を計算します。
     *
     * @param x
     * @param y
     */
    private int calculateScore(int x, int y) {
        // 得点タイプを取得する
        int scoreType = ballMap.getScoreType(x, y);
        if (scoreType > 0) {
            enball += getEnball(scoreType);
            // 得点を加算する
            BigDecimal sco = new BigDecimal(ballMap.getScore(scoreType));
            // コンボ点数追加
            sco = sco.multiply(new BigDecimal(10 + comboCount))
                    .divide(new BigDecimal(10))
                    .setScale(0, BigDecimal.ROUND_UP);
            score += sco.intValue();
            if (targetType == 0) {
                targetCount = score;
            } else if (targetType == 10) {
                targetCount = enball;
            }

            if (comboTextTimer != null) {
                comboTextTimer.cancel();
                comboTextTimer = null;
                comboTextTimerTask = null;
            }

            scoreTimerTask = new CountTimerTask();
            scoreTimer = new Timer(true);
            scoreTimer.schedule(scoreTimerTask, 16, 16);
            comboTextTimerTask = new CountTimerTask();
            comboTextTimer = new Timer();
            comboTextTimer.schedule(comboTextTimerTask, 16, 16);

            clearX = (x * 120) + 30;
            clearY = (y * 120) + 530;
            clearScore = getClearPoint(scoreType);
        }
        return scoreType;
    }

    private String getClearPoint(int scoreType) {
        return String.valueOf(scoreType * 10);
    }

    private int getEnball(int scoreType) {
        return (1 + ((scoreType - 1) * 2));
    }

    /**
     * STEP完了チェック
     *
     * @param x
     * @param y
     */
    private void endCheck(int x, int y) {

        if (ballMap.getMapValue(x, y) == 21) {
            onBomb(x, y);
        } else {
            int scoreType = calculateScore(x, y);
            if (!checkaddition(x, y)) {
                nextFlg = true;

                if (null != comboTimer) {
                    comboTimer.cancel();
                    comboTimer = null;
                    comboTimerTask = null;
                }
            } else {
                nextFlg = false;
                comboTimerTask = new CountTimerTask();
                comboTimer = new Timer(true);

                // 最低限の時間をセットする
                int interval = comboCount * 5;
                if (interval > 80) {
                    interval = 20;
                }
                comboTimer.schedule(comboTimerTask, 100, (100 - interval));
                comboCount++;
            }

            stepTimerTask = new CountTimerTask();
            stepTimer = new Timer(true);
            stepTimer.schedule(stepTimerTask, 16, 16);

//            checkaddition();

            if (scoreType > 2) {
                // 爆弾を置く
                ballMap.setMapValue(x, y, 21);
            }
        }

        if (!gameClearFlg && !gameOverFlg && checkClear(gameClearFlg)) {
            if (targetType == 0) {
                targetCount = score;
            } else if (targetType == 10) {
                targetCount = enball;
            }
            if (!gameClearFlg) {
                Log.d("test_log","playTimeCount : " + playTimeCount);
                bonusMap.put("playTimeCount", playTimeCount);
            }
            gameClearFlg = true;
//            fileUtil.setStageStatus(screen.getMapId(), screen.getStageId(), screen.getClearType());
            fileUtil.setStageStatus(screen.getStageId());
            fileUtil.addMoney(((totalScore - score) / 100) + enball);
            fileUtil.setStageRanking(screen.getStageId(), totalScore);
        } else if (!gameClearFlg && !gameOverFlg && checkGameOver()) {
            gameOverFlg = true;
            if (null != comboTimer) {
                comboTimer.cancel();
                comboTimer = null;
                comboTimerTask = null;
            }
            if (null != playTimer) {
                playTimer.cancel();
                playTimer = null;
                playTimerTask = null;
            }
        }
    }

    /**
     * 爆弾を引爆します
     *
     * @param x 爆弾位置
     * @param y 爆弾位置
     */
    private void onBomb(int x, int y) {

        explosionPointList = ballMap.removeDuplicatePoint(ballMap.getBombList(x, y));

        List<PointBean> clearPointList = ballMap.removeDuplicatePoint(ballMap.getBombPointList(x, y));
        int ballCount = clearPointList.size();

        // 目標の消し数を加算する
        targetCount += ballMap.getTargetPointCount(clearPointList, targetType);

        ballMap.clearMap(clearPointList);

        score += (ballCount * 50);
        if (targetType == 0) {
            targetCount = score;
        } else if (targetType == 10) {
            targetCount = enball;
        }

        bombTimerTask = new CountTimerTask();
        bombTimer = new Timer(true);
        bombTimer.schedule(bombTimerTask, 16, 16);

//        scoreTimerTask = new CountTimerTask();
//        scoreTimer = new Timer(true);
//        scoreTimer.schedule(scoreTimerTask, 16, 16);
//        comboTextTimerTask = new CountTimerTask();
//        comboTextTimer = new Timer();
//        comboTextTimer.schedule(comboTextTimerTask, 16, 16);
//        clearX = (x * 120) + 30;
//        clearY = (y * 120) + 730;
//        clearScore = "+".concat(String.valueOf(ballCount * 50));
    }
}
