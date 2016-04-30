package com.newclazz.parkinglot;

import java.sql.Date;
import java.util.Scanner;

/**
 * 进库汽车等级对象
 *
 * @author dyzhu
 */
public class Car {
    /** 车牌号 */
    private String licensePlate;
    /** 进库时间 */
    private Date checkInDateTime;
    /** 离库时间 */
    private Date checkOutDateTime;

    // 无参公共构造器,防止new不出来
    public Car() {
    }

    /**
     * 快速构造方法
     *
     * @param licensePlate    车牌号
     * @param checkInDateTime 进库时间
     */
    public Car(String licensePlate, Date checkInDateTime) {
        this.licensePlate = licensePlate;
        this.checkInDateTime = checkInDateTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public Date getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCheckInDateTime(Date checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public void setCheckOutDateTime(Date checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }
}
