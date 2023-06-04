package org.example.portim;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description ImportBeanDefinitionRegistrar 注册 bean
 * @author ryan
 * @date 2023/5/9 14:23
 * @version 1.0
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String beanName = ImportBeanC.class.getName();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ImportBeanC.class);
        builder.addPropertyValue("username", "ryan");
        builder.addPropertyValue("sex", "male");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
