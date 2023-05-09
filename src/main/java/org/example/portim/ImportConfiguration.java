package org.example.portim;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: ryan
 * @date 2023/5/9 14:07
 * @version: 1.0
 */
@Configuration
@Import(value = {ImportBeanA.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // 注入普通的bean
public class ImportConfiguration {
}
