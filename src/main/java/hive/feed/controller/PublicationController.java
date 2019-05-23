package hive.feed.controller;

import hive.feed.entity.Publication;
import hive.feed.exception.EntityNotFoundException;
import hive.feed.exception.NullValueException;
import hive.feed.repository.PublicationRepository;
import hive.feed.util.ValidationText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static hive.feed.util.ValidationText.*;

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
      @RequestParam(required = false) final String type,
      @RequestParam(required = false) final Integer userId,
      @RequestParam(required = false) final Date date
  ) {
    final var publication = new Publication(type,userId,date,null);

    final var foundPublications = publicationRepository.findAll(Example.of(publication));

    if (foundPublications.isEmpty()) {
      throw new EntityNotFoundException();
    }

    return foundPublications;
  }

  @PostMapping
  public Publication save(
      @RequestParam(required = false) Integer publicationId,
      @RequestParam final String type,
      @RequestParam final Integer userId,
      @RequestParam final String post
  ) {
    if(!isValid(type) || !isValid(userId.toString()) || !isValid(post)){
      throw new NullValueException();
    }

    final var publication = new Publication(type, userId, new Date(), post);

    if(publicationId != null){
      if(!publicationRepository.existsById(publicationId)){
        throw new EntityNotFoundException();
      }
      publication.setId(publicationId);
    }

    return publicationRepository.save(publication);
  }

  @DeleteMapping
  public void delete(@RequestParam final Integer id){
    if (!publicationRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    publicationRepository.deleteById(id);
  }
}
