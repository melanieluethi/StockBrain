package com.example.stockbrain.model.businessobject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;@Table(name="DailyPrice")
public class DailyPrice extends Model {

    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;

    @Column(name = "TickerSymbol", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private String tickerSymbol;
    @Column(name = "ClosingPrice")
    private Double closingPrice; // aktueller Kurs
    @Column(name = "Volume")
    private Integer volume; // Gehandeltes Volumen an Aktien


    public DailyPrice(){
        super();

    }
    public DailyPrice(Double closingPrice, Integer volume){
        super();
        this.closingPrice = closingPrice;
        this.volume = volume;

    }

    public long getRemoteId() {
        return remoteId;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * To access that primary key Id, you can call getId() on an instance of your model.
     * @return
     */
    @Override
    public String toString() {
        return this.getTickerSymbol() + " [ID:" + this.getRemoteId() + "]";
    }

    public JSONObject toJson(){
        try {
            JSONObject json = new JSONObject();
            json.put("id", remoteId);
            json.put("TickerSymbol", tickerSymbol);
            json.put("Closing Price", closingPrice);
            json.put("Volume", volume);



            return json;
        } catch (JSONException e) {
            return null;
        }
    }

}
