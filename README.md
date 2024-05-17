# TP 03 : Tests d'intégration

Ce dépôt contient un ensemble d'exercices pratiques sur les tests d'intégration en Java en utilisant des mocks pour simuler des interactions entre différents modules d'une application.

## Exercice 1: Interaction Simple entre Modules

Dans cet exercice, nous avons créé une classe `UserService` qui récupère les données d'un utilisateur à partir d'une classe `UserRepository`. Nous avons écrit un test JUnit pour la méthode `getUserById(long id)` de `UserService`, en utilisant un objet mock pour `UserRepository` afin de simuler la récupération de l'utilisateur. Nous avons vérifié que la méthode `findUserById` sur l'objet mock est appelée avec le bon argument lorsque `UserService.getUserById` est appelée.
```java
// UserServiceTest.java
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById() {
        long userId = 123;
        User expectedUser = new User(userId, "Kadaben Boumediene");

        Mockito.when(userRepositoryMock.findUserById(userId)).thenReturn(expectedUser);

        User actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser); // Vérification de l'utilisateur attendu
        Mockito.verify(userRepositoryMock).findUserById(userId); // Vérification de l'appel à findUserById
    }
}
```
## Exercice 2: Interaction avec une Base de Données avec des Mocks

Dans cet exercice, nous avons créé une classe `OrderController` qui interagit avec un `OrderService` pour créer une commande. L'`OrderService` enregistre les détails de la commande dans une base de données à l'aide d'une classe `OrderDao`. Nous avons écrit un test JUnit pour la méthode `createOrder(Order order)` de `OrderController`, en utilisant des mocks pour `OrderService` et `OrderDao`. Nous avons vérifié que `OrderService.createOrder` est appelé avec le bon argument et que `OrderDao.saveOrder` est appelé avec l'objet de commande créé.

```java
// OrderControllerTest.java
public class OrderControllerTest {

    @Mock
    private OrderService orderServiceMock;

    @Mock
    private OrderDao orderDaoMock;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(1, "Product", 100);

        Mockito.when(orderServiceMock.createOrder(order)).thenCallRealMethod();
        Mockito.when(orderDaoMock.saveOrder(order)).thenReturn(true);

        boolean result = orderController.createOrder(order);

        assertTrue(result); // Vérification du résultat
        Mockito.verify(orderServiceMock).createOrder(order); // Vérification de l'appel à createOrder
        Mockito.verify(orderDaoMock).saveOrder(order); // Vérification de l'appel à saveOrder
    }
}
```
## Exercice 3: Intégration d'API avec Mocking

Dans cet exercice, nous avons créé une classe `ProductService` qui récupère des informations sur un produit à partir d'une API externe à l'aide d'un `ProductApiClient`. Nous avons écrit un test JUnit pour la méthode `getProduct(String productId)` de `ProductService`, en utilisant un objet mock pour `ProductApiClient`. Nous avons vérifié que `ProductApiClient.getProduct` est appelé avec le bon argument, ainsi que testé différents scénarios tels que la récupération réussie, le format de donnée incompatible et les échecs d'appel d'API.
```java
// ProductServiceTest.java
public class ProductServiceTest {

    @Mock
    private ProductApiClient productApiClientMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProduct_Success() {
        String productId = "123";
        Product expectedProduct = new Product(productId, "Product Name", 50.0);

        Mockito.when(productApiClientMock.getProduct(productId)).thenReturn(expectedProduct);

        Product actualProduct = productService.getProduct(productId);

        assertEquals(expectedProduct, actualProduct); // Vérification du produit attendu
        Mockito.verify(productApiClientMock).getProduct(productId); // Vérification de l'appel à getProduct
    }

    @Test
    public void testGetProduct_APIError() {
        String productId = "456";

        Mockito.when(productApiClientMock.getProduct(productId)).thenThrow(new ApiException("API Error"));

        assertThrows(ApiException.class, () -> productService.getProduct(productId)); // Vérification de l'exception
        Mockito.verify(productApiClientMock).getProduct(productId); // Vérification de l'appel à getProduct
    }

    // Ajouter d'autres tests pour les autres scénarios...
}

```
