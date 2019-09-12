package com.ly.wjh.test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Test5 {
    public static void main(String[] args){

        Observable<Integer> ob1 = Observable.create(emitter -> {emitter.onNext(1);emitter.onComplete();System.out.println("ob1:"+Thread.currentThread().getName());});
        Observable<Integer> ob2 = Observable.create(emitter -> {Thread.sleep(3000);emitter.onNext(2);emitter.onComplete();System.out.println("ob2:"+Thread.currentThread().getName());});
        Observable<Integer> ob3 = Observable.create(emitter -> {emitter.onNext(3);emitter.onComplete();System.out.println("ob3:"+Thread.currentThread().getName());});
        ob1.subscribeOn(Schedulers.io());
        ob2.subscribeOn(Schedulers.io());
        ob3.subscribeOn(Schedulers.io());
        Observable.concat(ob1,ob2,ob3)
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("====================onSubscribe=====================");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("====================onNext:"+integer+" "+Thread.currentThread().getName()+"=====================");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("====================onComplete=====================");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("====================onError=====================");
                    }
                });
        System.out.println("Hello RxJava"+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
