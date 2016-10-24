package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.entity.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-24.
 */
@Repository
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo,Integer> {

    List<ActivityInfo> findByLevelGroup(int levelGroup);

    ActivityInfo findByActivityLevel(SapServiceEnum.ActivityLevel activityLevel);
}
