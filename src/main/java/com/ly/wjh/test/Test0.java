package com.ly.wjh.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test0 {
    public static void main(String[] args){

        /**
         * Observable、Observer、subscribe
         * 被观察者、观察者、订阅
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            public void onSubscribe(Disposable d) {
                System.out.println("====================onSubscribe=====================");
            }

            public void onNext(Integer integer) {
                System.out.println("====================onNext:"+integer+"=====================");
            }

            public void onError(Throwable e) {
                System.out.println("====================onError=====================");
            }

            public void onComplete() {
                System.out.println("====================onComplete=====================");
            }
        };

        observable.subscribe(observer);

        System.out.println("Hello RxJava");
    }
}
