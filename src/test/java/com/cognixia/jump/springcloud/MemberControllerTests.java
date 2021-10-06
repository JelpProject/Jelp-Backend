/**
 * 
 */
package com.cognixia.jump.springcloud;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


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
	@WithMockUser(username="phil", password="123456")
	void testReturnMember() throws Exception {
	
		Member member = new Member();
		member.setUsername("Phil");
		String uri = "http://localhost:8080/api/member/{username}";
		
		when( MemberRepo.findByUsername("Phil", Member.class)).thenReturn(member);
		
		RequestBuilder request = MockMvcRequestBuilders.get(uri, "Phil");
		
		MvcResult result = mvc.perform(request).andReturn();
		System.out.println(result);
		
//		assertEquals(result, member.get().toString());
	
		verify( MemberRepo, times(1) ).findByUsername("Phil", Member.class);
		verifyNoMoreInteractions(MemberRepo);
		
	}
	
	
	

}
