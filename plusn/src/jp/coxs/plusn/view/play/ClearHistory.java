package jp.coxs.plusn.view.play;

import java.util.ArrayList;
import java.util.List;

import jp.coxs.plusn.bean.BallClearBean;

public class ClearHistory {

    private List<BallClearBean> history1 = new ArrayList<BallClearBean>();
    private List<BallClearBean> history2 = new ArrayList<BallClearBean>();
    private List<BallClearBean> history3 = new ArrayList<BallClearBean>();
    private List<BallClearBean> history4 = new ArrayList<BallClearBean>();

    public void addClearHistory (List<BallClearBean> ballList) {

        if (history1.size() == 0) {
            history1 = ballList;
        } else if (history2.size() == 0) {
            history2 = history1;
            history1 = ballList;
        } else if (history3.size() == 0) {
            history3 = history2;
            history2 = history1;
            history1 = ballList;
        } else {
            history4 = history3;
            history3 = history2;
            history2 = history1;
            history1 = ballList;
        }
    }

    public List<BallClearBean> getHistory1() {
        return history1;
    }

    public List<BallClearBean> getHistory2() {
        return history2;
    }

    public List<BallClearBean> getHistory3() {
        return history3;
    }

    public List<BallClearBean> getHistory4() {
        return history4;
    }

}
