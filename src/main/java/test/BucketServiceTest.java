package test;

import dao.daofactory.*;
import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.BucketService;
import services.beans.BucketInfoView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BucketServiceTest {
    @Mock
    private Bucket bucket;

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
    private IBucketDAO bucketDAO;

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

    @InjectMocks
    private BucketService bucketService;

    private List<Bucket> bucketList;
    private List<Bucket> list;

    private void createEntities() {
        int userID = 1;
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

        bucketList = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bucket = new Bucket();
            bucket.setId(i);
            bucket.setUserID(userID);
            bucket.setCruiseID(userID);
            bucketList.add(bucket);
            list.add(bucket);
        }
    }

    @Before
    public void init() {
        createEntities();
        when(bucketDAO.findByUserId(anyInt())).thenReturn(bucketList);
        when(bucketDAO.createBucket(any())).thenReturn("SUCCESS");
        when(userDAO.findByLogin(anyString())).thenReturn(user);
        when(cruiseDAO.findById(anyInt())).thenReturn(cruise);
        when(countryDAO.findById(anyInt())).thenReturn(country);
        when(shipDAO.findById(anyInt())).thenReturn(ship);
        doAnswer(invocationOnMock -> {
            Iterator<Bucket> iterator = list.iterator();
            iterator.next();
            iterator.remove();
            return list;
        }).when(bucketDAO).deleteBucketById(anyInt());
    }

    @Test
    public void testFindBucketsOfUser() {
        List<BucketInfoView> bucketsOfUser = bucketService.findBucketsOfUser(user.getLogin());
        assertEquals(bucketList.size(), bucketsOfUser.size());
    }

    @Test
    public void testDeleteBucketOfUser() {
        bucketService.deleteBucketOfUser(user.getLogin());
        assertEquals(list.size(), 0);
    }

    @Test
    public void testAddBucketToUser() {
        assertEquals(bucketService.addBucketToUser(user.getLogin(), 1), "CRUISE_ALREADY_ADDED");
        assertEquals(bucketService.addBucketToUser(user.getLogin(), 2), "CREATE_BUCKET");
    }

}
