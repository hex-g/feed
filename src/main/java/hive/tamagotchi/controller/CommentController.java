package hive.tamagotchi.controller;

import hive.tamagotchi.entity.Comment;
import hive.tamagotchi.repository.CommentRepository;
import hive.tamagotchi.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/comment")
public class CommentController {
  private final String AUTHENTICATED_USER_ID = "1";
  private final CommentRepository commentRepository;
  private final PublicationRepository publicationRepository;

  @Autowired
  public CommentController(
      final CommentRepository commentRepository,
      final PublicationRepository publicationRepository
  ) {
    this.commentRepository = commentRepository;
    this.publicationRepository = publicationRepository;
  }

  @PostMapping
  public Comment save(
      @RequestHeader(name = AUTHENTICATED_USER_ID) final String userId,
      @RequestParam final Integer publicationId,
      @RequestParam final String message
  ) {
    var publication = publicationRepository.getOne(publicationId);

    var commentsList = publication.getComments();

    var comment = new Comment(Integer.parseInt(userId), message, new Date(), publication);

    commentsList.add(comment);

    publication.setComments(commentsList);

    publicationRepository.save(publication);

    return comment;
  }
}
