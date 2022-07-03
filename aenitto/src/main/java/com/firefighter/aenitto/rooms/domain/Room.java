package com.firefighter.aenitto.rooms.domain;

import com.firefighter.aenitto.common.baseEntities.CreationModificationLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends CreationModificationLog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Long id;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<MemberRoom> memberRooms = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Relation> relations = new ArrayList<>();

    private String title;
    private int capacity;

    private String invitation;

    @Enumerated(value = EnumType.STRING)
    private RoomState state = RoomState.PRE;

    @ColumnDefault(value = "false")
    private boolean deleted;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder
    public Room(String title, int capacity, String invitation, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.capacity = capacity;
        this.invitation = invitation;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
