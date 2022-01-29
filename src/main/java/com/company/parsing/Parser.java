package com.company.parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {


    public Parser() throws IOException {
    }

    public Document getHtmlDoc(Integer id) throws IOException {
//        WebClient webClient = new WebClient();
//        HtmlPage myPage = webClient.getPage(new File("page.html").toURI().toURL());
//
//        // convert page to generated HTML and convert to document
//        doc = Jsoup.parse(myPage.asXml());
//        "https://www.wildberries.ru/catalog/15875685/detail.aspx"
        Document doc = Jsoup.connect("https://www.wildberries.ru/catalog/"+id+"/detail.aspx").get();
        return doc;
//        System.out.println(doc);
    }

    public List<String> getPhotoLinks(Document document) throws IOException {
        List<String> photoLinks = new ArrayList<>();
    try{
        Elements elements = document.getElementsByClass("slide__content");
        for (Element item : elements){
            Elements photoUrls = item.getElementsByTag("img");
            if(photoUrls.isEmpty());
            else{
                String photoUrl = photoUrls.get(0).attr("src");
                photoLinks.add(photoUrl);
            }
        }

    }
    catch(Exception e ){
        System.out.println(e.getMessage());
    }
        return photoLinks;
    }
    public String getVendorCode(Document document){
        Element element = document.getElementById("productNmId");
        return element.text();
    }
    public void getSoldCount(Document document){
        Pattern pattern = Pattern.compile("'(ssrModel:{1})( .*)}'");
        Matcher matcher = pattern.matcher(document.toString());
//        if (matcher.find())
//                    {
                        System.out.println(matcher.group(1));
//                    }

//        Elements elements = document.getElementsByClass("same-part-kt__order-quantity");
//        String str = elements.get(0).text();
//        System.out.println(str);
//        Pattern pattern = Pattern.compile("'(.*?)'");
//        Matcher matcher = pattern.matcher(mydata);
//        if (matcher.find())
//        {
//            System.out.println(matcher.group(1));
//        }
    }
//    public List<String> getBrandNameAndGoodsName(){
//        List<String> Names = new ArrayList<>();
//        Elements elements = this.doc.getElementsByClass("slide__content");
//        for (Element item : elements){
//            String photoUrl = item.getElementsByTag("img").get(0).attr("src");;
//            photoLinks.add(photoUrl);
//        }
//        return photoLinks;
//    }
//    same-part-kt__header
}
