package com.managerbcs.bcsproject_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.managerbcs.bcsproject_backend.entity.User;
import com.managerbcs.bcsproject_backend.entity.ClassEntity;
import com.managerbcs.bcsproject_backend.entity.Account;
import com.managerbcs.bcsproject_backend.entity.Assignment;
import com.managerbcs.bcsproject_backend.entity.AssignmentSubmission;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expose ID cho tất cả entity để frontend dễ truy cập
        config.exposeIdsFor(
                entityManager.getMetamodel().getEntities()
                        .stream()
                        .map(Type::getJavaType)
                        .toArray(Class[]::new)
        );

        // Chặn tất cả các phương thức ghi đối với ClassEntity (lớp học) → Chỉ được phép GET
        HttpMethod[] readOnlyMethods = {
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.PATCH,
                HttpMethod.DELETE
        };
        disableHttpMethods(ClassEntity.class, config, readOnlyMethods);

        // Chỉ chặn xoá người dùng (không cho phép DELETE)
        HttpMethod[] onlyBlockDelete = { HttpMethod.DELETE };
        disableHttpMethods(User.class, config, onlyBlockDelete);

        // Chặn chỉnh sửa và xoá AssignmentSubmission (nộp bài)
        HttpMethod[] noEditOrDelete = { HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE };
        disableHttpMethods(AssignmentSubmission.class, config, noEditOrDelete);

        // Nếu cần bảo vệ thêm entity nào, có thể thêm vào dưới đây
        // disableHttpMethods(Role.class, config, readOnlyMethods);
    }

    private void disableHttpMethods(Class<?> domainType, RepositoryRestConfiguration config, HttpMethod[] methods) {
        config.getExposureConfiguration()
                .forDomainType(domainType)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(methods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(methods));
    }
}
