package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CartItem {
    private final SelenideElement rootElement;
    private final SelenideElement productName;
    private final SelenideElement quantityInput;
    private final SelenideElement unitPrice;
    private final SelenideElement rowTotal;
    private final SelenideElement removeButton;

    public CartItem(SelenideElement rootElement) {
        this.rootElement = rootElement;
        this.productName = rootElement.$("[data-test='product-title']");
        this.quantityInput = rootElement.$("[data-test='product-quantity']");
        this.unitPrice = rootElement.$("[data-test='product-price']");
        this.rowTotal = rootElement.$("[data-test='line-price']");
        this.removeButton = rootElement.$(".btn-danger");
    }

    @Step("Get product name from cart item")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Get quantity from cart item")
    public int getQuantity() {
        return Integer.parseInt(quantityInput.getValue());
    }

    @Step("Get unit price from cart item")
    public double getUnitPrice() {
        return parsePrice(unitPrice.getText());
    }

    @Step("Get row total from cart item")
    public double getRowTotal() {
        return parsePrice(rowTotal.getText());
    }

    @Step("Set quantity for item to: {newQuantity}")
    public CartItem setQuantity(int newQuantity) {
        quantityInput.setValue(String.valueOf(newQuantity));
        return this;
    }

    @Step("Remove item from cart")
    public void remove() {
        removeButton.click();
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "").trim());
    }
}