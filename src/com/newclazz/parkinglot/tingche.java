package com.newclazz.parkinglot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;

import static com.newclazz.parkinglot.Tingchechang.All_chewei;

public class tingche {
    public static String chepai;
    public static Date date1 = new Date();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入进库车牌号：");
        String chepai = input.nextLine();
        Tingchechang tcc = new Tingchechang();
        tcc.tingche(chepai);
        System.out.println("总车位为：" + All_chewei);
        Date date1 = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date1);
        System.out.println(time);
        tcc.writefile();
    }
}
