package co.develhope.RelationshipsInSpring.repositories;

import co.develhope.RelationshipsInSpring.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
