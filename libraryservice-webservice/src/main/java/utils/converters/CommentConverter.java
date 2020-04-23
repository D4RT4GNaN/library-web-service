package utils.converters;

import org.openclassroom.projet.model.database.service.Comment;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class CommentConverter extends AbstractConverter {

    public static generated.libraryservice.Comment fromDatabase(Comment comment) {
        generated.libraryservice.Comment generatedComment = new generated.libraryservice.Comment();

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

    public static List<generated.libraryservice.Comment> fromDatabase(List<Comment> comments) {
        List<generated.libraryservice.Comment> generatedComments = new ArrayList<>();

        for (Comment comment : comments) {
            generatedComments.add(fromDatabase(comment));
        }

        return generatedComments;
    }

}
