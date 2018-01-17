package test;

import dao.daofactory.*;
import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.AdminService;
import services.beans.OrdersInfoView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Mock
    private Order order;

    @Mock
    private Cruise cruise;

    @Mock
    private IOrderDAO orderDAO;

    @Mock
    private IUserDAO userDAO;

    @Mock
    private ICruiseDAO cruiseDAO;

    @Mock
    private ICountryDAO countryDAO;

    @Mock
    private IShipDAO shipDAO;

    @InjectMocks
    private AdminService adminService;

    private List<Order> orderList;

    private void createEntities() {
        orderList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            order = new Order();
            orderList.add(order);
        }
    }

    @Before
    public void init() {
        createEntities();
    }

    @Test
    public void testChangeBonus() {
        int orderID = 1;
        String value = "bonus";
        when(orderDAO.updateBonus(orderID, value)).thenReturn("Test!");
        assertEquals(adminService.changeBonus(orderID, value), "Test!");
    }

    @Test
    public void testFindAllOrders() {
        when(orderDAO.findAll()).thenReturn(orderList);
        when(cruiseDAO.findById(anyInt())).thenReturn(cruise);
        List<OrdersInfoView> allOrders = adminService.findAllOrders();
        assertEquals(allOrders.size(), orderList.size());
    }
}
