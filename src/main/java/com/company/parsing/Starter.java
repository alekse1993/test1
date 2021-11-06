package com.company.parsing;

import java.io.IOException;
import org.jsoup.nodes.Document;

public class Starter {
    public String path =  "https://www.wildberries.ru/catalog/43147631/detail.aspx";

    public void start() throws IOException {
        Parser parser = new Parser();
        Document doc2 = parser.getHtmlDoc(43147631);
        ProductDto product = new ProductDto();
//        Document doc = PhantomJsUtils.renderPage(this.path);
//        PhantomJsUtils.savePage(doc);
//        parser.getPhotoLinks(doc);
//        parser.getSoldCount(doc);
//         PhantomJsUtils.savePage(doc2);
//        List<String> photoLinks = new ArrayList<>();
//        parser.getPhotoLinks();
    }
}
