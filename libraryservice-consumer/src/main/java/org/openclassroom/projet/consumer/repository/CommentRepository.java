package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.service.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for the {@link Comment} object.
 * */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Allows you to find a {@link Comment comment} from the reference of book associated.
     * */
    List<Comment> findByBookReference(String bookReference);

}
