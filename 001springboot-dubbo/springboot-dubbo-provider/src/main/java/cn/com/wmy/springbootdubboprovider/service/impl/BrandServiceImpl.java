package cn.com.wmy.springbootdubboprovider.service.impl;

import cn.com.wmy.springbootdubboapi.pojo.Brand;
import cn.com.wmy.springbootdubboapi.service.BrandService;
import cn.com.wmy.springbootdubboprovider.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * BrandServiceImpl
 *
 * @author wmy
 * @date 2022/6/22 10:32
 **/
@DubboService(version = "1.0.0", group = "DUBBO")
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandMapper brandMapper;

	@Override
	public List<Brand> findAll() {
		return brandMapper.findAll();
	}
}
