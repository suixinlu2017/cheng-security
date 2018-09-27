package com.cheng.security.rbac.repository.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * JAP 实体类命名策略
 *
 * @author cheng
 *         2018/9/27 14:50
 */
public class ChengImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    private static final long serialVersionUID = -4016103213237842173L;

    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier("cheng_" + stringForm.toLowerCase(), buildingContext);
    }
}
