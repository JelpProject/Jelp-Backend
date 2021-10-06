/**
 * 
 */
package com.cognixia.jump.springcloud;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.springcloud.controller.RestaurantController;


import com.cognixia.jump.springcloud.controller.ReviewController;

import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.repository.CityRepository;
import com.cognixia.jump.springcloud.repository.MemberRepository;
import com.cognixia.jump.springcloud.repository.RestaurantRepository;
import com.cognixia.jump.springcloud.repository.ReviewRepository;
import com.cognixia.jump.springcloud.service.MyUserDetailsService;
import com.cognixia.jump.springcloud.util.JwtUtil;

/**
 * @author philip
 *
 */


@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
@AutoConfigureMockMvc
@SpringBootTest
class ResturantControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private RestaurantController controller;
	
	@InjectMocks
	private ReviewController Revcontroller;
	
	@MockBean
	private MemberRepository MemberRepo;
	
	@MockBean
	private RestaurantRepository RestaurantRepo;
	
	@MockBean 
	private ReviewRepository ReviewRepo;


	@MockBean
	private CityRepository cityRepo;
	
	@MockBean
	public MyUserDetailsService Service;
	
	@MockBean
	public JwtUtil token;


	
	
	@Test
	@WithMockUser(username="phil", password="123456")
	void testReturnResturant() throws Exception {
	
		Optional<Restaurant> restaurant = Optional.of(new Restaurant());
		restaurant.get().setName("Test Restaurant");
		restaurant.get().setDescription("Test Restaurant");
		
		String uri = "http://localhost:8080/api/restaurants/-1";
		
	
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri);
		when( RestaurantRepo.findById(-1L)).thenReturn(restaurant);
		MvcResult result = mvc.perform(request).andReturn();
		System.out.println(result);
	//	assertEquals(result, restaurant.get().toString());
	
		verify( RestaurantRepo, times(1) ).findById(-1L);
		verifyNoMoreInteractions(RestaurantRepo);
		
	}
	
	
	

}
