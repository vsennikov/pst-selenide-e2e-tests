package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {

    private final SelenideElement sortDropdown = $("[data-test='sort']");
    private final SelenideElement searchInput = $("[data-test='search-query']");
    private final SelenideElement searchButton = $("[data-test='search-submit']");
    private final SelenideElement categoryFilter =
            $x("//h4[normalize-space()='By category:']/following-sibling::fieldset");
    private final SelenideElement brandFilter =
            $x("//h4[normalize-space()='By brand:']/following-sibling::fieldset");
    private final ElementsCollection productCards = $$(".card");

    @Step("Open products page with url: {url}")
    public ProductsPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Sort products by {sortOption}")
    public ProductsPage sortBy(String sortOption) {
        sortDropdown.selectOption(sortOption);
        return this;
    }

    @Step("search for product: {productName}")
    public ProductsPage searchForProduct(String productName){
        searchInput.setValue(productName);
        searchButton.click();
        return this;
    }

    @Step("Filter by category: {categoryName}")
    public ProductsPage filterByCategory(String categoryName) {
        categoryFilter.findAll(".checkbox").findBy(exactText(categoryName)).$("input").click();
        return this;
    }

    @Step("Filter by brand: {brandName}")
    public ProductsPage filterByBrand(String brandName) {
        brandFilter.findAll(".checkbox").findBy(exactText(brandName)).$("input").click();
        return this;
    }

    @Step("Open product page: {productName}")
    public ProductDetailPage openProductPage(String productName){
        SelenideElement productCard = productCards.findBy(text(productName));
        productCard.click();
        return new ProductDetailPage();
    }

}
