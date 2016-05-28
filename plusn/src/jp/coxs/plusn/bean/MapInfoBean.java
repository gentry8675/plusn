package jp.coxs.plusn.bean;

public class MapInfoBean {

    private int left;
    private int top;
    private int right;
    private int bottom;

    private String mapImageName;

    private boolean showFlg = false;

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

    public String getMapImageName() {
        return mapImageName;
    }

    public void setMapImageName(String mapImageName) {
        this.mapImageName = mapImageName;
    }

    public boolean getShowFlg() {
        return showFlg;
    }

    public void setShowFlg(boolean showFlg) {
        this.showFlg = showFlg;
    }
}
