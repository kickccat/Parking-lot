package com.newclazz.parkinglot;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tingchechang implements ParkingLot {
    /** 总车位 */
    public static final int All_chewei = 10;
    /** 停车场内剩余车位 */
    int shengyu = 10;
    /** 成员变量尽量只声明,在set方法或者构造方法里再进行赋值,可以有效的对异常进行捕获 */
    File f;
    Map<String, Date> yiting;
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Map<String, Car> yitingMap;
//    Set<Car> yiting;
//    private Car car;
//    public void setCar(Car car) {
//        this.car = car;
//    }

    /**
     * 在创建对象时执行构造方法,读取文件中的数据对对象本身的值进行初始化.
     */
    public Tingchechang() {
        // 在对象创建的时候对f 成员变量进行赋值
        // System.getProperty("user.dir") 获取项目根目录
        f = new File(System.getProperty("user.dir"), "addfile.txt");
        // 创建父文件夹
        f.getParentFile().mkdirs();
        yiting = new HashMap<>();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(f));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            if (lines.size() > 0) {
                this.shengyu = Integer.parseInt(lines.get(0));
//                String[] che =new String[10];
                for (int i = 1; i < lines.size(); i++) {
                    String che = lines.get(i);
                    String[] che2 = che.split("=");
                    yiting.put(che2[0], new Date(che2[1]));
                }
//                String che = lines.get(1);
                /**
                 * 在简单调用List.toString()时按照其固定格式输出[member, member, other members...],两个成员之间会有一个逗号
                 * 和一个空格的分割,导致在截取数组时除了第一个数组外其他的key值前面都多了一个分割,暂行解决办法: 通过 ", "(逗号后加一个空格)
                 * 分割原始字符串,来去掉多余的一个空格.
                 * P.S. 数据库中有时会存入因为前端检查不足而写入的大量空格,解决办法有很多种,以后碰到了再说
                 * 本次修改了这个地方                                        ↓↓
                 */
//                String[] ches = che.replace("[", "").replace("]", "").split(", ");
//                for (String str1 : ches) {
//                    String[] che2 = str1.split("=");
//                    yiting.put(che2[0], new Date(che2[1]));
//                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writefile() {
        /**
         * 在构造FileWriter时有一个boolean型参数,可选,此参数代表写入文件时是重新写入还是在原有文件上继续写;
         * 之前我的理解错误所以导致传入true,使每次写入文件时在原有的基础上继续写,去掉这个参数就可以实现写之前清空文件的功能.
         * 本次修改了这个地方                   ↓↓
         */
        try (FileWriter writer = new FileWriter(f)) {
//            f.delete();
//            f.createNewFile();
            writer.write(shengyu + "\r\n");
            List<String> che = new ArrayList<>();
            int i = 0;
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Date> entry : yiting.entrySet()) {
//                che.add(entry.getKey() + "=" + entry.getValue().toString());
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\r\n");
            }
            writer.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean tingche(String chepai) {
        if (this.shengyu <= 0)
            return false;
        shengyu--;
        yiting.put(chepai, new Date());
        System.out.println("剩余的车位有：" + shengyu);
        return true;
    }

    public String liku(String chepai) throws Exception {
        if (yiting.containsKey(chepai)) {
            shengyu++;
            // Date jinku=yiting.get(chepai);
            System.out.println("剩余的车位有：" + shengyu);
            Date date2 = new Date();
            Date date1 = yiting.get(chepai);
            System.out.println("该车停留时间为：" + (date2.getTime() - date1.getTime()) / (1000 * 60 * 60));
            long time = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60);
            if (time * 60 < 30) {
                System.out.println("停车时间在30分钟之内，不收费。");
            } else if (time <= 2) {
                System.out.println("收费五元。");
            } else {
                long charge = (time - 2) * 5 + 5;
                System.out.println("收费" + charge);
            }
            /** 离库时将该车停留时间离开时间进行整理*/
            statistics(chepai, date2);
            yiting.remove(chepai);
            return "10分钟";
        } else {
            throw new Exception("库里没有该车。");
        }
    }

    public void statistics(String chepai, Date date) {
        File s = new File(System.getProperty("user.dir"), "statistics.txt");
        s.getParentFile().mkdirs();
        try {
            if (!s.exists()) {
                s.createNewFile();
            }
            try (FileWriter writer = new FileWriter(s)) {
                String nnn = "车牌:" + chepai + " " + "进库时间：" + yiting.get(chepai) + " " + "离库时间：" + date.getTime() / (1000 * 60 * 60) + "\r\n";
                writer.write(nnn.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}