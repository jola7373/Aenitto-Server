package com.firefighter.aenitto.rooms.domain;

import com.firefighter.aenitto.members.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Relation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "relation_id")
    private Long id;

    /*
    Manitto: 챙겨주는 사람
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manitto_id")
    private Member manitto;

    /*
    Manittee : 챙김 받는 사람
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manittee_id")
    private Member manittee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
