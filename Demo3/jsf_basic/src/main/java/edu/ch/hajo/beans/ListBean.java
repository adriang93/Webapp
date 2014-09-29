package edu.ch.hajo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * Supply list data to some page
 * @author hajo 
 */
@Named("list")
@SessionScoped
public class ListBean implements Serializable {

    private String selected;
    
    private List<SelectItem> dataList = new ArrayList<>();
    
    @PostConstruct
    public void postContruct(){
        // In real life get from database
        dataList.add(new SelectItem("banana_selection", "banana"));
        dataList.add(new SelectItem("apple_selection", "apple"));
        dataList.add(new SelectItem("orange_selection", "orange"));
        dataList.add(new SelectItem("pineapple_selection", "pineapple"));
        dataList.add(new SelectItem("pear_selection", "pear"));
        dataList.add(new SelectItem("kiwi_selection", "kiwi"));
    }
    

    public List<SelectItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<SelectItem> dataList) {
        this.dataList = dataList;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
    
    public void actionListener(ActionEvent e){
       Logger.getAnonymousLogger().log(Level.INFO, "{0} actionListener", this); 
    }
}
