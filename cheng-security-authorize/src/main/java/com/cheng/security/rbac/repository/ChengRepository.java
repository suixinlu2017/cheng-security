package com.cheng.security.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author cheng
 *         2018/9/27 15:24
 */
@NoRepositoryBean
public interface ChengRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
