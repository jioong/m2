package com.github.jioong.impl;


import org.glassfish.hk2.api.HK2RuntimeException;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;


public class MyServiceImplTest {
    private ServiceLocator serviceLocator;

    @Inject
    private MyServiceImpl serv;
    @Before
    public void setUp() {

        //针对单个类或接口
        serviceLocator = ServiceLocatorFactory.getInstance().create("Test");
        Populator.populator(serviceLocator);
    }


    @Test
    public void testSayHello() {
        MyServiceImpl myService = serviceLocator.getService(MyServiceImpl.class);
        myService.sayHello();
    }

/*    @Test
    public void testAnnotation() {
        System.out.println("-------");
        serv.sayHello();
    }*/


}
