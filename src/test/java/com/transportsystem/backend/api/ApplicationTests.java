package com.transportsystem.backend.api;

import com.transportsystem.backend.api.controller.TransportController;
import com.transportsystem.backend.api.dao.entity.Planet;
import com.transportsystem.backend.api.dao.entity.Route;
import com.transportsystem.backend.api.dao.repository.PlanetRepository;
import com.transportsystem.backend.api.service.TransportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransportSystemApplication.class)
@AutoConfigureMockMvc
public class ApplicationTests {
	@Autowired
	private TransportService transportService;
	@Autowired
	@MockBean
	private TransportController transportController;


	@Autowired
	MockMvc mockMvc;
	@MockBean
	private PlanetRepository planetRepository;

	@Test
	public void getPlanetTest(){
		List all = new ArrayList();
		all.add(new Planet(101, "A", "Earth"));
		all.add(new Planet( 102,"B","Moon"));
		all.add(new Planet( 103,"C","Mars"));
		System.out.println("Planet test");
		when(transportController.getPlanets()).thenReturn(all);
		System.out.println(all);

	}

	@Test
	public void getRouteTest(){
		List all = new ArrayList();
		all.add(new Route(102,1,"A","c",10.00));
		all.add(new Route( 102,2,"A","c",10.00));
		System.out.println("Route test");
		when(transportController.getRoutDetails()).thenReturn(all);
		System.out.println(all);

	}




	@Test
	public void testApplicationLoad()
	{
		assertNotNull("Controller is not loaded", transportController);
	}

}
