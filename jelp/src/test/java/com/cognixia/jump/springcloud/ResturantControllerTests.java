/**
 * 
 */
package com.cognixia.jump.springcloud;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.springcloud.controller.RestaurantController;
import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.Review;

/**
 * @author philip
 *
 */


@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class ResturantControllerTests {

	@Autowired
	private MockMvc mvc;
	
	
	
	@Test
	void testReturnResturant() throws Exception {
		int restaurant_id = 1;
		String uri = "http://localhost:8080/restaurant/";
		
		Restaurant restaurant = new Restaurant(restaurant_id, name, address, phone, city, state, country, description, reviews);
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri+restaurant_id);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		
		
		assertEquals("{restaurantJson}", result.getResponse().getContentAsString());
	}
	
	
	

}
