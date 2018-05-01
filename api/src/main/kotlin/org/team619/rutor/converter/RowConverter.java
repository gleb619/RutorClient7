package org.team619.rutor.converter;

import org.jsoup.nodes.Element;
import org.team619.rutor.core.Logger;
import org.team619.rutor.model.Row;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BORIS on 27.08.2016.
 */
public abstract class RowConverter extends DefaultConverter {

    public RowConverter(Logger logger) {
        super(logger);
    }

    protected Row parseRow(List<Element> torrentRow) {
        Row row = null;

        if (torrentRow.size() >= Constants.SIZE_STANDARD) {
            row = new Row();
            int torrentRowIndex = parseRowUrls(torrentRow, row);

            if (torrentRow.size() == Constants.SIZE_STANDARD) {
                row.setSize(parseString(torrentRow.get(torrentRowIndex++).text()));
            } else {
                if (torrentRow.size() >= Constants.SIZE_WITH_COMMENT) {
                    row.setComments(parseInteger(torrentRow.get(torrentRowIndex++).text()));
                    row.setSize(parseString(torrentRow.get(torrentRowIndex++).text()));
                }
            }

            List<Element> downloadInfoDetails = torrentRow.get(torrentRowIndex++).getElementsByTag(Constants.SPAN);
            if (downloadInfoDetails.size() >= Constants.SIZE_HAS_TOPICS) {
                int downloadInfoDetailsIndex = 0;
                row.setSeeds(parseInteger(downloadInfoDetails.get(downloadInfoDetailsIndex++).text()))
                        .setPeers(parseInteger(downloadInfoDetails.get(downloadInfoDetailsIndex++).text()));
            }
        }

        return row;
    }

    protected int parseRowUrls(List<Element> torrentRow, Row row) {
        int torrentRowIndex = 0;
        row.setCreationDate(torrentRow.get(torrentRowIndex++).text());
        List<Element> captionDetails = torrentRow.get(torrentRowIndex++).getElementsByTag(Constants.LINK);
        if (captionDetails.size() >= Constants.SIZE_CAPTION) {
            int captionDetailsIndex = 0;

            row.setDownloadUrl(captionDetails.get(captionDetailsIndex++).attr(Constants.HREF))
                    .setId(parseId(row.getDownloadUrl()))
                    .setMagnetUrl(captionDetails.get(captionDetailsIndex++).attr(Constants.HREF))
                    .setDetailUrl(captionDetails.get(captionDetailsIndex).attr(Constants.HREF))
                    .setFileName(parseTorrentName(captionDetails.get(captionDetailsIndex).attr(Constants.HREF)))
                    .setCaption(parseCaption(captionDetails.get(captionDetailsIndex++).text()));
        }

        return torrentRowIndex;
    }
    
    /* =================== */

    public interface Constants extends Serializable {

        int SIZE_STANDARD = 4;
        int SIZE_WITH_COMMENT = 5;
        int SIZE_HAS_TOPICS = 2;
        int SIZE_CAPTION = 3;

        String LINK = "a";
        String HREF = "href";
        String SPAN = "span";

    }

}
