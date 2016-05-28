package jp.coxs.plusn.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.coxs.plusn.R;
import jp.coxs.plusn.ScreenActivity;
import jp.coxs.plusn.bean.MapInfoBean;
import jp.coxs.plusn.bean.PointBean;
import jp.coxs.plusn.bean.SkillBean;
import jp.coxs.plusn.bean.StageInfoBean;
import jp.coxs.plusn.bean.TargetInfoBean;
import android.content.Context;
import android.content.res.AssetManager;

public class FileUtil {

    private ScreenActivity context;

    public FileUtil(Context context) {
        this.context = (ScreenActivity) context;
    }

    /**
     * ファイルを初期化する
     *
     * @param context
     */
    public void initFileUtil() {
        // ステージ状態を初期化する
        //        initFile("skill.txt", "0");

        AssetManager assetManager = context.getResources().getAssets();
        String[] fileList = null;
        try {
            fileList = assetManager.list("");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        for (int i = 0; i < fileList.length; i++) {
            try {
                context.openFileInput(fileList[i]);
            } catch (FileNotFoundException e1) {
                try {
                    InputStream inputStream = assetManager.open(fileList[i]);
                    FileOutputStream fileOutputStream = context.openFileOutput(fileList[i], Context.MODE_PRIVATE);
                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(buffer)) >= 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ファイルを初期化する
     *
     * @param context
     * @param path
     * @param initInfo
     */
    private void initFile(String fileName, String initInfo) {

        try {
            InputStream in = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    in, "UTF-8"));
            reader.close();
        } catch (Exception e) {
            try {
                OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                        out, "UTF-8"));
                if ("skill.txt".equals(fileName)) {
                    writer.append("1,0");
                    writer.append(System.getProperty("line.separator"));
                    writer.append("2,0");
                    writer.append(System.getProperty("line.separator"));
                    writer.append("3,0");
                    writer.append(System.getProperty("line.separator"));
                    writer.append("4,0");
                } else {
                    writer.append(initInfo);
                }
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * ステージ状態を取得する
     *
     * @return
     */
    public int getStageStatus() {

        try {
            InputStream in = context.openFileInput("stageStatus.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            if ((s = reader.readLine()) != null) {
                return Integer.valueOf(s).intValue();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * ステージ情報をセットする
     *
     * @param stageId
     * @param targetType
     * @return
     */
    public void setStageStatus(int mapId, int stageId, int targetType) {
        String fileName = "map" + mapId + "StageInfo";
        String[] aryStageStatus = null;

        try {
            InputStream stageStatusFile = context.openFileInput(fileName);
            BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
            String stageStatus = "";
            while ((stageStatus = stageStatusReader.readLine()) != null) {
                if (!"".equals(stageStatus)) {
                    aryStageStatus = stageStatus.split(",");
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        int aryid = stageId - 1;
        if (stageId == 99) {
            aryid = aryStageStatus.length -1;
        }
        int stageStutes = Integer.valueOf(aryStageStatus[aryid]).intValue();

        System.out.println("targetType = " + targetType);
        System.out.println("stageStutes = " + stageStutes);
        if (stageStutes == targetType) {
            aryStageStatus[aryid] = String.valueOf(stageStutes + 1);
        }
        if (stageStutes == 1 && stageId < 9) {
            aryStageStatus[stageId] = "1";
        }
        try {
            OutputStream os = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.append(aryStageStatus[0]);
            for (int i = 1; i < aryStageStatus.length; i++) {
                writer.append(",");
                writer.append(aryStageStatus[i]);
            }
            writer.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }


    /**
     * ステージ情報をセットする
     *
     * @param stageId
     * @param targetType
     * @return
     */
    public void setStageStatus(int stageId) {
        String fileName = "stage_status";
        String[] aryStageStatus = null;
        List<String> stageStatusList = new ArrayList<String>();

        try {
            InputStream stageStatusFile = context.openFileInput(fileName);
            BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
            String stageStatus = "";
            while ((stageStatus = stageStatusReader.readLine()) != null) {
                if (!"".equals(stageStatus)) {
                    aryStageStatus = stageStatus.split(",");
                    for (int i = 0; i < aryStageStatus.length; i++) {
                        stageStatusList.add(aryStageStatus[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        int lastStage = 1;
        for (int i = 0; i < stageStatusList.size(); i++) {
            if ("1".equals(stageStatusList.get(i))) {
                lastStage = i + 1;
            }
        }

        try {
            OutputStream os = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for (int i = 0; i < stageStatusList.size(); i++) {
                if (i > 0) {
                    writer.append(",");
                }
                if (stageId == (i + 1)) {
                    writer.append("2");
                } else if (stageId == i && lastStage == stageId) {
                    writer.append("1");
                } else {
                    writer.append(stageStatusList.get(i));
                }
            }
            writer.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }


    public List<String> getStageRanking(int mapId, int stageId, int clearType) {

        List<String> scorelist = new ArrayList<String>();

        // ファイル名: stageranking + マップID + _ + ステージID
        String fileName = "stage_ranking_" + mapId + "_" + stageId + "_" + clearType;
        try {
            InputStream in = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                if (!"".equals(s) && !"0".equals(s)) {
                    scorelist.add(s);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scorelist;
    }


    public List<String> getStageRanking(int stageId) {

        List<String> scorelist = new ArrayList<String>();

        // ファイル名: stageranking + マップID + _ + ステージID
        String fileName = "stage_ranking_" + stageId;
        try {
            InputStream in = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                if (!"".equals(s) && !"0".equals(s)) {
                    scorelist.add(s);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scorelist;
    }

    /**
     * ステージ得点情報をセットする
     *
     * @param stageId
     * @return
     */
    public void setStageRanking(int mapId, int stageId, int clearType, int score) {

        // ファイル名: stageranking + マップID + _ + ステージID
        String fileName = "stage_ranking_" + mapId + "_" + stageId + "_" + clearType;

        List<String> scorelist = new ArrayList<String>();
        List<String> newScorelist = new ArrayList<String>();

        try {
            InputStream in = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                if (!"".equals(s)) {
                    scorelist.add(s);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean inFlg = false;
        if (scorelist.size() == 0) {
            newScorelist.add(String.valueOf(score));
        } else {
            for (int i = 0; i < scorelist.size(); i++) {
                if (!inFlg
                        && Integer.valueOf(scorelist.get(i)).intValue() < score) {
                    newScorelist.add(String.valueOf(score));
                    inFlg = true;
                }
                newScorelist.add(scorelist.get(i));
            }
            if (!inFlg && newScorelist.size() < 3) {
                newScorelist.add(String.valueOf(score));
            }
        }

        try {
            OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            int lineCount = 0;
            if (newScorelist.size() > 3) {
                lineCount = 3;
            } else {
                lineCount = newScorelist.size();
            }
            for (int i = 0; i < lineCount; i++) {
                writer.append(newScorelist.get(i));
                writer.append(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * ステージ得点情報をセットする
     *
     * @param stageId
     * @return
     */
    public void setStageRanking(int stageId, int score) {

        // ファイル名: stageranking + マップID + _ + ステージID
        String fileName = "stage_ranking_" + stageId;

        List<String> scorelist = new ArrayList<String>();
        List<String> newScorelist = new ArrayList<String>();

        try {
            InputStream in = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                if (!"".equals(s)) {
                    scorelist.add(s);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean inFlg = false;
        if (scorelist.size() == 0) {
            newScorelist.add(String.valueOf(score));
        } else {
            for (int i = 0; i < scorelist.size(); i++) {
                if (!inFlg
                        && Integer.valueOf(scorelist.get(i)).intValue() < score) {
                    newScorelist.add(String.valueOf(score));
                    inFlg = true;
                }
                newScorelist.add(scorelist.get(i));
            }
            if (!inFlg && newScorelist.size() < 3) {
                newScorelist.add(String.valueOf(score));
            }
        }

        try {
            OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            int lineCount = 0;
            if (newScorelist.size() > 3) {
                lineCount = 3;
            } else {
                lineCount = newScorelist.size();
            }
            for (int i = 0; i < lineCount; i++) {
                writer.append(newScorelist.get(i));
                writer.append(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     *
     *
     * @param stageId
     * @return
     */
    public List<SkillBean> getUserSkillInfo() {

        List<SkillBean> skillList = new ArrayList<SkillBean>();
        List<String> userSkillId = new ArrayList<String>();

        try {
            InputStream count = context.openFileInput("user_skill");
            BufferedReader countReader = new BufferedReader(new InputStreamReader(count, "UTF-8"));
            String s;
            if ((s = countReader.readLine()) != null) {
                String[] skillId = s.split(",");
                userSkillId.add(skillId[0]);
                userSkillId.add(skillId[1]);
                userSkillId.add(skillId[2]);
            }
            countReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        skillList.add(getSkillInfo(userSkillId.get(0)));
        skillList.add(getSkillInfo(userSkillId.get(1)));
        skillList.add(getSkillInfo(userSkillId.get(2)));

        return skillList;
    }

    public void setSkillCount(List<SkillBean> skillList) {

        Map<String, String> map = new HashMap<String, String>();

        try {
            InputStream count = context.openFileInput("skill_count");
            BufferedReader countReader = new BufferedReader(new InputStreamReader(count, "UTF-8"));
            String s;
            while ((s = countReader.readLine()) != null) {
                if (!"".equals(s)) {
                    String[] skill = s.split(",");
                    map.put(skill[0], skill[1]);
                }
            }
            countReader.close();

            for (int i = 0; i < skillList.size(); i++) {
                map.put(skillList.get(i).getSkillId(), String.valueOf(skillList.get(i).getSkillCount()));
            }

            OutputStream out = context.openFileOutput("skill_count", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append("1," + map.get("1"));
            writer.append(System.getProperty("line.separator"));
            writer.append("2," + map.get("2"));
            writer.append(System.getProperty("line.separator"));
            writer.append("3," + map.get("3"));
            writer.append(System.getProperty("line.separator"));
            writer.append("4," + map.get("4"));
            writer.append(System.getProperty("line.separator"));
            writer.append("5," + map.get("5"));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * @param stageId
     * @return
     */
    private InputStream getStageInfoFile(int mapId, int stageId) {

        // ファイル名: stageinfo + マップID + _ + ステージID
        String fileName = "stage_info_" + mapId + "_" + stageId;
        if (stageId == 99) {
            fileName =  "stage_info_" + mapId + "_b";
        }
        // ファイルID
        int fileId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());

        try {
            return context.getResources().openRawResource(fileId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     *
     * @param stageId
     * @return
     */
    private InputStream getStageInfoFile(int stageId) {

        // ファイル名: stageinfo + マップID + _ + ステージID
        String fileName = "stage_info_" + stageId;
        // ファイルID
        int fileId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());

        try {
            return context.getResources().openRawResource(fileId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ステージ情報リストを取得します
     *
     * @param mapId
     * @return
     */
//    public List<StageInfoBean> getStageInfoList(int mapId) {
//
//        String[] aryStageStatus = null;
//        List<StageInfoBean> stageInfoList = new ArrayList<StageInfoBean>();
//
//        try {
//            InputStream stageStatusFile = context.openFileInput("map" + mapId + "StageInfo");
//            BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
//            String stageStatus = "";
//            while ((stageStatus = stageStatusReader.readLine()) != null) {
//                if (!"".equals(stageStatus)) {
//                    aryStageStatus = stageStatus.split(",");
//                }
//            }
//
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < aryStageStatus.length; i++) {
//            StageInfoBean stageInfo = getStageInfo(mapId, (i + 1), Integer.valueOf(aryStageStatus[i]).intValue());
//
//            if (null != stageInfo) {
//                stageInfoList.add(stageInfo);
//            }
//        }
//
//        return stageInfoList;
//    }

    /**
     * ステージ情報リストを取得します
     *
     * @param mapId
     * @return
     */
    public List<StageInfoBean> getStageInfoList() {

        String[] aryStageStatus = null;
        List<String> stageStatusList = new ArrayList<String>();
        List<StageInfoBean> stageInfoList = new ArrayList<StageInfoBean>();

        try {
            InputStream stageStatusFile = context.openFileInput("stage_status");
            BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
            String stageStatus = "";
            while ((stageStatus = stageStatusReader.readLine()) != null) {
                if (!"".equals(stageStatus)) {
                    aryStageStatus = stageStatus.split(",");
                    for (int i = 0; i < aryStageStatus.length; i++) {
                        stageStatusList.add(aryStageStatus[i]);
                    }
                }
            }

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stageStatusList.size(); i++) {
            StageInfoBean stageInfo = getStageInfo((i + 1), Integer.valueOf(stageStatusList.get(i)).intValue());

            if (null != stageInfo) {
                stageInfoList.add(stageInfo);
            }
        }

        return stageInfoList;
    }

//    public List<MapInfoBean> getMapInfoList() {
//
//        List<MapInfoBean> mapInfoList = new ArrayList<MapInfoBean>();
//
//        int i = 1;
//        do {
//            MapInfoBean mapInfo = getMapInfo(i);
//            mapInfoList.add(mapInfo);
//            i++;
//        } while (null != getMapInfoFile(i));
//
//        return mapInfoList;
//    }

//    public MapInfoBean getMapInfo(int mapId) {
//        InputStream mapInfoFile = getMapInfoFile(mapId);
//        BufferedReader mapInfoReader;
//        MapInfoBean mapInfo = new MapInfoBean();
//        try {
//            mapInfoReader = new BufferedReader(new InputStreamReader(mapInfoFile, "UTF-8"));
//
//            String line = "";
//            while ((line = mapInfoReader.readLine()) != null) {
//                if (!"".equals(line)) {
//                    String[] aryMapInfo = line.split(",");
//                    if ("CoordInfo".equals(aryMapInfo[0])) {
//                        mapInfo.setLeft(Integer.valueOf(aryMapInfo[1]).intValue());
//                        mapInfo.setTop(Integer.valueOf(aryMapInfo[2]).intValue());
//                        mapInfo.setRight(Integer.valueOf(aryMapInfo[3]).intValue());
//                        mapInfo.setBottom(Integer.valueOf(aryMapInfo[4]).intValue());
//                    }
//                    if ("MapIcon".equals(aryMapInfo[0])) {
//                        mapInfo.setMapImageName(aryMapInfo[1]);
//                    }
//                    if (mapId > 1) {
//
//                        InputStream stageStatusFile = context.openFileInput("map" + (mapId - 1) + "StageInfo");
//                        BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
//                        String stageStatus = "";
//                        boolean clearFlg = true;
//                        while ((stageStatus = stageStatusReader.readLine()) != null) {
//                            if (!"".equals(stageStatus)) {
//                                String[] aryStageStatus = stageStatus.split(",");
//                                for (int i = 0; i < aryStageStatus.length; i++) {
//                                    if ("1".equals(aryStageStatus[i])) {
//                                        clearFlg = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        //                        if (clearFlg){
//                        mapInfo.setShowFlg(clearFlg);
//                        //                        } else {
//                        //                            mapInfo.setShowFlg(false);
//                        //                        }
//
//                    } else {
//                        mapInfo.setShowFlg(true);
//                    }
//                }
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return mapInfo;
//    }

    /**
     *
     *
     * @param stageId
     * @return
     */
    private InputStream getMapInfoFile(int mapId) {
        // ファイル名: map_info_ + マップID
        String fileName = "map_info_";
        if (mapId == 0) {
            fileName += "sp";
        } else {
            fileName += mapId;
        }

        // ファイルID
        int fileId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());

        try {
            return context.getResources().openRawResource(fileId);
        } catch (Exception e) {
            return null;
        }
    }

//    public StageInfoBean getBossStageInfo(int mapId) {
//
//        InputStream stageInfoFile = getStageInfoFile(mapId, 99);
//        // ファイルの存在を確認する
//        if (IsFileExist(stageInfoFile)) {
//            StageInfoBean stageInfo = new StageInfoBean();
//            List<TargetInfoBean> targetInfoList = new ArrayList<TargetInfoBean>();
//            List<PointBean> stepMoveFromPointList = new ArrayList<PointBean>();
//            List<PointBean> stepMoveToPointList = new ArrayList<PointBean>();
//            List<PointBean> stepMoveStartPointList = new ArrayList<PointBean>();
//            List<PointBean> stepStackFromPointList = new ArrayList<PointBean>();
//            List<PointBean> stepStackToPointList = new ArrayList<PointBean>();
//            List<SkillBean> stageSkillList = new ArrayList<SkillBean>();
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stageInfoFile, "UTF-8"));
//                String s = "";
//                while ((s = reader.readLine()) != null) {
//                    if (!"".equals(s)) {
//                        String[] aryInfo = s.split(",");
//                        // ステージの表示位置を設定します
//                        if ("CoordInfo".equals(aryInfo[0])) {
//                            stageInfo.setLeft(Integer.valueOf(aryInfo[1]).intValue());
//                            stageInfo.setTop(Integer.valueOf(aryInfo[2]).intValue());
//                            stageInfo.setRight(Integer.valueOf(aryInfo[3]).intValue());
//                            stageInfo.setBottom(Integer.valueOf(aryInfo[4]).intValue());
//                        }
//                        // ステージの表示位置を設定します
//                        if ("TargetInfo".equals(aryInfo[0])) {
//                            targetInfoList.add(
//                                    new TargetInfoBean(
//                                            Integer.valueOf(aryInfo[1]).intValue(),
//                                            Integer.valueOf(aryInfo[2]).intValue(),
//                                            Integer.valueOf(aryInfo[3]).intValue(),
//                                            aryInfo[4]));
//                        }
//
//                        if ("PlayGoundInfo".equals(aryInfo[0])) {
//                            stageInfo.setPlayGound("0", aryInfo[1], aryInfo[2].replace(" ", ""));
//                            stageInfo.setPlayGound("1", aryInfo[1], aryInfo[3].replace(" ", ""));
//                            stageInfo.setPlayGound("2", aryInfo[1], aryInfo[4].replace(" ", ""));
//                            stageInfo.setPlayGound("3", aryInfo[1], aryInfo[5].replace(" ", ""));
//                            stageInfo.setPlayGound("4", aryInfo[1], aryInfo[6].replace(" ", ""));
//                            stageInfo.setPlayGound("5", aryInfo[1], aryInfo[7].replace(" ", ""));
//                            stageInfo.setPlayGound("6", aryInfo[1], aryInfo[8].replace(" ", ""));
//                        }
//
//                        if ("BallPool".equals(aryInfo[0])) {
//                            for (int i = 0; i < Integer.valueOf(aryInfo[2]).intValue(); i++) {
//                                stageInfo.addToBallPool(aryInfo[1]);
//                            }
//                        }
//
//                        if ("StageSkill".equals(aryInfo[0])) {
//                            stageSkillList.add(getSkillInfo(aryInfo[1]));
//                        }
//
//                        if ("StepMove".equals(aryInfo[0])) {
//                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            PointBean   toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
//                            stepMoveFromPointList.add(fromPoint);
//                            stepMoveToPointList.add(toPoint);
//                        }
//
//                        if ("StepStack".equals(aryInfo[0])) {
//                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            PointBean   toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
//                            stepStackFromPointList.add(fromPoint);
//                            stepStackToPointList.add(toPoint);
//                        }
//
//                        if ("StepMoveStart".equals(aryInfo[0])) {
//                            PointBean point = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            stepMoveStartPointList.add(point);
//                        }
//                        if ("GoundImage".equals(aryInfo[0])) {
//                            stageInfo.setBackGroundImageName(aryInfo[1]);
//                        }
//                        if ("BossImage".equals(aryInfo[0])) {
//                            stageInfo.setBossImageName(aryInfo[1]);
//                        }
//                        if ("BossHp".equals(aryInfo[0])) {
//                            stageInfo.setBossMaxHp(Integer.valueOf(aryInfo[1]).intValue());
//                        }
//                    }
//                }
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            stageInfo.setStageSkillList(stageSkillList);
//            stageInfo.setStageId(99);
//            stageInfo.setStepMoveFromPointList(stepMoveFromPointList);
//            stageInfo.setStepMoveToPointList(stepMoveToPointList);
//            stageInfo.setStepMoveStartPointList(stepMoveStartPointList);
//            stageInfo.setStepStackFromPointList(stepStackFromPointList);
//            stageInfo.setStepStackToPointList(stepStackToPointList);
//            // ステージ状態を設定します
//
//
//            try {
//                InputStream stageStatusFile = context.openFileInput("map" + mapId + "StageInfo");
//                BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
//                String stageStatus = "";
//                boolean clearFlg = true;
//                while ((stageStatus = stageStatusReader.readLine()) != null) {
//                    if (!"".equals(stageStatus)) {
//                        String[] aryStageStatus = stageStatus.split(",");
//                        for (int i = 0; i < aryStageStatus.length; i++) {
//                            if ("0".equals(aryStageStatus[i])) {
//                                clearFlg = false;
//                            }
//                        }
//                    }
//                }
//                if (clearFlg) {
//                    stageInfo.setStageStatus(1);
//                } else {
//                    stageInfo.setStageStatus(0);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // クリア情報リスト
//            stageInfo.setTargetInfoList(targetInfoList);
//            return stageInfo;
//        } else {
//            return null;
//        }
//    }

//    public StageInfoBean getStageInfo(int mapId, int stageId, int stageStatus) {
//
//        InputStream stageInfoFile = getStageInfoFile(mapId, stageId);
//        // ファイルの存在を確認する
//        if (IsFileExist(stageInfoFile)) {
//            StageInfoBean stageInfo = new StageInfoBean();
//            List<TargetInfoBean> targetInfoList = new ArrayList<TargetInfoBean>();
//            List<PointBean> stepMoveFromPointList = new ArrayList<PointBean>();
//            List<PointBean> stepMoveToPointList = new ArrayList<PointBean>();
//            List<PointBean> stepMoveStartPointList = new ArrayList<PointBean>();
//            List<PointBean> stepStackFromPointList = new ArrayList<PointBean>();
//            List<PointBean> stepStackToPointList = new ArrayList<PointBean>();
//            List<SkillBean> stageSkillList = new ArrayList<SkillBean>();
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stageInfoFile, "UTF-8"));
//                String s = "";
//                while ((s = reader.readLine()) != null) {
//                    if (!"".equals(s)) {
//                        String[] aryInfo = s.split(",");
//                        // ステージの表示位置を設定します
//                        if ("CoordInfo".equals(aryInfo[0])) {
//                            stageInfo.setLeft(Integer.valueOf(aryInfo[1]).intValue());
//                            stageInfo.setTop(Integer.valueOf(aryInfo[2]).intValue());
//                            stageInfo.setRight(Integer.valueOf(aryInfo[3]).intValue());
//                            stageInfo.setBottom(Integer.valueOf(aryInfo[4]).intValue());
//                        }
//                        // ステージの表示位置を設定します
//                        if ("TargetInfo".equals(aryInfo[0])) {
//                            targetInfoList.add(
//                                    new TargetInfoBean(
//                                            Integer.valueOf(aryInfo[1]).intValue(),
//                                            Integer.valueOf(aryInfo[2]).intValue(),
//                                            Integer.valueOf(aryInfo[3]).intValue(),
//                                            aryInfo[4]));
//                        }
//
//                        if ("PlayGoundInfo".equals(aryInfo[0])) {
//                            stageInfo.setPlayGound("0", aryInfo[1], aryInfo[2].replace(" ", ""));
//                            stageInfo.setPlayGound("1", aryInfo[1], aryInfo[3].replace(" ", ""));
//                            stageInfo.setPlayGound("2", aryInfo[1], aryInfo[4].replace(" ", ""));
//                            stageInfo.setPlayGound("3", aryInfo[1], aryInfo[5].replace(" ", ""));
//                            stageInfo.setPlayGound("4", aryInfo[1], aryInfo[6].replace(" ", ""));
//                            stageInfo.setPlayGound("5", aryInfo[1], aryInfo[7].replace(" ", ""));
//                            stageInfo.setPlayGound("6", aryInfo[1], aryInfo[8].replace(" ", ""));
//                        }
//
//                        if ("BallPool".equals(aryInfo[0])) {
//                            for (int i = 0; i < Integer.valueOf(aryInfo[2]).intValue(); i++) {
//                                stageInfo.addToBallPool(aryInfo[1]);
//                            }
//                        }
//
//                        if ("StageSkill".equals(aryInfo[0])) {
//                            stageSkillList.add(getSkillInfo(aryInfo[1]));
//                        }
//
//                        if ("StepMove".equals(aryInfo[0])) {
//                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            PointBean   toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
//                            stepMoveFromPointList.add(fromPoint);
//                            stepMoveToPointList.add(toPoint);
//                        }
//                        if ("StepStack".equals(aryInfo[0])) {
//                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            PointBean   toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
//                            stepStackFromPointList.add(fromPoint);
//                            stepStackToPointList.add(toPoint);
//                        }
//                        if ("StepMoveStart".equals(aryInfo[0])) {
//                            PointBean point = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
//                            stepMoveStartPointList.add(point);
//                        }
//                    }
//                }
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            stageInfo.setStageSkillList(stageSkillList);
//            stageInfo.setStageId(stageId);
//            stageInfo.setStepMoveFromPointList(stepMoveFromPointList);
//            stageInfo.setStepMoveToPointList(stepMoveToPointList);
//            stageInfo.setStepMoveStartPointList(stepMoveStartPointList);
//            stageInfo.setStepStackFromPointList(stepStackFromPointList);
//            stageInfo.setStepStackToPointList(stepStackToPointList);
//            // ステージ状態を設定します
//            stageInfo.setStageStatus(stageStatus);
//            // クリア情報リスト
//            stageInfo.setTargetInfoList(targetInfoList);
//            return stageInfo;
//        } else {
//            return null;
//        }
//    }


    /**
     * ステージ情報を取得します
     *
     * @param stageId
     * @param stageStatus
     * @return
     */
    public StageInfoBean getStageInfo(int stageId, int stageStatus) {

        InputStream stageInfoFile = getStageInfoFile(stageId);
        // ファイルの存在を確認する
        if (IsFileExist(stageInfoFile)) {
            StageInfoBean stageInfo = new StageInfoBean();
            List<TargetInfoBean> targetInfoList = new ArrayList<TargetInfoBean>();
            List<PointBean> stepMoveFromPointList = new ArrayList<PointBean>();
            List<PointBean> stepMoveToPointList = new ArrayList<PointBean>();
            List<PointBean> stepMoveStartPointList = new ArrayList<PointBean>();
            List<PointBean> stepStackFromPointList = new ArrayList<PointBean>();
            List<PointBean> stepStackToPointList = new ArrayList<PointBean>();
            List<PointBean> stepStackPointList = new ArrayList<PointBean>();
            List<SkillBean> stageSkillList = new ArrayList<SkillBean>();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stageInfoFile, "UTF-8"));
                String s = "";
                while ((s = reader.readLine()) != null) {
                    if (!"".equals(s)) {
                        String[] aryInfo = s.split(",");
                        // ステージの表示位置を設定します
                        if ("CoordInfo".equals(aryInfo[0])) {
                            stageInfo.setLeft(Integer.valueOf(aryInfo[1]).intValue());
                            stageInfo.setTop(Integer.valueOf(aryInfo[2]).intValue());
                            stageInfo.setRight(Integer.valueOf(aryInfo[3]).intValue());
                            stageInfo.setBottom(Integer.valueOf(aryInfo[4]).intValue());
                        }
                        // ステージの表示位置を設定します
                        if ("TargetInfo".equals(aryInfo[0])) {
                            stageInfo.setTargetInfo(
                                    new TargetInfoBean(
                                            Integer.valueOf(aryInfo[1]).intValue(),
                                            Integer.valueOf(aryInfo[2]).intValue(),
                                            Integer.valueOf(aryInfo[3]).intValue(),
                                            aryInfo[4]));
                            targetInfoList.add(
                                    new TargetInfoBean(
                                            Integer.valueOf(aryInfo[1]).intValue(),
                                            Integer.valueOf(aryInfo[2]).intValue(),
                                            Integer.valueOf(aryInfo[3]).intValue(),
                                            aryInfo[4]));
                        }

                        if ("PlayGoundInfo".equals(aryInfo[0])) {
                            stageInfo.setPlayGound("0", aryInfo[1], aryInfo[2].replace(" ", ""));
                            stageInfo.setPlayGound("1", aryInfo[1], aryInfo[3].replace(" ", ""));
                            stageInfo.setPlayGound("2", aryInfo[1], aryInfo[4].replace(" ", ""));
                            stageInfo.setPlayGound("3", aryInfo[1], aryInfo[5].replace(" ", ""));
                            stageInfo.setPlayGound("4", aryInfo[1], aryInfo[6].replace(" ", ""));
                            stageInfo.setPlayGound("5", aryInfo[1], aryInfo[7].replace(" ", ""));
                            stageInfo.setPlayGound("6", aryInfo[1], aryInfo[8].replace(" ", ""));
                        }

                        if ("BallPool".equals(aryInfo[0])) {
                            for (int i = 0; i < Integer.valueOf(aryInfo[2]).intValue(); i++) {
                                stageInfo.addToBallPool(aryInfo[1]);
                            }
                        }

                        if ("StageSkill".equals(aryInfo[0])) {
                            stageSkillList.add(getSkillInfo(aryInfo[1]));
                        }

                        // 水流効果
                        if ("StepMove".equals(aryInfo[0])) {
                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
                            PointBean toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
                            stepMoveFromPointList.add(fromPoint);
                            stepMoveToPointList.add(toPoint);
                        }
                        if ("StepMoveStart".equals(aryInfo[0])) {
                            PointBean point = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
                            stepMoveStartPointList.add(point);
                        }
                        if ("StepStack".equals(aryInfo[0])) {
                            PointBean fromPoint = new PointBean(Integer.valueOf(aryInfo[1]), Integer.valueOf(aryInfo[2]));
                            PointBean toPoint = new PointBean(Integer.valueOf(aryInfo[3]), Integer.valueOf(aryInfo[4]));
                            stepStackFromPointList.add(fromPoint);
                            stepStackToPointList.add(toPoint);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stageInfo.setStageSkillList(stageSkillList);
            stageInfo.setStageId(stageId);
            stageInfo.setStepMoveFromPointList(stepMoveFromPointList);
            stageInfo.setStepMoveToPointList(stepMoveToPointList);
            stageInfo.setStepMoveStartPointList(stepMoveStartPointList);
            stageInfo.setStepStackFromPointList(stepStackFromPointList);
            stageInfo.setStepStackToPointList(stepStackToPointList);
            stageInfo.setStepStackPointList(stepStackPointList);
            // ステージ状態を設定します
            stageInfo.setStageStatus(stageStatus);
            // クリア情報リスト
            stageInfo.setTargetInfoList(targetInfoList);
            return stageInfo;
        } else {
            return null;
        }
    }


    private SkillBean getSkillInfo(String skillId) {

        SkillBean skillInfo = null;
        InputStream info = context.getResources().openRawResource(R.raw.skill_info);
        try {

            Map<String, String> map = new HashMap<String, String>();

            InputStream count = context.openFileInput("skill_count");
            BufferedReader countReader = new BufferedReader(new InputStreamReader(count, "UTF-8"));
            String skillCountLine;
            while ((skillCountLine = countReader.readLine()) != null) {
                if (!"".equals(skillCountLine)) {
                    String[] skill = skillCountLine.split(",");
                    map.put(skill[0], skill[1]);
                }
            }
            countReader.close();

            BufferedReader infoReader = new BufferedReader(new InputStreamReader(info, "UTF-8"));
            String line = "";
            while ((line = infoReader.readLine()) != null) {
                if (!"".equals(line)) {
                    String[] skill = line.split(",");
                    if (skill[0].equals(skillId)) {
                        skillInfo = new SkillBean(skill[0], skill[1], skill[2], skill[3], skill[4], skill[5]);
                        if (null != map.get(skillId)) {
                            skillInfo.setSkillCount(Integer.valueOf(map.get(skillId)).intValue());
                        }
                        skillInfo.setBaceCost(Integer.valueOf(skill[6]).intValue());
                        skillInfo.setMaxLevel(Integer.valueOf(skill[7]).intValue());
                    }
                }
            }
            infoReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skillInfo;
    }

    /**
     * ファイル
     *
     * @param mapId
     * @return
     */
    private InputStream getStageStatusFile(int mapId) {

        // ファイル名: stageinfo + マップID + _ + ステージID
        String fileName = "mapStageInfo" + mapId;
        try {
            return context.openFileInput("map" + mapId + "StageInfo");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ファイル存在チェック
     *
     * @param stageInfoFile
     * @return
     */
    private boolean IsFileExist(InputStream stageInfoFile) {
        if (null == stageInfoFile) {
            return false;
        }
        //
        try {
            new InputStreamReader(stageInfoFile, "UTF-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public MapInfoBean getSpMapInfo() {
        InputStream mapInfoFile = getMapInfoFile(0);
        BufferedReader mapInfoReader;
        MapInfoBean mapInfo = new MapInfoBean();
        try {
            mapInfoReader = new BufferedReader(new InputStreamReader(mapInfoFile, "UTF-8"));

            String line = "";
            while ((line = mapInfoReader.readLine()) != null) {
                if (!"".equals(line)) {
                    String[] aryMapInfo = line.split(",");
                    if ("CoordInfo".equals(aryMapInfo[0])) {
                        mapInfo.setLeft(Integer.valueOf(aryMapInfo[1]).intValue());
                        mapInfo.setTop(Integer.valueOf(aryMapInfo[2]).intValue());
                        mapInfo.setRight(Integer.valueOf(aryMapInfo[3]).intValue());
                        mapInfo.setBottom(Integer.valueOf(aryMapInfo[4]).intValue());
                    }
                    if ("MapIcon".equals(aryMapInfo[0])) {
                        mapInfo.setMapImageName(aryMapInfo[1]);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapInfo;
    }

    public List<SkillBean> getSkillInfoList() {


        Map<String, String> map = new HashMap<String, String>();
        List<SkillBean> skillInfoList = new ArrayList<SkillBean>();
        InputStream info = context.getResources().openRawResource(R.raw.skill_info);

        try {
            InputStream count = context.openFileInput("skill_count");
            BufferedReader countReader = new BufferedReader(new InputStreamReader(count, "UTF-8"));
            String s;
            while ((s = countReader.readLine()) != null) {
                if (!"".equals(s)) {
                    String[] skill = s.split(",");
                    map.put(skill[0], skill[1]);
                }
            }
            countReader.close();


            BufferedReader infoReader = new BufferedReader(new InputStreamReader(info, "UTF-8"));
            String line = "";
            while ((line = infoReader.readLine()) != null) {
                if (!"".equals(line)) {
                    SkillBean skillInfo = null;
                    String[] skill = line.split(",");
                    skillInfo = new SkillBean(skill[0], skill[1], skill[2], skill[3], skill[4], skill[5]);
                    skillInfo.setSkillCount(Integer.valueOf(map.get(skill[0])).intValue());
                    skillInfoList.add(skillInfo);
                }
            }
            infoReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillInfoList;
    }

    public void setUserSkill(SkillBean skill1, SkillBean skill2, SkillBean skill3) {
        try {
            OutputStream out = context.openFileOutput("user_skill", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(String.valueOf(skill1.getSkillId()));
            writer.append(",");
            writer.append(String.valueOf(skill2.getSkillId()));
            writer.append(",");
            writer.append(String.valueOf(skill3.getSkillId()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getMoney() {
        String Money = "";

        try {
            InputStream robotParty = context.openFileInput("money");
            BufferedReader robotPartyReader = new BufferedReader(new InputStreamReader(robotParty, "UTF-8"));
            String robotPartyLine;
            while ((robotPartyLine = robotPartyReader.readLine()) != null) {
                if (!"".equals(robotPartyLine)) {
                    Money = robotPartyLine;
                }
            }
            robotPartyReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Money;
    }


    public void addMoney(int money) {
        String strMoney = "";

        try {
            InputStream robotParty = context.openFileInput("money");
            BufferedReader robotPartyReader = new BufferedReader(new InputStreamReader(robotParty, "UTF-8"));
            String robotPartyLine;
            while ((robotPartyLine = robotPartyReader.readLine()) != null) {
                if (!"".equals(robotPartyLine)) {
                    strMoney = robotPartyLine;
                }
            }
            robotPartyReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int moneyOld = Integer.valueOf(strMoney);

        strMoney = String.valueOf(moneyOld + money);

        try {
            OutputStream out = context.openFileOutput("money", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.append(strMoney);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void buySkill(int selectedSkillId, int selectedItemId) {

        Map<String, String> map = new HashMap<String, String>();

        try {
            InputStream count = context.openFileInput("skill_count");
            BufferedReader countReader = new BufferedReader(new InputStreamReader(count, "UTF-8"));
            String s;
            while ((s = countReader.readLine()) != null) {
                if (!"".equals(s)) {
                    String[] skill = s.split(",");
                    map.put(skill[0], skill[1]);
                }
            }
            countReader.close();

            String skillCount = map.get(String.valueOf(selectedSkillId + 1));
            int buyCount = 0;
            if (selectedItemId == 0) {
                buyCount = 1;
            } else if (selectedItemId == 1) {
                buyCount = 2;
            } else if (selectedItemId == 2) {
                buyCount = 10;
            } else if (selectedItemId == 3) {
                buyCount = 20;
            }

            skillCount = String.valueOf(Integer.valueOf(skillCount) + buyCount);

            map.put(String.valueOf(selectedSkillId + 1), skillCount);

            OutputStream out = context.openFileOutput("skill_count", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append("1," + map.get("1"));
            writer.append(System.getProperty("line.separator"));
            writer.append("2," + map.get("2"));
            writer.append(System.getProperty("line.separator"));
            writer.append("3," + map.get("3"));
            writer.append(System.getProperty("line.separator"));
            writer.append("4," + map.get("4"));
            writer.append(System.getProperty("line.separator"));
            writer.append("5," + map.get("5"));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStageIdNow() {
        //
        String fileName = "stage_status";
        String[] aryStageStatus = null;

        int stageId = 0;
        try {
            InputStream stageStatusFile = context.openFileInput(fileName);
            BufferedReader stageStatusReader = new BufferedReader(new InputStreamReader(stageStatusFile, "UTF-8"));
            String stageStatus = "";
            while ((stageStatus = stageStatusReader.readLine()) != null) {
                if (!"".equals(stageStatus)) {
                    aryStageStatus = stageStatus.split(",");
                    for (int i = 0; i < aryStageStatus.length; i++) {
                        stageId++;
                        if ("1".equals(aryStageStatus[i])) {
                            return stageId;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        return 1;
    }

    /**
     * 異常情報を出力する。
     *
     * @param e
     */
    public void printException(Exception e) {

        try {
            OutputStream out = context.openFileOutput("exception_log.txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            e.printStackTrace(writer);
        } catch (IOException e1) {
            e1.getStackTrace();
        }
    }
}
