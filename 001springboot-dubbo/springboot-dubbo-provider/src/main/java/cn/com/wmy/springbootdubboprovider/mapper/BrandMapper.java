package cn.com.wmy.springbootdubboprovider.mapper;

import cn.com.wmy.springbootdubboapi.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BrandMapper
 *
 * @author wmy
 * @date 2022/6/22 10:30
 **/
@Mapper
public interface BrandMapper {

	List<Brand> findAll();

}
