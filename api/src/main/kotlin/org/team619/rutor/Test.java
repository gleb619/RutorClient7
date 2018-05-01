package org.team619.rutor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.team619.rutor.converter.*;
import org.team619.rutor.core.Logger;
import org.team619.rutor.model.DetailPage;
import org.team619.rutor.model.MainGroupedPage;
import org.team619.rutor.model.MainPlainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test{

    public static void main(String... args) throws IOException {
        System.out.println("Test.main");
        String plainPath = "K:\\WORKSPACE_FOR_PORTFOLIO\\RutorClient7\\api\\plain.html";
        String groupedPath = "K:\\WORKSPACE_FOR_PORTFOLIO\\RutorClient7\\api\\grouped.html";
        String detailPath = "K:\\WORKSPACE_FOR_PORTFOLIO\\RutorClient7\\api\\detail.html";
        String contentPlain = new String(Files.readAllBytes(Paths.get(plainPath)));
        String contentGrouped = new String(Files.readAllBytes(Paths.get(groupedPath)));
        String contentDetail = new String(Files.readAllBytes(Paths.get(detailPath)));
        Document docPlain = Jsoup.parse(contentPlain);
        Document docGrouped = Jsoup.parse(contentGrouped);
        Document docDetail = Jsoup.parse(contentDetail);

        Logger logger = new Logger() {
            @Override
            public void error(String s) {
                System.out.println("Test.error#1#s: " + s);
            }

            @Override
            public void error(String s, Exception e) {
                System.out.println("Test.error#2#s: " + s);
                e.printStackTrace();
            }
        };
        MainPagePlainConverter mainPagePlainConverter = new MainPagePlainConverter(logger);
        MainPageGroupedConverter mainPageGroupedConverter = new MainPageGroupedConverter(logger);
        CardConverter cardConverter = new CardConverter(logger);
        CommentConverter commentConverter = new CommentConverter(logger);
        DetailPageConverter detailPageConverter = new DetailPageConverter(cardConverter, commentConverter, logger);

        MainPlainPage mainPlainPage = mainPagePlainConverter.convert(docPlain);
        MainGroupedPage mainGroupedPage = mainPageGroupedConverter.convert(docGrouped);
        DetailPage detailPage = detailPageConverter.convert(docDetail);
        System.out.println("Test.main#mainPlainPage: " + mainPlainPage);
        System.out.println("Test.main#mainGroupedPage: " + mainGroupedPage);
        System.out.println("Test.main#detailPage: " + detailPage);
    }

}
