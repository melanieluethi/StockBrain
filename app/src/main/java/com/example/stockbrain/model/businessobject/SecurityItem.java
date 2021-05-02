package com.example.stockbrain.model.businessobject;

import android.media.Image;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;


@Table(name="Securities")
public class SecurityItem extends Model {

    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;

    @Column(name = "Ticker Symbol", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private String tickerSymbol;
    @Column(name = "Name")
    private String name;
    @Column(name = "Logo")
    private Image logo;

    public SecurityItem() {
        super();

    }
    public SecurityItem(String tickerSymbol, String name, Image logo) {
        super();
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.logo = logo;
    }


    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
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
            json.put("Logo", logo);

            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}