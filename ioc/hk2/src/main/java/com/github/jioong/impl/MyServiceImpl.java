package com.github.jioong.impl;

import com.github.jioong.api.MyService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;

@Service @Singleton
public class MyServiceImpl implements MyService {
    public void sayHello() {
        System.out.println("Hello HH2...");
    }
}
