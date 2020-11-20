package com.pores.webscrapper.scrape.sources.boots;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URLEncoder;

public class bootsScrape {



    public void getBootsMoisturisers() {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            HtmlPage page = client.getPage("https://www.boots.com/beauty/skincare/skincare-all-skincare");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
