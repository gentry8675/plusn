package jp.coxs.plusn.bean;

/**
 * ステージ情報Bean
 *
 * @author Gen
 *
 */
public class TargetInfoBean {

    private int targetType;
    private int stepCount;
    private int targetTotal;
    private String info;

    public TargetInfoBean (int targetType,int stepCount, int targetTotal, String info) {
        this.targetTotal = targetTotal;
        this.stepCount = stepCount;
        this.targetType = targetType;
        this.info = info;
    }

    public TargetInfoBean() {
        this.targetType = 0;
    }

    public int getTargetType() {
        return targetType;
    }
    public int getStepCount() {
        return stepCount;
    }
    public int getTargetTotal() {
        return targetTotal;
    }

    public String getInfo() {
        return info;
    }

}
