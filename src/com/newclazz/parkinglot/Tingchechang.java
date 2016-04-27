package com.newclazz.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;


public class Tingchechang implements ParkingLot {
    /** 总车位 */
    public static final int All_chewei = 10;
    /** 停车场内剩余车位 */
    int shengyu = 10;
    /** 成员变量尽量只声明,在set方法或者构造方法里再进行赋值,可以有效的对异常进行捕获 */
    File f;
    Map<String, Date> yiting;
    private Car car;
    public void setCar(Car car){

        this.car=car;
    }

    /**
     * 在创建对象时执行构造方法,读取文件中的数据对对象本身的值进行初始化.
     */
    public Tingchechang() {

        // 在对象创建的时候对f 成员变量进行赋值
        // System.getProperty("user.dir") 获取项目根目录
        f = new File(System.getProperty("user.dir"), "addfile.txt");
        // 创建父文件夹
        f.getParentFile().mkdirs();
        yiting = new HashMap<String, Date>();
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
            this.shengyu = Integer.parseInt(lines.get(0));
            String che = lines.get(1);
            String[] ches = che.replace("[", "").replace("]", "").split(",");
            for (String str1 : ches) {
                String[] che2 = str1.split("=");
                yiting.put(che2[0], new Date(che2[1]));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writefile() {
        try (FileWriter writer = new FileWriter(f, true)) {
            f.delete();
            f.createNewFile();
            writer.write(shengyu + "\r\n");
            List<String> che = new ArrayList<>();
            int i = 0;
            for (Map.Entry<String, Date> entry : yiting.entrySet()) {
                che.add(entry.getKey() + "=" + entry.getValue().toString());
            }
            writer.write(che.toString());
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
            System.out.println("该车停留时间为：" + (date2.getTime() - date1.getTime())/1000);
            yiting.remove(chepai);
            return "10分钟";
        } else {
            throw new Exception("库里没有该车。");
        }
    }
}
