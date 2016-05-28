package jp.coxs.plusn.view.draw;

import java.util.ArrayList;
import java.util.List;

import jp.coxs.plusn.R;
import jp.coxs.plusn.bean.SkillBean;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * PlayViewの描画クラス
 *
 * @author Gen
 */
public class DrawShopView extends DrawView {

    private Drawable backGround; // 背景
    private Drawable stage_frame; // 背景

//    private Drawable start_up;
//    private Drawable start_down;

    private Drawable coin;               // ゴールド

    private Drawable selected;
//    private Drawable stage;               // ボールＮ

    private Drawable stage_button;
    private Drawable buy_button;
//    private Drawable shop_panel_l;
    private Drawable shop_panel_count;
    private Drawable shop_panel_m;
    private Drawable shop_panel_s;
    private Drawable shop_panel_ss;

    private Drawable gold_area;

    private List<Drawable> skillImageList = new ArrayList<Drawable>();

    private Drawable success_panel;
    private Drawable not_enough_panel;

    private Drawable ok_button;

    /**
     * コンストラクター
     *
     * @param diff 座標係数
     * @param resources
     */
    public DrawShopView(Resources resources) {
        super(resources);

        backGround = resources.getDrawable(R.drawable.back_ground);

        // 枠
        stage_frame = resources.getDrawable(R.drawable.stage_frame);

//        stage = resources.getDrawable(R.drawable.stage);

//        start_up = resources.getDrawable(R.drawable.startbutton);
//        start_down = resources.getDrawable(R.drawable.start_button_down);

        // 鉄の枠
        selected = resources.getDrawable(R.drawable.selected);

        coin = resources.getDrawable(R.drawable.coin);

        stage_button = resources.getDrawable(R.drawable.stage_button);
        buy_button = resources.getDrawable(R.drawable.buy_button);
        shop_panel_count = resources.getDrawable(R.drawable.shop_panel_count);
        shop_panel_m = resources.getDrawable(R.drawable.shop_panel_m);
        shop_panel_s = resources.getDrawable(R.drawable.shop_panel_s);
        shop_panel_ss = resources.getDrawable(R.drawable.shop_panel_ss);
        // ゴールドの表示枠
        gold_area = resources.getDrawable(R.drawable.gold_area);

        success_panel = resources.getDrawable(R.drawable.success_panel);
        not_enough_panel = resources.getDrawable(R.drawable.not_enough_panel);
        ok_button = resources.getDrawable(R.drawable.ok_button);
    }

    /**
     * 描画キャンバスを設定します
     *
     * @param can キャンバス
     */
    public void Init(Canvas can) {
        this.can = can;
        rectMap.put("stage_panel", new Rect(calculateCoordX(150), calculateCoordY(550), calculateCoordX(750), calculateCoordY(1250)));
        rectMap.put("stage_panel_button_1", new Rect(calculateCoordX(150), calculateCoordY(1150), calculateCoordX(350), calculateCoordY(1250)));
        rectMap.put("stage_panel_button_2", new Rect(calculateCoordX(350), calculateCoordY(1150), calculateCoordX(550), calculateCoordY(1250)));
        rectMap.put("stage_panel_button_3", new Rect(calculateCoordX(550), calculateCoordY(1150), calculateCoordX(750), calculateCoordY(1250)));
    }

    /**
     * 座標係数を設定します
     *
     * @param diff   全体係数
     * @param diffX  表示範囲のX偏移量
     * @param diffY  表示範囲のY偏移量
     */
//    public void setDiff(float diff, float diffX, float diffY) {
//        // 座標係数を設定する
//        this.diff = diff;
//        this.diffX = diffX;
//        this.diffY = diffY;
//    }

    /**
     * 背景を描画します
     */
    public void drawBackGround() {
        can.drawColor(Color.GRAY);
        backGround.setBounds(calculateCoordX(30), calculateCoordY(0), calculateCoordX(870), calculateCoordY(1600));
        backGround.draw(can);

        shop_panel_ss.setBounds(calculateCoordX(30), calculateCoordY(330), calculateCoordX(670), calculateCoordY(430));
        shop_panel_ss.draw(can);

        drawText(resources.getString(R.string.shop_select_item), 50, 410, diff * 70, Color.WHITE);

        shop_panel_ss.setBounds(calculateCoordX(30), calculateCoordY(750), calculateCoordX(670), calculateCoordY(850));
        shop_panel_ss.draw(can);

        drawText(resources.getString(R.string.shop_select_num), 50, 830, diff * 70, Color.WHITE);

        shop_panel_count.setBounds(calculateCoordX(30), calculateCoordY(850), calculateCoordX(240), calculateCoordY(1100));
        shop_panel_count.draw(can);
        shop_panel_count.setBounds(calculateCoordX(240), calculateCoordY(850), calculateCoordX(450), calculateCoordY(1100));
        shop_panel_count.draw(can);
        shop_panel_count.setBounds(calculateCoordX(450), calculateCoordY(850), calculateCoordX(660), calculateCoordY(1100));
        shop_panel_count.draw(can);
        shop_panel_count.setBounds(calculateCoordX(660), calculateCoordY(850), calculateCoordX(870), calculateCoordY(1100));
        shop_panel_count.draw(can);

        coin.setBounds(calculateCoordX(50), calculateCoordY(1035), calculateCoordX(100), calculateCoordY(1085));
        coin.draw(can);
        coin.setBounds(calculateCoordX(260), calculateCoordY(1035), calculateCoordX(310), calculateCoordY(1085));
        coin.draw(can);
        coin.setBounds(calculateCoordX(470), calculateCoordY(1035), calculateCoordX(520), calculateCoordY(1085));
        coin.draw(can);
        coin.setBounds(calculateCoordX(680), calculateCoordY(1035), calculateCoordX(730), calculateCoordY(1085));
        coin.draw(can);

        drawNumberString("100", 100, 1040, 30);
        drawNumberString("190", 310, 1040, 30);
        drawNumberString("900", 520, 1040, 30);
        drawNumberString("1700", 730, 1040, 30);
    }

    /**
     * ボタンを描画します
     *
     * @param startDownFlg
     */
    public void drawButton(boolean startDownFlg) {
//        start_up.setBounds(calculateCoordX(360), calculateCoordY(1025), calculateCoordX(540), calculateCoordY(1125));
//        start_down.setBounds(calculateCoordX(392), calculateCoordY(1050), calculateCoordX(514), calculateCoordY(1150));
        if (!startDownFlg) {
            //            start_up.draw(can);
        } else {
//            start_down.draw(can);
        }
    }

    /**
     * ボタンを描画します
     *
     * @param param
     */
    public Rect getBounds(String name) {
        return rectMap.get(name);
    }

    /**
     * 枠を描画します
     *
     */
    public void drawFrame() {
        stage_frame.setBounds(calculateCoordX(0), calculateCoordY(0), calculateCoordX(900), calculateCoordY(1600));
        stage_frame.draw(can);
    }

    public void drawController(String button_name, boolean buttonDownFlg) {

        // ショップボタン
        if ("stage".equals(button_name) && buttonDownFlg) {
            stage_button.setBounds(calculateCoordX(730), calculateCoordY(230), calculateCoordX(870), calculateCoordY(370));
            stage_button.draw(can);
        } else {
            stage_button.setBounds(calculateCoordX(740), calculateCoordY(240), calculateCoordX(860), calculateCoordY(360));
            stage_button.draw(can);
        }
        rectMap.put("stage", new Rect(calculateCoordX(740), calculateCoordY(240), calculateCoordX(860), calculateCoordY(360)));

        if ("buy".equals(button_name) && buttonDownFlg) {
            buy_button.setBounds(calculateCoordX(138), calculateCoordY(1450), calculateCoordX(763), calculateCoordY(1550));
            buy_button.draw(can);
        } else {
            buy_button.setBounds(calculateCoordX(200), calculateCoordY(1460), calculateCoordX(700), calculateCoordY(1540));
            buy_button.draw(can);
        }

        rectMap.put("buy", new Rect(calculateCoordX(200), calculateCoordY(1460), calculateCoordX(700), calculateCoordY(1540)));

    }

    /**
     * スキルエリアを描きます
     */
    public void drawSkill(List<SkillBean> skillList) {

        shop_panel_m.setBounds(calculateCoordX(30), calculateCoordY(430), calculateCoordX(870), calculateCoordY(630));
        shop_panel_m.draw(can);

        for (int i = 0; i < skillList.size(); i++) {
            Rect rect = new Rect(calculateCoordX(50 + (i * 165)), calculateCoordY(460), calculateCoordX(50 + (i * 165) + 140), calculateCoordY(600));

            skillImageList.get(i).setBounds(rect);
            skillImageList.get(i).draw(can);

            String skillcount = String.valueOf(skillList.get(i).getSkillCount());

            drawNumberString(skillcount, 50 + (i * 165) + 140 - (skillcount.length() * 40), 540, 40);

            rectMap.put("skill_" + (i + 1), rect);
        }
    }

    public void drawItem(int selectedSkillId) {

        Paint backPaint = new Paint();
        backPaint.setColor(Color.DKGRAY);
        Rect rect1 = new Rect(calculateCoordX(65), calculateCoordY(885), calculateCoordX(205), calculateCoordY(1025));
        Rect rect2 = new Rect(calculateCoordX(275), calculateCoordY(885), calculateCoordX(415), calculateCoordY(1025));
        Rect rect3 = new Rect(calculateCoordX(485), calculateCoordY(885), calculateCoordX(625), calculateCoordY(1025));
        Rect rect4 = new Rect(calculateCoordX(695), calculateCoordY(885), calculateCoordX(835), calculateCoordY(1025));

        skillImageList.get(selectedSkillId).setBounds(rect1);
        skillImageList.get(selectedSkillId).draw(can);
        skillImageList.get(selectedSkillId).setBounds(rect2);
        skillImageList.get(selectedSkillId).draw(can);
        skillImageList.get(selectedSkillId).setBounds(rect3);
        skillImageList.get(selectedSkillId).draw(can);
        skillImageList.get(selectedSkillId).setBounds(rect4);
        skillImageList.get(selectedSkillId).draw(can);

        drawNumberString("x1", 125, 965, 40);
        drawNumberString("x2", 335, 965, 40);
        drawNumberString("x10", 505, 965, 40);
        drawNumberString("x20", 715, 965, 40);

        rectMap.put("item_1", rect1);
        rectMap.put("item_2", rect2);
        rectMap.put("item_3", rect3);
        rectMap.put("item_4", rect4);
    }

    public void drawSelected(int selectedSkillId, int selectedItemId) {

        Rect rect = new Rect(calculateCoordX(50 + (selectedSkillId * 165)), calculateCoordY(460), calculateCoordX(50 + (selectedSkillId * 165) + 140), calculateCoordY(600));
        selected.setBounds(rect);
        selected.draw(can);

        Rect rect1 = new Rect(calculateCoordX(65), calculateCoordY(885), calculateCoordX(205), calculateCoordY(1025));
        Rect rect2 = new Rect(calculateCoordX(275), calculateCoordY(885), calculateCoordX(415), calculateCoordY(1025));
        Rect rect3 = new Rect(calculateCoordX(485), calculateCoordY(885), calculateCoordX(625), calculateCoordY(1025));
        Rect rect4 = new Rect(calculateCoordX(695), calculateCoordY(885), calculateCoordX(835), calculateCoordY(1025));
        if (selectedItemId == 0) {
            selected.setBounds(rect1);
        } else if (selectedItemId == 1) {
            selected.setBounds(rect2);
        } else if (selectedItemId == 2) {
            selected.setBounds(rect3);
        } else if (selectedItemId == 3) {
            selected.setBounds(rect4);
        }
        selected.draw(can);

    }

    /**
     * お金を描画します
     *
     * @param Money
     */
    public void drawMoney(String Money) {

        gold_area.setBounds(calculateCoordX(230), calculateCoordY(240), calculateCoordX(530), calculateCoordY(300));
        gold_area.draw(can);
        coin.setBounds(calculateCoordX(230), calculateCoordY(230), calculateCoordX(300), calculateCoordY(300));
        coin.draw(can);

        drawNumberString(Money, 330, 250, 30);
    }

    public void initSkillImage(List<SkillBean> skillList) {

        for (int i = 0; i < skillList.size(); i++) {
            int skillImageId = resources.getIdentifier(skillList.get(i).getSkillImageName(), "drawable", "jp.coxs.plusn");
            skillImageList.add(resources.getDrawable(skillImageId));
        }

    }

//    public void drawSkillInfo(SkillBean selectSkill) {
//
//        shop_panel_s.setBounds(calculateCoordX(30), calculateCoordY(630), calculateCoordX(870), calculateCoordY(730));
//        shop_panel_s.draw(can);
//
//        if (selectSkill.getSkillInfo().length() > 20) {
//            drawText(selectSkill.getSkillInfo().substring(0, 20), 150, 670, diff * 30, Color.WHITE);
//            drawText(selectSkill.getSkillInfo().substring(20, selectSkill.getSkillInfo().length()), 150, 720, diff * 30, Color.WHITE);
//        } else {
//            drawText(selectSkill.getSkillInfo(), 150, 670, diff * 30, Color.WHITE);
//        }
//    }

    public void drawSkillInfo(SkillBean selectSkill) {

        shop_panel_s.setBounds(calculateCoordX(30), calculateCoordY(630), calculateCoordX(870), calculateCoordY(730));
        shop_panel_s.draw(can);

        int StringId = resources.getIdentifier(selectSkill.getSkillInfo(), "string", "jp.coxs.plusn");

//        List<String> strList = makeString(resources.getString(StringId), 20);
//        for (int i = 0; i< strList.size(); i++) {
            drawText(resources.getString(StringId), 150, 670, diff * 30, Color.WHITE);
//        }
    }

    public void drawSuccessPanel(int kbn) {

        if (kbn == 1) {
            success_panel.setBounds(calculateCoordX(200), calculateCoordY(600), calculateCoordX(700), calculateCoordY(800));
            success_panel.draw(can);
        } else {
            not_enough_panel.setBounds(calculateCoordX(200), calculateCoordY(600), calculateCoordX(700), calculateCoordY(800));
            not_enough_panel.draw(can);
        }
        ok_button.setBounds(calculateCoordX(200), calculateCoordY(800), calculateCoordX(700), calculateCoordY(880));
        ok_button.draw(can);
        rectMap.put("ok", new Rect(calculateCoordX(200), calculateCoordY(800), calculateCoordX(700), calculateCoordY(880)));
    }
}
