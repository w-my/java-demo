package cn.com.wmy.springbootdubboapi.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Brand
 *
 * @author wmy
 * @date 2022/6/22 09:55
 **/
@Data
public class Brand implements Serializable {

	private static final long serialVersionUID = -4949803337939774464L;

	private Integer id;

	private String name;

	private String image;

	private String letter;

	private Integer seq;

}
