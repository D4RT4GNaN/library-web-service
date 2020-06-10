package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.service.Comment;

import java.util.List;

/**
 * Business module interface for the {@link Comment} object
 * */
public interface CommentService {

    /**
     * Retrieves the list of comments associated with a book.
     *
     * @param bookReference - The reference of the {@link org.openclassroom.projet.model.database.library.Book book} from which we want to retrieve the {@link Comment comments}.
     *
     * @return The list of {@link Comment}
     * */
    List<Comment> getCommentsFor(String bookReference);



    /**
     * Add a comment to a book.
     *
     * @param comment - The comment to be added.
     *
     * @return Comment added.
     * */
    Comment addComment(Comment comment);

}
