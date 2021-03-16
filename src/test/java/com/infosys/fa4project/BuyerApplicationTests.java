package com.infosys.fa4project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infosys.fa4project.User.dto.BuyerDTO;
import com.infosys.fa4project.User.service.BuyerService;




@RunWith(SpringRunner.class)
@SpringBootTest(classes=BuyerApplicationTests.class)

public class BuyerApplicationTests {

	@Mock
	BuyerService service = new BuyerService();
	
	
	@Test
	public void getSpecificBuyer(){
		BuyerDTO buy = new BuyerDTO();
		buy.setBuyerId(11);
		buy.setName("saurabh kumar");
		buy.setEmail("saurabh@gmail.com");
		buy.setPhoneNumber(7777766666L);
		buy.setPassword("Kumar@w*3996");
		Mockito.when(service.getBuyerById(11)).thenReturn(buy);
		Assert.assertEquals(service.getBuyerById(11), buy);
	}
	
	@Test
	public void getSpecificBuyerInvalid(){
		BuyerDTO buy = new BuyerDTO();
		buy.setBuyerId(19);
		buy.setName("rajesh singh");
		buy.setEmail("rajesh@gmail.com");
		buy.setPhoneNumber(7777766543L);
		buy.setPassword("Sin@w*3996");
		Mockito.when(service.getBuyerById(19)).thenReturn(buy);
		Assert.assertNotEquals(service.getBuyerById(19), null);
	}
	
}
