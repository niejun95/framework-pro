package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entities.RememberMeToken;

/**
 * @description
 * @author ryan
 * @date 2023/4/25 14:15
 * @version 1.0
 */
@Mapper
public interface RememberTokenMapper extends BaseMapper<RememberMeToken> {
}
