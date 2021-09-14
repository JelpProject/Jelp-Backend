/**
 * 
 */
package com.cognixia.jump.springcloud;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.cognixia.jump.springcloud.controller.MemberController;


import com.cognixia.jump.springcloud.controller.ReviewController;

import com.cognixia.jump.springcloud.model.Member;
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
@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
class MemberControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private MemberController controller;
	
	@InjectMocks
	private ReviewController Revcontroller;
	
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
	@WithMockUser(username="kenaz98", password="sock")
	void testReturnMember() throws Exception {
	
		Optional<Member> member = Optional.of(new Member());
		String uri = "http://localhost:8080/api/member/{id}";
		
		when( MemberRepo.findById(-1L)).thenReturn(member);
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri, -1L);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertEquals(result, member.get().toString());
	
		verify( MemberRepo, times(1) ).findById(-1L);
		verifyNoMoreInteractions(MemberRepo);
		
	}
	
	
	

}
