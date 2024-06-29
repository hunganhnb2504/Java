package phuong.restaurant.jdbc;

public class Food {
    private int id;
    private String name;
    private int categoryId;
    private String image;
    private String description;
    private int quantity;
    private double price;

    public Food(String name, String image, String description, int quantity, double price, int categoryId) {
        this.name = name;
        this.categoryId = categoryId;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Food(int id, String name, String image, String description, int quantity, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food [id=" + id + ", Name=" + name + ", Image=" + image + ", Description=" + description + "]";
    }
}
