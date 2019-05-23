package hive.feed.controller;

import hive.feed.entity.Publication;
import hive.feed.exception.EntityNotFoundException;
import hive.feed.exception.NullValueException;
import hive.feed.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static hive.feed.util.ValidationText.isValid;
import static hive.pandora.constant.HiveInternalHeaders.AUTHENTICATED_USER_ID;

@RestController
@RequestMapping("/publication")
public class PublicationController {
  final private PublicationRepository publicationRepository;

  @Autowired
  public PublicationController(final PublicationRepository publicationRepository) {
    this.publicationRepository = publicationRepository;
  }

  @GetMapping
  public List<Publication> find(
      @RequestHeader(name = AUTHENTICATED_USER_ID) final String userId,
      @RequestParam(required = false) final String type,
      @RequestParam(required = false) final Date date
  ) {
    final var publication = new Publication(type, Integer.parseInt(userId), date, null);

    final var foundPublications = publicationRepository.findAll(Example.of(publication));

    if (foundPublications.isEmpty()) {
      throw new EntityNotFoundException();
    }

    return foundPublications;
  }

  @PostMapping
  public Publication save(
      @RequestHeader(name = AUTHENTICATED_USER_ID) final String userId,
      @RequestParam(required = false) final Integer publicationId,
      @RequestParam(required = false) final String type,
      @RequestParam(required = false) final String message
  ) {
    if (!isValid(type) || !isValid(userId) || Integer.parseInt(userId) == 0 || !isValid(message)) {
      throw new NullValueException();
    }

    final var publication = new Publication(type, Integer.parseInt(userId), new Date(), message);

    if (publicationId != null) {
      if (!publicationRepository.existsById(publicationId)) {
        throw new EntityNotFoundException();
      }
      publication.setId(publicationId);
    }

    return publicationRepository.save(publication);
  }

  @DeleteMapping
  public void delete(
      @RequestHeader(name = AUTHENTICATED_USER_ID) final String userId,
      @RequestParam final Integer publicationId
  ) {
    if (!publicationRepository.existsById(publicationId)) {
      throw new EntityNotFoundException();
    }

    publicationRepository.deleteById(publicationId);
  }
}
