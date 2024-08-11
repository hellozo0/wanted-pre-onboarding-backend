package com.wanted_pre.wanted_pre_onboarding_backend.repository;

import com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    List<Recruitment> findByEnterpriseNameContainingIgnoreCase(String enterpriseName);
    List<Recruitment> findBySkillContainingIgnoreCase(String enterpriseName);

    @Query("SELECT r.id FROM Recruitment r WHERE r.enterprise.id = :enterpriseId AND r.id <> :recruitmentId")
    List<Long> findRecruitmentIdsByEnterpriseId(Long enterpriseId, Long recruitmentId);
}
