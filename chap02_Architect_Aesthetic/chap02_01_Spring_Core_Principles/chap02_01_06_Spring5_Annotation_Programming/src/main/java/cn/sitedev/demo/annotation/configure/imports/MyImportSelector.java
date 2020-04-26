package cn.sitedev.demo.annotation.configure.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.sitedev.project.entity.Company", "cn.sitedev.project.entity.Member"};
    }
}
