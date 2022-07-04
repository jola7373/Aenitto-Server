package com.firefighter.aenitto.members.domain;

import com.firefighter.aenitto.common.baseEntities.CreationModificationLog;
import com.firefighter.aenitto.rooms.domain.MemberRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends CreationModificationLog {
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id", columnDefinition = "BINARY(225)")
    private UUID id;

    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberRoom> memberRooms = new ArrayList<>();

    @Builder
    public Member(String nickname) {
        this.nickname = nickname;
    }
}