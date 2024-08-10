package com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment;

import com.wanted_pre.wanted_pre_onboarding_backend.domain.BaseTimeEntity;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.enterprise.Enterprise;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String position;

    @NotNull
    private int reward;

    @NotNull
    private String detail;

    @NotNull
    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    @NotNull
    private Enterprise enterprise;

    public Recruitment(int reward, String position, String detail, String skill, Enterprise enterprise) {
        this.enterprise = enterprise;
        this.position = position;
        this.reward = reward;
        this.detail = detail;
        this.skill = skill;
    }

    public void update(int reward, String position, String detail, String skill) {
        this.position = position;
        this.reward = reward;
        this.detail = detail;
        this.skill = skill;
    }
}
