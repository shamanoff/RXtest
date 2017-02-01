package com.rxtest;

import com.google.common.base.Stopwatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;
import rx.schedulers.Schedulers;


@SpringBootApplication
public class RxtestApplication {

    private static void printOut(int value) {
        System.out.println(Thread.currentThread().getName() + "- print: " + value);
    }

    private static int doubleValue(int value) {
        try {        System.out.println(Thread.currentThread().getName() + "- double");

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 2;
    }

    public static void main(String[] args) {
        SpringApplication.run(RxtestApplication.class, args);
        Stopwatch stopwatch = Stopwatch.createStarted();

        Observable.range(1, 10)
                .flatMap(integer -> Observable.just(integer)
                .map(RxtestApplication::doubleValue)
                .subscribeOn(Schedulers.computation())

                )
                .toBlocking()
                .subscribe(RxtestApplication::printOut);

        stopwatch.stop();
        System.out.println(stopwatch);

    /*    String names[] = {"E", "C", "D"};
        System.out.println(Arrays.toString(names));

        List count = Arrays.stream(names)
                .filter(n -> {
                    System.out.println(n);
                    return n.equals("E");
                })
                .collect(toList());

        System.out.println(count.toString());*/

    }
}
