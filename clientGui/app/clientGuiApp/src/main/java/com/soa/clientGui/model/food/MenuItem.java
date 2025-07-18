package com.soa.clientGui.model.food;

public class MenuItem {
  private String name;
  private String description;
  private double price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "MenuItem [name=" + name + ", description=" + description + ", price=" + price + ", getName()=" + getName()
        + ", getDescription()=" + getDescription() + ", getPrice()=" + getPrice() + "]";
  }

}
