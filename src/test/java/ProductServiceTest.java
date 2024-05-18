import org.example.Product;
import org.example.ProductApiClient;
import org.example.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    private ProductService productService;
    private ProductApiClient productApiClientMock;

    @BeforeEach
    public void setup() {
        productApiClientMock = mock(ProductApiClient.class);
        productService = new ProductService(productApiClientMock);
    }

    @Test
    public void testGetProduct() {
        // Configuration du mock pour simuler un appel réussi à l'API
        Product expectedProduct = new Product(/* Remplissez avec des données appropriées */);
        when(productApiClientMock.getProduct(anyString())).thenReturn(expectedProduct);

        // Appel de la méthode à tester
        Product actualProduct = productService.getProduct("productId");

        // Vérification de l'appel à l'API avec le bon argument
        verify(productApiClientMock, times(1)).getProduct("productId");

        // Vérification que le produit retourné est le bon
        assertEquals(expectedProduct, actualProduct);
    }
}
