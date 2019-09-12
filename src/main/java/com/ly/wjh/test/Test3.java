package com.ly.wjh.test;

import com.ly.wjh.test.bean.Person;
import com.ly.wjh.test.bean.Plan;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args){

        List<Person> personList = new ArrayList<>();
        List<Plan> planList = new ArrayList<>();
        List<String> actionsList = new ArrayList<>();
        Person person1 = new Person("cxk",planList);
        Person person2 = new Person("cxk",planList);
        Plan plan1 = new Plan("2019年9月10日16:46:18","出道");
        actionsList.add("唱");
        actionsList.add("跳");
        actionsList.add("rap");
        actionsList.add("打篮球");
        plan1.setActionList(actionsList);
        planList.add(plan1);
        person1.setPlanList(planList);
        person2.setPlanList(planList);
        personList.add(person1);
        personList.add(person2);


        //将 Person 集合中的每个元素中的 Plan 的 action 打印出来
        Observable.fromIterable(personList)
                .flatMap((Function<Person, ObservableSource<Plan>>) person -> Observable.fromIterable(person.getPlanList()))
                .flatMap((Function<Plan, ObservableSource<String>>) plan -> Observable.fromIterable(plan.getActionList()))
                .filter(s -> s.length() > 1)
                .distinct()
                .subscribe(System.out::println);

        System.out.println("Hello RxJava");
    }
}
