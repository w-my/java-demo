package cn.com.wmy.springbootdubboconsumer.controller;

import cn.com.wmy.springbootdubboapi.pojo.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * BrandControllerTest
 *
 * @author wmy
 * @date 2022/6/22 11:09
 **/
@SpringBootTest
public class BrandControllerTest {

	@Resource
	private BrandController brandController;

	@Test
	public void findAllTest() {
		List<Brand> result = brandController.findAll();
		System.out.println(result);
	}
}
