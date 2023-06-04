package org.example.portim;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description ImportSelector 注入bean
 * @author ryan
 * @date 2023/5/9 14:14
 * @version 1.0
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回的 数组中的bean都会注册到 IOC 容器中
        return new String[]{ImportBeanB.class.getName()};
    }
}
