package cn.com.wmy.springbootdubboprovider.service;

import cn.com.wmy.springbootdubboapi.pojo.Brand;
import cn.com.wmy.springbootdubboapi.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * BrandServiceTest
 *
 * @author wmy
 * @date 2022/6/22 10:36
 **/
@SpringBootTest
public class BrandServiceTest {

	@Resource
	private BrandService brandService;

	@Test
	public void findAllTest() {
		List<Brand> result = brandService.findAll();
		System.out.println(result);
	}

}
