package org.webApp.repos;

import org.springframework.stereotype.Repository;
import org.webApp.models.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PaperRepo extends JpaRepository<Paper, Long> {

    void deletePaperById(Long id);

    List<Paper> findPapersByName(String name);

    Optional<Paper> findPaperById(Long id);

    void deleteAllPapersByViewerNickName(String nickName);
}
