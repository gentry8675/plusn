package jp.coxs.plusn.view.draw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jp.coxs.plusn.R;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class DrawView {

    protected String language = Locale.getDefault().getLanguage();

//    protected ResourceBundle bundle =  ResourceBundle.getBundle("words");
    // 座標係数
    protected float diff = 0;
    protected float diffX = 0;
    protected float diffY = 0;

    protected Map<String, Rect> rectMap = new HashMap<String, Rect>();
    protected Resources resources;

    protected Canvas can = null;

    private Drawable left_side_1;
    private Drawable left_side_2;
    private Drawable right_side_1;
    private Drawable right_side_2;
    private Drawable loading;

    private Drawable num_0;
    private Drawable num_1;
    private Drawable num_2;
    private Drawable num_3;
    private Drawable num_4;
    private Drawable num_5;
    private Drawable num_6;
    private Drawable num_7;
    private Drawable num_8;
    private Drawable num_9;

    private Drawable num_p;
    private Drawable num_t;
    private Drawable num_x;
    private Drawable num_s;
    private Drawable num_c;

    public DrawView(Resources resources) {
        this.resources = resources;

        // 数字
        num_0 = resources.getDrawable(R.drawable.num_0);
        num_1 = resources.getDrawable(R.drawable.num_1);
        num_2 = resources.getDrawable(R.drawable.num_2);
        num_3 = resources.getDrawable(R.drawable.num_3);
        num_4 = resources.getDrawable(R.drawable.num_4);
        num_5 = resources.getDrawable(R.drawable.num_5);
        num_6 = resources.getDrawable(R.drawable.num_6);
        num_7 = resources.getDrawable(R.drawable.num_7);
        num_8 = resources.getDrawable(R.drawable.num_8);
        num_9 = resources.getDrawable(R.drawable.num_9);

        num_p = resources.getDrawable(R.drawable.num_p);
        num_t = resources.getDrawable(R.drawable.num_t);
        num_x = resources.getDrawable(R.drawable.num_x);
        num_s = resources.getDrawable(R.drawable.num_s);
        num_c = resources.getDrawable(R.drawable.num_c);

        left_side_1 = resources.getDrawable(R.drawable.left_side_1);
        left_side_2 = resources.getDrawable(R.drawable.left_side_2);
        right_side_1 = resources.getDrawable(R.drawable.right_side_1);
        right_side_2 = resources.getDrawable(R.drawable.right_side_2);

        loading = resources.getDrawable(R.drawable.loading);

    }

    /**
     * 座標係数を設定します
     *
     * @param diff   全体係数
     * @param diffX  表示範囲のX偏移量
     * @param diffY  表示範囲のY偏移量
     */
    public void setDiff(float diff, float diffX, float diffY) {
        // 座標係数を設定する
        this.diff = diff;
        this.diffX = diffX;
        this.diffY = diffY;
    }

    /**
     * 座標を計算します
     *
     * @param coordValue 座標
     * @return 結果座標
     */
    protected int calculateCoordX(int coordValue) {
        return (int) ((coordValue * diff) + diffX);
    }

    /**
     * 座標を計算します
     *
     * @param coordValue 座標
     * @return 結果座標
     */
    protected int calculateCoordY(int coordValue) {
        return (int) (((coordValue) * diff) + diffY);
    }

    /**
     * 描画キャンバスを設定します
     *
     * @param can キャンバス
     */
    public void Init(Canvas can) {
        this.can = can;
    }

    public void drawDoor(int changeScreenCount) {
        if (changeScreenCount > 450) {
            changeScreenCount = 450;
        }
        // 枠を描画する
        left_side_1.setBounds(calculateCoordX(-900 + changeScreenCount), calculateCoordY(0), calculateCoordX(0 + changeScreenCount), calculateCoordY(400));
        left_side_1.draw(can);
        left_side_2.setBounds(calculateCoordX(-900 + changeScreenCount), calculateCoordY(400), calculateCoordX(0 + changeScreenCount), calculateCoordY(800));
        left_side_2.draw(can);
        left_side_1.setBounds(calculateCoordX(-900 + changeScreenCount), calculateCoordY(800), calculateCoordX(0 + changeScreenCount), calculateCoordY(1200));
        left_side_1.draw(can);
        left_side_1.setBounds(calculateCoordX(-900 + changeScreenCount), calculateCoordY(1200), calculateCoordX(0 + changeScreenCount), calculateCoordY(1600));
        left_side_1.draw(can);
        right_side_1.setBounds(calculateCoordX(900 - changeScreenCount), calculateCoordY(0), calculateCoordX(1800 - changeScreenCount), calculateCoordY(400));
        right_side_1.draw(can);
        right_side_2.setBounds(calculateCoordX(900 - changeScreenCount), calculateCoordY(400), calculateCoordX(1800 - changeScreenCount), calculateCoordY(800));
        right_side_2.draw(can);
        right_side_1.setBounds(calculateCoordX(900 - changeScreenCount), calculateCoordY(800), calculateCoordX(1800 - changeScreenCount), calculateCoordY(1200));
        right_side_1.draw(can);
        right_side_1.setBounds(calculateCoordX(900 - changeScreenCount), calculateCoordY(1200), calculateCoordX(1800 - changeScreenCount), calculateCoordY(1600));
        right_side_1.draw(can);
    }

    /**
     * Loading
     *
     * @param changeScreenCount
     */
    public void drawLoading(int changeScreenCount, boolean changeScreenFlg) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        if (changeScreenFlg == false) {
            changeScreenCount = 2040 - changeScreenCount;
        }

        int count = (changeScreenCount / 120)  % 6;

        float radius1 = 5  * diff;
        float radius2 = 5  * diff;
        float radius3 = 5  * diff;
        float radius4 = 5  * diff;
        float radius5 = 5  * diff;
        float radius6 = 5  * diff;

        if (count == 0) {
            radius1 = 20 * diff;
            radius2 = 15 * diff;
            radius3 = 10 * diff;
        } else if (count == 1) {
            radius6 = 20 * diff;
            radius1 = 15 * diff;
            radius2 = 10 * diff;
        } else if (count == 2) {
            radius5 = 20 * diff;
            radius6 = 15 * diff;
            radius1 = 10 * diff;
        } else if (count == 3) {
            radius4 = 20 * diff;
            radius5 = 15 * diff;
            radius6 = 10 * diff;
        } else if (count == 4) {
            radius3 = 20 * diff;
            radius4 = 15 * diff;
            radius5 = 10 * diff;
        } else if (count == 5) {
            radius2 = 20 * diff;
            radius3 = 15 * diff;
            radius4 = 10 * diff;
        }

        can.drawCircle(calculateCoordX(420), calculateCoordY(600), radius1, paint);
        can.drawCircle(calculateCoordX(400), calculateCoordY(640), radius2, paint);
        can.drawCircle(calculateCoordX(420), calculateCoordY(680), radius3, paint);
        can.drawCircle(calculateCoordX(480), calculateCoordY(680), radius4, paint);
        can.drawCircle(calculateCoordX(500), calculateCoordY(640), radius5, paint);
        can.drawCircle(calculateCoordX(480), calculateCoordY(600), radius6, paint);

        loading.setBounds(calculateCoordX(375), calculateCoordY(700), calculateCoordX(520), calculateCoordY(750));
        loading.draw(can);
    }


    public void drawNumberString (String string, int x, int y, int size) {
        for (int i = 0; i < string.length(); i++) {
            drawChar(string.substring(i, i + 1), x + (i * size), y, size);
        }
    }

    /**
     * 数字を書く
     *
     * @param str
     * @param x
     * @param y
     * @param size
     */

    private void drawChar(String str, int x, int y, int size) {

        Rect rect = new Rect(calculateCoordX(x), calculateCoordY(y), calculateCoordX(x + size), calculateCoordY(y + (size * 3 / 2)));

        if ("0".equals(str)) {
            num_0.setBounds(rect);
            num_0.draw(can);
        } else if ("1".equals(str)) {
            num_1.setBounds(rect);
            num_1.draw(can);
        } else if ("2".equals(str)) {
            num_2.setBounds(rect);
            num_2.draw(can);
        } else if ("3".equals(str)) {
            num_3.setBounds(rect);
            num_3.draw(can);
        } else if ("4".equals(str)) {
            num_4.setBounds(rect);
            num_4.draw(can);
        } else if ("5".equals(str)) {
            num_5.setBounds(rect);
            num_5.draw(can);
        } else if ("6".equals(str)) {
            num_6.setBounds(rect);
            num_6.draw(can);
        } else if ("7".equals(str)) {
            num_7.setBounds(rect);
            num_7.draw(can);
        } else if ("8".equals(str)) {
            num_8.setBounds(rect);
            num_8.draw(can);
        } else if ("9".equals(str)) {
            num_9.setBounds(rect);
            num_9.draw(can);
        } else if ("+".equals(str)) {
            num_p.setBounds(rect);
            num_p.draw(can);
        } else if ("/".equals(str)) {
            num_s.setBounds(rect);
            num_s.draw(can);
        } else if (".".equals(str)) {
            num_t.setBounds(rect);
            num_t.draw(can);
        } else if (":".equals(str)) {
            num_c.setBounds(rect);
            num_c.draw(can);
        } else if ("x".equals(str)) {
            num_x.setBounds(rect);
            num_x.draw(can);
        }
    }

    public void drawText(String text, int x, int y, float size, int color) {

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(size);
        can.drawText(text, calculateCoordX(x), calculateCoordY(y), paint);
    }

    public void callback() {
        num_0.setCallback(null);
        num_1.setCallback(null);
        num_2.setCallback(null);
        num_3.setCallback(null);
        num_4.setCallback(null);
        num_5.setCallback(null);
        num_6.setCallback(null);
        num_7.setCallback(null);
        num_8.setCallback(null);
        num_9.setCallback(null);

        num_p.setCallback(null);
        num_t.setCallback(null);
        num_x.setCallback(null);
        num_s.setCallback(null);
        num_c.setCallback(null);
        left_side_1.setCallback(null);
        left_side_2.setCallback(null);
        right_side_1.setCallback(null);
        right_side_2.setCallback(null);

        loading.setCallback(null);

    }


    protected List<String> makeString (String inputString, int len) {

        List<String> rtnList = new ArrayList<String>();

        // 文字数を取得する
        int size = inputString.length();

        String line = "";
        String harf = "";
        for (int i = 0; i < size; i++) {
            // 文字を取得する
            String str = inputString.substring(i, i+1);
            // 表示サイズ：半角→1 以外→2
            int strLen = str.getBytes().length;

            if (1 == strLen) {
                harf = harf.concat(str);
            } else {
                if (len >= getStringLen(line) + getStringLen(harf)) {
                    line = line.concat(harf);
                    harf = "";
                } else {
                    rtnList.add(line);
                    line = harf;
                    harf = "";
                }
                line = line.concat(str);
            }

            if (getStringLen(line) >= len) {
                rtnList.add(line);
                line = "";
            }
            if (getStringLen(harf) >= len) {
                rtnList.add(line);
                rtnList.add(harf);
                line = "";
                harf = harf.substring(len);

    }

            if (i == size - 1) {
                if (len >= getStringLen(line) + getStringLen(harf)) {
                    line = line.concat(harf);
                    rtnList.add(line);
                } else {
                    rtnList.add(line);
                    rtnList.add(harf);
                }

    }
        }
        return rtnList;
    }

    private int getStringLen (String inputString) {
        int rtnLen = 0;
        int size = inputString.length();

        for (int i = 0; i < size; i++) {
            // 文字を取得する
            String str = inputString.substring(i, i+1);
            int strLen = str.getBytes().length;
            if (1 == strLen) {
                rtnLen += 1;
            } else {
                rtnLen += 2;
            }
        }
        return rtnLen;
    }

    public void DrawMemory() {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);

        DecimalFormat format_mem =   new DecimalFormat("#,###KB");
        DecimalFormat format_ratio = new DecimalFormat("##.#");
        long free =  Runtime.getRuntime().freeMemory() / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024;
        long max =   Runtime.getRuntime().maxMemory() / 1024;
        long used =  total - free;
        double ratio = (used * 100 / (double)total);

        can.drawText("Total   = " + format_mem.format(total), calculateCoordX(0), calculateCoordY(230), paint);
        can.drawText("Free    = " + format_mem.format(total), calculateCoordX(0), calculateCoordY(260), paint);
        can.drawText("use     = " + format_mem.format(used) + " (" + format_ratio.format(ratio) + "%)", calculateCoordX(0), calculateCoordY(290), paint);
        can.drawText("can use = " + format_mem.format(max), calculateCoordX(0), calculateCoordY(320), paint);
    }
}
