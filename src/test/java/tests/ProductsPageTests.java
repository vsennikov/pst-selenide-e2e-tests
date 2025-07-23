package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import config.ProjectConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProductDetailPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Product Catalog")
@Feature("Products Page Functionality")
public class ProductsPageTests {

    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private ProductsPage productsPage;

    @BeforeEach
    void setUp() {
        productsPage = new ProductsPage();
        productsPage.openPage(config.baseUrl());
    }

    @Test
    @Story("Sorting")
    @DisplayName("Products should be sorted by Name from A to Z")
    void productsShouldBeSortableByNameAscending() {
        productsPage.sortBy("Name (A - Z)");

        ElementsCollection productNamesElements = $$("[data-test='product-name']");
        List<String> actualProductNames = productNamesElements.texts();

        List<String> expectedSortedNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedSortedNames);
        assertThat(actualProductNames).isEqualTo(expectedSortedNames);
    }

    @Test
    @Story("Filtering")
    @DisplayName("Products should be filterable by 'Chisels' category")
    void productsShouldBeFilterableByCategory() {
        String categoryToFilter = "Chisels";

        productsPage.filterByCategory(categoryToFilter);
        $$(".card").filter(text(categoryToFilter)).shouldHave(CollectionCondition
                .sizeGreaterThan(0));
    }

    @Test
    @Story("Search")
    @DisplayName("User should be able to search for a specific product")
    void userCanSearchForProduct() {
        String searchTerm = "Hammer";

        productsPage.searchForProduct(searchTerm);
        $$(".card").filter(text(searchTerm)).shouldHave(CollectionCondition
                .sizeGreaterThan(0));
    }

    @Test
    @Story("Navigation")
    @DisplayName("User should be able to navigate to a product's detail page")
    void userCanNavigateToProductDetailsPage() {
        String productName = "Combination Pliers";

        ProductDetailPage productDetailPage = productsPage.openProductPage(productName);
        productDetailPage.verifyOnPage(productName);
    }
}