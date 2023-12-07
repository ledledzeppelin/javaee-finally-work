package com.milotnt.mapper;

import com.milotnt.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/10
 */

@Mapper
public interface AdminMapper extends BaseMapper<Admin>{

    Admin selectByAccountAndPassword(Admin admin);

}
