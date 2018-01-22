package test;

import dao.daofactory.*;
import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.OrderService;
import services.beans.BucketInfoView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private Order order;

    @Mock
    private User user;

    @Mock
    private Cruise cruise;

    @Mock
    private Excursion excursion;

    @Mock
    private Country country;

    @Mock
    private Ship ship;

    @Mock
    private Bucket bucket;

    @Mock
    private IOrderDAO orderDAO;

    @Mock
    private IUserDAO userDAO;

    @Mock
    private ICruiseDAO cruiseDAO;

    @Mock
    private IExcursionDAO excursionDAO;

    @Mock
    private ICountryDAO countryDAO;

    @Mock
    private IShipDAO shipDAO;

    @Mock
    private IBucketDAO bucketDAO;

    @InjectMocks
    private OrderService orderService;

    private List<Order> orderList;
    private List<Order> list;
    private List<Bucket> bucketList;

    private void createEntities() {int userID = 1;
        int cruiseID = 1;
        int countryID = 1;
        int shipID = 1;

        user = new User();
        user.setId(userID);
        user.setLogin("User");

        cruise = new Cruise();
        cruise.setId(cruiseID);

        country = new Country();
        country.setId(countryID);
        country.setName("Country");

        ship = new Ship();
        ship.setId(shipID);
        ship.setName("Ship");

        orderList = new ArrayList<>();
        list = new ArrayList<>();
        bucketList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            order = new Order();
            order.setId(i);
            order.setUserID(userID);
            order.setCruiseID(userID);
            orderList.add(order);
            list.add(order);
            bucket = new Bucket();
            bucket.setId(i);
            bucket.setCruiseID(cruiseID);
            bucket.setUserID(userID);
            bucketList.add(bucket);
        }
    }

    @Before
    public void init() {
        createEntities();
        when(orderDAO.findByUserId(anyInt())).thenReturn(orderList);
        when(userDAO.findByLogin(anyString())).thenReturn(user);
        when(cruiseDAO.findById(anyInt())).thenReturn(cruise);
        when(countryDAO.findById(anyInt())).thenReturn(country);
        when(shipDAO.findById(anyInt())).thenReturn(ship);
        when(bucketDAO.findById(anyInt())).thenReturn(bucket);
        when(orderDAO.createOrder(any())).thenReturn("SUCCESS");
        doAnswer(invocationOnMock -> {
            Iterator<Bucket> iterator = bucketList.iterator();
            iterator.next();
            iterator.remove();
            return list;
        }).when(bucketDAO).deleteBucketById(anyInt());
    }

    @Test
    public void testFindOrdersOfUser() {
        List<BucketInfoView> ordersOfUser = orderService.findOrdersOfUser(user.getLogin());
        assertEquals(ordersOfUser.size(), orderList.size());
    }

    @Test
    public void testMakeOrder() {
        assertEquals(orderService.makeOrder(user.getLogin(), 1, bucket.getCruiseID(), 0, ""), "ORDER_ALREADY_ADDED");
        assertEquals(bucketList.size(), orderList.size());

        assertEquals(orderService.makeOrder(user.getLogin(), 2, bucket.getCruiseID(), 0, ""), "CREATE_ORDER");
        assertEquals(bucketList.size(), orderList.size() - 1);
    }

}
