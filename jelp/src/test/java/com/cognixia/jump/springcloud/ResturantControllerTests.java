/**
 * 
 */
package com.cognixia.jump.springcloud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.springcloud.controller.RestaurantController;
import com.cognixia.jump.springcloud.model.City;
import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.Review;
import com.cognixia.jump.springcloud.model.State;
import com.cognixia.jump.springcloud.repository.CityRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;

/**
 * @author philip
 *
 */


@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class ResturantControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private RestaurantController controller;
	
	@MockBean
	private RestaurantRepository RestaurantRepo;

	@MockBean
	private CityRepository cityRepo;
	
	
	@Test
	void testReturnResturant() throws Exception {
		Long restaurant_id = 1L;
		List<Review> reviews = null;
		String uri = "http://localhost:8080/api/restaurants/";
		
		Optional<Restaurant> restaurant = Optional.of(new Restaurant(restaurant_id, "adam bistro", "1000 fake ave", "5161111111", "description", new City(1L, "fake city", new State(1L, "NY")), reviews));

		when(RestaurantRepo.findById(restaurant_id)).thenReturn(restaurant);
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri+restaurant_id);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertEquals("{ 'restaurant_id':1, 'name':'adam bistro','address':'1000 fake ave', 'phone':'5161111111','city':'fake city', 'state':'NY', 'country':'USA', 'description':'Tiny Bistro', 'reviews':null}", result.getResponse().getContentAsString());
	}
	
	
	

}
