package test;

import dao.daofactory.IUserDAO;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.CheckService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckServiceTest {

    @Mock
    private User user;

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private CheckService checkService;

    @Before
    public void init() {
        user = new User();
        user.setLogin("Login");
        user.setPassword("Password");
        when(userDAO.findByLogin(user.getLogin())).thenReturn(user);
    }

    @Test
    public void testCheckUser() {
        assertEquals(checkService.checkUser(null, "sd"), "EMPTY_LOGIN");
        assertEquals(checkService.checkUser(user.getLogin(), "sd"), "WRONG_PASSWORD");
        assertEquals(checkService.checkUser("NoUser", "sd"), "IS_EXIST_USER");
        assertEquals(checkService.checkUser(user.getLogin(), user.getPassword()), null);
    }

}
