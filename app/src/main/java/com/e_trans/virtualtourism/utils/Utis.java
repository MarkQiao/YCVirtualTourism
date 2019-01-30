package com.e_trans.virtualtourism.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * @author MarkQiao
 * @create 2019/1/23
 * @Describe
 */
public class Utis {
    private static long mLastClickTime = 0;
    /**
     * 判断点击崩溃
     */
    public static final int TIME_INTERVAL = 1000;

    public static boolean RepeatOnclick() {
        if (System.currentTimeMillis() - mLastClickTime >= TIME_INTERVAL) {
            mLastClickTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String formatNumber(long val) {
        if (val < 10000) {
            return val + "";
        }
        DecimalFormat df = new DecimalFormat("######0.0");
        double d = val / 10000.0;
        return df.format(d) + "万";
    }

    public static String[] getTitlelis = {
            "中国最牛水龙头，挂在悬崖上，一年四季常年不断向外吐水#贵州",
            "贵州啊贵州神奇的贵州！贵州啊贵州美丽的贵州！真的太美了！##贵州",
            "贵州.安顺.平坝区.明万历年间，白云寺僧卓锡天台，依山形地势，以木石架建五龙寺#航拍",
            "中国的骄傲，云贵交界线上的北盘江大桥，世界第一高桥#贵州",
            "航拍贵州大山雪景时发现一座木屋，谁在此隐居？#贵州",
            "贵州深山里的一条盘山公路，惊险而又美丽，弯弯曲曲，转了大半天才下山#贵州",
            "小学课本上的黄果树大瀑布你还知道吗？百闻不如一见，旱季都这么壮观#贵州",
            "贵州网红夜郎谷，7旬老人花了20年，就地取材用石头堆出来的城堡",
            "贵州深山发现一千年古寨，比北京故宫还久远，宛如穿越时空隧道！#航拍",
            "贵州这个地方最适合表白，机会给你们了，好好把握！#贵州",
            "#贵州 无限风光在险峰，为啥贵州总能出奇迹！#航拍",
            "贵州一个小镇上的梯田，如手指的指纹一样，太美了#贵州",
            "据说，它是上仙在贵州留下的脚印，梵天净土，天工开路，不得不佩服贵州人征服自然的勇气！#贵州",
            "这不是欧洲，是贵州！#贵州",
            "美#航拍",
            "咱们大贵州真美",
            "贵州四大名镇之一，贵阳第一个五A景区，青岩古镇#贵州#航拍",
            "黔行迹--贵州都匀螺蛳壳景区。悟空，你在哪里....#贵州#旅游贵州",
            "贵州大山深处一座险峻山峰，看着都觉得险，你敢爬吗？ #贵州",
            "贵州刘三姐。",
            "贵州大山发现一山洞，洞内竟住有70多人，与世无争自得自乐 #贵州",
            "航拍天下奇观，贵州双乳峰景区。",
            "来自贵州的大山。",
            "贵州大山里的人间仙境，有山有水有瀑布，隐居的好地方#贵州",
            "贵州一小镇中竟藏着一片绝美的梯田，如手指纹，太美了#贵州",
            "客从天上来，贵州的朋友支持大贵州！！",
            "贵州 的千户苗寨夜景。",
            "贵州第一条悬崖玻璃栈道，不用出省也可以挑战一下刺激，你敢走么 #贵州",
            "壮观的景色。 #贵州",
            "没想到贵州也有这么大的宫殿群，深山老林里，离贵阳不远，不知道还以为是黄土高坡的西安！#贵州",
            "美景在贵州",
            "贵州大山中的世外桃源，有山有水，有鸟语花香，你向往这样的地方吗？#贵州",
            "贵州贵州？#贵州",
            "苗族美神 仰阿莎 雕像坐落于贵州市剑河县仰阿莎湖畔。仰阿莎是全国最高的苗族美神雕像。 #贵州",
            "航拍贵州第一花海，怎一个美字了得~",
            "梵净山--世界遗产景区#贵州",
            "航拍贵州天生桥最后一幕，看得两脚瑟瑟发抖，知道为什么吗？#贵州",
            "贵州的这个地方被誉为上帝的一座花园，相信一定有认识的人。 #贵州",
            "这里不是欧洲是贵州。 #贵州",
            "这里不是欧洲是贵州。 #贵州",
            "这里不是欧洲是贵州。 #贵州",
            "世界桥梁看中国，中国桥梁看贵州，贵州水泊铁路北盘江大桥，真佩服桥梁的建设者#贵州"

    };

}

