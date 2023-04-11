package org.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webApp.security.ViewerDetailsImpl;
import org.webApp.model.Viewer;
import org.webApp.repos.ViewerRepo;

@Service
public class ViewerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ViewerRepo viewerRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Viewer viewer = (Viewer) viewerRepo.findViewerByNickName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким ником не существует:" + username.trim()));
        return ViewerDetailsImpl.build(viewer);
    }
}
