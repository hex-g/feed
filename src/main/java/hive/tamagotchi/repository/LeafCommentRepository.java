package hive.tamagotchi.repository;

import hive.tamagotchi.entity.LeafComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafCommentRepository extends JpaRepository<LeafComment, Integer> {
}
