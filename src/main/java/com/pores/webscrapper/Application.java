package com.pores.webscrapper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.gargoylesoftware.htmlunit.BrowserVersion.CHROME;

public class Application {

    static int counter = 1;


    public static void extractValues(List<DomNode> list) {
        list.forEach(element -> element.getChildNodes()
                .stream()
                .filter(elem -> elem instanceof HtmlDivision)
                .filter(ele -> ((HtmlDivision) ele).getAttribute("class").equalsIgnoreCase("estore_product_container"))
                .forEach(div -> div.getChildNodes().stream()
                        .filter(divs -> divs instanceof HtmlDivision)
                        .filter(d -> ((HtmlDivision) d).getAttribute("class").equalsIgnoreCase("product_image"))
                        .forEach(pi -> pi.getChildNodes()
                                .stream()
                                .filter(i -> i instanceof HtmlDivision)
                                .filter(img -> ((HtmlDivision) img).getAttribute("class").equalsIgnoreCase("image"))
                                .forEach(innerImage -> innerImage.getChildNodes()
                                        .stream()
                                        .filter(a -> a.getAttributes().getLength() != 0)
                                        .forEach(b -> {
                                            String dataValue = b.getAttributes().getNamedItem("data-value").getNodeValue();
                                            if (dataValue.startsWith("{")) {
                                                JSONObject json = new JSONObject(dataValue);
                                                System.out.println("Reading HTML for " + json.get("name"));
                                                json.put("source", "boots");
                                                json.put("href", b.getAttributes().getNamedItem("href").getNodeValue());
                                                b.getChildNodes()
                                                        .stream()
                                                        .filter(imaes -> imaes instanceof HtmlImage)
                                                        .forEach(src -> {
                                                            src.getAttributes().getNamedItem("src").getNodeValue();
                                                            json.put("imageLocation", src.getAttributes().getNamedItem("src").getNodeValue());
                                                            json.put("srcSets", src.getAttributes().getNamedItem("srcset").getNodeValue());
                                                        });

                                                WebClient client = new WebClient(CHROME);
                                                client.getOptions().setCssEnabled(false);
                                                client.getOptions().setJavaScriptEnabled(false);
                                                try {
                                                    HtmlPage page = client.getPage(json.get("href").toString());
                                                    DomNodeList<DomElement> divs = page.getElementsByTagName("div");

                                                    divs.stream()
                                                            .filter(domElement -> domElement.getAttribute("id").equalsIgnoreCase("contentOmnipresent"))
                                                            .forEach(domElement -> json.put("description", domElement.getFirstElementChild()
                                                                    .asText()));


                                                    //ingredients
                                                    divs.stream()
                                                            .filter(domElement -> domElement.getAttribute("class").equalsIgnoreCase("product_long_description_subsection"))
                                                            .forEach(p -> p.getChildNodes()
                                                                    .stream()
                                                                    .filter(para -> para instanceof HtmlParagraph)
                                                                    .forEach(ing -> json.put("Ingredients", ing.asText())));


                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }


                                                //write to file
                                                writeToFile(json);
                                            }
                                        })))));

    }

    public static void writeToFile(JSONObject productJson) {
        try {
            //if file name contains / change it
            String fileName = productJson.get("name").toString().contains("/") ? productJson.getString("name").replace("/", "U+2215") : productJson.getString("name");
            File jsonFile = new File("src/main/resources/Product_json/" + fileName + ".json");
            if (jsonFile.createNewFile()) {
                System.out.println("Writing file: " + fileName);
                BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
                writer.write(productJson.toString(1));
                writer.close();
            } else {
                //overwrite file
//                BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
//                writer.write(productJson.toString());
//                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        WebClient client = new WebClient(CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        do {
            try {
                String url = "https://www.boots.com/beauty/skincare/skincare-all-skincare?pageNo=" + counter;
                HtmlPage page = client.getPage(url);
                System.out.println("Starting with " + url);
                long startTime = System.currentTimeMillis();
                DomNodeList<DomElement> divs = page.getElementsByTagName("div");
                divs.stream()
                        .filter(div -> div.getAttribute("class").equalsIgnoreCase("plp_gridView_redesign"))
                        .findFirst()
                        .ifPresent(d -> {
                            List<DomNode> unorderedList = d.getFirstElementChild().getChildNodes().stream().filter(ch ->
                                    (!(ch instanceof DomText))
                            ).collect(Collectors.toList());

                            try {
                                extractValues(unorderedList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                counter++;
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken: " + (endTime - startTime) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (counter < 91);
    }

}


//                counter++;

//            divs.stream()
//                    .filter(nextPageDiv -> nextPageDiv.getAttribute("class").equalsIgnoreCase("pageControl number"))
//                    .findFirst()
//                    .ifPresent(pageControl -> {
//                        pageControl.getChildNodes().stream()
//                                .filter(ul -> ul instanceof HtmlUnorderedList)
//                                .forEach(ull -> ull.getChildNodes()
//                                        .stream()
//                                        .filter(li -> li instanceof HtmlListItem)
//                                        .forEach(listItem -> {
//                                            listItem.getChildNodes().forEach(System.out::println);
//                                        }));
//                    });