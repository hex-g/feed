package hive.feed.repository;

import hive.feed.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

  List<Publication> findByUserId(Integer userId);

}
