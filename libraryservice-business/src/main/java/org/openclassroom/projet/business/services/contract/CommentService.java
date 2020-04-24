package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.service.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsFor(String bookReference);
    void addComment(Comment comment);

}
