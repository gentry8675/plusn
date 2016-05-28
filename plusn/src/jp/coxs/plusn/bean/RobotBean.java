package jp.coxs.plusn.bean;

/**
 * ロボットBean
 *
 * @author Gen
 *
 */
public class RobotBean {

    /* ロボットの番号 */
    private String robotId;

    /* ロボットの画像 */
    private String robotImageLarge;
    private String robotImageIcon;

    /* ロボットのスキル */
    private SkillBean skillBean;

    /* ロボットの説明 */
    private String robotInfo;

    /* ロボットのタイプ：陸、海、空 */
    private String robotType;

    /* ロボットの基本攻撃力 */
    private int baceAtkPoint;

    /* ロボットの攻撃力 */
    private int addAtkPoint;

    /* ロボットの基本マナ */
    private int baceManaPoint;

    /* ロボットのゲーム開始時マナ */
    private int beginManaPoint;

    private int ball1;
    private int ball2;

    private int atkLevel;
    private int maxManaLevel;
    private int beginManaLevel;
    private int skillLevel;

    private int atkpoint;

    public int getAtkLevel() {
        return atkLevel;
    }

    public void setAtkLevel(int atkLevel) {
        this.atkLevel = atkLevel;
    }

    public int getMaxManaLevel() {
        return maxManaLevel;
    }

    public void setMaxManaLevel(int maxManaLevel) {
        this.maxManaLevel = maxManaLevel;
    }

    public int getBeginManaLevel() {
        return beginManaLevel;
    }

    public void setBeginManaLevel(int beginManaLevel) {
        this.beginManaLevel = beginManaLevel;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getRobotImageLarge() {
        return robotImageLarge;
    }

    public void setRobotImageLarge(String robotImageLarge) {
        this.robotImageLarge = robotImageLarge;
    }

    public String getRobotImageIcon() {
        return robotImageIcon;
    }

    public void setRobotImageIcon(String robotImageIcon) {
        this.robotImageIcon = robotImageIcon;
    }

    public SkillBean getSkillBean() {
        return skillBean;
    }

    public void setSkillBean(SkillBean skillBean) {
        this.skillBean = skillBean;
    }

    public String getRobotInfo() {
        return robotInfo;
    }

    public void setRobotInfo(String robotInfo) {
        this.robotInfo = robotInfo;
    }

    public String getRobotType() {
        return robotType;
    }

    public void setRobotType(String robotType) {
        this.robotType = robotType;
    }

    public int getBaceAtkPoint() {
        return baceAtkPoint;
    }

    public void setBaceAtkPoint(int baceAtkPoint) {
        this.baceAtkPoint = baceAtkPoint;
    }

    public int getAddAtkPoint() {
        return addAtkPoint;
    }

    public void setAddAtkPoint(int addAtkPoint) {
        this.addAtkPoint = addAtkPoint;
    }

    public int getBaceManaPoint() {
        return baceManaPoint;
    }

    public void setBaceManaPoint(int baceManaPoint) {
        this.baceManaPoint = baceManaPoint;
    }

    public int getBeginManaPoint() {
        return beginManaPoint;
    }

    public void setBeginManaPoint(int beginManaPoint) {
        this.beginManaPoint = beginManaPoint;
    }

    public int getBall1() {
        return ball1;
    }

    public void setBall1(int ball1) {
        this.ball1 = ball1;
    }

    public int getBall2() {
        return ball2;
    }

    public void setBall2(int ball2) {
        this.ball2 = ball2;
    }

    public int getAtkpoint() {
        return atkpoint;
    }

    public void setAtkpoint(int atkpoint) {
        this.atkpoint = atkpoint;
    }

//    private String skillImageName;
//
//    private PointBean skillTarget;
//
//    private int skillAnimationTimeCount;

}
