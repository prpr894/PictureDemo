package com.prpr894.picturedemo.beans.mainpage;

import com.github.florent37.retrojsoup.annotations.JsoupHref;
import com.github.florent37.retrojsoup.annotations.JsoupSrc;
import com.github.florent37.retrojsoup.annotations.JsoupText;

/**
 * Created by florentchampigny on 28/02/2017.
 */

public class MainPageList {

    @JsoupText(".caption a")
    public String title;

    @JsoupHref("a:eq(0)")
    public String href;

    @JsoupSrc("a img")
    public String img;

    @JsoupText(".caption p span")
    public String category;

    public MainPageList() {
    }

    @Override
    public String toString() {
        return "title: " + title + "\n" +
                "href: " + href + "\n" +
                "category: " + category + "\n" +
                "img: " + img + "\n";
    }
}
