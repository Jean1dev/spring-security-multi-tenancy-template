package com.security.template.domain.tenant.repository;

import com.security.template.domain.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}
