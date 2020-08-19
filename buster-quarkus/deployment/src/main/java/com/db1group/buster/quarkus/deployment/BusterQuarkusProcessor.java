package com.db1group.buster.quarkus.deployment;

import com.db1group.buster.quarkus.command.CommandBusFactory;
import com.db1group.buster.quarkus.command.CommandHandlerFactoryQuarkusImpl;
import com.db1group.buster.quarkus.query.QueryBusFactory;
import com.db1group.buster.quarkus.query.QueryHandlerFactoryQuarkusImpl;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class BusterQuarkusProcessor {

    private static final String FEATURE = "buster-quarkus";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem registerBeans() {
        return AdditionalBeanBuildItem.builder()
                .addBeanClass(CommandHandlerFactoryQuarkusImpl.class)
                .addBeanClass(CommandBusFactory.class)
                .addBeanClass(QueryHandlerFactoryQuarkusImpl.class)
                .addBeanClass(QueryBusFactory.class)
                .build();
    }

}
