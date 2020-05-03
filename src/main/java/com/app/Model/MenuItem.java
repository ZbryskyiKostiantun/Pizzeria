package com.app.Model;

import java.util.List;

public  class MenuItem {
    Long id;
    String name;
    String discription;
    Long price;
    List<Ingredient> ingredients;
    List<Tag> tags;
    Type type;

    public MenuItem(Long id, String name, String discription, Long price, Type type) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.price = price;
        this.type = type;
    }

    public MenuItem(Long id, String name, String discription, Long price, List<Ingredient> ingredients, List<Tag> tags, Type type) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.price = price;
        this.ingredients = ingredients;
        this.tags = tags;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public Long getPrice() {
        return price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Type getType() {
        return type;
    }
}
