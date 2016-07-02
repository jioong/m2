package com.github.jioong.impl;

import com.github.jioong.api.MyService;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.BuilderHelper;

import javax.inject.Singleton;

public class Populator {
    public static void populator(ServiceLocator serviceLocator) {
        DynamicConfigurationService dcs = serviceLocator.getService(DynamicConfigurationService.class);
        DynamicConfiguration config = dcs.createDynamicConfiguration();

        config.bind(BuilderHelper.link(MyServiceImpl.class)
                    .to(MyService.class)
                    .in(Singleton.class.getName())
                    .build());
        config.commit();
    }
}
