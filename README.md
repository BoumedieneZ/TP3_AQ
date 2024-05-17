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
        User expectedUser = new User(userId, "John Doe");

        Mockito.when(userRepositoryMock.findUserById(userId)).thenReturn(expectedUser);

        User actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser); // Vérification de l'utilisateur attendu
        Mockito.verify(userRepositoryMock).findUserById(userId); // Vérification de l'appel à findUserById
    }
}
```
## Exercice 2: Interaction avec une Base de Données avec des Mocks

Dans cet exercice, nous avons créé une classe `OrderController` qui interagit avec un `OrderService` pour créer une commande. L'`OrderService` enregistre les détails de la commande dans une base de données à l'aide d'une classe `OrderDao`. Nous avons écrit un test JUnit pour la méthode `createOrder(Order order)` de `OrderController`, en utilisant des mocks pour `OrderService` et `OrderDao`. Nous avons vérifié que `OrderService.createOrder` est appelé avec le bon argument et que `OrderDao.saveOrder` est appelé avec l'objet de commande créé.

## Exercice 3: Intégration d'API avec Mocking

Dans cet exercice, nous avons créé une classe `ProductService` qui récupère des informations sur un produit à partir d'une API externe à l'aide d'un `ProductApiClient`. Nous avons écrit un test JUnit pour la méthode `getProduct(String productId)` de `ProductService`, en utilisant un objet mock pour `ProductApiClient`. Nous avons vérifié que `ProductApiClient.getProduct` est appelé avec le bon argument, ainsi que testé différents scénarios tels que la récupération réussie, le format de donnée incompatible et les échecs d'appel d'API.
