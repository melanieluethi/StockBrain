package com.example.stockbrain.model.businessobject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Table(name="SecurityItem")
public class SecurityItem extends Model {
    @Column(name = "TickerSymbol", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private String tickerSymbol;
    @Column(name = "Name")
    private String name;
    @Column(name = "Logo")
    private String urlLogo;

    public SecurityItem() {
        super();

    }
    public SecurityItem(String tickerSymbol, String name, String urlLogo) {
        super();
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    /**
     * To access that primary key Id, you can call getId() on an instance of your model.
     * @return
     */
    @Override
    public String toString() {
        return this.getName() + " [ID:" + this.getId() +"," +  this.getName()  + "]";
    }

    public JSONObject toJson(){
        try {
            JSONObject json = new JSONObject();
            json.put("id", getId());
            json.put("TickerSymbol", tickerSymbol);
            json.put("Name", name);
            json.put("Logo", urlLogo);

            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}