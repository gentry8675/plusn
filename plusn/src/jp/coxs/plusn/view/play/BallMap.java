package jp.coxs.plusn.view.play;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jp.coxs.plusn.bean.BallClearBean;
import jp.coxs.plusn.bean.PointBean;
import jp.coxs.plusn.bean.StageInfoBean;
import android.annotation.SuppressLint;

/**
 * ボールフィールドを計算する
 *
 * @author Gen
 *
 */
public class BallMap {

    Random random = new Random();
    private int[][] ballMap = null;

    private String ballPool = "";

    /**
     * コンストラクター
     */
    public BallMap(StageInfoBean stageInfo) {
        this.ballMap = stageInfo.getPlayGound();
        this.ballPool = stageInfo.getBallPool();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap[i][j] == -2) {
                    setBall(i, j);
                }
                if (ballMap[i][j] == -3) {
                    setIceBall(i, j);
                }
            }
        }
    }

    //
    // public int[][] getBallMap() {
    // return ballMap;
    // }

    public int getMapValue(int x, int y) {
        return ballMap[x][y];
    }

    public void setMapValue(int x, int y, int value) {
        ballMap[x][y] = value;
    }

    public int[][] getBallMap() {
        return ballMap;
    }

    /**
     * ボールを設置します
     *
     * @param ballType
     */
    //    public void setBall() {
    //        // 空タイルリストを取得する。
    //        List<PointBean> noBallList = getNoBallList();
    //        int count = 0;
    //        PointBean noBallPoint = null;
    //        // 空タイルあり
    //        if (noBallList.size() > 0) {
    //            do {
    //                // 空タイルの中にランダムで選定する
    //                noBallPoint = noBallList.get(random.nextInt(noBallList.size()));
    //                // ボールの種類をランダムで選定
    //                int type = random.nextInt(ballPool.length());
    //                String str = ballPool.substring(type, type + 1);
    //                // ボールの値を選定
    //                if ("N".equals(str)) {
    //                    ballMap[noBallPoint.getX()][noBallPoint.getY()] = 11;
    //                } else if ("0".equals(str)) {
    //                    ballMap[noBallPoint.getX()][noBallPoint.getY()] = 10;
    //                } else {
    //                    ballMap[noBallPoint.getX()][noBallPoint.getY()] = Integer.valueOf(str).intValue();
    //                }
    //                count++;
    //            } while (getClearBallList(noBallPoint.getX(), noBallPoint.getY()).size() > 0 && count < 100);
    //        }
    //    }

    /**
     * ボールを設置します
     *
     * @param ballType
     */
    public void setBall(int ballType) {
        // 空タイルリストを取得する。
        List<PointBean> noBallList = getNoBallList();
        int count = 0;
        PointBean noBallPoint = null;
        // 空タイルあり
        if (noBallList.size() > 0) {
            //            do {
            // 空タイルの中にランダムで選定する
            noBallPoint = noBallList.get(random.nextInt(noBallList.size()));
            // ボールの種類をランダムで選定
            // ボールの値を選定
            ballMap[noBallPoint.getX()][noBallPoint.getY()] = ballType;
            //                count++;
            //            } while (getClearBallList(noBallPoint.getX(), noBallPoint.getY()).size() > 0 && count < 100);
        }
    }

    /**
     * ボールを設置します
     *
     * @param ballType
     */
    public void setBall(int x, int y) {

        int count = 0;
        do {
            int type = random.nextInt(ballPool.length());
            String str = ballPool.substring(type, type + 1);
            if ("N".equals(str)) {
                ballMap[x][y] = 11;
            } else if ("0".equals(str)) {
                ballMap[x][y] = 10;
            } else {
                ballMap[x][y] = Integer.valueOf(str).intValue();
            }
            count++;
        } while (getClearBallList(x, y).size() > 0 && count < 100);
    }

    /**
     * ボールを設置します
     *
     * @param ballType
     */
    public void setIceBall(int x, int y) {

//        int count = 0;
//        do {
            int type = random.nextInt(ballPool.length());
            String str = ballPool.substring(type, type + 1);
            ballMap[x][y] = Integer.valueOf(str).intValue() + 10;
//            count++;
//        } while (getClearBallList(x, y).size() > 0 && count < 100);
    }

    /**
     * 空タイルのリストを取得します。
     *
     * @return
     */
    private List<PointBean> getNoBallList() {
        List<PointBean> noBallList = new ArrayList<PointBean>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap[i][j] == 0) {
                    noBallList.add(new PointBean(i, j));
                }
            }
        }

        return noBallList;
    }

    /**
     * 消去ボールのリストを取得します
     *
     * @return
     */
    public List<PointBean> getClearBallList(int x, int y) {
        // ボール0やボールNの場合空リストを戻す

        List<PointBean> clearPointList = new ArrayList<PointBean>();
        // 立てチェック
        //
        List<Integer> col = new ArrayList<Integer>();
        col.add(ballMap[x][0]);
        col.add(ballMap[x][1]);
        col.add(ballMap[x][2]);
        col.add(ballMap[x][3]);
        col.add(ballMap[x][4]);
        col.add(ballMap[x][5]);
        col.add(ballMap[x][6]);

        // 合計値
        int sumx = 0;
        // 開始位置
        for (int i = 0; i < 7; i++) {
            // 開始位置をiに設定する
            for (int j = i; j < 7; j++) {
                if (col.get(j) < 1 || col.get(j) > 20) {
                    // タイルなしの場合、合計値をリセット
                    j = 7;
                } else {
                    sumx += col.get(j);
                }
                if (j < 7 && sumx % 10 == 0) {
                    if (i <= y && j >= y) {
                        for (int k = i; k <= j; k++) {
                            PointBean rootPoint = new PointBean(x, k);
                            rootPoint.setValue(ballMap[x][k]);
                            // ポイントをクリアリストに追加する
                            clearPointList.add(rootPoint);
                        }
                    }
                    //                    j = 7;
                    // 合計値をリセット
                    sumx = 0;
                }
            }
            // 合計値をリセット
            sumx = 0;
        }
        //                }

        //        for (int y = 0; y < 7; y++) {
        // 立てチェック
        //
        List<Integer> row = new ArrayList<Integer>();
        row.add(ballMap[0][y]);
        row.add(ballMap[1][y]);
        row.add(ballMap[2][y]);
        row.add(ballMap[3][y]);
        row.add(ballMap[4][y]);
        row.add(ballMap[5][y]);
        row.add(ballMap[6][y]);

        // 合計値
        int sumy = 0;
        // 開始位置
        for (int i = 0; i < 7; i++) {
            // 開始位置をiに設定する
            for (int j = i; j < 7; j++) {

                if (row.get(j) < 1 || row.get(j) > 20) {
                    // タイルなしの場合、合計値をリセット
                    j = 7;
                } else {
                    sumy += row.get(j);
                }
                if (j < 7 && sumy % 10 == 0) {
                    if (i <= x && j >= x) {
                        for (int k = i; k <= j; k++) {
                            PointBean rootPoint = new PointBean(k, y);
                            rootPoint.setValue(ballMap[k][y]);
                            // ポイントをクリアリストに追加する
                            clearPointList.add(rootPoint);
                        }
                    }
                    //                    j = 7;
                    // 合計値をリセット
                    sumy = 0;
                }
            }
            // 合計値をリセット
            sumy = 0;
        }

        return clearPointList;
    }

    /**
     * 得点種類を取得します
     *
     * @param x
     * @param y
     * @return
     */
    public int getScoreType(int x, int y) {

        int scoreType = 0;

//        Log.d("test_log", "点:"+ scoreType);
        // 立てチェック
        //
        List<Integer> col = new ArrayList<Integer>();
        col.add(ballMap[x][0]);
        col.add(ballMap[x][1]);
        col.add(ballMap[x][2]);
        col.add(ballMap[x][3]);
        col.add(ballMap[x][4]);
        col.add(ballMap[x][5]);
        col.add(ballMap[x][6]);

        // 合計値
        // 合計値
        int type = 0;
        int sumx = 0;
        // 開始位置
        for (int i = 0; i < 7; i++) {
            // 開始位置をiに設定する
            for (int j = i; j < 7; j++) {
//                Log.d("test_log", "i:"+ i + " j:"+ j + " v:"+ col.get(j));
                if (col.get(j) < 1 || col.get(j) > 20) {
                    // タイルなしの場合、合計値をリセット
                    j = 7;
                } else {
                    sumx += col.get(j) % 10;
                }
//                Log.d("test_log", "sumx:"+ sumx);
                if (j < 7 && sumx % 10 == 0) {
                    if (i <= y && j >= y) {
                        type = (sumx / 10);
                    }
                    //                    j = 7;
                    // 合計値をリセット
                    sumx = 0;
                    scoreType += type;
                }
                type = 0;
            }
            // 合計値をリセット
            sumx = 0;
        }

//        Log.d("test_log", "点:"+ scoreType);

        // 立てチェック
        //
        List<Integer> row = new ArrayList<Integer>();
        row.add(ballMap[0][y]);
        row.add(ballMap[1][y]);
        row.add(ballMap[2][y]);
        row.add(ballMap[3][y]);
        row.add(ballMap[4][y]);
        row.add(ballMap[5][y]);
        row.add(ballMap[6][y]);

        // 合計値
        type = 0;
        int sumy = 0;
        for (int i = 0; i < 7; i++) {
            // 開始位置をiに設定する
            for (int j = i; j < 7; j++) {
//                Log.d("test_log", "i:"+ i + " j:"+ j + " v:"+ row.get(j));
                if (row.get(j) < 1 || row.get(j) > 20) {
                    // タイルなしの場合、合計値をリセット
                    j = 7;
                } else {
                    sumy += row.get(j) % 10;
                }
//                Log.d("test_log", "sumy:"+ sumy);
                if (j < 7 && sumy % 10 == 0) {
                    if (i <= x && j >= x) {
                        type += (sumy / 10);
                    }
                    //                    j = 7;
                    // 合計値をリセット
                    sumy = 0;
                    scoreType += type;
                }
                type = 0;
            }
            // 合計値をリセット
            sumy = 0;
        }

//        Log.d("test_log", "点:"+ scoreType);
        return scoreType;
    }

    /**
     * 得点を取得します。
     *
     * @param scoreType
     * @return
     */
    public int getScore(int scoreType) {
        return (100 + ((scoreType - 1) * 150));
    }

    public List<PointBean> getBombPointList(int x, int y) {
        // リスト
        List<PointBean> pointList = new ArrayList<PointBean>();

        addBombPoint(x, y, pointList);
        return pointList;
    }

    /**
     * 爆発
     *
     * @param x
     * @param y
     */
    private void addBombPoint(int x, int y, List<PointBean> pointList) {

        ballMap[x][y] = 0;
        if (x > 0) {
            if (y > 0) {
                if (isBomb(ballMap[x - 1][y - 1])) {
                    addBombPoint(x - 1, y - 1, pointList);
                } else if (canClear(ballMap[x - 1][y - 1])) {
                    PointBean point = new PointBean(x - 1, y - 1);
                    point.setValue(ballMap[x - 1][y - 1]);
                    pointList.add(point);
                }
            }
            if (y < 6) {
                if (isBomb(ballMap[x - 1][y + 1])) {
                    addBombPoint(x - 1, y + 1, pointList);
                } else if (canClear(ballMap[x - 1][y + 1])) {
                    PointBean point = new PointBean(x - 1, y + 1);
                    point.setValue(ballMap[x - 1][y + 1]);
                    pointList.add(point);
                }
            }
            if (isBomb(ballMap[x - 1][y])) {
                addBombPoint(x - 1, y, pointList);
            } else if (canClear(ballMap[x - 1][y])) {
                PointBean point = new PointBean(x - 1, y);
                point.setValue(ballMap[x - 1][y]);
                pointList.add(point);
            }
        }
        if (x < 6) {
            if (y > 0) {
                if (isBomb(ballMap[x + 1][y - 1])) {
                    addBombPoint(x + 1, y - 1, pointList);
                } else if (canClear(ballMap[x + 1][y - 1])) {
                    PointBean point = new PointBean(x + 1, y - 1);
                    point.setValue(ballMap[x + 1][y - 1]);
                    pointList.add(point);
                }
            }
            if (y < 6) {
                if (isBomb(ballMap[x + 1][y + 1])) {
                    addBombPoint(x + 1, y + 1, pointList);
                } else if (canClear(ballMap[x + 1][y + 1])) {
                    PointBean point = new PointBean(x + 1, y + 1);
                    point.setValue(ballMap[x + 1][y + 1]);
                    pointList.add(point);
                }
            }
            if (isBomb(ballMap[x + 1][y])) {
                addBombPoint(x + 1, y, pointList);
            } else if (canClear(ballMap[x + 1][y])) {
                PointBean point = new PointBean(x + 1, y);
                point.setValue(ballMap[x + 1][y]);
                pointList.add(point);
            }
        }
        if (y > 0) {
            if (isBomb(ballMap[x][y - 1])) {
                addBombPoint(x, y + 1, pointList);
            } else if (canClear(ballMap[x][y - 1])) {
                PointBean point = new PointBean(x, y - 1);
                point.setValue(ballMap[x][y - 1]);
                pointList.add(point);
            }
        }
        if (y < 6) {
            if (isBomb(ballMap[x][y + 1])) {
                addBombPoint(x, y + 1, pointList);
            } else if (canClear(ballMap[x][y + 1])) {
                PointBean point = new PointBean(x, y + 1);
                point.setValue(ballMap[x][y + 1]);
                pointList.add(point);
            }
        }
    }

    /**
     * 爆弾チェック
     *
     * @param value
     * @return
     */
    private boolean isBomb(int value) {
        if (value == 21) {
            // ボール１
            return true;
        }
        return false;
    }

    /**
     * 消去したボールのマップ値を設定します。
     *
     * @param pointList
     */
    public void clearMap(List<PointBean> pointList) {
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).getValue() > 10 && pointList.get(i).getValue() < 20) {
                setMapValue(pointList.get(i).getX(), pointList.get(i).getY(), (pointList.get(i).getValue() - 10));
            } else {
                setMapValue(pointList.get(i).getX(), pointList.get(i).getY(), 0);
            }
            clearNebula(pointList.get(i).getX(), pointList.get(i).getY());
        }
    }

    private void clearNebula(int i, int j) {
        int x = -1;
        int y = -1;
        if (i > 0) {
            x = i - 1;
            y = j;
            if (getMapValue(x, y) == 33) {
                setMapValue(x, y, 0);
            }
        }

        if (i < 6) {
            x = i + 1;
            y = j;
            if (getMapValue(x, y) == 33) {
                setMapValue(x, y, 0);
            }
        }

        if (j > 0) {
            x = i;
            y = j - 1;
            if (getMapValue(x, y) == 33) {
                setMapValue(x, y, 0);
            }
        }

        if (j < 6) {
            x = i;
            y = j + 1;
            if (getMapValue(x, y) == 33) {
                setMapValue(x, y, 0);
            }
        }

    }

    /**
     * 重複ポイントを除去します
     *
     * @param pointList
     * @return
     */
    @SuppressLint("UseSparseArrays")
    public List<PointBean> removeDuplicatePoint(List<PointBean> pointList) {
        // リスト
        List<PointBean> newPointList = new ArrayList<PointBean>();

        Map<Integer, PointBean> pointMap = new HashMap<Integer, PointBean>();

        // ボールを消去する
        for (int i = 0; i < pointList.size(); i++) {
            int key = pointList.get(i).getX() * 10 + pointList.get(i).getY();
            pointMap.put(key, pointList.get(i));
        }
        // リスト再構築
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                int key = i * 10 + j;
                if (null != pointMap.get(key)) {
                    newPointList.add(pointMap.get(key));
                }
            }
        }
        return newPointList;
    }

    /**
     * 消されるポイントを確認します
     *
     * @param value
     * @return
     */
    public boolean canClear(int value) {
        if (value > 0 && value < 10) {
            // ボール
            return true;
        } else if (value > 10 && value < 20) {
            // 凍り付いたボール
            return true;
        } else if (value == 31) {
            // 障害物
            return true;
        } else if (value == 33) {
            // 渦
            return true;
        }
        return false;
    }

    /**
     * 目標ポイントの数を取得します。
     *
     * @param pointList
     * @param targetType
     * @return
     */
    public int getTargetPointCount(List<PointBean> pointList, int targetType) {
        // カウント
        int count = 0;

        for (int i = 0; i < pointList.size(); i++) {
            // 目標を一致する場合、カウントアップ
            if (pointList.get(i).getValue() == targetType) {
                count++;
            }
        }
        return count;
    }

    public List<PointBean> getBombList(int x, int y) {
        // リスト
        List<PointBean> pointList = new ArrayList<PointBean>();

        HashMap<Integer, PointBean> pointMap = new HashMap<Integer, PointBean>();

        getBombList(x, y, pointMap);

        // リスト再構築
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                int key = i * 10 + j;
                if (null != pointMap.get(key)) {
                    pointList.add(pointMap.get(key));
                }
            }
        }
        return pointList;
    }

    public void getBombList(int x, int y, HashMap<Integer, PointBean> pointMap) {

        int key = x * 10 + y;
        if (null == pointMap.get(key)) {
            pointMap.put(key, new PointBean(x, y));
            if (x > 0) {
                if (y > 0) {
                    if (isBomb(ballMap[x - 1][y - 1])) {
                        getBombList(x - 1, y - 1, pointMap);
                    }
                }
                if (y < 6) {
                    if (isBomb(ballMap[x - 1][y + 1])) {
                        getBombList(x - 1, y + 1, pointMap);
                    }
                }
                if (isBomb(ballMap[x - 1][y])) {
                    getBombList(x - 1, y, pointMap);
                }
            }
            if (x < 6) {
                if (y > 0) {
                    if (isBomb(ballMap[x + 1][y - 1])) {
                        getBombList(x + 1, y - 1, pointMap);
                    }
                }
                if (y < 6) {
                    if (isBomb(ballMap[x + 1][y + 1])) {
                        getBombList(x + 1, y + 1, pointMap);
                    }
                }
                if (isBomb(ballMap[x + 1][y])) {
                    getBombList(x + 1, y, pointMap);
                }
            }
            if (y > 0) {
                if (isBomb(ballMap[x][y - 1])) {
                    getBombList(x, y + 1, pointMap);
                }
            }
            if (y < 6) {
                if (isBomb(ballMap[x][y + 1])) {
                    getBombList(x, y + 1, pointMap);
                }
            }
        }
    }

    public PointBean getRandomBall() {

        List<PointBean> ballList = new ArrayList<PointBean>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap[i][j] > 0) {
                    PointBean point = new PointBean(i, j);
                    point.setValue(ballMap[i][j]);
                    ballList.add(point);
                }
            }
        }

        return ballList.get(random.nextInt(ballList.size()));
    }

    public List<PointBean> getBallList() {

        List<PointBean> ballList = new ArrayList<PointBean>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap[i][j] > 0 && ballMap[i][j] < 20) {
                    PointBean point = new PointBean(i, j);
                    point.setValue(ballMap[i][j]);
                    ballList.add(point);
                }
            }
        }

        return ballList;
    }

    public int getRanBall(StageInfoBean stageInfo) {

        int type = random.nextInt(stageInfo.getBallPool().length());
        String str = ballPool.substring(type, type + 1);

        return Integer.valueOf(str).intValue();
    }

    public List<PointBean> getBallList(int value) {

        List<PointBean> ballList = new ArrayList<PointBean>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ballMap[i][j] == value) {
                    PointBean point = new PointBean(i, j);
                    point.setValue(ballMap[i][j]);
                    ballList.add(point);
                }
            }
        }

        return ballList;
    }

    public BallClearBean getClearBallList(int x, int y, int i, String string) {
        BallClearBean ballClearBean = new BallClearBean();

        List<PointBean> clearPointList = new ArrayList<PointBean>();
        // 立てチェック
        //
        List<Integer> valueList = new ArrayList<Integer>();
        if ("x".equals(string)) {
            valueList.add(ballMap[x][0]);
            valueList.add(ballMap[x][1]);
            valueList.add(ballMap[x][2]);
            valueList.add(ballMap[x][3]);
            valueList.add(ballMap[x][4]);
            valueList.add(ballMap[x][5]);
            valueList.add(ballMap[x][6]);
        } else {
            valueList.add(ballMap[0][y]);
            valueList.add(ballMap[1][y]);
            valueList.add(ballMap[2][y]);
            valueList.add(ballMap[3][y]);
            valueList.add(ballMap[4][y]);
            valueList.add(ballMap[5][y]);
            valueList.add(ballMap[6][y]);
        }

        // 合計値
        int sumx = 0;
        // 開始位置
        // 開始位置をiに設定する
        for (int j = i; j < 7; j++) {
            if (valueList.get(j) < 1 || valueList.get(j) > 20) {
                // タイルなしの場合、合計値をリセット
                j = 7;
            } else {
                sumx += valueList.get(j);
            }
            if (j < 7 && sumx % 10 == 0) {
                if ("x".equals(string)) {
                    if (i <= y && j >= y) {
                        for (int k = i; k <= j; k++) {
                            PointBean rootPoint = new PointBean(x, k);
                            rootPoint.setValue(ballMap[x][k]);
                            // ポイントをクリアリストに追加する
                            clearPointList.add(rootPoint);
                        }
                    }
                } else {
                    if (i <= x && j >= x) {
                        for (int k = i; k <= j; k++) {
                            PointBean rootPoint = new PointBean(k, y);
                            rootPoint.setValue(ballMap[k][y]);
                            // ポイントをクリアリストに追加する
                            clearPointList.add(rootPoint);
                        }
                    }
                }
                // 合計値をリセット
                sumx = 0;
            }
        }

        ballClearBean.setBallList(clearPointList);

        return ballClearBean;
    }
}
