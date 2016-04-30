package com.newclazz.parkinglot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.Exception;

import static com.newclazz.parkinglot.Tingchechang.All_chewei;
import static com.newclazz.parkinglot.tingche.chepai;

public class liku {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入离库车牌号：");
        String chepai = input.nextLine();
        Tingchechang tcc = new Tingchechang();
        System.out.println("总车位：" + All_chewei);
        Date date2 = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date2);
        System.out.println(time);
        tcc.liku(chepai);
        tcc.writefile();
//        tcc.sa
    }
}

