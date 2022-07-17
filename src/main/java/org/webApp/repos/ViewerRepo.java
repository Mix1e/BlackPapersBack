package org.webApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webApp.model.Viewer;

import java.util.Optional;

@Repository
public interface ViewerRepo extends JpaRepository<Viewer, String> {

    Optional<Object> findViewerByNickName(String nickName);

    Boolean existsViewerByNickName(String nickName);

    void deleteByNickName(String nickName);

}
