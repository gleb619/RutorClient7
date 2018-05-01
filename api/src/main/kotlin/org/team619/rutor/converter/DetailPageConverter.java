package org.team619.rutor.converter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.team619.rutor.core.Logger;
import org.team619.rutor.core.Converter;
import org.team619.rutor.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by BORIS on 07.08.2016.
 */
public class DetailPageConverter extends DefaultConverter implements Converter<DetailPage, Document> {

    private final String TAG = DetailPageConverter.class.getName() + ":";

    private final CardConverter cardConverter;
    private final CommentConverter commentConverter;

    public DetailPageConverter(CardConverter cardConverter, CommentConverter commentConverter, Logger logger) {
        super(logger);
        this.cardConverter = cardConverter;
        this.commentConverter = commentConverter;
    }

    private String parseIdentifier(Document document) {
        String id = "";
        if (document.location() != null && document.location().length() > 0) {
            id = document.location();
        }

        if (document.title() != null && document.title().length() > 0) {
            id = id + document.title().hashCode() + "";
        } else {
            id = "";
        }

        return parseFileName(id);
    }

    private Element parseElement(Element element) {
        return Optional.ofNullable(element).orElse(BlankElement.newOne());
    }

    private Elements parseElement(Elements element) {
        return Optional.ofNullable(element).orElse(BlankElements.newList());
    }

    /* =================== */

    private String parseFileName(String location) {
        return new StringBuilder()
                .append(location.replaceAll("[^A-z0-9]+", "")
                        .replaceAll("https|http", "")
                )
                .append(Selectors.EXTENSION)
                .toString();
    }

    @Override
    public DetailPage convert(Document input) {
        String fileName = parseIdentifier(input);
        DetailPage output = parseContent(input, createLink(fileName));

        return output;
    }

    private String createLink(String fileName) {
        return new StringBuilder(Selectors.PROTOCOL_HTTP)
                .append("localhost")
                .append(Selectors.COLON)
                .append("8080")
                .append(Selectors.BACKSLASH)
                .append(fileName)
                .toString();
    }

    private DetailPage parseContent(Document document, String link) {
        Element htmlBody = document.body();
        String name = htmlBody.select(Selectors.HEADER).text();
        Element mainContent = htmlBody.select(Selectors.CONTENT).first();

        Element mainLink = parseElement(htmlBody.select(Selectors.TOPIC_ID).first());
        String id = Integer.toString(parseId(mainLink.attr("href").toString()));
        String selfLink = mainLink.attr("href");
        Element table = mainContent.select(Selectors.MAIN_TABLE).first();

        //999937765 = Залил
        if (table.getElementsByTag("tr").get(1).getElementsByTag("td").get(0).text().hashCode() != 999937765) {
            table.select("tr:nth-child(1)").after("" +
                    "<tr>" +
                    "<td class='header'>Залил</td>" +
                    "<td><b><a href='/browse/0/0/0/0' target='_blank'>Unknown</a></b></td>" +
                    "</tr>");
        }

        String topicSpecification = parseElement(table.select(Selectors.TOPIC_DETAILS).first()).toString();
        Element boundedContentElement = parseElement(table.select(Selectors.TOPIC_BOUNDED).first());
        Elements commentsContentElement = parseElement(mainContent.select(Selectors.TOPIC_COMMENTS));

        parseElement(table.select(Selectors.TOPIC_FILE).first()).remove();
        parseElement(table.select(Selectors.TOPIC_BOUNDED).first()).remove();
        parseElement(table.getElementsByTag(Selectors.TR).first()).remove();

        String distributionDetails = parseElement(table.getElementsByTag(Selectors.TR)).toString();
        List<Row> boundedContent = cardConverter.convert(boundedContentElement.select(Selectors.TOPIC_BOUNDED_DETAILS));
        List<Comment> commentsContent = commentConverter.convert(commentsContentElement);

        topicSpecification = topicSpecification.replace("hidehead", "hidehead plus");
//        commentsContent = commentsContent.replace("hidehead", "hidehead plus");

        return DetailPage.builder()
                .id(id)
                .name(name)
                .fileName(link)
                .link(selfLink)
                .body(topicSpecification)
                .details(distributionDetails)
                .bounded(boundedContent)
                .comments(commentsContent)
                .build();
    }

    public interface Selectors extends Serializable {

        String HEADER = "#all > h1";
        String CONTENT = "#content";
        String MAIN_TABLE = "#details > tbody";
        String TR = "tr";
        String TOPIC_ID = "#download > a:nth-child(2)";
        String TOPIC_DETAILS = "tr:nth-child(1) > td:nth-child(2)";
        String TOPIC_BOUNDED = "tr:nth-child(11)";
        String TOPIC_BOUNDED_DETAILS = "#index > fieldset > table > tbody > tr";
        String TOPIC_FILE = "tr:nth-child(12)";
        String TOPIC_COMMENTS = "#content > table:nth-child(16) > tbody > tr";

        String COLON = ":";
        String BACKSLASH = File.separator;
        String PROTOCOL_HTTP = "http://";
        String EXTENSION = ".html";

    }

}
