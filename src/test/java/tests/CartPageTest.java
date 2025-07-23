package tests;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartItem;
import pages.CartPage;
import pages.HeaderComponent;
import pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageTest {
    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private CartPage cartPage;

    @BeforeEach
    void setUp() {
        String productName = "Combination Pliers";

        new ProductsPage()
                .openPage(config.baseUrl())
                .openProductPage(productName)
                .clickAddToCartButton();

        cartPage = new HeaderComponent().navigateToCartPage();
    }

    @Test
    @DisplayName("Verify item details in the cart")
    void verifyItemDetailsInCart() {
        String productName = "Combination Pliers";
        CartItem item = cartPage.getCartItem(productName);

        assertThat(item.getProductName()).isEqualTo(productName);
        assertThat(item.getQuantity()).isEqualTo(1);
        assertThat(item.getUnitPrice()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Verify that quantity change updates the row total")
    void verifyQuantityChangeUpdatesTotal() {
        String productName = "Combination Pliers";
        CartItem item = cartPage.getCartItem(productName);
        double unitPrice = item.getUnitPrice();

        item.setQuantity(3);
        double expectedRowTotal = unitPrice * 3;
        assertThat(item.getRowTotal()).isEqualTo(expectedRowTotal);
    }
}
