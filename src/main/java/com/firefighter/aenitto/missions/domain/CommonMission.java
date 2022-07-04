package com.firefighter.aenitto.missions.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonMission {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "common_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private LocalDate date;

    @Builder
    public CommonMission(LocalDate date) {
        this.date = date;
    }
}
