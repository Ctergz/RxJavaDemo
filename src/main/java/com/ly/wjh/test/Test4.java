package com.ly.wjh.test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test4 {
    public static void main(String[] args){

        Observable<Integer> ob1 = Observable.create(emitter -> {emitter.onNext(1);emitter.onComplete();});
        Observable<Integer> ob2 = Observable.create(emitter -> {Thread.sleep(3000);emitter.onNext(2);emitter.onComplete();});
        Observable<Integer> ob3 = Observable.create(emitter -> {emitter.onNext(3);emitter.onComplete();});
        Observable.concat(ob1,ob2,ob3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("====================onSubscribe=====================");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("====================onNext:"+integer+"=====================");
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

        System.out.println("Hello RxJava");
    }
}
