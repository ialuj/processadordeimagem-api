package br.com.bixtecnologia.processadordeimagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingRequest;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface ImageProcessingRequestRepository extends JpaRepository<ImageProcessingRequest, Long> {

}
