package org.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.exceptions.NotFoundException;
import org.webApp.model.Paper;
import org.webApp.repos.PaperRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaperService {
    private final PaperRepo paperRepo;

    @Autowired
    public PaperService(PaperRepo paperRepo) {
        this.paperRepo = paperRepo;
    }
    @Transactional
    public Paper addPaper(Paper paper) {
        return paperRepo.save(paper);
    }

    @Transactional
    public List<Paper> findAllPapers() {
        return paperRepo.findAll();
    }

    @Transactional
    public Paper updatePaper(Paper paper) {
        return paperRepo.save(paper);
    }
    @Transactional
    public void deletePaper(Long id) {
        paperRepo.deletePaperById(id);
    }

    @Transactional
    public Paper findPaperById(Long id) {
        return paperRepo.findPaperById(id).orElseThrow(() -> new NotFoundException("Такой статьи не существует"));
    }

    @Transactional
    public void deleteAllPapersByViewerNickName(String nickName) {
        paperRepo.deleteAllPapersByViewerNickName(nickName);
    }
}
