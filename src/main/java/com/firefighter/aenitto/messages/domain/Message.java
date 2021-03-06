package com.firefighter.aenitto.messages.domain;

import com.firefighter.aenitto.common.baseEntities.CreationLog;
import com.firefighter.aenitto.rooms.domain.Room;
import com.firefighter.aenitto.members.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends CreationLog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL)
    private Report report;

    private String content;

    @ColumnDefault(value = "false")
    private boolean read;

    @Column
    private String imgUrl;

    @Builder
    public Message(String content, String imgUrl) {
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public boolean didRead() {
        return this.read;
    }

    public void readMessage() {
        this.read = true;
    }

    public boolean reportExists() {
        return report != null;
    }
}
