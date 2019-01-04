package com.prpr894.picturedemo.retrojsoupinterfaces;

import com.github.florent37.retrojsoup.annotations.Select;
import com.prpr894.picturedemo.beans.mainpage.MainPageList;

import io.reactivex.Observable;

/**
 * Created by florentchampigny on 01/03/2017.
 */

public interface AndroidArsenal {

    @Select(".col-xs-6 .thumbnail")
    Observable<MainPageList> projects();

}
