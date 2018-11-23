/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.common;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * @author ricolwang
 */
public class utils
{

}

class JavaMain
{

    public static void main(String[] args)
    {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
        System.out.println(payday);
    }
}
