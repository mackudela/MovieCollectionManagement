package pl.polsl.moviecollectionmanagement.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "answers")
public class Answer extends BaseEntity {

    @NotNull
    @Column(columnDefinition="TEXT", length=2048)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonBackReference("User-Answer")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @NotNull
    @JsonBackReference("Topic-Answer")
    private Topic topic;
}
