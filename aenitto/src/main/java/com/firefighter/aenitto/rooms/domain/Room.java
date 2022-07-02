package com.firefighter.aenitto.rooms.domain;

import com.firefighter.aenitto.common.baseEntities.CreationModificationLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends CreationModificationLog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Long id;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<MemberRoom> memberRooms = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Relation> relations = new ArrayList<>();

    private int capacity;

    private String invitation;

    @Enumerated(value = EnumType.STRING)
    private RoomState state;

    @ColumnDefault(value = "false")
    private boolean deleted;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder
    public Room(int capacity, String invitation, RoomState state, String startDate, String endDate) {
        this.capacity = capacity;
        this.invitation = invitation;
        this.state = state;
        this.startDate = stringToLocalDate(startDate);
        this.endDate = stringToLocalDate(endDate);
    }

    private String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
