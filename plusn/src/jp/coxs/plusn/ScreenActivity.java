package jp.coxs.plusn;

import java.util.List;

import jp.coxs.plusn.R;
import jp.coxs.plusn.bean.StageInfoBean;
import jp.coxs.plusn.util.SystemUiHider;
import jp.coxs.plusn.view.PlayView;
import jp.coxs.plusn.view.ShopView;
import jp.coxs.plusn.view.StageSelectView;
import jp.coxs.plusn.view.TopView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class ScreenActivity extends Activity {
    public float disp_w, disp_h;

    /**
     * 表示される画面
     */
    private String displayScreen = "TopScreen";

    private int stageId = 0;
    private int mapMoveY = 0;
    private List<StageInfoBean> stageInfoList = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager manager = window.getWindowManager();

        // 表示部分情報取得
        Display disp = manager.getDefaultDisplay();
//        Point size = new Point();
//        disp.getSize(size);
//        disp_w = size.x; // 幅
//        disp_h = size.y; // 高
        disp_w = disp.getWidth(); // 幅
        disp_h = disp.getHeight(); // 高
        setContentView(R.layout.top_screen);

        AdView mAdView = (AdView) findViewById(R.id.top_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    /**
     * 画面再描画
     */
    public void InvalidatePlayScreen(){
        // スキル設定画面
        TopView topView = (TopView)findViewById(R.id.top_view);
        // プレイ画面
        PlayView playView = (PlayView)findViewById(R.id.play_view);
        // ステージ選択画面
        StageSelectView stageSelectView = (StageSelectView)findViewById(R.id.stage_select_view);
        // ショップ画面
        ShopView shopView = (ShopView)findViewById(R.id.shop_view);

        // 表示画面により、再描画する
        if ("PlayScreen".equals(displayScreen)) {
            playView.invalidate();
        } else if ("StageSelectScreen".equals(displayScreen)) {
            stageSelectView.invalidate();
        } else if ("TopScreen".equals(displayScreen)) {
            topView.invalidate();
        } else if ("ShopScreen".equals(displayScreen)) {
            shopView.invalidate();
        }
    }

    public void showPlayScreen(int stageId) {
        this.stageId = stageId;
        displayScreen = "PlayScreen";

        System.gc();
        setContentView(R.layout.play_screen);
    }

    public void showStageSelectScreen() {
        this.stageId = 0;
        displayScreen = "StageSelectScreen";

        System.gc();

        setContentView(R.layout.stage_select_screen);
        AdView mAdView = (AdView) findViewById(R.id.stage_select_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void showNextStage() {
        this.stageId++;
        displayScreen = "StageSelectScreen";

        System.gc();
        setContentView(R.layout.stage_select_screen);

        AdView mAdView = (AdView) findViewById(R.id.stage_select_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void showShopScreen() {
        displayScreen = "ShopScreen";

        System.gc();
        setContentView(R.layout.shop_screen);

        AdView mAdView = (AdView) findViewById(R.id.shop_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public int getStageId() {
        return stageId;
    }

    public int getMapMoveY() {
        return mapMoveY;
    }

    public void setMapMoveY(int mapMoveY) {
        this.mapMoveY = mapMoveY;
    }

    public void sns (Intent intent) {
        startActivity(intent);
    }

    public void setStageInfoList(List<StageInfoBean> stageInfoList) {
        this.stageInfoList = stageInfoList;
    }

    public List<StageInfoBean> getStageInfoList() {
        return this.stageInfoList;
    }

}
