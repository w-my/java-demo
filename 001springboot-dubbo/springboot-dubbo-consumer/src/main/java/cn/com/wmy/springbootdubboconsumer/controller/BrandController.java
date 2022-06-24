package cn.com.wmy.springbootdubboconsumer.controller;

import cn.com.wmy.springbootdubboapi.pojo.Brand;
import cn.com.wmy.springbootdubboapi.service.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BrandController
 *
 * @author wmy
 * @date 2022/6/22 11:06
 **/
@RestController
@RequestMapping("/brand")
public class BrandController {

	@DubboReference(version = "1.0.0", group = "DUBBO", interfaceClass = BrandService.class)
	private BrandService brandService;

	@RequestMapping("/findAll")
	public List<Brand> findAll() {
		return brandService.findAll();
	}

}
