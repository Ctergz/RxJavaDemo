package com.ly.wjh.test;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Test6 {
    public static void main(String[] args){

        Observable<Integer> ob1 = Observable.create(emitter -> {emitter.onNext(1);emitter.onComplete();System.out.println(Thread.currentThread().getName());});
        Observable<Integer> ob2 = Observable.create(emitter -> {emitter.onNext(2);emitter.onComplete();System.out.println(Thread.currentThread().getName());});

        ob1.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .doOnNext(integer -> {Thread.sleep(1000);System.out.println("ob1 success...");})
                .flatMap((Function<Integer, ObservableSource<?>>) integer -> ob2)
                .observeOn(Schedulers.newThread())
                .subscribe(s -> {Thread.sleep(1000);System.out.println("ob2 success...");}, throwable -> System.out.println("ob2 failed..."));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello RxJava");
    }
}
