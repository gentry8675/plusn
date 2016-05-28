package jp.coxs.plusn.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * スキルBean
 *
 * @author Gen
 *
 */
public class SkillBean {

    /* スキルの数 */
    private String skillId;

    /* スキルの種類	 */
    private String skillType;

    /* スキルの数 */
    private String skillCost;

    /* スキルの数 */
    private int skillCount;

    /* スキルの説明 */
    private String skillInfo;

    /* スキルの発動フラグ */
    private String skillStartFlg;

    private String skillImageName;

    private List<PointBean> skillTargetList = new ArrayList<PointBean>();

    private int skillAnimationTimeCount;

    private int baceCost;

    private int maxLevel;

    /**
     * コンストラクター
     *
     * @param skillType
     * @param skillCost
     */
    public SkillBean (String skillId, String skillCost, String skillType, String skillAnimationTimeCount, String skillImageName, String skillInfo) {
        this.skillId = skillId;
        this.skillType = skillType;
        if ("2".equals(skillType)) {
            this.skillStartFlg = "0";
        } else {
            this.skillStartFlg = "1";
        }
        this.skillCost = skillCost;
        this.skillAnimationTimeCount = Integer.valueOf(skillAnimationTimeCount).intValue();
        this.skillImageName = skillImageName;
        this.skillInfo = skillInfo;
    }

    public String getSkillType() {
        return skillType;
    }
    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }
    public String getSkillCost() {
        return skillCost;
    }
    public void setSkillCost(String skillCost) {
        this.skillCost = skillCost;
    }

    public String getSkillInfo() {
        return skillInfo;
    }

    public void setSkillInfo(String skillInfo) {
        this.skillInfo = skillInfo;
    }

    public String getSkillImageName() {
        return skillImageName;
    }

    public void setSkillImageName(String skillImageName) {
        this.skillImageName = skillImageName;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillStartFlg() {
        return skillStartFlg;
    }

    public void setSkillStartFlg(String skillStartFlg) {
        this.skillStartFlg = skillStartFlg;
    }

    public int getSkillAnimationTimeCount() {
        return skillAnimationTimeCount;
    }

    public void setSkillAnimationTimeCount(int skillAnimationTimeCount) {
        this.skillAnimationTimeCount = skillAnimationTimeCount;
    }

    public int getSkillCount() {
        return skillCount;
    }

    public void setSkillCount(int skillCount) {
        this.skillCount = skillCount;
    }

    public int getBaceCost() {
        return baceCost;
    }

    public void setBaceCost(int baceCost) {
        this.baceCost = baceCost;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public List<PointBean> getSkillTargetList() {
        return skillTargetList;
    }

    public void setSkillTargetList(List<PointBean> skillTargetList) {
        this.skillTargetList = skillTargetList;
    }

    public void clearSkillTargetList() {
        this.skillTargetList = new ArrayList<PointBean>();
    }

    public void addSkillTarget(PointBean point) {
        this.skillTargetList.add(point);
    }
}
