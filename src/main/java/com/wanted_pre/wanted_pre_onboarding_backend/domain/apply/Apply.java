package com.wanted_pre.wanted_pre_onboarding_backend.domain.apply;

import com.wanted_pre.wanted_pre_onboarding_backend.domain.BaseTimeEntity;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment.Recruitment;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.user.User;
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
public class Apply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    @NotNull
    private Recruitment recruitment;

    public Apply(User user, Recruitment recruitment) {
        this.user = user;
        this.recruitment = recruitment;
    }
}
