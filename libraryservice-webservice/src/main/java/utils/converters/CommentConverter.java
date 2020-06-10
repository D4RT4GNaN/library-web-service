package utils.converters;

import generated.libraryservice.GeneratedComment;
import org.openclassroom.projet.model.database.service.Comment;
import org.openclassroom.projet.model.database.usager.Usager;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Allows conversion between {@link GeneratedComment} and {@link Comment}.
 * */
public class CommentConverter extends AbstractConverter {

    /**
     * Converts a {@link Comment} object from the database to a {@link GeneratedComment} object.
     *
     * @param comment - {@link Comment} object from database.
     *
     * @return A {@link GeneratedComment} object for sending to the client via WSDL.
     * */
    public static GeneratedComment fromDatabase(Comment comment) {
        GeneratedComment generatedComment = new GeneratedComment();

        try {
            generatedComment.setAuthor(UsagerConverter.fromDatabase(comment.getUsager()));
            generatedComment.setTitle(comment.getTitle());
            generatedComment.setContent(comment.getContent());
            generatedComment.setDate(toXMLGregorianCalendar(comment.getDate()));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return generatedComment;
    }
    
    

    /**
     * Converts a list of {@link Comment} object from the database to a list of {@link GeneratedComment} object.
     *
     * @param comments - A list of {@link Comment} object from database.
     *
     * @return A list of {@link GeneratedComment} object for sending to the client via WSDL.
     * */
    public static List<GeneratedComment> fromDatabase(List<Comment> comments) {
        List<GeneratedComment> generatedComments = new ArrayList<>();

        for (Comment comment : comments) {
            generatedComments.add(fromDatabase(comment));
        }

        return generatedComments;
    }


    
    /**
     * Converts a {@link GeneratedComment} object from the client to a {@link Comment} object.
     *
     * @param generatedComment - {@link GeneratedComment} object from client.
     *
     * @return A {@link Comment} object saved in database.
     * */
    public static Comment fromClient(GeneratedComment generatedComment) {
        Comment comment = new Comment();

        comment.setDate(new Date());
        comment.setBookReference(generatedComment.getBookReference());
        comment.setTitle(generatedComment.getTitle());
        comment.setContent(generatedComment.getContent());
        comment.setUsager(new Usager(UsagerConverter.fromClient(generatedComment.getAuthor())));

        return comment;
    }

}
