package com.ly.wjh.test;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args){

        Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);

        Observable.just(1, 2, 3).map(integer -> "I'm " + integer).subscribe(System.out::println);

        System.out.println("Hello RxJava");
    }
}
