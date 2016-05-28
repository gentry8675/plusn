package jp.coxs.plusn.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ステージ情報Bean
 *
 * @author Gen
 *
 */
public class StageInfoBean {

    private int stageId;

    private int stageStatus;

    private int left;
    private int top;
    private int right;
    private int bottom;

    private List<TargetInfoBean> targetInfoList = new ArrayList<TargetInfoBean>();

    private TargetInfoBean targetInfo = new TargetInfoBean();

    private List<SkillBean> stageSkillList = new ArrayList<SkillBean>();

    private int[][] playGound = {
            // 1, 2, 3, 4, 5, 6, 7
            { 0, 0, 0, 0, 0, 0, 0 }, // 1
            { 0, 0, 0, 0, 0, 0, 0 }, // 2
            { 0, 0, 0, 0, 0, 0, 0 }, // 3
            { 0, 0, 0, 0, 0, 0, 0 }, // 4
            { 0, 0, 0, 0, 0, 0, 0 }, // 5
            { 0, 0, 0, 0, 0, 0, 0 }, // 6
            { 0, 0, 0, 0, 0, 0, 0 }, // 7
    };

    private String ballPool = "";

    private List<PointBean> stepMoveFromPointList = new ArrayList<PointBean>();
    private List<PointBean> stepMoveToPointList = new ArrayList<PointBean>();
    private List<PointBean> stepMoveStartPointList = new ArrayList<PointBean>();

    private List<PointBean> stepStackFromPointList = new ArrayList<PointBean>();
    private List<PointBean> stepStackToPointList = new ArrayList<PointBean>();
    private List<PointBean> stepStackPointList = new ArrayList<PointBean>();

    private String backGroundImageName;

    private String bossImageName;
    private int bossMaxHp;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setStageStatus(int stageStatus) {
        this.stageStatus = stageStatus;
    }

    public StageInfoBean() {
    }

    public int getStageStatus() {
        return stageStatus;
    }

    public List<TargetInfoBean> getTargetInfoList() {
        return targetInfoList;
    }

    public void setTargetInfoList(List<TargetInfoBean> targetInfoList) {
        this.targetInfoList = targetInfoList;
    }

    public int[][] getPlayGound() {
        return playGound;
    }

    public void setPlayGound(int x, int y, int value) {
        this.playGound[x][y] = value;
    }

    public void setPlayGound(String x, String y, String value) {
        this.playGound[Integer.valueOf(x).intValue()][Integer.valueOf(y).intValue()] = Integer.valueOf(value).intValue();
    }

    public String getBallPool() {
        return ballPool;
    }

    public void addToBallPool(String ball) {
        this.ballPool = this.ballPool.concat(ball);
    }

    public List<PointBean> getStepMoveFromPointList() {
        return stepMoveFromPointList;
    }

    public void setStepMoveFromPointList(List<PointBean> stepMoveFromPointList) {
        this.stepMoveFromPointList = stepMoveFromPointList;
    }

    public List<PointBean> getStepMoveToPointList() {
        return stepMoveToPointList;
    }

    public void setStepMoveToPointList(List<PointBean> stepMoveToPointList) {
        this.stepMoveToPointList = stepMoveToPointList;
    }

    public List<PointBean> getStepMoveStartPointList() {
        return stepMoveStartPointList;
    }

    public void setStepMoveStartPointList(List<PointBean> stepMoveStartPointList) {
        this.stepMoveStartPointList = stepMoveStartPointList;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public List<SkillBean> getStageSkillList() {
        return stageSkillList;
    }

    public void setStageSkillList(List<SkillBean> stageSkillList) {
        this.stageSkillList = stageSkillList;
    }

    public String getBackGroundImageName() {
        return backGroundImageName;
    }

    public void setBackGroundImageName(String backGroundImageName) {
        this.backGroundImageName = backGroundImageName;
    }

    public String getBossImageName() {
        return bossImageName;
    }

    public void setBossImageName(String bossImageName) {
        this.bossImageName = bossImageName;
    }

    public int getBossMaxHp() {
        return bossMaxHp;
    }

    public void setBossMaxHp(int bossMaxHp) {
        this.bossMaxHp = bossMaxHp;
    }

    public List<PointBean> getStepStackFromPointList() {
        return stepStackFromPointList;
    }

    public void setStepStackFromPointList(List<PointBean> stepStackFromPointList) {
        this.stepStackFromPointList = stepStackFromPointList;
    }

    public List<PointBean> getStepStackToPointList() {
        return stepStackToPointList;
    }

    public void setStepStackToPointList(List<PointBean> stepStackToPointList) {
        this.stepStackToPointList = stepStackToPointList;
    }

    public List<PointBean> getStepStackPointList() {
        return stepStackPointList;
    }

    public void setStepStackPointList(List<PointBean> stepStackPointList) {
        this.stepStackPointList = stepStackPointList;
    }

    public TargetInfoBean getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfoBean targetInfo) {
        this.targetInfo = targetInfo;
    }

}
