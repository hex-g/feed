package hive.tamagotchi.controller;

import hive.tamagotchi.entity.Comment;
import hive.tamagotchi.entity.LeafComment;
import hive.tamagotchi.repository.CommentRepository;
import hive.tamagotchi.repository.LeafCommentRepository;
import hive.tamagotchi.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static hive.pandora.constant.HiveInternalHeaders.AUTHENTICATED_USER_ID;

@RestController
@RequestMapping("/leaf-comment")
public class LeafCommentController {
  final private CommentRepository commentRepository;
  final private LeafCommentRepository leafCommentRepository;

  @Autowired
  public LeafCommentController(
      final CommentRepository commentRepository,
      final LeafCommentRepository leafCommentRepository
  ) {
    this.commentRepository = commentRepository;
    this.leafCommentRepository = leafCommentRepository;
  }

  @PostMapping
  public LeafComment save(
      @RequestHeader(name = AUTHENTICATED_USER_ID) final String userId,
      @RequestParam final Integer commentId,
      @RequestParam final String message
  ) {
    var comment = commentRepository.getOne(commentId);

    var leafComment = new LeafComment(Integer.parseInt(userId), message, new Date(), comment);

    return leafCommentRepository.save(leafComment);
  }
}
