package com.wanted_pre.wanted_pre_onboarding_backend.repository;

import com.wanted_pre.wanted_pre_onboarding_backend.domain.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

}
