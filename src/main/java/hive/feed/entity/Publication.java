package hive.feed.entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_publication")
@DynamicUpdate
@Check(constraints = "type in ('NOTIFICATIONS', 'STATUS')")
public class Publication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "type")
  private String type;
  @Column(name = "user_id")
  private Integer userId;
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateTime;
  @Column(name = "post")
  private String post;

  public Publication() {
  }

  public Publication(
      final String type,
      final Integer user_id,
      final Date dateTime,
      final String post
  ) {
    this.type = type;
    this.userId = user_id;
    this.dateTime = dateTime;
    this.post = post;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(final Date dateTime) {
    this.dateTime = dateTime;
  }

  public String getPost() {
    return post;
  }

  public void setPost(final String post) {
    this.post = post;
  }
}
