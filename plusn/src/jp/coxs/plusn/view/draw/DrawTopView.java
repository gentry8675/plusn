package jp.coxs.plusn.view.draw;

import java.util.HashMap;
import java.util.Map;

import jp.coxs.plusn.R;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * PlayViewの描画クラス
 *
 * @author Gen
 */
public class DrawTopView extends DrawView{

    // 座標係数
//    private float diff = 0;
//    private float diffX = 0;
//    private float diffY = 0;

    private Drawable backGround; // 背景

    private Drawable play_button;
//    private Drawable start_down;

//    private Canvas can = null;

    private Map<String, Rect> rectMap = new HashMap<String, Rect>();

    private Drawable top_frame;
    private Drawable logo;

    /**
     * コンストラクター
     *
     * @param diff 座標係数
     * @param resources
     */
    public DrawTopView(Resources resources) {
        super(resources);

        backGround = resources.getDrawable(R.drawable.back_ground);

        play_button = resources.getDrawable(R.drawable.play_button);
//        start_down = resources.getDrawable(R.drawable.startbuttondown);

        // 枠
        top_frame = resources.getDrawable(R.drawable.top_frame);
        // 枠
        logo = resources.getDrawable(R.drawable.logo);
    }

    /**
     * 描画キャンバスを設定します
     *
     * @param can キャンバス
     */
//    public void Init(Canvas can) {
//        this.can = can;
//    }

    /**
     * 背景を描画します
     */
    public void drawBackGround() {
        can.drawColor(Color.GRAY);
        backGround.setBounds(calculateCoordX(30), calculateCoordY(0), calculateCoordX(870), calculateCoordY(1600));
        backGround.draw(can);
        top_frame.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        top_frame.draw(can);
        logo.setBounds(calculateCoordX(30), calculateCoordY(300), calculateCoordX(870), calculateCoordY(580));
        logo.draw(can);
    }

    /**
     * ボタンを描画します
     *
     * @param startDownFlg
     */
    public void drawButton(boolean startDownFlg) {
        if (!startDownFlg) {
            play_button.setBounds(calculateCoordX(200), calculateCoordY(850), calculateCoordX(700), calculateCoordY(1150));
        } else {
            play_button.setBounds(calculateCoordX(250), calculateCoordY(880), calculateCoordX(650), calculateCoordY(1120));
        }
        play_button.draw(can);
        rectMap.put("start", new Rect(calculateCoordX(300), calculateCoordY(900), calculateCoordX(600), calculateCoordY(1100)));
    }

    /**
     * ボタンを描画します
     *
     * @param param
     */
    public Rect getBounds(String name) {
        // スタートボタン
        return rectMap.get(name);
    }

    public void callback() {
        super.callback();
        backGround.setCallback(null);
        play_button.setCallback(null);
//        start_down.setCallback(null);
        top_frame.setCallback(null);
        logo.setCallback(null);
    }

}
