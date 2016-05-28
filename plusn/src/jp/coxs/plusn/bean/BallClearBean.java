package jp.coxs.plusn.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ボール消去Bean
 *
 * @author Gen
 */
public class BallClearBean {

    /** 消去ボールのリスト */
    private List<PointBean> ballList = new ArrayList<PointBean>();

    /**
     * ボールリストを取得します。
     *
     * @return
     */
    public List<PointBean> getBallList() {
        return ballList;
    }

    /**
     * ボールリストを設定します。
     *
     * @return
     */
    public void setBallList(List<PointBean> ballList) {
        this.ballList = ballList;
    }

    /**
     * 消去の合計値を取得します。
     *
     * @return
     */
    public int getSumValue() {

        int sumValue = 0;
        for (int i = 0; i < ballList.size(); i++) {
            if (ballList.get(i).getValue() < 10) {
                sumValue += ballList.get(i).getValue();
            } else if (ballList.get(i).getValue() < 20) {
                sumValue += ballList.get(i).getValue() - 10;
            }
        }
        return sumValue;
    }

}
