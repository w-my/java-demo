package cn.com.wmy.springbootdubboapi.service;

import cn.com.wmy.springbootdubboapi.pojo.Brand;

import java.util.List;

/**
 * BrandService TODO
 *
 * @author wmy
 * @date 2022/6/22 09:53
 **/
public interface BrandService {

	List<Brand> findAll();

}
