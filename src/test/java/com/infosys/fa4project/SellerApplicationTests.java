package com.infosys.fa4project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infosys.fa4project.User.dto.SellerDTO;
import com.infosys.fa4project.User.service.SellerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BuyerApplicationTests.class)
public class SellerApplicationTests {

	@Mock
	SellerService service= new SellerService();
	
	@Test
	public void getSpecificSeller() {
		SellerDTO sell = new SellerDTO();
		sell.setSellerId(1);
		sell.setName("Roshan kumar");
		sell.setEmail("mroshan@gmail.com");
		sell.setPhoneNumber(8484886578L);
		sell.setPassword("Roshan@w*2338");
		Mockito.when(service.getSellerById(1)).thenReturn(sell);
		Assert.assertEquals(service.getSellerById(1), sell);
	}
	
	@Test
	public void getSpecificSellerInvalid() {
		SellerDTO sell = new SellerDTO();
		sell.setSellerId(29);
		sell.setName("Thomas Shelby");
		sell.setEmail("france@gmail.com");
		sell.setPhoneNumber(8484881964L);
		sell.setPassword("Peaky@w*1914");
		Mockito.when(service.getSellerById(29)).thenReturn(sell);
		Assert.assertNotEquals(service.getSellerById(29), null);
	}
	
}
