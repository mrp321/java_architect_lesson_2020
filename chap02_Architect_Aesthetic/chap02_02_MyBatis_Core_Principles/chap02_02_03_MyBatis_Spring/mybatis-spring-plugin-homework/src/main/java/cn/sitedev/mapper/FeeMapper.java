package cn.sitedev.mapper;

import cn.sitedev.entity.Fee;

import java.util.List;

public interface FeeMapper {
    Fee selectByFeeDate(String feeDate);

    int insert(List<Fee> feeList);
}