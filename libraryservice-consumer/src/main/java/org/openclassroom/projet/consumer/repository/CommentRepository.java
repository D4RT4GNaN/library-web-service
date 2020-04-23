package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.service.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByBookReference(String bookReference);

}
