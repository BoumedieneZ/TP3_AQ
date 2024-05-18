import static org.mockito.Mockito.*;

import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class UserServiceTest {
    @Test
    public void testGetUserById() {
        UserRepository userRepositoryMock = mock(UserRepository.class);

        User expectedUser = new User(1, "John Doe", "john.doe@example.com");

        when(userRepositoryMock.findUserById(1)).thenReturn(expectedUser);

        UserService userService = new UserService(userRepositoryMock);

        User actualUser = userService.getUserById(1);

        verify(userRepositoryMock).findUserById(1);

        assertEquals(expectedUser, actualUser);
    }
}
