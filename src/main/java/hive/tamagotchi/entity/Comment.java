package hive.tamagotchi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "comment")
  private String comment;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateTime;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "publication_id")
  private Publication publication;

  public Comment() {
  }

  public Comment(
      final Integer userId,
      final String comment,
      final Date dateTime,
      final Publication publication
  ) {
    this.userId = userId;
    this.comment = comment;
    this.dateTime = dateTime;
    this.publication = publication;
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

  public String getComment() {
    return comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(final Date dateTime) {
    this.dateTime = dateTime;
  }

  public Publication getPublication() {
    return publication;
  }

  public void setPublication(final Publication publication) {
    this.publication = publication;
  }
}
