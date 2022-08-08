/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

/**
 *
 * @author m.pulido
 */
public class ComCommodityEntryArticlesLogEntity {
    
    private int id;
    private int articleId;
    private int quantityPrevious;
    private int quantityNew;
    private int userId;
    private String creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getQuantityPrevious() {
        return quantityPrevious;
    }

    public void setQuantityPrevious(int quantityPrevious) {
        this.quantityPrevious = quantityPrevious;
    }

    public int getQuantityNew() {
        return quantityNew;
    }

    public void setQuantityNew(int quantityNew) {
        this.quantityNew = quantityNew;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    
    
}
