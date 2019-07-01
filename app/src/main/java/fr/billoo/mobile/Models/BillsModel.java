package fr.billoo.mobile.Models;

public class BillsModel
{
    String title;
    String category;
    String cost;
    String date;

    public BillsModel()
    {

    }

    public BillsModel(String title,String category,String cost,String date)
    {
        this.title=title;
        this.category=category;
        this.cost=cost;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
