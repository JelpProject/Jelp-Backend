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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.springcloud.controller.ReviewController;


import com.cognixia.jump.springcloud.model.Review;
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
@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc
class ReviewControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private ReviewController controller;
	
	@MockBean
	private MemberRepository MemberRepo;
	
	@MockBean
	private RestaurantRepository RestaurantRepo;
	
	@MockBean 
	private ReviewRepository ReviewRepo;
	
	@MockBean
	public MyUserDetailsService Service;
	
	@MockBean
	public JwtUtil token;


	
	
	@Test
	@WithMockUser(username="phil", password="123456")
	void testReturnReview() throws Exception {
	
		Optional<Review> review = Optional.of(new Review());
		String uri = "http://localhost:8080/api/reviews/{id}";
		
		when( ReviewRepo.findById(-1L)).thenReturn(review);
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri, -1L);
		
		MvcResult result = mvc.perform(request).andReturn();
		System.out.println(result);
//		assertEquals(result, review.get().toString());
	
		verify( ReviewRepo, times(1) ).findById(-1L);
		verifyNoMoreInteractions(ReviewRepo);
		
	}
	
	
	

}
