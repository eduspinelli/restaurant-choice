package com.restaurant.choice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.repository.RestaurantRepository;
import com.restaurant.choice.service.RestaurantServiceImpl;

@RunWith(MockitoJUnitRunner.class) public class RestaurantServiceTest {

    @Mock private RestaurantRepository restaurantRepository;

    @InjectMocks private RestaurantServiceImpl restaurantService;

    @Test public void mostVotedRestaurantTest() {

        Restaurant dummyRestauratWinner = Restaurant.createNew("Subway");
        dummyRestauratWinner.vote();
        dummyRestauratWinner.vote();
        dummyRestauratWinner.vote();


        var dummyRestaurats = new ArrayList<Restaurant>();
        dummyRestaurats.add(dummyRestauratWinner);

        when(restaurantRepository.getMaxVote()).thenReturn(dummyRestaurats);

        Restaurant result = restaurantService.getMostVotedRestaurant();

        assertEquals(result, dummyRestauratWinner);
    }


    @Test public void resetVotesOfRestaurantsTest() {

        Restaurant dummyRestauratOne = Restaurant.createNew("Subway");
        dummyRestauratOne.vote();
        dummyRestauratOne.vote();
        dummyRestauratOne.vote();

        Restaurant dummyRestauratTwo = Restaurant.createNew("Subway2");
        dummyRestauratTwo.vote();
        dummyRestauratTwo.vote();

        Restaurant dummyRestauratThree = Restaurant.createNew("Subway3");
        dummyRestauratThree.vote();


        List<Restaurant> dummyRestaurats =
            Arrays.asList(dummyRestauratOne, dummyRestauratTwo, dummyRestauratThree);

        when(restaurantRepository.findByVotesGreaterThanZero()).thenReturn(dummyRestaurats);

        List<Restaurant> restaurantsReturn = restaurantService.resetRestaurantVotes();

        assertTrue(
            restaurantsReturn.stream().anyMatch(restaurant -> restaurant.getNumberVotes() == 0));
    }

}
