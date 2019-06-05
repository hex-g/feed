package hive.tamagotchi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_leaf_comment")
public class LeafComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "leaf_comment")
  private String leafComment;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateTime;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "comment_id")
  @JsonIgnore
  private Comment comment;

  public LeafComment() {
  }

  public LeafComment(
      final Integer userId,
      final String leafComment,
      final Date dateTime,
      final Comment comment
  ) {
    this.userId = userId;
    this.leafComment = leafComment;
    this.dateTime = dateTime;
    this.comment = comment;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  public String getLeafComment() {
    return leafComment;
  }

  public void setLeafComment(final String leafComment) {
    this.leafComment = leafComment;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(final Date dateTime) {
    this.dateTime = dateTime;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(final Comment comment) {
    this.comment = comment;
  }
}
