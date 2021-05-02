package com.example.stockbrain.model.businessobject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Table(name = " Fundamental Data")
public class FundamentalData extends Model {

    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;
    @Column(name = "Ticker Symbol", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private String FK_tickerSymbol;
    @Column(name = "Revenue")
    private Double revenue;
    @Column(name = "Profit")
    private Double profit;
    @Column(name = "Assets")
    private Double assets;
    @Column(name = "Liabilities")
    private Double liabilities;

    public FundamentalData() {
        super();
    }

    public FundamentalData(Double revenue, Double profit, Double assets, Double liabilities) {
        super();
        this.revenue = revenue;
        this.profit = profit;
        this.assets = assets;
        this.liabilities = liabilities;
    }

    public long getRemoteId() {
        return remoteId;
    }

    public String getFK_tickerSymbol() {
        return FK_tickerSymbol;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
    }

    public void setFK_tickerSymbol(String FK_tickerSymbol) {
        this.FK_tickerSymbol = FK_tickerSymbol;
    }

    public Double getRevenue() {
        return revenue;
    }

    public Double getProfit() {
        return profit;
    }

    public Double getAssets() {
        return assets;
    }

    public Double getLiabilities() {
        return liabilities;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public void setLiabilities(Double liabilities) {
        this.liabilities = liabilities;
    }

    /**
     * To access that primary key Id, you can call getId() on an instance of your model.
     * @return
     */
    @Override
    public String toString() {
        return this.getFK_tickerSymbol() + " [ID:" + this.getRemoteId() + "]";
    }

    public JSONObject toJson(){
        try {
            JSONObject json = new JSONObject();
            json.put("id", remoteId);
            json.put("TickerSymbol", FK_tickerSymbol);
            json.put("Revenue", revenue);
            json.put("Profit", profit);
            json.put("Assets", assets);
            json.put("Liabilities", liabilities);


            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
