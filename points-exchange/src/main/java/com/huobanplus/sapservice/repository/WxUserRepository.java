package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.entity.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wuxiongliu on 2016-10-14.
 */
public interface WxUserRepository extends JpaRepository<WxUser,Long> {
}
