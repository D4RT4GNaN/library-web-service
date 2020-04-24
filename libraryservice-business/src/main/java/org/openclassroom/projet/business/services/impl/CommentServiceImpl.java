package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.CommentService;
import org.openclassroom.projet.model.database.service.Comment;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractService implements CommentService {

    @Override
    public List<Comment> getCommentsFor(String bookReference) {
        return getDaoFactory().getCommentRepository().findByBookReference(bookReference);
    }

    @Override
    public void addComment(Comment comment) {
        String email = comment.getUsager().getEmail();
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(email);
        comment.setUsager(usager);
        getDaoFactory().getCommentRepository().save(comment);
    }

}
