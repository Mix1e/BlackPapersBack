package org.webApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webApp.models.VnLControl;

import java.util.Optional;

@Repository
public interface VnLControlRepo extends JpaRepository<VnLControl, Long> {

    Boolean existsVnLControlByPaperIdAndViewerNickName(Long id, String nickname);

    Optional<Object> findVnLControlByPaperIdAndViewerNickName(Long id, String nickName);

    void deleteAllVnLControlsByViewerNickName(String nickName);

    void deleteAllVnLControlsByPaperId(Long id);
}
