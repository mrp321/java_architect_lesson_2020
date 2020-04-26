package cn.sitedev.demo.annotation.configure.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WinCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        Environment environment = context.getEnvironment();

        String os = environment.getProperty("os.name");
        if (os.contains("Windows")) {
            return true;
        }
        return false;
    }
}
