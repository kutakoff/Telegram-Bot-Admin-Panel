package max.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utils")
public class Utils {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "utilsIdSeq", sequenceName = "utils_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilsIdSeq")
    private Integer id;

    @Column(name = "bottoken")
    private String botToken;

    @Column(name = "password")
    private String password;

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return botToken + "/" + password;
    }
}
