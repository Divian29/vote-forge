package voteforge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voteforge.entities.model.VoterEntity;

import java.util.Optional;

public interface VoterEntityRepository extends JpaRepository<VoterEntity, Long> {
//    Optional<VoterEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
