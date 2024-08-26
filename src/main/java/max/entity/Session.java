package max.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "session", indexes = @Index(columnList = "CHAT_ID"))
public class Session {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "sessionIdSeq", sequenceName = "session_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.UUID, generator = "sessionIdSeq")
    private Integer id;

    @Column(name = "CHAT_ID")
    private String chatId;

    @Column(name = "DATE")
    private Timestamp date;

    public Session(String chatId, Timestamp date) {
        this.chatId = chatId;
        this.date = date;
    }

    public Session() {
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "/" + chatId + "/" + date;
    }
}