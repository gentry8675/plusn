package jp.coxs.plusn.bean;

/**
 * ポイントBean
 *
 * @author Gen
 *
 */
public class PointBean {

    // プレイ範囲の行　0から
    private int x;
    // プレイ範囲の列　0から
    private int y;
    // 移動可能フラグ
    private boolean canMove = true;
    // 値
    private int value = 0;

    public PointBean(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.canMove = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
