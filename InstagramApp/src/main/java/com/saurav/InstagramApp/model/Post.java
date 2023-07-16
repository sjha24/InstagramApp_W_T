package com.saurav.InstagramApp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    @NotEmpty
    private String postData;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_ID")
    User user;

}
