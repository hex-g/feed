package hive.tamagotchi.controller;

import hive.tamagotchi.entity.Comment;
import hive.tamagotchi.repository.CommentRepository;
import hive.tamagotchi.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static hive.pandora.constant.HiveInternalHeaders.AUTHENTICATED_USER_ID;

@RestController
@RequestMapping("/comment")
public class CommentController {
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

    var comment = new Comment(Integer.parseInt(userId), message, new Date(), publication);

    return commentRepository.save(comment);
  }
}
