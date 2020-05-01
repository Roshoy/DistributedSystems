package sr.grpc.server;

import sr.proto.ArticleType;
import sr.proto.NewsResponse;
import java.time.LocalDateTime;
import java.util.Random;

public class NewsGenerator {
    static final private String[] authors = {"Marry Jane", "Elane Drake", "9kier", "Jan Kowalski"};
    static final private String[] tags = {"football", "basketball", "america", "biology", "movie", "kpop"};
    static final private String content = "Lorem Ipsum";
    static final private String title = "Title";
    static final Random randomGen = new Random();

    /*
    Random news generator
     */
    static public NewsResponse getNewNews(){
        return getNewNews(tags[randomGen.nextInt(tags.length)],
                          ArticleType.values()[randomGen.nextInt(ArticleType.values().length - 1)]);

    }

    static public NewsResponse getNewNews(String tag, ArticleType type){
        LocalDateTime now = LocalDateTime.now();

        NewsResponse.Date date = NewsResponse.Date.newBuilder().setDay(now.getDayOfMonth())
                .setMonth(now.getMonthValue())
                .setYear(now.getYear()).build();
        NewsResponse.Builder response =  NewsResponse.newBuilder().setAuthor(authors[randomGen.nextInt(authors.length)])
                .setContent(content)
                .setDate(date)
                .setType(type)
                .setTitle(title);
        for(int i = 0; i<3; i++){
            response.addTags(tags[randomGen.nextInt(tags.length)]);
        }
        response.addTags(tag);
        return response.build();
    }

}
