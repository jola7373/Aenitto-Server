package com.firefighter.aenitto.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IndividualMission {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "individual_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_room_id")
    private MemberRoom memberRoom;

    private LocalDate date;

    @ColumnDefault(value = "false")
    private boolean fulfilled;

    @Column
    private LocalDateTime fulfilledAt;

    @Builder
    public IndividualMission(LocalDate date) {
        this.date = date;
    }
}

