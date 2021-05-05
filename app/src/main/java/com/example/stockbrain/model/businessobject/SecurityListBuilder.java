package com.example.stockbrain.model.businessobject;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class SecurityListBuilder {
    private String tickerSymbol;
    private String name;
    private Bitmap logo;

    public SecurityListBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }

    public SecurityListBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SecurityListBuilder withLogo(Bitmap logo){
        this.logo = logo;
        return this;
    }

    public SecurityItem buildSecurityItem(){
        SecurityItem securityItem = new SecurityItem(this.tickerSymbol, this.name, this.logo);
        return securityItem;
    }


        private String listName;
        private List<SecurityListItem> securityListItems;

        public SecurityListBuilder(){}

        public SecurityListBuilder withId(long id){
            return this;
        }

        public SecurityListBuilder withListName(String listName){
            this.listName = listName;
            return this;
        }

        public SecurityListBuilder withShoppingListItems(List<SecurityListItem> securityListItems){
            this.securityListItems = securityListItems;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public SecurityList buildSecurityList(){
            SecurityList securityList = new SecurityList();

            return  securityList;
        }


}
