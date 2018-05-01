package org.team619.rutor.converter;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.team619.rutor.core.Logger;
import org.team619.rutor.core.Converter;
import org.team619.rutor.model.Comment;
import org.team619.rutor.util.AppUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by BORIS on 27.08.2016.
 */
public class CommentConverter extends DefaultConverter implements Converter<List<Comment>, Elements> {

    public CommentConverter(Logger logger) {
        super(logger);
    }

    @Override
    public List<Comment> convert(Elements rawComments) {
        List<List<Element>> parts = AppUtil.batch(rawComments, 3);
        List<Comment> comments = parseComments(parts);

        return comments;
    }

    /* =================== */

    private List<Comment> parseComments(List<List<Element>> comments) {
        return comments.stream()
                .map(comment -> {
                    int index = 0;
                    Element head = comment.get(index++);
                    Element body = comment.get(index++);
                    Elements tds = head.getElementsByTag("td");
                    index = 0;
                    String userName = tds.get(index++).text();
                    String creationDate = tds.get(index++).text();
                    String mark = parseInteger(tds.get(index++).toString()).toString();
                    String commentId = tds.get(index++).getElementsByTag("span").first().attr("id");
                    String text = body.getElementsByTag("td").html().toString();

                    return new Comment(commentId, userName, creationDate, mark, text);
                })
                .collect(Collectors.toList());
    }

}
