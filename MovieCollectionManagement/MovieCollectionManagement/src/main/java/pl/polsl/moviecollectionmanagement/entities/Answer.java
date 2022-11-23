package pl.polsl.moviecollectionmanagement.entities;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "answers")
public class Answer extends BaseEntity {

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @NotNull
    private Topic topic;
}
