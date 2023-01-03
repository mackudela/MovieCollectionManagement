package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.dtos.TopicDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "topics")
public class Topic extends BaseEntity {

    @NotNull
    private String name;

    @Column(columnDefinition="TEXT", length=2048)
    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonBackReference("User-Topic")
    private User user;

    @JsonManagedReference("Topic-Answer")
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private Set<Answer> answers = new LinkedHashSet<>();
}
