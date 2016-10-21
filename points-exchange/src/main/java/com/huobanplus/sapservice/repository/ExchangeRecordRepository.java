package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.entity.ExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-14.
 */
public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord,Long> {

    List<ExchangeRecord> findByWxOpenIdOrderByCreateTimeDesc(String wxUserId);
}
